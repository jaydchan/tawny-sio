;; Must be declared first in order oproperties to work.
(defclass language_entity)
(defclass realizable_entity)
(defclass quality)
(defclass identifier)
(defclass measurement_value)
(defclass unit_of_measurement)
(defclass unique_identifier)
(defclass capability)
(defclass role)
(defclass disposition)
(defclass function)
(defclass quantity)
(defclass term)
(defclass time_measurement)
(defclass process)
(defclass object)
(defclass deoxyribonucleic_acid)
(defclass ribonucleic_acid)
(defclass lipid)
(defclass protein)
(defclass polypeptide)
(defclass time_instant)
(defclass atom)
(defclass molecule)
;; Define oproperties
;; See https://code.google.com/p/semanticscience/wiki/ODPMereotopology
;; See https://code.google.com/p/semanticscience/wiki/ODPProvenance
(defoproperty is_related_to
  :characteristic :symmetric)
(defoproperty is_transitively_related_to
  :characteristic :transitive :symmetric)
(as-inverse
 (defoproperty has_attribute
   :super is_related_to)
 (defoproperty is_attribute_of
   :super is_related_to))
(defoproperty is_comparable_to
  :super is_related_to
  :characteristic :symmetric)
(defoproperty is_generically_related_with
  :super is_related_to)
(defoproperty is_mutually_related_to
  :super is_related_to
  :characteristic :symmetric)
(defoproperty is_spatiotemporally_related_to
  :super is_related_to
  :characteristic :symmetric)
(defoproperty is_causally_related_with
  :super is_spatiotemporally_related_to
  :characteristic :transitive :symmetric)
(as-inverse
 (defoproperty is_referred_to_by
   :super is_related_to)
 (defoproperty refers_to
   :super is_related_to))
(as-inverse
 (defoproperty has_annotation
   :super has_attribute
   :range language_entity)
 (defoproperty is_annotation_of
   :super is_attribute_of
   :domain language_entity))
(as-inverse
 (defoproperty has_basis
   :super has_attribute
   :domain realizable_entity
   :range quality)
 (defoproperty is_base_for
   :super is_attribute_of
   :domain quality
   :range realizable_entity))
(as-inverse
 (defoproperty has_identifier
   :super has_attribute
   :range identifier)
 (defoproperty is_identifier_for
   :super is_attribute_of
   :domain identifier))
(as-inverse
 (defoproperty has_implementation
   :super has_attribute)
 (defoproperty is_implementation_of
   :super is_attribute_of))
(as-inverse
 (defoproperty has_measurement_value
   :super has_attribute
   :range measurement_value)
 (defoproperty is_measurement_value_of
   :super is_attribute_of
   :domain measurement_value))
(as-inverse
 (defoproperty has_member
   :super has_attribute
   :characteristic :irreflexive)
 (defoproperty is_member_of
   :super is_attribute_of))
(as-inverse
 (defoproperty has_property
   :super has_attribute
   :characteristic
   :inversefunctional)
 (defoproperty is_property_of
   :super is_attribute_of
   :characteristic :functional))
(as-inverse
 (defoproperty has_source
   :super has_attribute)
 (defoproperty is_source_of
   :super is_attribute_of))
(as-inverse
 (defoproperty has_unit
   :super has_attribute
   :range unit_of_measurement
   :characteristic :functional)
 (defoproperty is_unit_of
   :super is_attribute_of
   :domain unit_of_measurement))
(as-inverse
 (defoproperty satisfies
   :super has_attribute)
 (defoproperty is_satisfied_by
   :super is_attribute_of))
(as-inverse
 (defoproperty has_unique_identifier
   :super has_identifier
   :range unique_identifier
   :characteristic
   :inversefunctional)
 (defoproperty is_unique_identifier_for
   :super is_identifier_for
   :domain unique_identifier
   :characteristic :functional))
(as-inverse
 (defoproperty has_frequency
   :super has_measurement_value)
 (defoproperty is_frequency_of
   :super is_measurement_value_of))
(as-inverse
 (defoproperty has_quality
   :super has_property
   :range quality
   :characteristic
   :inversefunctional)
 (defoproperty is_quality_of
   :super is_property_of
   :domain quality))
(as-inverse
 (defoproperty has_realizable_property
   :super has_property
   :characteristic
   :inversefunctional)
 (defoproperty is_realizable_property_of
   :super is_property_of
   :characteristic :functional))
(as-inverse
 (defoproperty has_capability
   :super has_realizable_property
   :range capability
   :characteristic
   :inversefunctional)
 (defoproperty is_capability_of
   :super is_realizable_property_of
   :domain capability
   :characteristic :functional))
(as-inverse
 (defoproperty has_role
   :super has_realizable_property
   :range role
   :characteristic
   :inversefunctional)
 (defoproperty is_role_of
   :super is_realizable_property_of
   :domain role
   :characteristic :functional))
(as-inverse
 (defoproperty has_disposition
   :super has_capability
   :range disposition
   :characteristic
   :inversefunctional)
 (defoproperty is_disposition_of
   :super is_capability_of
   :domain disposition
   :characteristic :functional))
(as-inverse
 (defoproperty has_function
   :super has_disposition
   :range function
   :characteristic
   :inversefunctional)
 (defoproperty is_function_of
   :super is_disposition_of
   :domain function
   :characteristic :functional))
(as-inverse
 (defoproperty has_creator
   :super has_source)
 (defoproperty is_created_by
   :super is_source_of))
(as-inverse
 (defoproperty has_provider
   :super has_source)
 (defoproperty is_provider_of
   :super is_source_of))
(defoproperty is_identical_to
  :super is_comparable_to
  :characteristic :symmetric)
(defoproperty is_numerically_comparable_to
  :super is_comparable_to
  :domain quantity
  :range quantity
  :characteristic :symmetric)
(defoproperty is_variant_of
  :super is_comparable_to
  :characteristic :transitive :symmetric)
(defoproperty is_equal_to
  :super is_numerically_comparable_to
  :characteristic :symmetric)
(defoproperty is_inequal_to
  :super is_numerically_comparable_to
  :characteristic :symmetric)
(as-inverse
 (defoproperty is_greater_than
   :super is_numerically_comparable_to)
 (defoproperty is_lesser_than
   :super is_numerically_comparable_to))
(as-inverse
 (defoproperty is_greater_than_or_equal_to
   :super is_numerically_comparable_to)
 (defoproperty is_lesser_than_or_equal_to
   :super is_numerically_comparable_to))
(defoproperty is_similar_to
  :super is_variant_of
  :characteristic :symmetric)
(defoproperty is_dissimilar_to
  :super is_variant_of
  :characteristic :symmetric)
(defoproperty is_homologous_to
  :super is_variant_of
  :characteristic :symmetric)
(defoproperty is_version_of
  :super is_variant_of
  :characteristic :transitive :symmetric)
(as-inverse
 (defoproperty is_specialization_of
   :super is_variant_of)
 (defoproperty is_generalization_of
   :super is_variant_of))
(defoproperty is_alternate_of
  :super is_variant_of
  :characteristic :symmetric)
(add-inverse is_alternate_of is_alternate_of)
(defoproperty is_opposite_to
  :super is_dissimilar_to
  :characteristic :symmetric)
(defoproperty is_orthologous_to
  :super is_homologous_to
  :characteristic :symmetric)
(defoproperty is_paralogous_to
  :super is_homologous_to
  :characteristic :symmetric)
(defoproperty is_xenologous_to
  :super is_orthologous_to
  :characteristic :symmetric)
(as-inverse
 (defoproperty is_broader_than__t_
   :super is_similar_to
   :domain term
   :range term
   :characteristic :transitive)
 (defoproperty is_narrower_than__t_
   :super is_similar_to
   :domain term
   :range term
   :characteristic :transitive))
(defoproperty is_match_to
  :super is_similar_to
  :domain term
  :range term
  :characteristic :symmetric)
(as-inverse
 (defoproperty is_broader_than
   :super is_broader_than__t_)
 (defoproperty is_narrower_than
   :super is_narrower_than__t_))
(defoproperty is_broad_match_to
  :super is_match_to
  :characteristic :symmetric)
(defoproperty is_exact_match_to
  :super is_match_to
  :characteristic :symmetric)
(defoproperty is_close_match_to
  :super is_match_to
  :characteristic :symmetric)
(as-inverse
 (defoproperty is_expression_of
   :super is_generically_related_with)
 (defoproperty has_expression
   :super is_generically_related_with))
(as-inverse
 (defoproperty is_manifestation_of
   :super is_generically_related_with)
 (defoproperty is_manifested_as
   :super is_generically_related_with))
(as-inverse
 (defoproperty has_concretization
   :super is_generically_related_with)
 (defoproperty is_concretization_of
   :super is_generically_related_with))
(defoproperty is_mutual_capability_of
  :super is_mutually_related_to
  :characteristic :symmetric)
(defoproperty is_mutual_disposition_of
  :super is_mutually_related_to
  :characteristic :symmetric)
(defoproperty is_mutual_role_of
  :super is_mutually_related_to
  :characteristic :symmetric)
(as-inverse
 (defoproperty in_relation_from
   :super is_referred_to_by)
 (defoproperty in_relation_to
   :super refers_to))
(as-inverse
 (defoproperty is_represented_by
   :super is_referred_to_by)
 (defoproperty represents
   :super refers_to))
(as-inverse
 (defoproperty references
   :super refers_to)
 (defoproperty is_referenced_by
   :super is_referred_to_by))
(as-inverse
 (defoproperty describes
   :super refers_to)
 (defoproperty is_described_by
   :super is_referred_to_by))
(as-inverse
 (defoproperty is_subject_of
   :super is_described_by)
 (defoproperty is_about
   :super describes))
(as-inverse
 (defoproperty is_specified_by
   :super is_causally_related_with is_described_by)
 (defoproperty specifies
   :super describes is_causally_related_with))
(as-inverse
 (defoproperty is_cited_by
   :super is_referenced_by)
 (defoproperty cites
   :super references))
(as-inverse
 (defoproperty is_evidence_for
   :super is_referenced_by)
 (defoproperty has_evidence
   :super references))
(as-inverse
 (defoproperty is_disputed_by
   :super has_evidence)
 (defoproperty is_disputing_evidence_for
   :super is_evidence_for))
(as-inverse
 (defoproperty is_supporting_evidence_for
   :super is_evidence_for)
 (defoproperty is_supported_by
   :super has_evidence))
(as-inverse
 (defoproperty is_refuted_by
   :super has_evidence)
 (defoproperty is_refuting_evidence_for
   :super is_evidence_for))
(as-inverse
 (defoproperty denotes
   :super represents)
 (defoproperty is_denoted_by
   :super is_represented_by))
(as-inverse
 (defoproperty is_modelled_by
   :super is_represented_by)
 (defoproperty is_model_of
   :super represents))
(defoproperty exists_at
  :super is_spatiotemporally_related_to
  :range time_measurement)
(defoproperty is_adjacent_to
  :super is_spatiotemporally_related_to
  :characteristic :symmetric)
(defoproperty overlaps_with
  :super is_spatiotemporally_related_to
  :characteristic :symmetric :reflexive)
(defoproperty is_connected_to
  :super is_spatiotemporally_related_to
  :characteristic :transitive :symmetric)
(as-inverse
 (defoproperty is_location_of
   :super is_spatiotemporally_related_to
   :characteristic :transitive)
 (defoproperty is_located_in
   :super is_spatiotemporally_related_to
   :characteristic :transitive))
(as-inverse
 (defoproperty is_participant_in
   :super is_spatiotemporally_related_to
   :range process)
 (defoproperty has_participant
   :super is_spatiotemporally_related_to
   :domain process))
(defoproperty measured_at
  :super exists_at
  :range time_measurement)
(as-inverse
 (defoproperty has_output
   :super has_participant)
 (defoproperty is_output_of
   :super is_participant_in))
(as-inverse
 (defoproperty has_agent
   :super has_participant)
 (defoproperty is_agent_in
   :super is_participant_in))
(as-inverse
 (defoproperty is_input_in
   :super is_participant_in)
 (defoproperty has_input
   :super has_participant))
(as-inverse
 (defoproperty has_target
   :super has_input)
 (defoproperty is_target_in
   :super is_input_in))
(as-inverse
 (defoproperty is_parameter_in
   :super is_input_in)
 (defoproperty has_parameter
   :super has_input))
(defoproperty has_substrate
  :super has_target)
(as-inverse
 (defoproperty has_product
   :super has_output)
 (defoproperty is_product_of
   :super is_output_of))
(as-inverse
 (defoproperty affects
   :super has_output)
 (defoproperty is_affected_by
   :super is_participant_in))
(as-inverse
 (defoproperty is_realized_in
   :super is_participant_in)
 (defoproperty realizes
   :super has_output))
(as-inverse
 (defoproperty is_causally_related_to
   :super is_causally_related_with)
 (defoproperty is_causally_related_from
   :super is_causally_related_with))
(as-inverse
 (defoproperty derives_into
   :super is_causally_related_to
   :domain object
   :range object
   :characteristic :transitive)
;; NOTE should be derived_from -> is_derived_from
;; HYPERLINK BROKEN
 (defoproperty is_derived_from
   :super is_causally_related_from
   :characteristic :transitive))
(as-inverse
 (defoproperty is_regulated_by
   :super is_causally_related_from)
 (defoproperty regulates
   :super is_causally_related_to))
(as-inverse
 (defoproperty is_preceded_by
   :super is_causally_related_to
   :characteristic :transitive)
 (defoproperty precedes
   :super is_causally_related_from
   :characteristic :transitive))
(as-inverse
 (defoproperty results_in
   :super has_output is_causally_related_to)
 (defoproperty is_result_of
   :super is_causally_related_from))
(as-inverse
 (defoproperty is_transformed_from
   :super is_causally_related_from
   :characteristic :transitive)
 (defoproperty transforms_into
   :super is_causally_related_to
   :characteristic :transitive))
(as-inverse
 (defoproperty is_trigger_for
   :super is_causally_related_from)
 (defoproperty has_trigger
   :super is_causally_related_to))
(as-inverse
 (defoproperty is_immediately_derived_from
   :super is_derived_from)
 (defoproperty immediately_derives_into
   :super derives_into))
(as-inverse
 (defoproperty is_subsequent_version_of
   :super is_version_of)
 (defoproperty is_prior_version_of
   :super is_derived_from is_version_of))
(as-inverse
 (defoproperty is_immediately_transformed_from
   :super is_transformed_from)
 (defoproperty immediately_transforms_into
   :super transforms_into))
(as-inverse
 (defoproperty is_immediately_preceded_by
   :super is_preceded_by)
 (defoproperty immediately_precedes
   :super precedes))
(as-inverse
 (defoproperty has_part
   :super is_location_of
   :characteristic :transitive :reflexive)
 (defoproperty is_part_of
   :super is_located_in
   :characteristic :transitive :reflexive))
(add-subchain overlaps_with [overlaps_with is_part_of])
(as-inverse
 (defoproperty is_encoded_by
   :super is_specified_by)
 (defoproperty encodes
   :super specifies
   :domain (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))
   :range (owl-or lipid protein ribonucleic_acid (owl-some is_part_of lipid) (owl-some is_part_of protein) (owl-some is_part_of ribonucleic_acid))))
(as-inverse
 (defoproperty is_transcribed_into
   :super encodes
   :domain (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))
   :range (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid)))
 (defoproperty is_transcribed_from
   :super is_encoded_by
   :domain (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
   :range (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))))
(as-inverse
 (defoproperty is_translated_from
   :super is_encoded_by
   :range (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid)))
 (defoproperty is_translated_into
   :super encodes
   :domain (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
   :range polypeptide))
(as-inverse
 (defoproperty has_proper_part
   :super has_part
   :characteristic :asymmetric :irreflexive)
 (defoproperty is_proper_part_of
   :super is_part_of
   :characteristic :asymmetric :irreflexive))
(as-inverse
 (defoproperty has_direct_part
   :super has_proper_part)
 (defoproperty is_direct_part_of
   :super is_proper_part_of))
(as-inverse
 (defoproperty has_boundary
   :super has_proper_part)
 (defoproperty is_boundary_of
   :super is_proper_part_of))
;; missing inverse???
(defoproperty has_ordered_part
  :super has_proper_part)
(defoproperty is_ordered_part_of
  :super is_proper_part_of)
(as-inverse
 (defoproperty has_first_part
   :super has_ordered_part)
 (defoproperty is_first_part_of
   :super is_ordered_part_of))
(as-inverse
 (defoproperty has_last_part
   :super has_ordered_part)
 (defoproperty is_last_part_of
   :super is_ordered_part_of))
(as-inverse
 (defoproperty has_component_part
   :super has_direct_part)
 (defoproperty is_component_part_of
   :super is_direct_part_of))
(as-inverse
 (defoproperty is_contained_in
   :super is_located_in
   :characteristic :transitive)
 (defoproperty contains
   :super is_location_of
   :characteristic :transitive))
(as-inverse
 (defoproperty is_surrounded_by
   :super is_contained_in)
 (defoproperty surrounds
   :super contains))
(as-inverse
 (defoproperty is_time_boundary_of
   :super is_boundary_of
   :domain time_instant)
 (defoproperty has_time_boundary
   :super has_boundary
   :range time_instant
   :characteristic :functional))
(as-inverse
 (defoproperty has_start_time
   :super has_time_boundary
   :range time_instant
   :characteristic :functional)
 (defoproperty is_start_time_of
   :super is_time_boundary_of
   :domain time_instant))
(as-inverse
 (defoproperty has_end_time
   :super has_time_boundary
   :range time_instant
   :characteristic :functional)
 (defoproperty is_end_time_of
   :super is_time_boundary_of
   :domain time_instant))
(as-inverse
 (defoproperty is_positionally_before
   :super is_connected_to
   :characteristic :transitive)
 (defoproperty is_positionally_after
   :super is_connected_to
   :characteristic :transitive))
(defoproperty is_directly_connected_to
  :super is_connected_to
  :characteristic :symmetric)
(defoproperty is_covalently_connected_to__transitive_
  :super is_connected_to
  :domain atom
  :range atom
  :characteristic :transitive :symmetric)
(defoproperty is_covalently_connected_to
  :super is_covalently_connected_to__transitive_ is_directly_connected_to
  :characteristic :symmetric)
(defoproperty is_weakly_interacting_with__transitive_
  :super is_connected_to
  :domain (owl-or molecule (owl-some is_part_of molecule))
  :range (owl-or molecule (owl-some is_part_of molecule))
  :characteristic :transitive :symmetric)
(defoproperty is_weakly_interacting_with
  :super is_directly_connected_to is_weakly_interacting_with__transitive_
  :characteristic :symmetric)
(as-inverse
 (defoproperty is_directly_after
   :super is_directly_connected_to is_positionally_after
   :characteristic :functional)
 (defoproperty is_directly_before
   :super is_directly_connected_to is_positionally_before
   :characteristic :functional))