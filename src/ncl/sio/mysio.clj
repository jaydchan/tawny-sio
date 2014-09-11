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

(ns ncl.sio.mysio
  (:refer-clojure :only [])
  (:use [tawny.owl])
  (:require [ncl.sio.patterns :as p]))

(defontology mysio
  :iri "http://ncl.ac.uk/sio/mysio"
  :prefix "mysio:"
  :noname true)

;; predump -- necessary
(clojure.core/load-file "./src/ncl/sio/mysio_ent.clj")

;; sio aproperty (and tawny-name) = 8 (+1)
(defaproperty narrowerThan)
(defaproperty equivalentTo)
(defaproperty subset)
(defaproperty similarTo)
(defaproperty example)
(defaproperty broaderThan)
(defaproperty seeAlso)
(defaproperty hasSynonym)

;; other aproperty  = 12
(annotation-property (iri "http://protege.stanford.edu/plugins/owl/protege#defaultLanguage"))
(annotation-property (iri "http://purl.org/dc/terms/creator"))
(annotation-property (iri "http://purl.org/dc/terms/description"))
(annotation-property (iri "http://purl.org/dc/terms/alternativeName"))
(annotation-property (iri "http://www.w3.org/2000/01/rdf-schema#label"))
(annotation-property (iri "http://purl.org/dc/terms/license"))
(annotation-property (iri "http://www.w3.org/2000/01/rdf-schema#comment"))
(annotation-property (iri "http://www.w3.org/2002/07/owl#versionInfo"))
(annotation-property (iri "http://www.w3.org/2000/01/rdf-schema#seeAlso"))
(annotation-property (iri "http://purl.org/dc/terms/rights"))
(annotation-property (iri "http://purl.org/dc/terms/contributor"))
(annotation-property (iri "http://purl.org/dc/terms/title"))

;; patterns
(def sio-class (clojure.core/partial p/sio-class0 mysio))
(def sio-atom (clojure.core/partial p/sio-atom0 mysio seeAlso atom))

(def sio-oproperty (clojure.core/partial p/sio-oproperty0 mysio))

(def sadi (p/sadi0 mysio subset))
(def subset-rdf (clojure.core/partial p/subset-rdf0 mysio subset))
(def core (subset-rdf "core"))
(def synonym-rdf (clojure.core/partial p/synonym-rdf0 mysio hasSynonym))
(def synonym-en (clojure.core/partial p/synonym-en0 mysio hasSynonym))
(def eg (clojure.core/partial p/example0 mysio example))
(def equivalent-rdf (clojure.core/partial p/equivalent-rdf0 mysio equivalentTo))
(def equivalent-uri (clojure.core/partial p/equivalent-uri0 mysio equivalentTo))
(def similar-uri (clojure.core/partial p/similar-uri0 mysio similarTo))
(def see-also-uri (clojure.core/partial p/see-also-uri0 mysio))
(def see-also-rdf (clojure.core/partial p/see-also0 mysio seeAlso))

;; dproperty = 1
(defdproperty has_value
  :label "has value"
  :characteristic :functional
  :annotation
  core
  (annotation subset (literal "sadi" :lang "en"))
  (p/desc mysio "A relation between a informational entity and its actual value (numeric, date, text, etc).")
  (annotation subset (literal "relations" :type :RDF_PLAIN_LITERAL))
  (annotation subset (literal "nlp" :type :RDF_PLAIN_LITERAL)))

;; oproperty = 203
(clojure.core/load-file "./src/ncl/sio/oproperties.clj")

;; individuals = 0
;; NONE

;; classes (excluding atoms) = 1396 - 118
(clojure.core/load-file "./src/ncl/sio/mysio_ii.clj")

;; atoms = 118
(clojure.core/load-file "./src/ncl/sio/atom.clj")