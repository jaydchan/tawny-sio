;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013-2014, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.  If not, see http://www.gnu.org/licenses/.

(ns ncl.sio.sio
  (:refer-clojure :only [fn println let spit str instance? doseq when])
  (:require [tawny.read]
            [tawny.render :only [as-form]]
            [ncl.sio.generic :only
             [specific-replaces predump pretty-print]]))

;; Reads in the sio ontology from the owl file
(tawny.read/defread sio
  :location
  (tawny.owl/iri (clojure.java.io/resource "sio.owl"))
  :iri "http://semanticscience.org/ontology/sio.owl"
  :prefix "sio:"
  :filter
  (fn [e]
    ;; (println "Filtering:" e)
    (tawny.read/iri-starts-with-filter
      "http://semanticscience.org/resource/" e))
  :transform
  (fn [e]
    ;; (println "transforming:" e)
    (if (clojure.core/some
              #(do
                 (clojure.core/= (.toString (.getIRI e)) %))
              ["http://semanticscience.org/resource/seeAlso"
               "http://semanticscience.org/resource/similarTo"
               "http://semanticscience.org/resource/narrowerThan"
               "http://semanticscience.org/resource/hasSynonym"
               "http://semanticscience.org/resource/example"
               "http://semanticscience.org/resource/subset"
               "http://semanticscience.org/resource/equivalentTo"
               "http://semanticscience.org/resource/broaderThan"])
      (do
        ;; (println "Splitting" e)
        (clojure.core/last
         (clojure.string/split (.toString (.getIRI e)) #"/" )))
      (let [t (tawny.read/noisy-nil-label-transform e)]
        ;; (println "transformed: " t)
        (tawny.read/stop-characters-transform
         (clojure.core/get ncl.sio.generic/specific-replaces t t))))))

(let [mapfile "./output/sio_map.txt"
      entfile "./output/sio_ent.clj"
      outfile "./output/sio_ii.clj"]

  ;; Overwrites original file
  (spit mapfile "")
  (spit entfile "")
  (spit outfile "")

  ;; Predump everything so that it's all defined before use
  ;; ONLY necessary as :transform frame of defread was used
  (doseq [e (.getSignature sio)]
    (spit entfile
          (clojure.core/format
           (clojure.core/cond
            (instance? org.semanticweb.owlapi.model.OWLClass e)
            "(defclass %s)\n"
            (instance? org.semanticweb.owlapi.model.OWLObjectProperty e)
            "(defoproperty %s)\n"
            (instance? org.semanticweb.owlapi.model.OWLDataProperty e)
            "(defdproperty %s)\n"
            (instance? org.semanticweb.owlapi.model.OWLAnnotationProperty e)
            (if (tawny.read/iri-starts-with-filter
                  "http://semanticscience.org/resource/" e)
              "(defaproperty %s)\n"
              "")
            (instance? org.semanticweb.owlapi.model.OWLDatatype e)
            ""
            :default
            (throw
             (Exception. (str "Unknown entity type:" e (clojure.core/type e)))))
           (tawny.lookup/resolve-entity e))
          :append true))

  ;; Include ontology annotations
  (doseq [ann (.getAnnotations sio)]
    (spit outfile
          (str "(add-annotation rendered_sio " (tawny.render/as-form ann) ")\n")
          :append true))

  ;; IRI map for SIO entities and output full entity definitions
  (let [ent (.getSignature sio)]
    (doseq [e ent]
      (try
        (when (tawny.read/iri-starts-with-filter
                "http://semanticscience.org/resource/" e)
          (spit mapfile
                (str (.toStringID e) ","
                     (tawny.lookup/resolve-entity e) "\n")
                :append true))

        (when (clojure.core/or
               (tawny.read/iri-starts-with-filter
                 "http://semanticscience.org/resource/" e)
               (tawny.read/iri-starts-with-filter
                 "http://purl.org/dc/terms/" e))
          (spit outfile
                (ncl.sio.generic/pretty-print
                 (str (clojure.core/pr-str
                       (tawny.render/as-form e)) "\n"))
                :append true))
        (catch
            Exception exp (println e " causes " exp))))))

(println "sio.clj Complete")