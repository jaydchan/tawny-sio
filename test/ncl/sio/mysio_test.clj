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

(ns ncl.sio.mysio_test
  (:use [clojure.test])
  (:require
   [tawny.owl :only [ontology-to-namespace]]
   [tawny.read :only [iri-starts-with-filter]]
   [tawny.reasoner :as r]
   [ncl.sio.mysio :as m]
   [ncl.sio.rendered_sio :as rsio])
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
  (is
   (= (count (.getSignature rsio/rendered_sio))
      (count (.getSignature m/mysio))))
  (is
   (= (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/rendered_sio" %)
              (.getSignature rsio/rendered_sio)))
      (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/mysio" %)
              (.getSignature m/mysio))))))

(deftest classes
  (is
   (= (count (.getClassesInSignature rsio/rendered_sio))
      (count (.getClassesInSignature m/mysio)))))

(deftest oproperties
  (is
   (= (count (.getObjectPropertiesInSignature rsio/rendered_sio))
      (count (.getObjectPropertiesInSignature m/mysio)))))

(deftest aproperties
  (is
   (= (count (.getAnnotationPropertiesInSignature rsio/rendered_sio))
      (count (.getAnnotationPropertiesInSignature m/mysio))))
  (is
   (= (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/rendered_sio" %)
              (.getAnnotationPropertiesInSignature rsio/rendered_sio)))
      (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/mysio" %)
              (.getAnnotationPropertiesInSignature m/mysio))))))

(deftest dproperties
  (is
   (= (count (.getDataPropertiesInSignature rsio/rendered_sio))
      (count (.getDataPropertiesInSignature m/mysio)))))

;; off by +2
;; (deftest axioms
;;   (is
;;    (= (count (.getAxioms rsio/rendered_sio))
;;       (count (.getAxioms m/mysio)))))

(deftest subclassofaxioms
  (is
   (= (count
       (filter
        #(instance? OWLSubClassOfAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLSubClassOfAxiom %)
        (.getAxioms m/mysio))))))

(deftest equivalentaxioms
  (is
   (= (count
       (filter
        #(instance? OWLEquivalentClassesAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLEquivalentClassesAxiom %)
        (.getAxioms m/mysio))))))

(deftest subpropertyofaxioms
  (is
   (= (count
       (filter
        #(instance? OWLSubObjectPropertyOfAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLSubObjectPropertyOfAxiom %)
        (.getAxioms m/mysio))))))