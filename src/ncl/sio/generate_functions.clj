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

(ns ncl.sio.generate_functions
  (:use tawny.owl)
  (:require [tawny.read :only [stop-characters-transform]]))

;; ANNOTATION PATERNS
(defonce ^{:private true
           :doc "dc:description IRI"}
  dc-description (iri "http://purl.org/dc/terms/description"))
(defn desc
  "Annotation pattern to ensure the consistency of description
annotations."
  [o description]
  (annotation o dc-description (literal description :lang "en")))

;; (defn subset-rdf
;;   [value]
;;   (annotation subset (literal value :type :RDF_PLAIN_LITERAL)))
;; ;; one exception (sadi)
;; (defn subset-en
;;   [value]
;;   (annotation subset (literal value :lang "en")))

;; (defn has-synonym-rdf
;;   [value]
;;   (annotation hasSynonym (literal value :type :RDF_PLAIN_LITERAL)))
;; (defn has-synonym-en
;;   [value]
;;   (annotation hasSynonym (literal value :lang "en")))

;; (defn example
;;   "Annotation pattern to ensure the consistency of example
;; annotations."
;;   [value]
;;   (annotation example (literal value :lang "en")))

;; (def rdf-seeAlso (iri "http://www.w3.org/2000/01/rdf-schema#seeAlso"))
;; (defn see-also-rdf
;;   [value]
;;   (annotation rdf-seeAlso (literal value :type :XSD_ANY_URI)))
;; (defn see-also-rdf
;;   ;; CHEMINF
;;   [value]
;;   (annotation seeAlso (literal value :type :RDF_PLAIN_LITERAL))

;; (defn equivalent-to-rdf
;;   [value]
;;   (annotation equivalentTo (literal value :type :RDF_PLAIN_LITERAL)))
;; (defn equivalent-to-uri
;;   [value]
;;   (annotation equivalentTo (literal value :type :XSD_ANY_URI)))

;; only one of these
;; (defn broader-than
;;   [value]
;;   (annotation broaderThan (literal value :type :RDF_PLAIN_LITERAL)))

;; only one of these
;; (defonce ^{:private true
;;            :doc "dc:alternativeName IRI"}
;;   dc-alternative (iri "http://purl.org/dc/terms/alternativeName"))
;; (defn alternative-name
;;   "Annotation pattern to ensure the consistency of alternativeName
;; annotations."
;;   [value]
;;   (annotation dc-alternative (literal value :lang "en")))

;; ;; two of these
;; (defn similar-to-uri
;;   [value]
;;   (annotation similarTo (literal value :type :XSD_ANY_URI)))
;; ;; only one of these
;; (defn similar-to-rdf
;;   [value]
;;   (annotation similarTo (literal value :type :RDF_PLAIN_LITERAL)))

;; CLASS PATTERNS
(defn make-safe
  "Ensures the entity NAME is 'safe'. Shortcut function to
tawny.read/stop-characters-transform function."
  [name]
  (tawny.read/stop-characters-transform name))

(defn sio-class0
  "Generic pattern for most sio classes.
1) Ensures the name is 'safe'
2) Ensures each class has description annotation
3) Automatically generates the label annotation"
  [o name description & frames]
  (apply owl-class
         (list* o
                (make-safe name)
                :label name
                :annotation (desc o description)
                frames)))

(defn owl-atom-annotation-maybe
  "Pattern -- If a chebi value is provided then an annotation axiom is
added to the atom class."
  [o cls chebi]
  (if-not (nil? chebi)
    (add-annotation
     o cls (see-also chebi))))

(defn owl-atom0
  "Localised pattern for sio atoms.
1) Ensures the name is 'safe'
2) Ensures each class has SubClassOf atom restriction
3) Automatically generates the label annotation"
  [o atom name chebi]
  (owl-atom-annotation-maybe
   o (owl-class o
                (make-safe name)
                :subclass atom
                :label name)
   chebi))