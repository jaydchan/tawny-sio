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
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns ncl.sio.rendered_sio_test
  (:use [clojure.test])
  (:require
   [tawny.owl :only [ontology-to-namespace]]
   [tawny.read :only [iri-starts-with-filter]]
   [tawny.reasoner :as r]
   [ncl.sio.rendered_sio :as rsio]
   [ncl.sio.sio :as s])
  (:import (org.semanticweb.owlapi.model
            OWLOntology
            OWLSubClassOfAxiom
            OWLEquivalentClassesAxiom
            OWLSubObjectPropertyOfAxiom)))

(defn ontology-reasoner-fixture [tests]
  (r/reasoner-factory :hermit)
  (tawny.owl/ontology-to-namespace rsio/rendered_sio)
  (binding [r/*reasoner-progress-monitor*
            (atom
            r/reasoner-progress-monitor-silent)]
    (tests)))

(use-fixtures :once ontology-reasoner-fixture)

;; to run: M-x 'lein' 'test'

(deftest Basic
  (is (r/consistent?))
  (is (r/coherent?)))

(deftest ontology
  (is
   (instance? OWLOntology rsio/rendered_sio)))

(deftest signature
  ;; NOTE: Tawny-OWL adds a tawny-name annotation property in rsio
  (is
   (= (count (.getSignature s/sio))
      (- (count (.getSignature rsio/rendered_sio)) 1)))
  (is
   (= (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://semanticscience.org/resource/" %)
              (.getSignature s/sio)))
      (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/rendered_sio" %)
              (.getSignature rsio/rendered_sio))))))

(deftest classes
  (is
   (= (count (.getClassesInSignature s/sio))
      (count (.getClassesInSignature rsio/rendered_sio)))))

(deftest oproperties
  (is
   (= (count (.getObjectPropertiesInSignature s/sio))
      (count (.getObjectPropertiesInSignature rsio/rendered_sio)))))

(deftest aproperties
  ;; NOTE: Tawny-OWL adds a tawny-name annotation property in rsio
  (is
   (= (count (.getAnnotationPropertiesInSignature s/sio))
      (- (count (.getAnnotationPropertiesInSignature rsio/rendered_sio)) 1)))
  (is
   (= (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://semanticscience.org/resource/" %)
              (.getAnnotationPropertiesInSignature s/sio)))
      (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/rendered_sio" %)
              (.getAnnotationPropertiesInSignature rsio/rendered_sio))))))

(deftest dproperties
  (is
   (= (count (.getDataPropertiesInSignature s/sio))
      (count (.getDataPropertiesInSignature rsio/rendered_sio)))))

;; actually 8597 axioms
;; (deftest axioms
;;   (is
;;    (= (count (.getAxioms s/sio))
;;       (count (.getAxioms rsio/rendered_sio)))))

;; off by 1???
;; (deftest subclassofaxioms
  ;; (let [sio (filter
  ;;            #(instance? OWLSubClassOfAxiom %)
  ;;            (.getAxioms s/sio))
  ;;       rio (filter
  ;;            #(instance? OWLSubClassOfAxiom %)
  ;;            (.getAxioms rsio/rendered_sio))]
  ;;   (spit "./sio.log"
  ;;         (clojure.string/join "\n" sio))
  ;;   (spit "./rendered.log"
  ;;         (clojure.string/join "\n" rio)))

  ;; sio.owl, s/sio and sio_ii/clj are doubles
  ;; while rsio/rendered_sio are integers
  ;; 1132,1133c1132
  ;; < SubClassOf(<probability_value> DataSomeValuesFrom(<has_value> DataRangeRestriction(xsd:integer facetRestriction(maxInclusive "1"^^xsd:integer))))
  ;; < SubClassOf(<probability_value> DataSomeValuesFrom(<has_value> DataRangeRestriction(xsd:integer facetRestriction(minInclusive "0"^^xsd:integer))))
  ;; ---
  ;; > SubClassOf(<probability_value> DataSomeValuesFrom(<has_value> DataRangeRestriction(xsd:double facetRestriction(minInclusive "0.0"^^xsd:double) facetRestriction(maxInclusive "1.0"^^xsd:double))))

  ;; Two issues
  ;; 1. Rendering should be (owl-some has_value (span >=< 0.0 1.0))
  ;; 2. Span function return double not integer

  ;; (is
  ;;  (= (count
  ;;      (filter
  ;;       #(instance? OWLSubClassOfAxiom %)
  ;;       (.getAxioms s/sio)))
  ;;     (count
  ;;      (filter
  ;;       #(instance? OWLSubClassOfAxiom %)
  ;;       (.getAxioms rsio/rendered_sio))))))

(deftest equivalentaxioms
  (is
   (= (count
       (filter
        #(instance? OWLEquivalentClassesAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLEquivalentClassesAxiom %)
        (.getAxioms rsio/rendered_sio))))))

(deftest subpropertyofaxioms
  (is
   (= (count
       (filter
        #(instance? OWLSubObjectPropertyOfAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLSubObjectPropertyOfAxiom %)
        (.getAxioms rsio/rendered_sio))))))