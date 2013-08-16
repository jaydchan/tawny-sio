(defclass movement
  :subclass process
  :annotation
  (label "movement" "en")
  (annotation subset "core")
  (annotation (iri "http://purl.org/dc/terms/description") "Movement
  is the process in which an object is spatially displaced." "en"))


(defclass passive_movement
  :subclass movement
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Passive
  movement is the process in which an object is spatially displaced
  without an expenditure of energy contained in molecular bonds.")
  (label "passive movement" "en"))
(defclass active_movement
  :subclass movement
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Active
  movement is the process in which an object is spatially displaced
  using some chemical energy." "en")
  (label "active movement" "en"))


(defclass locomotion
  :subclass active_movement
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "The
  self-propelled movement of an object." "en")
  (label "locomotion" "en")
  (annotation broaderThan "GO:0040011"))


(defclass diffusion
  :subclass passive_movement
  :annotation
  (label "diffusion" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Diffusion
  is motion of particles at temperatures above absolute zero." "en"))


(defclass brownian_motion
  :subclass diffusion
  :annotation
  (label "brownian motion" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Brownian
  motion is the seemlingly random movement of particles suspended in a
  fluid." "en"))
(defclass osmosis
  :subclass diffusion
  :annotation
  (label "osmosis" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Osmosis is
  the movement of water molecules through a selectively-permeable
  membrane down a water potential gradient." "en"))
