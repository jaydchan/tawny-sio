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

;; to run: M-x 'lein' 'test'

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
      (count (.getSignature m/mysio))))
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
      (count (.getAnnotationPropertiesInSignature m/mysio))
      ))
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

;; 7467 vs 7564 vs 7467
(deftest axioms
  (is
   (=
    ;; +5 see declaration-axioms test
    ;; -1 see disjoint-classes-axioms test
    (+ (count (.getAxioms s/sio)) 4)
    ;; +1 see subproperty-chain-of-axioms test
    ;; -1 see subclass-of-axioms
    ;; commented -- see disjoint-classes-axioms test
    ;; (+ (count (.getAxioms rsio/rendered_sio)) 0)
    ;; +2 see disjoint-classes-axioms test
    (+ (count (.getAxioms m/mysio)) 2))))

;; 1 vs 0 vs 1
(deftest subproperty-chain-of-axioms
  (is
   (= (count
       (filter
        #(instance? OWLSubPropertyChainOfAxiom %)
        (.getAxioms s/sio)))
      ;; update when subchain render fixed
      (+ (count
          (filter
           #(instance? OWLSubPropertyChainOfAxiom %)
           (.getAxioms rsio/rendered_sio))) 1)
      (count
       (filter
        #(instance? OWLSubPropertyChainOfAxiom %)
        (.getAxioms m/mysio))))))

(deftest inverse-object-properties-axioms
  (is
   (= (count
       (filter
        #(instance? OWLInverseObjectPropertiesAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLInverseObjectPropertiesAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLInverseObjectPropertiesAxiom %)
        (.getAxioms m/mysio))))))

;; mysio has extra declarations:
;; Declaration(AnnotationProperty(<http://protege.stanford.edu/plugins/owl/protege#defaultLanguage>))
;; Declaration(AnnotationProperty(owl:versionInfo))
;; Declaration(AnnotationProperty(rdfs:comment))
;; Declaration(AnnotationProperty(rdfs:label))
;; Declaration(AnnotationProperty(rdfs:seeAlso))
(deftest declaration-axioms
  (is
   (= (+ (count
          (filter
           #(instance? OWLDeclarationAxiom %)
           (.getAxioms s/sio))) 5)
      (count
       (filter
        #(instance? OWLDeclarationAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLDeclarationAxiom %)
        (.getAxioms m/mysio))))))

(deftest object-property-range-axioms
  (is
   (= (count
       (filter
        #(instance? OWLObjectPropertyRangeAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLObjectPropertyRangeAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLObjectPropertyRangeAxiom %)
        (.getAxioms m/mysio))))))

(deftest equivalent-classes-axioms
  (is
   (= (count
       (filter
        #(instance? OWLEquivalentClassesAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLEquivalentClassesAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLEquivalentClassesAxiom %)
        (.getAxioms m/mysio))))))

(deftest subclass-of-axioms
  (is
   (= (count
       (filter
        #(instance? OWLSubClassOfAxiom %)
        (.getAxioms s/sio)))
      ;; update when span render fixed
      (- (count
          (filter
           #(instance? OWLSubClassOfAxiom %)
           (.getAxioms rsio/rendered_sio))) 1)
      (count
       (filter
        #(instance? OWLSubClassOfAxiom %)
        (.getAxioms m/mysio))))))

(deftest reflexive-object-property-axioms
  (is
   (= (count
       (filter
        #(instance? OWLReflexiveObjectPropertyAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLReflexiveObjectPropertyAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLReflexiveObjectPropertyAxiom %)
        (.getAxioms m/mysio))))))

(deftest subproperty-of-axioms
  (is
   (= (count
       (filter
        #(instance? OWLSubObjectPropertyOfAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLSubObjectPropertyOfAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLSubObjectPropertyOfAxiom %)
        (.getAxioms m/mysio))))))

(deftest inverse-functional-object-property-axioms
  (is
   (= (count
       (filter
        #(instance? OWLInverseFunctionalObjectPropertyAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLInverseFunctionalObjectPropertyAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLInverseFunctionalObjectPropertyAxiom %)
        (.getAxioms m/mysio))))))

(deftest functional-object-property-axioms
  (is
   (= (count
       (filter
        #(instance? OWLFunctionalObjectPropertyAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLFunctionalObjectPropertyAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLFunctionalObjectPropertyAxiom %)
        (.getAxioms m/mysio))))))

(deftest transitive-object-property-axioms
  (is
   (= (count
       (filter
        #(instance? OWLTransitiveObjectPropertyAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLTransitiveObjectPropertyAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLTransitiveObjectPropertyAxiom %)
        (.getAxioms m/mysio))))))

;; sio
;; ("female" "hermaphrodite") ("female" "male") ("hermaphrodite" "male")
;; mysio
;; ("female" "hermaphrodite" "male")
;; Semantically similar => mysio +2

;; sio => -1
;; A ("_3D_cartesian_coordinate" "x_cartesian_coordinate" "y_cartesian_coordinate" "z_cartesian_coordinate")
;; B ("x_cartesian_coordinate" "y_cartesian_coordinate" "z_cartesian_coordinate")
;; mysio
;; A ("_3D_cartesian_coordinate" "x_cartesian_coordinate" "y_cartesian_coordinate" "z_cartesian_coordinate")
;; Dont think B is necessary => sio -1

;; 74 (75-1) vs 170 vs 74 (72+2)
(deftest disjoint-classes-axioms
  (let [[sdisj rdisj mdisj]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLDisjointClassesAxiom %)
           (.getAxioms o)))]
    (is
     (= (- (count sdisj) 1)
        ;; (count rdisj)
        (+ (count mdisj) 2)))))

(deftest symmetric-object-property-axioms
  (is
   (= (count
       (filter
        #(instance? OWLSymmetricObjectPropertyAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLSymmetricObjectPropertyAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLSymmetricObjectPropertyAxiom %)
        (.getAxioms m/mysio))))))

(deftest irreflexive-object-property-axioms
  (is
   (= (count
       (filter
        #(instance? OWLIrreflexiveObjectPropertyAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLIrreflexiveObjectPropertyAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLIrreflexiveObjectPropertyAxiom %)
        (.getAxioms m/mysio))))))

(deftest functional-data-property-axioms
  (is
   (= (count
       (filter
        #(instance? OWLFunctionalDataPropertyAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLFunctionalDataPropertyAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLFunctionalDataPropertyAxiom %)
        (.getAxioms m/mysio))))))

(deftest asymmetric-object-property-axioms
  (is
   (= (count
       (filter
        #(instance? OWLAsymmetricObjectPropertyAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLAsymmetricObjectPropertyAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLAsymmetricObjectPropertyAxiom %)
        (.getAxioms m/mysio))))))

(deftest object-property-domain-axioms
  (is
   (= (count
       (filter
        #(instance? OWLObjectPropertyDomainAxiom %)
        (.getAxioms s/sio)))
      (count
       (filter
        #(instance? OWLObjectPropertyDomainAxiom %)
        (.getAxioms rsio/rendered_sio)))
      (count
       (filter
        #(instance? OWLObjectPropertyDomainAxiom %)
        (.getAxioms m/mysio))))))

(deftest annotation-axioms
  (let [[sanns ranns manns]
        (for [o [s/sio rsio/rendered_sio m/mysio]]
          (filter
           #(instance? OWLAnnotationAxiom %)
           (.getAxioms o)))]
    (is
     (= (count sanns)
        ;; update when render property annotation pulled
        ;; (count ranns)
        (count manns)))))

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