;; MEREOLOGY
;; See https://code.google.com/p/semanticscience/wiki/ODPMereotopology
(as-inverse
 (object-property has_part
                  :super is_location_of
                  :characteristic :transitive :reflexive)
 (object-property is_part_of
                  :super is_located_in
                  :characteristic :transitive :reflexive))
;; transitive is not asserted but inherited from parent
(as-inverse
 (object-property has_proper_part
                  :super has_part
                  :characteristic :asymmetric :irreflexive)
 (object-property is_proper_part_of
                  :super is_part_of
                  :characteristic :asymmetric :irreflexive))
(as-inverse
 (object-property has_direct_part
                  :super has_proper_part)
 (object-property is_direct_part_of
                  :super is_proper_part_of))
(as-inverse
 (object-property has_component_part
                  :super has_direct_part)
 (object-property is_component_part_of
                  :super is_direct_part_of))
(as-inverse
 (object-property is_located_in
                  :super is_spatiotemporally_related_to
                  :characteristic :transitive)
 (object-property is_location_of
                  :super is_spatiotemporally_related_to
                  :characteristic :transitive))
(as-inverse
 (object-property contains
                  :super is_location_of
                  :characteristic :transitive)
 (object-property is_contained_in
                  :super is_located_in
                  :characteristic :transitive))
(as-inverse
 (object-property surrounds
                  :super contains)
 (object-property is_surrounded_by
                  :super is_contained_in))
;; TOPOLOGY
;; See https://code.google.com/p/semanticscience/wiki/ODPMereotopology
(object-property is_connected_to
                 :super is_spatiotemporally_related_to
                 :characteristic :transitive :symmetric)
(object-property is_directly_connected_to
                 :super is_connected_to
                 :characteristic :symmetric)
;; not mentionned in documentation that these are functional
(as-inverse
 (object-property is_directly_before
                  :super is_positionally_before is_directly_connected_to
                  :characteristic :functional)
 (object-property is_directly_after
                  :super is_positionally_after is_directly_connected_to
                  :characteristic :functional))

;; PROCESS-BASED PROVENANCE
;; https://code.google.com/p/semanticscience/wiki/ODPProvenance
(as-inverse
 (object-property has_participant
                  :super is_spatiotemporally_related_to
                  :domain process)
 (object-property is_participant_in
                  :super is_spatiotemporally_related_to
                  :range process))
(as-inverse
 (object-property has_agent
                  :super has_participant)
 (object-property is_agent_in
                  :super is_participant_in
                  :inverse has_agent))
(as-inverse
 (object-property has_input
                  :super has_participant)
 (object-property is_input_in
                  :super is_participant_in))
(as-inverse
 (object-property has_target
                  :super has_input)
 (object-property is_target_in
                  :super is_input_in))
;; on its lonesome
(object-property has_substrate
                 :super has_target)
(as-inverse
 (object-property has_product
                  :super has_output
                  :inverse is_product_of)
 (object-property is_product_of
                  :super is_output_of))
;; is_located_in defined in MEREOLOGY
;; on its lonesome
(object-property exists_at
                 :super is_spatiotemporally_related_to
                 :range time_measurement)

;; OBJECT-LEVEL PROVENANCE
;; https://code.google.com/p/semanticscience/wiki/ODPProvenance
(as-inverse
 (object-property has_source
                  :super has_attribute)
 (object-property is_source_of
                  :super is_attribute_of))
(as-inverse
 (object-property has_creator
                  :super has_source)
 (object-property is_created_by
                  :super is_source_of))
(as-inverse
 (object-property has_provider
                  :super has_source)
 (object-property is_provider_of
                  :super is_source_of))
;; NOTE should be derived_from -> is_derived_from
;; HYPERLINK BROKEN
(as-inverse
 (object-property is_derived_from
                  :super is_causally_related_from
                  :characteristic :transitive)
 (object-property derives_into
                  :super is_causally_related_to
                  :domain object
                  :range object
                  :characteristic :transitive))
;; on its lonesome
(object-property is_version_of
                 :super is_variant_of
                 :characteristic :transitive :symmetric)
(as-inverse
 (object-property is_prior_version_of
                  :super is_derived_from is_version_of)
 (object-property is_subsequent_version_of
                  :super is_version_of))
;; OTHERS
(object-property has_output
	:super has_participant
	:inverse is_output_of)
(object-property has_function
	:super has_disposition
	:range function
	:inverse is_function_of
	:characteristic
	:inversefunctional)
(object-property is_function_of
	:super is_disposition_of
	:domain function
	:inverse has_function
	:characteristic :functional)
(object-property is_match_to
	:super is_similar_to
	:domain term
	:range term
	:characteristic :symmetric)
(object-property is_spatiotemporally_related_to
	:super is_related_to
	:characteristic :symmetric)
(object-property is_role_of
	:super is_realizable_property_of
	:domain role
	:inverse has_role
	:characteristic :functional)
(object-property has_role
	:super has_realizable_property
	:range role
	:inverse is_role_of
	:characteristic
	:inversefunctional)
(object-property is_exact_match_to
	:super is_match_to
	:characteristic :symmetric)
(object-property has_unit
	:super has_attribute
	:range unit_of_measurement
	:inverse is_unit_of
	:characteristic :functional)
(object-property overlaps_with
	:super is_spatiotemporally_related_to
	:characteristic :symmetric :reflexive)
(object-property is_unit_of
	:super is_attribute_of
	:domain unit_of_measurement
	:inverse has_unit)
(object-property has_property
	:super has_attribute
	:inverse is_property_of
	:characteristic
	:inversefunctional)
(object-property is_property_of
	:super is_attribute_of
	:inverse has_property
	:characteristic :functional)
(object-property is_member_of
	:super is_attribute_of
	:inverse has_member)
(object-property is_boundary_of
	:super is_proper_part_of
	:inverse has_boundary)
(object-property is_equal_to
	:super is_numerically_comparable_to
	:characteristic :symmetric)
(object-property is_numerically_comparable_to
	:super is_comparable_to
	:domain quantity
	:range quantity
	:characteristic :symmetric)
(object-property is_comparable_to
	:super is_related_to
	:characteristic :symmetric)
(object-property is_lesser_than
	:super is_numerically_comparable_to
	:inverse is_greater_than)
(object-property is_greater_than
	:super is_numerically_comparable_to
	:inverse is_lesser_than)
(object-property is_lesser_than_or_equal_to
	:super is_numerically_comparable_to
	:inverse is_greater_than_or_equal_to)
(object-property is_broader_than
	:super is_broader_than__t_
	:inverse is_narrower_than)
(object-property is_quality_of
	:super is_property_of
	:domain quality
	:inverse has_quality)
(object-property has_measurement_value
	:super has_attribute
	:range measurement_value
	:inverse is_measurement_value_of)
(object-property has_quality
	:super has_property
	:range quality
	:inverse is_quality_of
	:characteristic
	:inversefunctional)
(object-property is_concretization_of
	:super is_generically_related_with
	:inverse has_concretization)
(object-property is_measurement_value_of
	:super is_attribute_of
	:domain measurement_value
	:inverse has_measurement_value)
(object-property is_referred_to_by
	:super is_related_to
	:inverse refers_to)
(object-property has_concretization
	:super is_generically_related_with
	:inverse is_concretization_of)
(object-property represents
	:super refers_to
	:inverse is_represented_by)
(object-property is_positionally_after
	:super is_connected_to
	:inverse is_positionally_before
	:characteristic :transitive)
(object-property is_inequal_to
	:super is_numerically_comparable_to
	:characteristic :symmetric)
(object-property is_adjacent_to
	:super is_spatiotemporally_related_to
	:characteristic :symmetric)
(object-property is_mutually_related_to
	:super is_related_to
	:characteristic :symmetric)
(object-property is_represented_by
	:super is_referred_to_by
	:inverse represents)
(object-property is_supported_by
	:super has_evidence
	:inverse is_supporting_evidence_for)
(object-property is_disputed_by
	:super has_evidence
	:inverse is_disputing_evidence_for)
(object-property is_supporting_evidence_for
	:super is_evidence_for
	:inverse is_supported_by)
(object-property is_disputing_evidence_for
	:super is_evidence_for
	:inverse is_disputed_by)
(object-property is_manifested_as
	:super is_generically_related_with
	:inverse is_manifestation_of)
(object-property is_homologous_to
	:super is_variant_of
	:characteristic :symmetric)
(object-property is_mutual_role_of
	:super is_mutually_related_to
	:characteristic :symmetric)
(object-property is_covalently_connected_to
	:super is_covalently_connected_to__transitive_ is_directly_connected_to
	:characteristic :symmetric)
(object-property is_dissimilar_to
	:super is_variant_of
	:characteristic :symmetric)
(object-property is_about
	:super describes
	:inverse is_subject_of)
(object-property is_opposite_to
	:super is_dissimilar_to
	:characteristic :symmetric)
(object-property is_specified_by
	:super is_causally_related_with is_described_by
	:inverse specifies)
(object-property specifies
	:super is_causally_related_with describes
	:inverse is_specified_by)
(object-property is_weakly_interacting_with
	:super is_weakly_interacting_with__transitive_ is_directly_connected_to
	:characteristic :symmetric)
(object-property in_relation_from
	:super is_referred_to_by
	:inverse in_relation_to)
(object-property refers_to
	:super is_related_to
	:inverse is_referred_to_by)
(object-property is_broad_match_to
	:super is_match_to
	:characteristic :symmetric)
(object-property is_close_match_to
	:super is_match_to
	:characteristic :symmetric)
(object-property is_specialization_of
	:super is_variant_of
	:inverse is_generalization_of)
(object-property is_alternate_of
	:super is_variant_of
	:inverse is_alternate_of
	:characteristic :symmetric)
(object-property is_generalization_of
	:super is_variant_of
	:inverse is_specialization_of)
(object-property has_frequency
	:super has_attribute)
(object-property is_frequency_of
	:super is_attribute_of)
(object-property is_attribute_of
	:super is_related_to
	:inverse has_attribute)
(object-property has_time_boundary
	:super has_boundary
	:range time_instant
	:inverse is_time_boundary_of
	:characteristic :functional)
(object-property is_weakly_interacting_with__transitive_
	:super is_connected_to
	:domain (owl-or molecule (owl-some is_part_of molecule))
	:range (owl-or molecule (owl-some is_part_of molecule))
	:characteristic :transitive :symmetric)
(object-property has_identifier
	:super has_attribute
	:range identifier
	:inverse is_identifier_for)
(object-property has_unique_identifier
	:super has_identifier
	:range unique_identifier
	:inverse is_unique_identifier_for
	:characteristic
	:inversefunctional)
(object-property is_identifier_for
	:super is_attribute_of
	:domain identifier
	:inverse has_identifier)
(object-property is_unique_identifier_for
	:super is_identifier_for
	:domain unique_identifier
	:inverse has_unique_identifier
	:characteristic :functional)
(object-property is_regulated_by
	:super is_causally_related_from
	:inverse regulates)
(object-property regulates
	:super is_causally_related_to
	:inverse is_regulated_by)
(object-property is_affected_by
	:super is_participant_in
	:inverse affects)
(object-property affects
	:super has_output
	:inverse is_affected_by)
(object-property is_result_of
	:super is_causally_related_from)
(object-property results_in
	:super has_output is_causally_related_to)
(object-property is_causally_related_to
	:super is_causally_related_with
	:inverse is_causally_related_from)
(object-property denotes
	:super represents
	:inverse is_denoted_by)
(object-property is_start_time_of
	:super is_time_boundary_of
	:domain time_instant
	:inverse has_start_time)
(object-property is_time_boundary_of
	:super is_boundary_of
	:domain time_instant
	:inverse has_time_boundary)
(object-property has_end_time
	:super has_time_boundary
	:range time_instant
	:inverse is_end_time_of
	:characteristic :functional)
(object-property has_start_time
	:super has_time_boundary
	:range time_instant
	:inverse is_start_time_of
	:characteristic :functional)
(object-property is_end_time_of
	:super is_time_boundary_of
	:domain time_instant
	:inverse has_end_time)
(object-property is_transitively_related_to
	:characteristic :transitive :symmetric)
(object-property is_identical_to
	:super is_comparable_to
	:characteristic :symmetric)
(object-property is_greater_than_or_equal_to
	:super is_numerically_comparable_to
	:inverse is_lesser_than_or_equal_to)
(object-property measured_at
	:super exists_at
	:range time_measurement)
(object-property is_mutual_disposition_of
	:super is_mutually_related_to
	:characteristic :symmetric)
(object-property cites
	:super references
	:inverse is_cited_by)
(object-property is_cited_by
	:super is_referenced_by
	:inverse cites)
(object-property is_variant_of
	:super is_comparable_to
	:characteristic :transitive :symmetric)
(object-property is_related_to
	:characteristic :symmetric)
(object-property has_attribute
	:super is_related_to
	:inverse is_attribute_of)
(object-property is_expression_of
	:super is_generically_related_with
	:inverse has_expression)
(object-property is_broader_than__t_
	:super is_similar_to
	:domain term
	:range term
	:inverse is_narrower_than__t_
	:characteristic :transitive)
(object-property has_expression
	:super is_generically_related_with
	:inverse is_expression_of)
(object-property is_narrower_than
	:super is_narrower_than__t_
	:inverse is_broader_than)
(object-property is_covalently_connected_to__transitive_
	:super is_connected_to
	:domain atom
	:range atom
	:characteristic :transitive :symmetric)
(object-property is_generically_related_with
	:super is_related_to)
(object-property is_manifestation_of
	:super is_generically_related_with
	:inverse is_manifested_as)
(object-property is_similar_to
	:super is_variant_of
	:characteristic :symmetric)
(object-property is_narrower_than__t_
	:super is_similar_to
	:domain term
	:range term
	:inverse is_broader_than__t_
	:characteristic :transitive)
(object-property is_refuting_evidence_for
	:super is_evidence_for
	:inverse is_refuted_by)
(object-property is_paralogous_to
	:super is_homologous_to
	:characteristic :symmetric)
(object-property references
	:super refers_to
	:inverse is_referenced_by)
(object-property is_model_of
	:super represents
	:inverse is_modelled_by)
(object-property is_modelled_by
	:super is_represented_by
	:inverse is_model_of)
(object-property has_evidence
	:super references
	:inverse is_evidence_for)
(object-property has_boundary
	:super has_proper_part
	:inverse is_boundary_of)
(object-property is_xenologous_to
	:super is_orthologous_to
	:characteristic :symmetric)
(object-property is_evidence_for
	:super is_referenced_by
	:inverse has_evidence)
(object-property has_trigger
	:super is_causally_related_to
	:inverse is_trigger_for)
(object-property is_refuted_by
	:super has_evidence
	:inverse is_refuting_evidence_for)
(object-property is_trigger_for
	:super is_causally_related_from
	:inverse has_trigger)
(object-property has_member
	:super has_attribute
	:inverse is_member_of
	:characteristic :irreflexive)
(object-property is_immediately_preceded_by
	:super is_preceded_by
	:inverse immediately_precedes)
(object-property immediately_precedes
	:super precedes
	:inverse is_immediately_preceded_by)
(object-property is_subject_of
	:super is_described_by
	:inverse is_about)
(object-property is_referenced_by
	:super is_referred_to_by
	:inverse references)
(object-property has_annotation
	:super has_attribute
	:range language_entity
	:inverse is_annotation_of)
(object-property is_annotation_of
	:super is_attribute_of
	:domain language_entity
	:inverse has_annotation)
(object-property is_realized_in
	:super is_participant_in
	:inverse realizes)
(object-property realizes
	:super has_output
	:inverse is_realized_in)
(object-property is_causally_related_from
	:super is_causally_related_with
	:inverse is_causally_related_to)
(object-property has_first_part
	:super has_ordered_part
	:inverse is_first_part_of)
(object-property has_last_part
	:super has_ordered_part
	:inverse is_last_part_of)
(object-property is_first_part_of
	:super is_ordered_part_of
	:inverse has_first_part)
(object-property has_basis
	:super has_attribute
	:domain realizable_entity
	:range quality
	:inverse is_base_for)
(object-property is_base_for
	:super is_attribute_of
	:domain quality
	:range realizable_entity
	:inverse has_basis)
(object-property is_capability_of
	:super is_realizable_property_of
	:domain capability
	:inverse has_capability
	:characteristic :functional)
(object-property is_realizable_property_of
	:super is_property_of
	:inverse has_realizable_property
	:characteristic :functional)
(object-property has_realizable_property
	:super has_property
	:inverse is_realizable_property_of
	:characteristic
	:inversefunctional)
(object-property is_ordered_part_of
	:super is_proper_part_of)
(object-property is_last_part_of
	:super is_ordered_part_of
	:inverse has_last_part)
(object-property has_ordered_part
	:super has_proper_part)
(object-property has_capability
	:super has_realizable_property
	:range capability
	:inverse is_capability_of
	:characteristic
	:inversefunctional)
(object-property is_denoted_by
	:super is_represented_by
	:inverse denotes)
(object-property is_satisfied_by
	:super is_attribute_of
	:inverse satisfies)
(object-property is_mutual_capability_of
	:super is_mutually_related_to
	:characteristic :symmetric)
(object-property satisfies
	:super has_attribute
	:inverse is_satisfied_by)
(object-property is_translated_from
	:super is_encoded_by
	:range (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
	:inverse is_translated_into)
(object-property is_translated_into
	:super encodes
	:domain (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
	:range polypeptide
	:inverse is_translated_from)
(object-property is_transcribed_from
	:super is_encoded_by
	:domain (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
	:range (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))
	:inverse is_transcribed_into)
(object-property is_transcribed_into
	:super encodes
	:domain (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))
	:range (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
	:inverse is_transcribed_from)
(object-property is_immediately_transformed_from
	:super is_transformed_from
	:inverse immediately_transforms_into)
(object-property transforms_into
	:super is_causally_related_to
	:inverse is_transformed_from
	:characteristic :transitive)
(object-property immediately_transforms_into
	:super transforms_into
	:inverse is_immediately_transformed_from)
(object-property is_transformed_from
	:super is_causally_related_from
	:inverse transforms_into
	:characteristic :transitive)
(object-property is_implementation_of
	:super is_attribute_of
	:inverse has_implementation)
(object-property is_output_of
	:super is_participant_in
	:inverse has_output)
(object-property has_disposition
	:super has_capability
	:range disposition
	:inverse is_disposition_of
	:characteristic
	:inversefunctional)
(object-property has_implementation
	:super has_attribute
	:inverse is_implementation_of)
(object-property has_parameter
	:super has_input
	:inverse is_parameter_in)
(object-property is_parameter_in
	:super is_input_in
	:inverse has_parameter)
(object-property is_orthologous_to
	:super is_homologous_to
	:characteristic :symmetric)
(object-property is_described_by
	:super is_referred_to_by
	:inverse describes)
(object-property is_disposition_of
	:super is_capability_of
	:domain disposition
	:inverse has_disposition
	:characteristic :functional)
(object-property in_relation_to
	:super refers_to
	:inverse in_relation_from)
(object-property immediately_derives_into
	:super derives_into
	:inverse is_immediately_derived_from)
(object-property is_causally_related_with
	:super is_spatiotemporally_related_to
	:characteristic :transitive :symmetric)
(object-property is_positionally_before
	:super is_connected_to
	:inverse is_positionally_after
	:characteristic :transitive)
(object-property describes
	:super refers_to
	:inverse is_described_by)
(object-property encodes
	:super specifies
	:domain (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))
	:range (owl-or ribonucleic_acid lipid protein (owl-some is_part_of ribonucleic_acid) (owl-some is_part_of lipid) (owl-some is_part_of protein))
	:inverse is_encoded_by)
(object-property is_encoded_by
	:super is_specified_by
	:inverse encodes)
(object-property is_preceded_by
	:super is_causally_related_to
	:inverse precedes
	:characteristic :transitive)
(object-property precedes
	:super is_causally_related_from
	:inverse is_preceded_by
	:characteristic :transitive)
(object-property is_immediately_derived_from
	:super is_derived_from
	:inverse immediately_derives_into)
