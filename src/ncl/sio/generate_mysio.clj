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

(defonce sio-map (apply merge
                        (map #(hash-map (second %) (first %))
                             (map #(clojure.string/split % #",")
                                  (ncl.sio.generic/get-lines
                                   "./output/sio_map.txt")))))

(defn get-sio-entity
  ""
  [name]
  (iri (get sio-map name)))

(defn class?
  [entity]
  (instance? org.semanticweb.owlapi.model.OWLClass entity))

(defn oproperty?
  [entity]
  (instance? org.semanticweb.owlapi.model.OWLObjectProperty entity))

(defn get-label
  "Returns the label annotation"
  [annotations]
  (first (for [ann annotations
               :let [label ann]
               :when (.isLabel ann)]
           label)))

(defn get-name
  "Returns the name (value) of the label annotation."
  [o entity]
  (let [annotations
        (map #(.getAnnotation %)
             (.getAnnotationAssertionAxioms o (.getIRI entity)))
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
  [o entity]
  (let [annotations
        (map #(.getAnnotation %)
             (.getAnnotationAssertionAxioms o (.getIRI entity)))
        description (get-description annotations)]
    (if (nil? description)
      ""
      (let [value (.getLiteral (.getValue description))]
        (if (nil? value)
          ""
          (str (clojure.string/replace value #"\"" "'")))))))

(defn render-sio-entity
  [o iri]
  (let [entity (object-property o iri)
        render (into [] (rest (rest (tawny.render/as-form entity))))
        rlabel
        (remove
         #(re-find (re-pattern "\\(label \\(literal") (str %))
         render)
        rdesc
        (remove
         #(re-find
           (re-pattern
            "\\(annotation \\(iri \"http://purl.org/dc/terms/description\"")
           (str %))
         rlabel)
        annotations
        (.getAnnotationAssertionAxioms o (.getIRI entity))
        rann (remove
              #(if (= 2 (count annotations))
                 (re-find
                  (re-pattern ":annotation") (str %)))
              rdesc)
        label (get-name o entity)
        desc (get-description-value o entity)]
    (conj rann desc label
          (cond
           (oproperty? entity)
           'sio-oproperty
           (class? entity)
           'sio-class
           :default
           (throw
            (IllegalArgumentException.
             (str "Entity must be OWLClass or OWLObjectProperty:" entity)))))))

(defn driver []
  (let [outfile "./output/rest.clj"
        ;; all (into #{} (filter
        ;;                #(tawny.read/iri-starts-with-filter
        ;;                   "http://semanticscience.org/resource/" %)
        ;;                (.getSignature s/sio)))
        ;; atoms (into #{} (subclasses s/sio s/atom))
        ;; aproperties (into #{} (.getAnnotationPropertiesInSignature s/sio))
        ;; dproperties (into #{} (.getDataPropertiesInSignature s/sio))
        ;; rest (clojure.set/difference all atoms aproperties dproperties)
        rest (for [s ["is_specialization_of"
                      "is_generalization_of"
                      "is_alternate_of"
                      "is_opposite_to"
                      "is_orthologous_to"
                      "is_paralogous_to"
                      "is_xenologous_to"
                      "is_broader_than__t_"
                      "is_narrower_than__t_"
                      "is_match_to"
                      "is_broader_than"
                      "is_narrower_than"
                      "is_broad_match_to"
                      "is_exact_match_to"
                      "is_close_match_to"
                      "is_expression_of"
                      "has_expression"
                      "is_manifestation_of"
                      "is_manifested_as"
                      "has_concretization"
                      "is_concretization_of"
                      "is_mutual_capability_of"
                      "is_mutual_disposition_of"
                      "is_mutual_role_of"
                      "in_relation_from"
                      "in_relation_to"
                      "is_represented_by"
                      "represents"
                      "references"
                      "is_referenced_by"
                      "describes"
                      "is_described_by"
                      "is_subject_of"
                      "is_about"
                      "is_specified_by"
                      "specifies"
                      "is_cited_by"
                      "cites"
                      "is_evidence_for"
                      "has_evidence"
                      "is_disputed_by"
                      "is_disputing_evidence_for"
                      "is_supporting_evidence_for"
                      "is_supported_by"
                      "is_refuted_by"
                      "is_refuting_evidence_for"
                      "denotes"
                      "is_denoted_by"
                      "is_modelled_by"
                      "is_model_of"
                      "exists_at"
                      "is_adjacent_to"
                      "overlaps_with"
                      "is_connected_to"
                      "is_location_of"
                      "is_located_in"
                      "is_participant_in"
                      "has_participant"
                      "measured_at"
                      "has_output"
                      "is_output_of"
                      "has_agent"
                      "is_agent_in"
                      "is_input_in"
                      "has_input"
                      "has_target"
                      "is_target_in"
                      "is_parameter_in"
                      "has_parameter"
                      "has_substrate"
                      "has_product"
                      "is_product_of"
                      "affects"
                      "is_affected_by"
                      "is_realized_in"
                      "realizes"
                      "is_causally_related_to"
                      "is_causally_related_from"
                      "derives_into"
                      "is_derived_from"
                      "is_regulated_by"
                      "regulates"
                      "is_preceded_by"
                      "precedes"
                      "results_in"
                      "is_result_of"
                      "is_transformed_from"
                      "transforms_into"
                      "is_trigger_for"
                      "has_trigger"
                      "is_immediately_derived_from"
                      "immediately_derives_into"
                      "is_subsequent_version_of"
                      "is_prior_version_of"
                      "is_immediately_transformed_from"
                      "immediately_transforms_into"
                      "is_immediately_preceded_by"
                      "immediately_precedes"
                      "has_part"
                      "is_part_of"
                      "is_encoded_by"
                      "encodes"
                      "is_transcribed_into"
                      "is_transcribed_from"
                      "is_translated_from"
                      "is_translated_into"
                      "has_proper_part"
                      "is_proper_part_of"
                      "has_direct_part"
                      "is_direct_part_of"
                      "has_boundary"
                      "is_boundary_of"
                      "has_ordered_part"
                      "is_ordered_part_of"
                      "has_first_part"
                      "is_first_part_of"
                      "has_last_part"
                      "is_last_part_of"
                      "has_component_part"
                      "is_component_part_of"
                      "is_contained_in"
                      "contains"
                      "is_surrounded_by"
                      "surrounds"
                      "is_time_boundary_of"
                      "has_time_boundary"
                      "has_start_time"
                      "is_start_time_of"
                      "has_end_time"
                      "is_end_time_of"
                      "is_positionally_before"
                      "is_positionally_after"
                      "is_directly_connected_to"
                      "is_covalently_connected_to__transitive_"
                      "is_covalently_connected_to"
                      "is_weakly_interacting_with__transitive_"
                      "is_weakly_interacting_with"
                      "is_directly_after"
                      "is_directly_before"]]
               (get-sio-entity s))
        ;; rest (.getObjectPropertiesInSignature s/sio)
        ]
    (spit outfile "")
    (doseq [e rest]
      ;; (spit outfile
      (println 
            (g/pretty-print
             (pr-str (render-sio-entity s/sio e)))
            ;; :append true
            )
      )))

;; TODO
;; 1. renders namespace (e.g. ncl.sio.sio/computational_quality)