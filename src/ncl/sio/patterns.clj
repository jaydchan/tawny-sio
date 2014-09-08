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

(ns ncl.sio.patterns
  (:use [tawny.owl :exclude [defentity]])
  (:require [ncl.sio.generic
             :as g :only [specific-replaces]]))

;; NOTE redefined as entity-generator is private
(defmacro ^{:private true
            :author "Phillip Lord"} defentity
  "Defines a new entity macro."
  [name docstring entity-function]
  `(defmacro
     ~name
     ~docstring
     [entity# & frames#]
     (#'tawny.owl/entity-generator entity# frames# ~entity-function)))

;; Required: while defclass utilises this, owl-class does not
(defn make-safe
  "Ensures the entity NAME is 'safe'. Shortcut function to
tawny.read/stop-characters-transform function."
  [name]
  (tawny.read/stop-characters-transform
   (get g/specific-replaces name name)))

;; ANNOTATION PATERNS
(defonce ^{:private true
           :doc "dc:description IRI"}
  dc-description (iri "http://purl.org/dc/terms/description"))
(defn desc
  "Annotation pattern to ensure the consistency of description
annotations."
  [o description]
  (annotation o dc-description (literal description :lang "en")))

(defn subset-rdf0
  "Annotation pattern to ensure the consistency of subset
annotations."
  [o subset value]
  (annotation o subset (literal value :type :RDF_PLAIN_LITERAL)))

;; "Annotation pattern to ensure the consistency of hasSynonym
;; annotations."
(defn synonym-rdf0
  [o synonym value]
  (annotation o synonym (literal value :type :RDF_PLAIN_LITERAL)))
(defn synonym-en0
  [o synonym value]
  (annotation o synonym (literal value :lang "en")))

(defn example0
  "Annotation pattern to ensure the consistency of example
annotations."
  [o example value]
  (annotation o example (literal value :lang "en")))

;; "Annotation pattern to ensure the consistency of equivalentTo
;; annotations."
(defn equivalent-rdf0
  [o equivalent value]
  (annotation equivalent (literal value :type :RDF_PLAIN_LITERAL)))
(defn equivalent-uri0
  [o equivalent value]
  (annotation equivalent (literal value :type :XSD_ANY_URI)))

(defn similar-uri0
  [o similar value]
  (annotation similar (literal value :type :XSD_ANY_URI)))

;; see-also-rdf (using rdf:seeAlso) 1
;; broaderThan 1
;; alternative-name-en 1
;; alternative-name-rdf 1
;; similar-rdf 1
;; subset-en 2 same value

;; CLASS PATTERNS
(defn sio-class0
  "Generic pattern for most sio classes.
0) Interns entity
1) Ensures name is 'safe'
2) Ensures each class has description annotation
3) Automatically generates the label annotation"
  [o name description & frames]
  (let [n (make-safe name)]
    (intern-owl-string
     n
     (apply owl-class
          (list* o n
                 :label name
                 :annotation (desc o description)
                 frames)))))

;; (defentity defsioclass
;;     "Macro thats uses the sio-class0 pattern."
;;   sio-class0)

(defn see-also-uri0
  [o value]
  (annotation
   o (iri "http://www.w3.org/2000/01/rdf-schema#seeAlso")
   (literal value :type :XSD_ANY_URI)))

(defn see-also0
  "Annotation pattern to ensure the consistency of see-also
annotations."
  [o see-also value]
  (annotation o see-also
              (literal value :type :RDF_PLAIN_LITERAL)))

;; uses sio:seeAlso not rdfs:seeAlso
(defn sio-atom-annotation-maybe
  "Pattern -- If a chebi value is provided then an annotation axiom is
added to the atom class."
  [o see-also cls chebi]
  (if-not (nil? chebi)
    (add-annotation
     o cls
     (see-also0 o see-also chebi))))

(defn sio-atom0
  "Localised pattern for sio atoms.
1) Ensures name is 'safe'
2) Ensures each class has SubClassOf atom restriction
3) Automatically generates the label annotation
4) (if provided) Automatically generates the see-also annotation"
  [o see-also atom name chebi]
  (sio-atom-annotation-maybe
   o see-also
   (owl-class o (make-safe name)
              :subclass atom
              :label name)
   chebi))

;; (defentity
;;   defsioatom
;;   "Macro thats uses the sio-atom0 pattern."
;;   sio-atom0)