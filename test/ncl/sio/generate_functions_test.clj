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

(ns ncl.sio.generate_functions_test
  (:use [clojure.test])
  (:require [tawny.owl :as o]
            [ncl.sio.generate_functions :as gf])
  (:import (org.semanticweb.owlapi.model
            OWLAnnotation OWLClass)))

;; (defn ontology-reasoner-fixture [tests]
;;   (r/reasoner-factory :hermit)
;;   (tawny.owl/ontology-to-namespace s/sio)
;;   (binding [r/*reasoner-progress-monitor*
;;             (atom
;;             r/reasoner-progress-monitor-silent)]
;;     (tests)))

;; (use-fixtures :once ontology-reasoner-fixture)

;; to run: M-x 'lein' 'test'

(defonce to
  (o/ontology :name "to"
              :iri "http://test"
              :prefix "test:"))

(deftest make-safe
  (is
   (gf/make-safe "this is a test")
   "this_is_a_test"))

(deftest desc
  (let [ann (gf/desc to "Test description")]
    (is
     (instance? OWLAnnotation ann))))

(deftest sio-class0
  (let [clazz (gf/sio-class0 to "test" "test class")]
    (is
     (instance? OWLClass clazz))))

(deftest owl-atom-annotation-maybe
  (let [without (gf/owl-atom-annotation-maybe
                 to
                 (o/owl-class to "without chebi")
                 nil)
        with (gf/owl-atom-annotation-maybe
              to
              (o/owl-class to "with chebi")
              "CHEBI:00000")]
    (is
     (= 0
        (count without)))
    (is
     (= 1
        (count with)))))

(deftest owl-atom0
  (let [atom (gf/sio-class0 to "atom" "the atom class")]
    (gf/owl-atom0 to atom "test atom" nil)
    (is
     (instance? OWLClass (o/owl-class to "test_atom")))))