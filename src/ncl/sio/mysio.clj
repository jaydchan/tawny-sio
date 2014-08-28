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
  (:require [ncl.sio.patterns :as p
             :only [sio-atom0 sio-class0]]))

(defontology mysio
  :iri "http://ncl.ac.uk/sio/mysio"
  :prefix "mysio:")

;; predump -- necessary
(clojure.core/load-file "./src/ncl/sio/mysio_ent.clj")

;; oproperty = 203
(clojure.core/load-file "./src/ncl/sio/oproperties.clj")

;; sio aproperty (and tawny-name) = 8 (+1)
;; No additional information required

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

;; dproperty = 1
(datatype-property has_value
                   :characteristic :functional)

;; individuals = 0
;; NONE

;; classes (excluding atoms) = 1396 - 118
(def sio-class (clojure.core/partial p/sio-class0 mysio))
(clojure.core/load-file "./src/ncl/sio/rest.clj")

;; atoms = 118
(def sio-atom (clojure.core/partial p/sio-atom0 mysio atom))
(clojure.core/load-file "./src/ncl/sio/atom.clj")