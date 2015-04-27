;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013-2015, Newcastle University

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

(ns ncl.sio.core
  (:use [ncl.sio.generic :only [save-ontology]])
  (:require [ncl.sio
             sio
             rendered_sio
             generate_mysio
             mysio
             downstream_functions
             downstream_usage
             ])
  (:gen-class))

(defn -main
  "Save ontologies in .omn and .owl format"
  [& args]

  ;; non-patternised rendering of sio (not using sio_ii)
  (save-ontology ncl.sio.sio/sio "sio.omn" :omn)
  (save-ontology ncl.sio.sio/sio "sio.owl" :owl)

  ;; non-patternised rendering of sio (using sio_ii)
  (save-ontology ncl.sio.rendered_sio/rendered_sio "rsio.omn" :omn)
  (save-ontology ncl.sio.rendered_sio/rendered_sio "rsio.owl" :owl)

  ;; ;; generate mysio code
  ;; (ncl.sio.generate_mysio/driver)

  ;; patternised recasting of (my)sio
  (save-ontology ncl.sio.mysio/mysio "mysio.omn" :omn)
  (save-ontology ncl.sio.mysio/mysio "mysio.owl" :owl)

  ;; patterns for downstream usage are defined in downstream_functions
  ;; exammple usage for each pattern are defined in downstream_usage
  (save-ontology
   ncl.sio.downstream_usage/downstream_usage "downstream_usage.omn" :omn)
  (save-ontology
   ncl.sio.downstream_usage/downstream_usage "downstream_usage.owl" :owl)
)
