;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013-2014, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.
;; You should have received a copy of the GNU General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns ncl.sio.generate_mysio
  (:use [tawny.owl])
  (:require [ncl.sio.sio :as s]
            [ncl.sio.generic :as g
             :only [specific-replaces pretty-print]]))

(defn shorten
  [string]
  (clojure.string/replace string #"ncl.sio.sio/" ""))

(defn get-label
  "Returns the label annotation"
  [annotations]
  (first (for [ann annotations
               :let [label ann]
               :when (.isLabel ann)]
           label)))

(defn get-name
  "Returns the name (value) of the label annotation"
  [o clazz]
  (let [annotations
        (map #(.getAnnotation %)
             (.getAnnotationAssertionAxioms o (.getIRI clazz)))
        name (.getLiteral (.getValue (get-label annotations)))]
    (get g/specific-replaces name name)))

(defn description?
  "Determines if an annotation is a terms.description annotation"
  [annotation]
  (= (iri "http://purl.org/dc/terms/description")
     (.getIRI (.getProperty annotation))))

(defn get-description
  "Returns the description annotation"
  [annotations]
  (first (for [ann annotations
        :let [description ann]
               :when (description? ann)]
           description)))

(defn get-description-value
  "Returns the description annotation"
  [annotations]
  (let [description (get-description annotations)]
    (if (nil? description)
      ""
      (let [value (.getLiteral (.getValue description))]
        (if (nil? value)
          ""
          (str " \"" (clojure.string/replace value #"\"" "'") "\""))))))

(defn render-sio-class
  [to clazz]
  (let [render (into [] (rest (rest (tawny.render/as-form clazz))))
        rlabel (remove
                #(re-find (re-pattern "\\(label \\(literal") (str %))
                render)
        rdesc (remove
               #(re-find
                 (re-pattern "\\(annotation \\(iri \"http://purl.org/dc/terms/description\"") (str %))
               rlabel)
        annotations (map #(.getAnnotation %)
                         (.getAnnotationAssertionAxioms to (.getIRI clazz)))
        rann (remove
                #(if (= 2 (count annotations))
                   (re-find
                    (re-pattern ":annotation") (str %)))
                rdesc)
        label (get-name to clazz)
        desc (clojure.string/trim (get-description-value annotations))]
    (conj rann desc label "sio-class")))

(defn driver
  []
  (let [outfile "./output/rest.clj"
        all (into #{} (.getClassesInSignature s/sio))
        atoms (into #{} (subclasses s/sio s/atom))
        rest (clojure.set/difference all atoms)]
    (spit outfile "")
    (doseq [c rest]
      (spit outfile
            (g/pretty-print
             (str
              (pr-str (render-sio-class s/sio c))
              "\n"))
            :append true))))

;; TODO
;; 1. renders namespace (e.g. ncl.sio.sio/computational_quality)
;; 2. description includes extra pair of speech marks
;; 3. outputs sio-class as string (i.e. "owl-class")