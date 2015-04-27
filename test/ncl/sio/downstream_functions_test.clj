;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013-2015, Newcastle University

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

(ns ncl.sio.downstream_functions_test
  (:use [clojure.test])
  (:require [tawny.owl :as o]
            [ncl.sio.downstream_functions :as d])
  (:import (org.semanticweb.owlapi.model
            OWLAnnotation OWLClass
            OWLObjectIntersectionOf)))

;; to run: M-x 'lein' 'test'

(defonce to
  (o/ontology :name "to"
              :iri "http://test"
              :prefix "test:"
              :noname true))

(deftest sio-map
  (is
   1608
   (count d/sio-map)))

(deftest get-sio-entity
  (is
   (=
    (o/iri "http://semanticscience.org/resource/SIO_000391")
    (d/get-sio-entity "time_measurement"))))

(deftest biochemical-pathway0
  (let [inter (#'ncl.sio.downstream_functions/biochemical-pathway0
               [(o/owl-class to "A")
                (o/owl-class to "B")
                (o/owl-class to "C")])]
    (is
     (instance? OWLObjectIntersectionOf inter))
    (is
     (= 3
        (count (.asConjunctSet inter))))))

(deftest biochemical-pathway
  (let [clazz (d/biochemical-pathway
               to
               "test"
               [(o/owl-class to "A")
                (o/owl-class to "B")
                (o/owl-class to "C")])]
    (is
     (instance? OWLClass clazz))

    ;; (is
    ;;  (instance? OWLObjectIntersectionOf
    ;;             (first (.getEquivalentClasses clazz to))))
    ;; (is
    ;;  (= 5
    ;;     (count (.asConjunctSet (first (.getEquivalentClasses clazz to))))))
    ))
