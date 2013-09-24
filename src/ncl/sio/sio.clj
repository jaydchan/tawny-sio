;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013, Newcastle University

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
  (:refer-clojure :only [fn println some = last let spit doseq format
                         str get instance? and re-find])
  (:require [tawny.read] [tawny.render])
  (:import
   (org.semanticweb.owlapi.model IRI)))

;; Map used to made sure that symbols
;; {true,false,symbol,label,annotation} start with '_' as these can
;; not be defined as unique symbols in Clojure
(def specific-replaces {"true" "_true", "false" "_false",
                        "symbol" "_symbol", "label" "_label",
                        "annotation" "_annotation", "count" "_count"})

;; Reads in the sio ontology from the owl file
(tawny.read/defread sio
  :location (tawny.owl/iri (clojure.java.io/resource "sio.owl"))
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
    (if
        (some #(do
                 (= (.toString (.getIRI e)) %))
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
        (last (clojure.string/split (.toString (.getIRI e)) #"/" )))
      (let [t
            (tawny.read/stop-characters-transform
             (tawny.read/noisy-nil-label-transform e))]
        ;; (println "transformed: " t)
        (get specific-replaces t t)))))

;; Overwrites original file
(spit "./src/ncl/sio/sio_ent.txt" "")
(spit "./src/ncl/sio/sio_ii.clj" "")

;; Predump everything so that it's all defined before use
(doseq [e (.getSignature sio)]
  (spit "./src/ncl/sio/sio_ii.clj"
        (format
         (clojure.core/cond
          (instance? org.semanticweb.owlapi.model.OWLClass e)
          "(defclass %s)\n"
          (instance? org.semanticweb.owlapi.model.OWLObjectProperty e)
          "(defoproperty %s)\n"
          (instance? org.semanticweb.owlapi.model.OWLDataProperty e)
          "(defdproperty %s)\n"
          (instance? org.semanticweb.owlapi.model.OWLAnnotationProperty e)
          (if (re-find #"http://semanticscience.org/resource/"
                       (.toString (.getIRI e)))
            "(defaproperty %s)\n"
            "")
          (instance? org.semanticweb.owlapi.model.OWLDatatype e)
          ""
          :default
          (throw (Exception. (str "Help!!!" e (clojure.core/type e)))))
         (tawny.lookup/resolve-entity e))
        :append true))


;; Full entity definitions
(let [ent (.getSignature sio)]
  ;; (println ent)
  (doseq [e ent]
    (spit "./src/ncl/sio/sio_ent.txt"
          (str (.toStringID e) "," (tawny.lookup/resolve-entity e) "\n")
          :append true)
    (try
      (spit "./src/ncl/sio/sio_ii.clj"
            (str (tawny.render/as-form e) "\n")
            :append true)
      (catch
          Exception exp (println e " causes " exp)))))

(println "sio.clj Complete")