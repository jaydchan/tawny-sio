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

(ns ncl.sio.mysio_test
  (:use [clojure.test])
  (:require
   [ncl.sio.mysio :as m]
   [ncl.sio.sio :as s]
   [tawny.owl :as o]
   [tawny.reasoner :as r]))

(defn ontology-reasoner-fixture [tests]
  (r/reasoner-factory :hermit)
  (o/ontology-to-namespace m/mysio)
  (binding [r/*reasoner-progress-monitor*
            (atom
            r/reasoner-progress-monitor-silent)]
    (tests)))

(use-fixtures :once ontology-reasoner-fixture)

;; to run: M-x 'lein' 'test'

(deftest Basic
  (is (r/consistent?))
  (is (r/coherent?)))

(deftest ontologies
  (is
   (instance? org.semanticweb.owlapi.model.OWLOntology s/sio))
  (is
   (instance? org.semanticweb.owlapi.model.OWLOntology m/mysio)))

(deftest signature
  (is
   (= (count (.getSignature s/sio))
      ;; tawny adds an annotation property
      (- (count (.getSignature m/mysio)) 1))))


(deftest classes
  (is
   (= (count (.getClassesInSignature s/sio))
      (count (.getClassesInSignature m/mysio)))))

(deftest object
   (is
    (= (count (.getObjectPropertiesInSignature s/sio))
       (count (.getObjectPropertiesInSignature m/mysio)))))

(deftest annotation
   (is
    (= (count (.getAnnotationPropertiesInSignature s/sio))
      ;; tawny adds an annotation property
       (- (count (.getAnnotationPropertiesInSignature m/mysio)) 1))))

(deftest data
   (is
    (= (count (.getDataPropertiesInSignature s/sio))
       (count (.getDataPropertiesInSignature m/mysio)))))