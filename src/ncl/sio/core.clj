;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.
;; You should have received a copy of the GNU General Public License
;; along with this program.  If not, see http://www.gnu.org/licenses/.

(ns ncl.sio.core
  (:use [tawny.owl])
  ;; (:require [ncl.sio sio mysio])
  ;; (:require [ncl.sio mysio])
  ;; (:require [ncl.sio sio])

  ;; (:require [ncl.sio mysio generate_functions generate_mysio])
  ;; (:require [ncl.sio mysio generate_functions print_des])

  (:require [ncl.sio mysio downstream_functions])
  (:gen-class))

;; to run:
;; 1. M-x 'compile' ('lein run')
;; 2. M-x 'lein run'

(defn -main [& args]
  ;; "Save ontologies in .omn and .owl format"
  ;; (with-ontology ncl.sio.sio/sio
  ;;   (save-ontology "sio.omn" :omn)
  ;;   (save-ontology "sio.owl" :owl))

  ;; (with-ontology ncl.sio.mysio/mysio
  ;;   (save-ontology "mysio.omn" :omn)
  ;;   (save-ontology "mysio.owl" :owl))

  ;; (with-ontology ncl.sio.generate_mysio/generate_mysio
  ;;   (save-ontology "generate_mysio.omn" :omn)
  ;;   (save-ontology "generate_mysio.owl" :owl))

  (with-ontology ncl.sio.downstream_functions/downstream_functions
    (save-ontology "downstream.omn" :omn)
    (save-ontology "downstream.owl" :owl))
)
