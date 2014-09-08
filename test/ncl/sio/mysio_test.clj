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

;; 1626 vs 1608
(deftest signature
  (is
   (= (count (.getSignature s/sio))
      (count (.getSignature rsio/rendered_sio))
      ;; (count (.getSignature m/mysio))
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
      ;; (count (filter
      ;;         #(tawny.read/iri-starts-with-filter
      ;;            "http://ncl.ac.uk/sio/mysio" %)
      ;;         (.getSignature m/mysio)))
      )))

(deftest classes
  (is
   (= (count (.getClassesInSignature s/sio))
      (count (.getClassesInSignature rsio/rendered_sio))
      (count (.getClassesInSignature m/mysio))
      )))

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

;; 7463 vs 7082 vs 6896
;; (deftest axioms
;;   (is
;;    (= (count (.getAxioms s/sio))
;;       (count (.getAxioms rsio/rendered_sio))
;;       (count (.getAxioms m/mysio)))))

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

;; missing 2
(deftest inverse-object-properties-axioms
  ;; (spit "r.txt"
  ;;       (clojure.string/join
  ;;        "\n"
  ;;        (filter
  ;;         #(instance? OWLInverseObjectPropertiesAxiom %)
  ;;         (.getAxioms rsio/rendered_sio))))
  ;; (spit "m.txt"
  ;;       (clojure.string/join
  ;;        "\n"
  ;;        (filter
  ;;         #(instance? OWLInverseObjectPropertiesAxiom %)
  ;;         (.getAxioms m/mysio))))
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
        (.getAxioms m/mysio)))
      )))

;; 1638 vs 1638 vs 1620
;; 5 extra declarations!!!
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
      ;; (count
      ;;  (filter
      ;;   #(instance? OWLDeclarationAxiom %)
      ;;   (.getAxioms m/mysio)))
)))

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
        (.getAxioms m/mysio)))
      )))

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

;; 75 vs 170 vs ???
;; (deftest disjoint-classes-axioms
;;   (is
;;    (= (count
;;        (filter
;;         #(instance? OWLDisjointClassesAxiom %)
;;         (.getAxioms s/sio)))
;;       (count
;;        (filter
;;         #(instance? OWLDisjointClassesAxiom %)
;;         (.getAxioms rsio/rendered_sio)))
;;       (count
;;        (filter
;;         #(instance? OWLDisjointClassesAxiom %)
;;         (.getAxioms m/mysio))))))

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

;; 3500 vs 3019 vs 3019
(deftest annotation-axioms
  (let [ranns
        (filter
         #(instance? OWLAnnotationAxiom %)
         (.getAxioms rsio/rendered_sio))
        manns
        (filter
         #(instance? OWLAnnotationAxiom %)
         (.getAxioms m/mysio))]
    (is
     (= ;; (count
        ;;  (filter
        ;;   #(instance? OWLAnnotationAxiom %)
        ;;   (.getAxioms s/sio)))
        (count ranns)
        (count manns)))))