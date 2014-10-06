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
  (:use [tawny.owl :exclude [defentity see-also]])
  (:require [ncl.sio.generic
             :as g :only [specific-replaces]]))

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

(defn sadi
  "Annotation pattern to ensure the consistency of subset
annotations."
  [o subset]
  (annotation o subset (literal "sadi" :lang "en")))

(defn subset-rdf
  "Annotation pattern to ensure the consistency of subset
annotations."
  [o subset value]
  (annotation o subset (literal value :type :RDF_PLAIN_LITERAL)))

;; "Annotation pattern to ensure the consistency of hasSynonym
;; annotations."
(defn synonym-rdf
  [o synonym value]
  (annotation o synonym (literal value :type :RDF_PLAIN_LITERAL)))
(defn synonym-en
  [o synonym value]
  (annotation o synonym (literal value :lang "en")))

(defn example
  "Annotation pattern to ensure the consistency of example
annotations."
  [o example value]
  (annotation o example (literal value :lang "en")))

;; "Annotation pattern to ensure the consistency of equivalentTo
;; annotations."
(defn equivalent-rdf
  [o equivalent value]
  (annotation equivalent (literal value :type :RDF_PLAIN_LITERAL)))
(defn equivalent-uri
  [o equivalent value]
  (annotation equivalent (literal value :type :XSD_ANY_URI)))

(defn similar-uri
  [o similar value]
  (annotation similar (literal value :type :XSD_ANY_URI)))

(defn see-also-uri
  [o value]
  (annotation
   o (iri "http://www.w3.org/2000/01/rdf-schema#seeAlso")
   (literal value :type :XSD_ANY_URI)))

(defn see-also
  "Annotation pattern to ensure the consistency of see-also
annotations."
  [o see-also value]
  (annotation o see-also
              (literal value :type :RDF_PLAIN_LITERAL)))

;; see-also-rdf (using rdf:seeAlso) 1
;; broaderThan 1
;; alternative-name-en 1
;; alternative-name-rdf 1
;; similar-rdf 1
;; subset-en 2 same value

(defonce ^{:private true
           :doc "sio:see-also IRI"}
  sio-see-also (iri "http://ncl.ac.uk/sio/mysio#seeAlso"))

(defonce ^{:private true
           :doc "sio:atom IRI"}
  sio-atom-class (iri "http://ncl.ac.uk/sio/mysio#atom"))

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

(defn make-label
  "Takes Symbol and tries to generate label value."
  [s]
  (let [r (clojure.string/replace s #"_" " ")
        ^Character f (first r)]
    (if (Character/isWhitespace f)
      (subs (str r) 1)
      r)))

(defn sio-class
  "Generic pattern for most sio classes.
1) Ensures each class has description annotation
2) Automatically generates the label annotation"
  [name description & frames]
  (let [o (get-current-ontology)]
    (apply owl-class
           o name
           :label (make-label name)
           :annotation (desc o description)
           frames)))

(defentity defsclass
  "Macro thats uses the sio-class pattern."
  sio-class)

;; uses sio:seeAlso not rdfs:seeAlso
(defn sio-atom-annotation-maybe
  "Pattern -- If a chebi value is provided then an annotation axiom is
added to the atom class."
  [o cls chebi]
  (if-not (nil? chebi)
    (add-annotation
     o cls
     (see-also o sio-see-also chebi)))
  cls)

(defn sio-atom
  "Localised pattern for sio atoms.
1) Ensures each class has SubClassOf atom restriction
2) Automatically generates the label annotation
3) (if provided) Automatically generates the see-also annotation"
  [name chebi]
  (let [o (get-current-ontology)]
    (sio-atom-annotation-maybe
     o (owl-class o name
                  :subclass sio-atom-class
                  :label (make-label name))
     chebi)))

(defentity
  defsatom
  "Macro thats uses the sio-atom pattern."
  sio-atom)

(defn sio-oproperty
  "Generic pattern for most sio oproperties.
1) Ensures each property has description annotation
2) Automatically generates the label annotation"
  [name description & frames]
  (let [o (get-current-ontology)]
    (apply object-property
           o name
           :label (make-label name)
           :annotation (desc o description)
           frames)))

(defentity
  defsoproperty
  "Macro thats uses the sio-oproperty pattern."
  sio-oproperty)
