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

(ns ncl.sio.sio_test
  (:use [clojure.test])
  (:require
   [tawny.owl :only [ontology-to-namespace]]
   [tawny.read :only [iri-starts-with-filter]]
   [tawny.reasoner :as r]
   [ncl.sio.sio :as s])
  (:import (org.semanticweb.owlapi.model
            OWLOntology
            OWLSubClassOfAxiom
            OWLEquivalentClassesAxiom
            OWLSubObjectPropertyOfAxiom
            OWLAnnotationAssertionAxiom)))

(defn ontology-reasoner-fixture [tests]
  (r/reasoner-factory :hermit)
  (tawny.owl/ontology-to-namespace s/sio)
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
   (instance? OWLOntology s/sio)))

(deftest signature
  (is
   (= 1626
      (count (filter
              (partial
               tawny.read/iri-starts-with-filter
               "http://semanticscience.org/resource/")
              (.getSignature s/sio))))))

(deftest classes
  (is
   (= 1414
      (count (.getClassesInSignature s/sio)))))

(deftest oproperties
  (is
   (= 203
      (count (.getObjectPropertiesInSignature s/sio)))))

(deftest aproperties
  (is
   (= 20
      (count (.getAnnotationPropertiesInSignature s/sio))))
  (is
   (= 8
      (count (filter
              (partial
               tawny.read/iri-starts-with-filter
               "http://semanticscience.org/resource/")
              (.getAnnotationPropertiesInSignature s/sio))))))

(deftest dproperties
  (is
   (= 1
      (count (.getDataPropertiesInSignature s/sio)))))

(deftest subclass-of-axioms
  (is
   (= 1774
      (count
       (filter
        #(instance? OWLSubClassOfAxiom %)
        (.getAxioms s/sio))))))

(deftest equivalent-classes-axioms
  (is
   (= 44
      (count
       (filter
        #(instance? OWLEquivalentClassesAxiom %)
        (.getAxioms s/sio))))))

(deftest subproperty-of-axioms
  (is
   (= 209
      (count
       (filter
        #(instance? OWLSubObjectPropertyOfAxiom %)
        (.getAxioms s/sio))))))