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
   [tawny.owl :only [ontology-to-namespace iri]]
   [tawny.read :only [iri-starts-with-filter]]
   [tawny.reasoner :as r]
   [ncl.sio.generic :only [get-lines]]
   [ncl.sio.mysio :as m]
   [ncl.sio.sio :as s]
   [ncl.sio.rendered_sio :as rsio])
  (:import (org.semanticweb.owlapi.model OWLOntology
            OWLSubPropertyChainOfAxiom OWLInverseObjectPropertiesAxiom
            OWLDeclarationAxiom OWLObjectPropertyRangeAxiom
            OWLSubClassOfAxiom OWLEquivalentClassesAxiom
            OWLSubObjectPropertyOfAxiom
            OWLReflexiveObjectPropertyAxiom
            OWLInverseFunctionalObjectPropertyAxiom
            OWLFunctionalObjectPropertyAxiom
            OWLTransitiveObjectPropertyAxiom OWLDisjointClassesAxiom
            OWLSymmetricObjectPropertyAxiom
            OWLIrreflexiveObjectPropertyAxiom
            OWLFunctionalDataPropertyAxiom
            OWLAsymmetricObjectPropertyAxiom
            OWLObjectPropertyDomainAxiom
            OWLAnnotationAxiom)))

(defn ontology-reasoner-fixture [tests]
  (r/reasoner-factory :hermit)
  (tawny.owl/ontology-to-namespace m/mysio)
  (binding [r/*reasoner-progress-monitor*
            (atom
            r/reasoner-progress-monitor-silent)]
    (tests)))

(use-fixtures :once ontology-reasoner-fixture)

(deftest Basic
  (is (r/consistent?))
  (is (r/coherent?)))

(deftest ontology
  (is
   (instance? OWLOntology m/mysio)))

(deftest signature
  (is
   (= (count (.getSignature s/sio))
      (count (.getSignature rsio/rendered_sio))
      (count (.getSignature m/mysio))
      ))
  (is
   (= (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://semanticscience.org/resource/" %)
              (.getSignature s/sio)))
      (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/rendered_sio" %)
              (.getSignature rsio/rendered_sio)))
      (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/mysio" %)
              (.getSignature m/mysio))))))

(deftest classes
  (is
   (= (count (.getClassesInSignature s/sio))
      (count (.getClassesInSignature rsio/rendered_sio))
      (count (.getClassesInSignature m/mysio)))))

(deftest oproperties
  (is
   (= (count (.getObjectPropertiesInSignature s/sio))
      (count (.getObjectPropertiesInSignature rsio/rendered_sio))
      (count (.getObjectPropertiesInSignature m/mysio)))))

(deftest aproperties
  (is
   (= (count (.getAnnotationPropertiesInSignature s/sio))
      (count (.getAnnotationPropertiesInSignature rsio/rendered_sio))
      (count (.getAnnotationPropertiesInSignature m/mysio))))
  (is
   (= (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://semanticscience.org/resource/" %)
              (.getAnnotationPropertiesInSignature s/sio)))
      (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/rendered_sio" %)
              (.getAnnotationPropertiesInSignature rsio/rendered_sio)))
      (count (filter
              #(tawny.read/iri-starts-with-filter
                 "http://ncl.ac.uk/sio/mysio" %)
              (.getAnnotationPropertiesInSignature m/mysio))))))

(deftest dproperties
  (is
   (= (count (.getDataPropertiesInSignature s/sio))
      (count (.getDataPropertiesInSignature rsio/rendered_sio))
      (count (.getDataPropertiesInSignature m/mysio)))))

;; 7463 vs 7558 vs 7460
(deftest axioms
  (is
   (=
   (count (.getAxioms s/sio))
   ;; (count (.getAxioms rsio/rendered_sio))
   ;; see disjoint axioms
   (+ (count (.getAxioms m/mysio)) 3))))

(deftest subproperty-chain-of-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLSubPropertyChainOfAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest inverse-object-properties-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLInverseObjectPropertiesAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest declaration-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLDeclarationAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest object-property-range-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLObjectPropertyRangeAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest equivalent-classes-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLEquivalentClassesAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest subclass-of-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLSubClassOfAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest reflexive-object-property-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLReflexiveObjectPropertyAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest subproperty-of-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLSubObjectPropertyOfAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest inverse-functional-object-property-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLInverseFunctionalObjectPropertyAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest functional-object-property-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLFunctionalObjectPropertyAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest transitive-object-property-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLTransitiveObjectPropertyAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

;; 75 vs 170 vs 72
(deftest disjoint-classes-axioms
  ;; sio
  ;; ("female" "hermaphrodite") ("female" "male") ("hermaphrodite" "male")
  ;; mysio
  ;; ("female" "hermaphrodite" "male")
  ;; Semantically similar => mysio +2

  ;; sio
  ;; A ("_3D_cartesian_coordinate" "x_cartesian_coordinate" "y_cartesian_coordinate" "z_cartesian_coordinate")
  ;; B ("x_cartesian_coordinate" "y_cartesian_coordinate" "z_cartesian_coordinate")
  ;; mysio
  ;; A ("_3D_cartesian_coordinate" "x_cartesian_coordinate" "y_cartesian_coordinate" "z_cartesian_coordinate")
  ;; Dont think B is necessary => mysio +1

  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLDisjointClassesAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        ;; (count r)
        (+ (count m) 3)))))

(deftest symmetric-object-property-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLSymmetricObjectPropertyAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest irreflexive-object-property-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLIrreflexiveObjectPropertyAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest functional-data-property-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLFunctionalDataPropertyAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest asymmetric-object-property-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLAsymmetricObjectPropertyAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest object-property-domain-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLObjectPropertyDomainAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(deftest annotation-axioms
  (let [[s r m]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLAnnotationAxiom %)
           (.getAxioms o)))]
    (is
     (= (count s)
        (count r)
        (count m)))))

(defn equal-annotation?
  [a1 a2]
  (let [literal1 (.getValue a1)
        literal2 (.getValue a2)]
    (if (and (.hasLang literal1) (.hasLang literal2))
      (let [[l1 l2]
            (map
             #(clojure.string/replace % #"." "" )
             [(.getLiteral literal1) (.getLiteral literal2)])]
        (.equalsIgnoreCase l1 l2))
      (=  literal1 literal2))))

(defn get-mysio-aproperty
  [o aproperty]
  (if (tawny.read/iri-starts-with-filter
        "http://ncl.ac.uk/sio/rendered_sio" aproperty)
    (tawny.owl/annotation-property
     o (last (clojure.string/split (str aproperty) #"[\#>]")))
    aproperty))

(defn check-annotation-values
  []
  (let [[ranns manns]
        (for [o [rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLAnnotationAxiom %)
           (.getAxioms o)))
        [rgroups mgroups]
        (for [a [ranns manns]]
          (group-by #(.getSubject %) a))
        rkeys (keys rgroups)]

    (doseq [rk rkeys]
      (let [rset (get rgroups rk)
            mk (tawny.owl/iri
                (clojure.string/replace rk #"rendered_sio" "mysio"))
            mset (get mgroups mk)]
        (doseq [ra rset]
          (let [rproperty (.getProperty ra)
                mproperty (get-mysio-aproperty m/mysio rproperty)
                mproperties (filter #(= mproperty
                                        (.getProperty %))
                                    mset)]
            (if-not (some (partial equal-annotation? ra) mproperties)
              (println (str "ERROR:\n" ra "\n"
                            (clojure.string/join
                             "\n" mproperties))))))))))

;; (check-annotation-values)