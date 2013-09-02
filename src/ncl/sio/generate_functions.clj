(ns ncl.sio.generate_functions
  (:use tawny.owl)
  (:require [tawny.lookup]
            [tawny.render :as r]
            [ncl.sio.mysio :as m]))

(def specific-replaces {"true" "_true", "false" "_false",
                        "symbol" "_symbol", "label" "_label",
                        "annotation" "_annotation", "count" "_count"})

(defn create-sio-class0
  "Creates a class with given parent, name and description"
  ([parent name description]
     (let [entity (tawny.read/stop-characters-transform name)]
       (tawny.read/intern-entity
        (owlclass entity
                  :subclass parent
                  :annotation
                  (label (literal name :lang "en"))
                  (annotation
                   (iri "http://purl.org/dc/terms/description")
                   (literal description :lang "en"))))))
  ([parent name]
     (let [entity (tawny.read/stop-characters-transform name)]
       (tawny.read/intern-entity
        (owlclass entity
                  :subclass parent
                  :annotation
                  (label (literal name :lang "en"))))
       (println "Error: Missing description - " entity)
     )))

(defn create-atom
  "Creates an atom class with given name"
  [name chebi]
  (let [entity (tawny.read/stop-characters-transform name)]
    (tawny.read/intern-entity
     (owlclass entity
               :subclass "atom"
               :annotation
               (label (literal name :lang "en"))))
    (if-not (nil? chebi)
      (add-annotation
       (owlclass entity)
       (clojure.core/list
        (annotation m/seeAlso (literal chebi :type :RDF_PLAIN_LITERAL)))))))

(defmacro defquality [the-name description]
  (let [namestr# (name the-name)]
    `(defclass ~the-name
       :annotation
       (label (literal ~namestr# :lang "en"))
       (annotation
        (iri "http://purl.org/dc/terms/description")
        (literal ~description :lang "en")))))

(defn shorten
  [string]
  (clojure.string/replace string #"ncl.sio.mysio/" ""))

(defn get-label
  "Returns the label annotation"
  [annotations]
  (first (for [ann annotations
        :let [label ann]
        :when (.isLabel ann)]
    label)))

(defn get-name
  "Returns the name (value) of the label annotation"
  [annotations]
  (let [name (.getLiteral (.getValue (get-label annotations)))]
    (get specific-replaces name name)))

(defn name?
  "Determines if an annotation is a tawny.name annotation"
  [annotation]
  (= (iri "http://www.purl.org/ontolink/tawny/name")
     (.getIRI (.getProperty annotation))))

(defn description?
  "Determines if an annotation is a terms.description annotation"
  [annotation]
  (= (iri "http://purl.org/dc/terms/description")
     (.getIRI (.getProperty annotation))))

(defn get-description
  "Returns the description annotation"
  [annotations]
  (first (for [ann annotations
        :let [description ann]
        :when (description? ann)]
    description)))

(defn get-description-value
  "Returns the description annotation"
  [annotations]
  (let [description (get-description annotations)]
    (if (nil? description)
      ""
      (let [value (.getLiteral (.getValue description))]
        (if (nil? value)
          ""
          (str " \"" (clojure.string/replace value #"\"" "'") "\""))))))

(defn generate-sio-class0
  "Returns the string of the class0 definition for a given parent and
  class"
  [clazz parent]
  (let [ann (.getAnnotations (owlclass m/mysio clazz) m/mysio)]
    (str "\n(gf/create-sio-class0 " (r/form parent)
         " \"" (get-name ann) "\"" (get-description-value ann) ")")))

(defn generate-partial
  "Returns the string of the partial atom for a given parent"
  [parent]
  (str "\n(def create-sio-class (partial gf/create-sio-class0 " parent "))"))

(defn generate-sio-class
  "Returns the string of the class definition for a given class"
  [clazz]
  (let [ann (.getAnnotations clazz m/mysio)]
    (str "(create-sio-class \"" (get-name ann) "\""
         (get-description-value ann) ")")))

(defn output
  "Outputs STRING to OUTPUT-FILE unless there is an ERROR"
  [output-file string error]
   (try
     (spit output-file (shorten string)
     :append true)
   (catch
       Exception exp (println error exp))))

(defn generate-add-subclasses
  "Generates extra add-subclass atom for a given class"
  [clazz output-file parent]
  (let [axioms (remove
                #(= (owlclass m/mysio parent) %)
                (direct-superclasses m/mysio clazz))
        final (for [axiom axioms] (str " " (r/form axiom)))]
      (output
       output-file
       (str "(add-subclass " (r/form clazz) (apply str final) ")\n")
       (str "generate-add-subclasses" clazz " causes "))))

(defn generate-add-annotations
  "Generates extra add-annotations atom for a given class"
  [clazz output-file]
  (let [axioms (remove
                #(or (.isLabel %)
                     (description? %)
                     (name? %))
                (.getAnnotations clazz m/mysio))
        final (into [] (r/form axioms))]
    (output
     output-file
     (str "(add-annotation " (r/form clazz) " " final ")\n")
     (str "generate-add-annotations" clazz " causes "))))

(defn generate-add-disjoint
  "Generates extra add-disjoint atoms for a given class"
  [clazz output-file]
  (let [axioms (.getDisjointClasses clazz m/mysio)]
    (doseq [axiom axioms]
      (output
       output-file
       (str "(add-disjoint " (r/form clazz) " " (r/form axiom) ")\n")
       (str "generate-add-disjoint" clazz " causes ")))))

(defn generate-add-equivalent
  "Generates extra add-equivalent atom for a given class"
  [clazz output-file]
  (let [axioms (.getEquivalentClasses clazz m/mysio)]
    (doseq [axiom axioms]
      (output
       output-file
       (str "(add-equivalent " (r/form clazz) " " (r/form axiom) ")\n")
       (str "generate-add-equivalent" clazz " causes ")))))

(defn generate-other-axioms
  "Handles the generation of any extra axioms"
  [clazz output-file parent]
  (if (> (count (direct-superclasses m/mysio clazz)) 1)
    (generate-add-subclasses clazz output-file parent))
  (if (> (count (.getAnnotations clazz m/mysio)) 3)
    (generate-add-annotations clazz output-file))
  (if (> (count (.getDisjointClasses clazz m/mysio)) 0)
    (generate-add-disjoint clazz output-file))
  (if (> (count (.getEquivalentClasses clazz m/mysio)) 0)
    (generate-add-equivalent clazz output-file)))

(defn save-one
  "Handles the output of single class0 definitions"
  [e output-file parent]
  (output
   output-file
   (str (generate-sio-class0 e parent) "\n")
   (str "generate-sio-class0" e " causes "))
  (generate-other-axioms e output-file parent))

(defn save-many
  "Handles the output of the partial atom and class definitions"
  [ents output-file parent]
  (output
   output-file
   (str (generate-partial parent) "\n")
   (str "generate-partial " parent " causes "))
  (doseq [e ents]
    (output
     output-file
     (str (generate-sio-class e) "\n")
     (str "generate-sio-class" e " causes "))
    (generate-other-axioms e output-file parent)))

(defn generate-mini-defs
  "Handles the mini-definitions for each class"
  [parent output-file]
  (let [ents (direct-subclasses m/mysio (owlclass m/mysio parent))
        no (count ents)]
    (cond
     (= no 1)
     (save-one (first ents) output-file parent)
     (> no 1)
     (save-many ents output-file parent))))

(defn generate
  "Driver for the overall generation process"
  [top-dog output-file]
  ;; START
  (println (str top-dog ".clj") "Started")

  ;; Overwrites original file
  (spit output-file "")

  ;; Mini entity definitions for quality classes
  (generate-mini-defs top-dog output-file)
  (subclasses m/mysio (owlclass m/mysio top-dog))
  (let [todo (into #{} (for [clazz (subclasses m/mysio
                                               (owlclass m/mysio top-dog))
                             :let [parent clazz]
                             :when (>
                                    (count (direct-subclasses m/mysio clazz))
                                    0)]
                         parent))]
    (doseq [clazz todo]
      (generate-mini-defs
       (shorten (tawny.lookup/resolve-entity clazz))
       output-file)))

  ;; END
  (println (str top-dog ".clj") "Complete"))

(defn predump
  "Predump everything so that it's all defined before use"
  [output-file]
  ;; START
  (println "ent.clj Started")

  (doseq [e (.getSignature m/mysio)]
    (spit output-file
          (format
           (clojure.core/cond
            (instance? org.semanticweb.owlapi.model.OWLClass e)
            "(defclass %s)\n"
            (instance? org.semanticweb.owlapi.model.OWLObjectProperty e)
            "(defoproperty %s)\n"
            (instance? org.semanticweb.owlapi.model.OWLDataProperty e)
            "(defdproperty %s)\n"
            (instance? org.semanticweb.owlapi.model.OWLAnnotationProperty e)
            "(defannotationproperty %s)\n"
            (instance? org.semanticweb.owlapi.model.OWLDatatype e)
            ""
            :default
            (throw (Exception. (str "Help!!!" e (clojure.core/type e)))))
           (if-not (clojure.core/nil? (tawny.lookup/resolve-entity e))
             (shorten (tawny.lookup/resolve-entity e))
             (println "Error:" e "is type" (type e))))
          :append true))

  ;; END
  (println "ent.clj Complete"))

(defn generate_rest
  [top-dogs output-file]
  (let [query (for [top-dog top-dogs]
                (subclasses m/mysio (owlclass m/mysio top-dog)))
        children (apply clojure.set/union query)
        parents (for [top-dog top-dogs]
                  (owlclass m/mysio top-dog))
        done (clojure.set/union children parents)
        total (into #{} (.getClassesInSignature m/mysio))
        todo (clojure.set/difference total done)]
    (println "DONE:" (count done))
    (println "TOTAL:" (count total))
    (println "EXPECTED:" (- (count total) (count done)))
    (println "ACTUAL:" (count todo))
    (println "OVERLAP:")
    (doseq [t top-dogs]
      (let [subvec (subvec top-dogs
                           (+ (.indexOf top-dogs t) 1))
            t1 (into #{} (subclasses
                m/mysio
                (owlclass m/mysio t)))]
        (println t (count t1))
        (doseq [s subvec]
          (let [t2 (into #{} (subclasses
                    m/mysio
                    (owlclass m/mysio s)))
                inter (clojure.set/intersection t1 t2)]
            (if-not (empty? inter)
              (println "   " s (count t2) "\n   " inter ))))))

    ;; START
    (println "other.clj Started")
    (doseq [clazz todo]
      (generate-mini-defs
       (shorten (tawny.lookup/resolve-entity clazz))
       output-file))
    ;; END
    (println "other.clj Complete")))



   ;; ;; AXIOMS STILL TODO
   ;; (let [todo (for [clazz (subclasses m/mysio (owlclass m/mysio top-dog))
   ;;                  :let [parent clazz]
   ;;                  :when (or
   ;;                         (> (count (direct-superclasses m/mysio clazz)) 1)
   ;;                         (> (count (.getAnnotations clazz m/mysio)) 3)
   ;;                         (> (count (.getDisjointClasses clazz m/mysio)) 0)
   ;;                         (> (count (.getEquivalentClasses clazz m/mysio)) 0))]
   ;;              parent)]
   ;;   (doseq [clazz todo]
   ;;     (println
   ;;      (filter identity
   ;;              [(tawny.lookup/resolve-entity clazz)
   ;;               (if (> (count (direct-superclasses m/mysio clazz)) 1) ":superclass")
   ;;               (if (> (count (.getAnnotations clazz m/mysio)) 3) ":annotation")
   ;;               (if (> (count (.getDisjointClasses clazz m/mysio)) 0) ":disjoint")
   ;;               (if (> (count (.getEquivalentClasses clazz m/mysio)) 0) ":equivalent")]))))
