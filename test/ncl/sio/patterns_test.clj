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

(ns ncl.sio.patterns_test
  (:refer-clojure :exclude [atom])
  (:use [clojure.test])
  (:require [tawny.owl :as o]
            [ncl.sio.patterns :as p])
  (:import (org.semanticweb.owlapi.model
            OWLAnnotation OWLClass)))

;; to run: M-x 'lein' 'test'

(defonce to
  (o/ontology :name "to"
              :iri "http://test"
              :prefix "test:"))

(deftest make-safe
  (is
   (p/make-safe "this is a test")
   "this_is_a_test"))

(deftest desc
  (let [ann (p/desc to "Test description")]
    (is
     (instance? OWLAnnotation ann))))

(deftest sio-class0
  (let [test (p/sio-class0 to "label" "description")]
    (is
     (instance? OWLClass (var-get test)))))

(deftest sio-atom-annotation-maybe
  (let [without (p/sio-atom-annotation-maybe
                 to
                 o/see-also-property
                 (o/owl-class to "without_chebi")
                 nil)
        with (p/sio-atom-annotation-maybe
              to
              o/see-also-property
              (o/owl-class to "with_chebi")
              "CHEBI:00000")]
    (is
     (= 0
        (count without)))
    (is
     (= 1
        (count with)))))

(deftest sio-atom0
  (let [atom (p/sio-class0 to "atom" "the atom class")
        tatom (p/sio-atom0 to o/see-also-property (var-get atom)
                           "test atom" nil)]
    (is
     (instance? OWLClass (o/owl-class to "test_atom")))))