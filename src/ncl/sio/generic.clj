;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2014, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.  If not, see http://www.gnu.org/licenses/.

(ns ^{:doc "TODO"
      :author "Jennifer Warrender"}
  ncl.sio.generic
  (:use [clojure.java.shell :only [sh]]
        [clojure.java.io :only [as-file reader]])
  (:require [tawny.owl :only save-ontology]))

(defonce ^{:private true
       :doc "TODO"}
  output-file-path "./output/")

;; ensure output-file-path exists
(if-not (.exists (clojure.java.io/as-file output-file-path))
  (sh "mkdir" "-p" output-file-path))

(defn save-ontology
  "'Overloads' save-ontology function."
  [o name type]
  (tawny.owl/save-ontology o (str output-file-path name) type))

(def regexs
  {" :super" "\n\t:super",
   " :annotation" "\n\t:annotation",
   " :disjoint" "\n\t:disjoint",
   " :equivalent" "\n\t:equivalent",
   " :inverse" "\n\t:inverse",
   " :range" "\n\t:range",
   " :domain" "\n\t:domain",
   " :characteristic" "\n\t:characteristic",
   " \\(label " "\n\t(label ",
   " \\(annotation " "\n\t(annotation "})

(defn pretty-print
  [string]
  (reduce
   (fn [a b]
     (clojure.string/replace
     a
     (re-pattern b)
     (get regexs b)))
   string
   (keys regexs)))