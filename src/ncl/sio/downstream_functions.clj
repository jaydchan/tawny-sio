;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013-2015, Newcastle University

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

(ns ncl.sio.downstream_functions
  (:use [tawny.owl]
        [ncl.sio.generic :only [get-lines]])
  (:require [ncl.sio.sio :as s :only [sio]]
            [ncl.sio.patterns
             :as p :only [make-safe]]
            [ncl.sio.generate_mysio
             :as gm :only [get-name]]))

(defonce sio-map (apply merge
                        (map #(hash-map (second %) (first %))
                             (map #(clojure.string/split % #",")
                                  (get-lines "./output/sio_map.txt")))))

(defn get-sio-entity
  ""
  [name]
  (iri (get sio-map name)))

;; BIOCHEMICAL PATHWAY PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry

(def precedes (get-sio-entity "precedes"))
(def pathway (get-sio-entity "pathway"))
(def has_proper_part (get-sio-entity "has_proper_part"))

(defn- biochemical-pathway0
  ""
  [reactions]
  (owl-and (first reactions)
           (owl-some precedes (rest reactions))))

(defn biochemical-pathway
  ""
  [o name reactions]
  (owl-class o
             (p/make-safe name)
             :equivalent
             (owl-and pathway
                      (owl-some has_proper_part
                                (biochemical-pathway0 reactions))
                      (owl-some has_proper_part reactions))))

;; BIOCHEMICAL REACTION PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry

(def realizes (get-sio-entity "realizes"))
(def is_role_of (get-sio-entity "is_role_of"))
(def biochemical_reaction (get-sio-entity "biochemical_reaction"))

(defn- some-role
  ""
  [role class]
  (owl-some realizes
            (owl-and role
                     (owl-some is_role_of class))))

(defn- biochemical-reaction0
  ""
  [function values]
  (for [v values]
    (some-role function v)))

(defn biochemical-reaction-pattern
  ""
  [o name target catalytic product]
  (owl-class o
             (p/make-safe name)
             :equivalent
             (owl-and biochemical_reaction
                      (map
                       (partial apply biochemical-reaction0)
                       [target catalytic product]))))

;; (GENERIC) REACTION_PATTERN -- WORKS
;; https://code.google.com/p/semanticscience/wiki/ODPChange

;; Missing :super or :equivalent in documentation -> assumed :equivalent

(defn reaction-pattern
  ""
  [o name type target catalytic product]
  (owl-class o
             (p/make-safe name)
             :equivalent
             (owl-and type
                      (map
                       (partial apply biochemical-reaction0)
                       [target catalytic product]))))

;; ENZYME MECHANISM PATTERNS

;; A. ENZYME-PART-OF PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry

;; NOTE should be complex_formation -> molecular_complex_formation
(def molecular_complex_formation
  (get-sio-entity "molecular_complex_formation"))
;; NOTE should be to_be_part_of -> to_be_a_part_of
(def to_be_a_part_of (get-sio-entity "to_be_a_part_of"))

(def realizes (get-sio-entity "realizes"))
(def is_disposition_of (get-sio-entity "is_disposition_of"))
(def in_relation_to (get-sio-entity "in_relation_to"))

(defn enzyme-part-of
  [o name disposition relation]
  (owl-class
   o
   (p/make-safe name)
   :equivalent
   (owl-and molecular_complex_formation
            (map
             #(owl-some realizes
                        (owl-and to_be_a_part_of
                                 (object-some is_disposition_of %)
                                 (owl-some in_relation_to relation)))
             disposition))))

;; B. ENZYME-ROLE PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry

(def biochemical_reaction (get-sio-entity "biochemical_reaction"))
(def realizes (get-sio-entity "realizes"))
(def substrate_role (get-sio-entity "substrate_role"))
(def product_role (get-sio-entity "product_role"))
(def is_role_of (get-sio-entity "is_role_of"))

(defn enzyme-role
  [o name substrate product]
  (owl-class o
             (p/make-safe name)
             :equivalent
             (owl-and biochemical_reaction
                      (map
                       #(some-role %1 %2)
                       [substrate_role product_role]
                       [substrate product]))))

;; C. ENZYME-DISSOCIATION PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry

;; Note should be complex_dissociation -> molecular_complex_dissociation
(def molecular_complex_dissociation
  (get-sio-entity "molecular_complex_dissociation"))
;; Note should be to_dissociate -> to_disassociate
(def to_disassociate
  (get-sio-entity "to_disassociate"))
(def is_disposition_of (get-sio-entity "is_disposition_of"))
(def realizes (get-sio-entity "realizes"))
(def in_relation_to (get-sio-entity "in_relation_to"))

(defn enzyme-dissociate
  [o name dissociate relation]
  (owl-class
   o
   (p/make-safe name)
   :equivalent
   (owl-and molecular_complex_dissociation
            (map
             #(owl-some realizes
                        (owl-and to_disassociate
                                 (owl-some is_disposition_of %)
                                 (owl-some in_relation_to relation)))
             dissociate))))

;; MEREOTOPOLOGY

;; A. MOLECULE -- WORKS
;; https://code.google.com/p/semanticscience/wiki/ODPMereotopology

(def molecule (get-sio-entity "molecule"))
(def has_component_part (get-sio-entity "has_component_part"))
(def is_component_part_of (get-sio-entity "is_component_part_of"))
(def is_covalently_connected_to
  (get-sio-entity "is_covalently_connected_to"))
;; Note should be part_of -> is_part_of
(def is_part_of (get-sio-entity "is_part_of"))

(defn molecule-atom-name
  ""
  [molecule atom]
  (p/make-safe
   (str molecule " " (gm/get-name s/sio (owl-class s/sio atom)))))

(defn molecule-atom
  ""
  [o molecule atom others]
  (refine o (owl-class o (molecule-atom-name molecule atom))
          :equivalent
          (owl-and atom
                   (exactly 1 is_component_part_of (owl-class o molecule))
                   (map
                    #(exactly (second %)
                              is_covalently_connected_to
                              (molecule-atom-name molecule (first %)))
                    others))))

(defn molecule-pattern
  ""
  [o name atoms]
  (let [the-molecule (owl-class o (p/make-safe name) :label name)
        names (map #(molecule-atom-name name (first %)) atoms)
        matoms (map #(owl-class o % :label %) names)]
    (refine
     o
     the-molecule
     :equivalent
     (owl-and
      molecule
      (map
       #(exactly (second %1) has_component_part %2)
       atoms matoms)
      (owl-only has_component_part
                (owl-or (map #(owl-some is_part_of %) matoms)))))
    (doseq [a atoms]
      (molecule-atom o name (first a) (remove #(= % a) atoms)))))

;; B. PROTEIN -- WORKS
;; https://code.google.com/p/semanticscience/wiki/ODPMereotopology

;; NOTE should be human p53 isoform 1 methionine residue @ pos1 ->
;; human p53 isoform 1 methionine residue @ p1

;; TODO whats does the chebi comment mean???

(def protein (get-sio-entity "protein"))
(def has_component_part (get-sio-entity "has_component_part"))
(def is_component_part_of (get-sio-entity "is_component_part_of"))
(def has_attribute (get-sio-entity "has_attribute"))
(def position (get-sio-entity "position"))
(def has_value (get-sio-entity "has_value"))
(def is_directly_before (get-sio-entity "is_directly_before"))

(defn protein-residue-name
  ""
  [o protein residue position]
  (p/make-safe
   (clojure.string/join
    " "
    [protein
     (gm/get-name o (owl-class o residue))
     (str "@ p" position)])))

(defn protein-residue
  ""
  [o protein residue pos next]
  (refine o (owl-class o (protein-residue-name o protein residue pos))
          :equivalent
          residue
          (owl-and
           (exactly 1 is_component_part_of (owl-class o protein))
           (exactly 1 has_attribute
                    (owl-and position
                             (has-value has_value (literal pos))))
           (if-not (empty? next)
             (exactly 1 is_directly_before
                      (owl-class
                       o (protein-residue-name
                          o protein (first next) (+ 1 pos))))
             ""))))

(defn protein-pattern
  ""
  [o name residues]
  (let [sname (p/make-safe name)
        the-protein (owl-class o sname)
        names (map
               #(protein-residue-name o sname % (+ (.indexOf residues %) 1))
               residues)
        presidues (map #(owl-class o % :label %) names)]
    (refine
     o
     the-protein
     :equivalent
     (owl-and
      protein
      (map #(exactly 1 has_component_part %) presidues)))
    (doseq [r (range 0 (count residues))]
      (protein-residue o
                       sname
                       (get residues r)
                       (+ r 1)
                       (subvec residues (+ r 1))))))

;; C. PROTEIN CONTAINMENT -- WORKS
;; https://code.google.com/p/semanticscience/wiki/ODPMereotopology

(def protein_complex (get-sio-entity "protein_complex"))
(def has_proper_part (get-sio-entity "has_proper_part"))
(def contains (get-sio-entity "contains"))
;; NOTE should be is_contained_by -> is_contained_in
(def is_contained_in (get-sio-entity "is_contained_in"))

(defn protein-containment
  ""
  [o complex with]
  (owl-class
   o
   (p/make-safe
    (str (gm/get-name o complex)
         "-complex with "
         (gm/get-name o with)))
   :super
   (owl-and
    protein_complex
    (map
     #(owl-some has_proper_part
                (owl-and
                 %1 (owl-some %2 %3)))
     [complex with] [contains is_contained_in] [with complex]))))

;; PARAMETERS
;; See https://code.google.com/p/semanticscience/wiki/ODPParameters

(def parameter (get-sio-entity "parameter"))
(def is_attribute_of (get-sio-entity "is_attribute_of"))
(def collection (get-sio-entity "collection"))
(def has_member (get-sio-entity "has_member"))
(def has_value (get-sio-entity "has_value"))

(defn software
  ""
  [o tool]
  (owl-class o (p/make-safe (str tool " software"))))

(defn parameter-pattern
  ""
  ([o tool]
   (owl-class
    o
    (p/make-safe (str tool " parameter"))
    :equivalent
    (owl-and
     parameter
     (owl-some is_attribute_of (software o tool)))))
  ([o tool & parameters]
   (owl-class
    o
    (p/make-safe (str tool " parameters"))
    :equivalent
    (owl-and
     collection
     (map
      #(at-least 0 has_member
                 (owl-and (first %)
                          (owl-some has_value (second %))))
      parameters)
     (owl-some is_attribute_of (software o tool))))))
