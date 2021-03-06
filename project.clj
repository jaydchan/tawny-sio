;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013-2014 Newcastle University

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

(defproject ncl.sio "1.0.0-SNAPSHOT"
  :description "Refactoring of SIO using Tawny-OWL"
  :dependencies [[uk.org.russet/tawny-owl "1.3.0-SNAPSHOT"]]
  :scm {:url "https://github.com/jaydchan/tawny-sio.git"
        :name "git"}
  :license {:name "LGPL"
            :url "http://www.gnu.org/licenses/lgpl-3.0.txt"
            :distribution :repo}
  :main ncl.sio.core
  :aot [ncl.sio.core])
