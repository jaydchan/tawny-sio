(ns ncl.sio.mysio
  (:use tawny.owl))

(defn create-sio-class0
  "Creates a class with given parent, name and description"
  [parent name description]
  (let [entity (clojure.string/replace name #" " "_")]
    (tawny.read/intern-entity
     (owlclass entity
               :subclass parent
               :annotation
               (label (literal name :lang "en"))
               (annotation
                (iri "http://purl.org/dc/terms/description")
                (literal description :lang "en"))))))


(defmacro defquality [the-name description]
  (let [namestr# (name the-name)]
    `(defclass ~the-name
       :annotation
       (label (literal ~namestr# :lang "en"))
       (annotation
        (iri "http://purl.org/dc/terms/description")
        (literal ~description :lang "en")))))


(def specific-replaces {"true" "_true", "false" "_false",
                        "symbol" "_symbol", "label" "_label"
                        "annotation" "_annotation"})