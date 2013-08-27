(ns ncl.sio.mysio
  (:use tawny.owl)
  (:require [tawny.lookup]
            [tawny.render :as r]))

(def specific-replaces {"true" "_true", "false" "_false",
                        "symbol" "_symbol", "label" "_label"
                        "annotation" "_annotation"})

(defn get-label
  [annotations]
  (first (for [ann annotations
        :let [label ann]
        :when (.isLabel ann)]
    label)))

(defn get-name
  [annotations]
  (let [name (.getLiteral (.getValue (get-label annotations)))]
    (get specific-replaces name name)))

(defn name?
  [annotation]
  (= (iri "http://www.purl.org/ontolink/tawny/name")
                  (.getIRI (.getProperty annotation))))

(defn description?
  [annotation]
  (= (iri "http://purl.org/dc/terms/description")
                  (.getIRI (.getProperty annotation))))

(defn get-description
  [annotations]
  (first (for [ann annotations
        :let [description ann]
        :when (description? ann)]
    description)))

(defn generate-sio-class0
  [parent clazz]
  (let [ann (.getAnnotations (owlclass clazz) mysio)]
    (str "(create-sio-class0 " (r/form parent)
         " \"" (get-name ann) "\"" " \""
         (.getLiteral (.getValue (get-description ann))) "\")")))

(defn generate-partial
  [parent]
  (str "(def create-sio-class (partial create-sio-class0 " parent "))"))

(defn generate-sio-class
  [clazz]
  (let [ann (.getAnnotations clazz mysio)]
    (str "(create-sio-class \"" (get-name ann) "\"" " \""
         (.getLiteral (.getValue (get-description ann))) "\")")))

(defn output
  [output-file string error]
   (try
     (spit output-file string
     :append true)
   (catch
       Exception exp (println error exp))))

(defn generate-add-subclasses
  [clazz output-file parent]
  (let [axioms (remove #(= (owlclass parent) %) (direct-superclasses clazz))]
    (doseq [axiom axioms]
      (output
       output-file
       (str "(add-subclass " (r/form clazz) " " (r/form axiom) ")\n")
       (str "generate-add-subclasses" clazz " causes ")))))

(defn generate-add-annotations
  [clazz output-file]
  (let [axioms (remove
                #(or (.isLabel %)
                     (description? %)
                     (name? %))
                (.getAnnotations clazz mysio))
        final (into [] (r/form axioms))]

    (output
     output-file
     (str "(add-annotation " (r/form clazz) " " final ")\n")
     (str "generate-add-annotations" clazz " causes "))))

(defn generate-add-disjoint
  [clazz output-file]
  (let [axioms (.getDisjointClasses clazz mysio)]
    (doseq [axiom axioms]
      (output
       output-file
       (str "(add-disjoint " (r/form clazz) " " (r/form axiom) ")\n")
       (str "generate-add-disjoint" clazz " causes ")))))

(defn generate-add-equivalent
  [clazz output-file]
  (let [axioms (.getEquivalentClasses clazz mysio)]
    (doseq [axiom axioms]
      (output
       output-file
       (str "(add-equivalent " (r/form clazz) " " (r/form axiom) ")\n")
       (str "generate-add-equivalent" clazz " causes ")))))

(defn generate-other-axioms
  [clazz output-file parent]
  (if (> (count (direct-superclasses clazz)) 1)
    (generate-add-subclasses clazz output-file parent))
  (if (> (count (.getAnnotations clazz mysio)) 3)
    (generate-add-annotations clazz output-file))
  (if (> (count (.getDisjointClasses clazz mysio)) 0)
    (generate-add-disjoint clazz output-file))
  (if (> (count (.getEquivalentClasses clazz mysio)) 0)
    (generate-add-equivalent clazz output-file)))

(defn save-one
  [e output-file parent]
  (output
   output-file
   (str (generate-sio-class0 e parent) "\n")
   (str "generate-sio-class0" e " causes "))
  (generate-other-axioms e output-file parent))

(defn save-many
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
  [parent output-file]
  (let [ents (direct-subclasses (owlclass parent))]
    (if (= 1 (count ents))
     (save-one (first ents) output-file parent)
     (save-many ents output-file parent))))


;; MAIN
;; START
(println "roles_generate.clj Started")

; VARIABLES
(def output-file "./src/ncl/sio/roles2.clj")
(def top-dog "role")

;; Overwrites original file
(spit output-file "")
;; Mini entity definitions for classes
(generate-mini-defs top-dog output-file)
(let [todo (for [clazz (subclasses (owlclass top-dog))
                 :let [parent clazz]
                 :when (> (count (direct-subclasses clazz)) 0)]
             parent)]
  (doseq [clazz todo]
    (generate-mini-defs (tawny.lookup/resolve-entity clazz) output-file)))


;; AXIOMS STILL TODO
(let [todo (for [clazz (subclasses (owlclass top-dog))
                 :let [parent clazz]
                 :when (or
                        (> (count (direct-superclasses clazz)) 1)
                        (> (count (.getAnnotations clazz mysio)) 3)
                        (> (count (.getDisjointClasses clazz mysio)) 0)
                        (> (count (.getEquivalentClasses clazz mysio)) 0))]
             parent)]
  (doseq [clazz todo]
    (println
     (filter identity
             [(tawny.lookup/resolve-entity clazz)
              (if (> (count (direct-superclasses clazz)) 1) ":superclass")
              (if (> (count (.getAnnotations clazz mysio)) 3) ":annotation")
              (if (> (count (.getDisjointClasses clazz mysio)) 0) ":disjoint")
              (if (> (count (.getEquivalentClasses clazz mysio)) 0) ":equivalent")])))
    )

;; END
(println "roles_generate.clj Complete")
