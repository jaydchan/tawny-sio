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

(ns ncl.sio.mysio
  (:refer-clojure :only [])
  (:use [tawny.owl]
        [ncl.sio.patterns
         :only [defsclass defsatom defsoproperty]])
  (:require [ncl.sio.patterns :as p]))

(defontology mysio
  :iri "http://homepages.cs.ncl.ac.uk/jennifer.warrender/mysio/latest/mysio"
  :prefix "mysio:"
  :noname true
  :versioninfo (literal "1.0.10" :type :RDF_PLAIN_LITERAL)
  :comment "general class inclusion axioms:\n'is part of' some 'physical entity' subClassOf 'is located in' some 'physical entity'\n\nrole chains:\n'has capability' o 'is realized in' -> 'is participant in'"
  :annotation
  (annotation
   (iri "http://purl.org/dc/terms/license")
   (literal "http://creativecommons.org/licenses/by/3.0/" :type :XSD_ANY_URI))
  (annotation
   (iri "http://purl.org/dc/terms/creator")
   "Michel Dumontier")
  (annotation
   (iri "http://purl.org/dc/terms/description")
   "The semanticscience integrated ontology (SIO) provides a simple, integrated ontology (types, relations) for objects, processes and their attributes.\n\nThis project provides foundational support for the Bio2RDF (http://bio2rdf.org) and SADI (http://sadiframework.org) projects. \n\nwebsite: http://semanticscience.org\nemail: sio-ontology@googlegroups.com\nmailing list: http://groups.google.com/group/sio-ontology\n")
 (annotation
  (iri "http://purl.org/dc/terms/rights")
  (literal "use,share,modify,commercial;\nby attribution [http://creativecommons.org/licenses/by/3.0/]." :type :RDF_PLAIN_LITERAL))
 (annotation
  (iri "http://protege.stanford.edu/plugins/owl/protege#defaultLanguage")
  (literal "en" :type :RDF_PLAIN_LITERAL))
 (annotation
  (iri "http://purl.org/dc/terms/title")
  "Semanticscience Integrated Ontology (SIO)")
 (annotation
  (iri "http://purl.org/dc/terms/contributor")
  "Contributors are those that engage in discussions in the context of SIO (in alphabetical order):\nchristopher baker\njoachim baran\njerven bolleman\nalison callahan\nleonid chepelev\nkevin cohen\nmelanie courtot\ngeraint duck\nlaura furlong\nluke mccarthy\njim mccusker\njose miguel cruz-toledo\nrobert hoehndorf\nsimon jupp\njin-dong kim\ndana klassen\njames malone\nchris mungall\ndavid osumi-sutherland\nnuria queralt\nalexandre riazanov\nmatthias samwald\nrobert stevens\nmark wilkinson\nkarin verspoor\nnatalia villanueva-rosales"))

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
(annotation-property (iri "http://purl.org/dc/terms/alternativeName"))
(annotation-property (iri "http://purl.org/dc/terms/creator"))
(annotation-property (iri "http://purl.org/dc/terms/contributor"))
(annotation-property (iri "http://purl.org/dc/terms/description"))
(annotation-property (iri "http://purl.org/dc/terms/license"))
(annotation-property (iri "http://purl.org/dc/terms/rights"))
(annotation-property (iri "http://purl.org/dc/terms/title"))

;; patterns
(def desc (clojure.core/partial p/desc mysio))
(def sadi (p/sadi mysio subset))
(def subset-rdf (clojure.core/partial p/subset-rdf mysio subset))
(def core (subset-rdf "core"))
(def synonym-rdf (clojure.core/partial p/synonym-rdf mysio hasSynonym))
(def synonym-en (clojure.core/partial p/synonym-en mysio hasSynonym))
(def eg (clojure.core/partial p/example mysio example))
(def equivalent-rdf (clojure.core/partial p/equivalent-rdf mysio equivalentTo))
(def equivalent-uri (clojure.core/partial p/equivalent-uri mysio equivalentTo))
(def similar-uri (clojure.core/partial p/similar-uri mysio similarTo))
(def see-also-uri (clojure.core/partial p/see-also-uri mysio))
(def see-also-rdf (clojure.core/partial p/see-also mysio seeAlso))

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

;; (clojure.core/println mysio)
;; oproperty = 203
(clojure.core/load-file "./src/ncl/sio/oproperties.clj")
;; (clojure.core/println mysio)

;; classes = 1396
(clojure.core/load-file "./src/ncl/sio/mysio_ii.clj")
