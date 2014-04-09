(ns ncl.sio.mysio
  (:refer-clojure :only [defn if-not nil? println let partial])
  (:use [tawny.owl])
  (:require [tawny.read]))

(defontology mysio
  :iri "http://ncl.ac.uk/sio/mysio"
  :prefix "mysio:")

 (clojure.core/load-file "./src/ncl/sio/sio_ii.clj")