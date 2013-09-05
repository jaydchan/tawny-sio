(ns ncl.sio.generate_mysio
  (:refer-clojure :only [defn partial])
  (:use [tawny.owl])
  (:require [ncl.sio.generate_functions :as gf]))

(defontology generate_mysio
  :iri "http://ncl.ac.uk/sio/generate_mysio"
  :prefix "generate:")

(def top-dogs
  ["role"
   "quality"
   "behaviour"
   "capability"
   "interacting"
   "procedure"
   "spatial_region"
   "submolecular_entity"
   "covalently_connected_entity"
   "chemical_substance"
   "social_entity"
   "mathematical_entity"
   "geometric_entity"
   "computational_entity"
   "media"
   "description"
   "document"
   "document_component"
   "identifier"
   "atom"])

(gf/output
 "./src/ncl/sio/gen_ent.clj"
 ""
 false
 "Error with ent.clj: ")

(gf/predump "./src/ncl/sio/gen_ent.clj")

(clojure.core/doseq [t (clojure.core/pop top-dogs)]
  (gf/output
   (clojure.core/str "./src/ncl/sio/gen_" t ".clj")
   ""
   false
   (clojure.core/str "Error with gen_" t ".clj: ")))

(clojure.core/doseq [t (clojure.core/pop top-dogs)]
  (gf/generate t (clojure.core/str "./src/ncl/sio/gen_" t ".clj")))

(clojure.core/load "gen_ent")

(clojure.core/doseq [t top-dogs]
  (clojure.core/load (clojure.core/str "gen_" t)))

(gf/output
 "./src/ncl/sio/gen_other.clj"
 ""
 false
 "Error with gen_other.clj: ")

(gf/generate_rest top-dogs "./src/ncl/sio/gen_other.clj")

(clojure.core/load "gen_other")
