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
(sio-oproperty "is related to" "A is related to B iff there is some relation between A and B."
               :characteristic :symmetric
               :annotation
               core
               (owl-comment (literal "'is related to' is the top level relation in SIO" :type :RDF_PLAIN_LITERAL))
               (subset-rdf "relations+"))
;; missing desc
(defoproperty is_transitively_related_to
  :label "is transitively related to"
  :characteristic :transitive :symmetric
  :annotation
  (subset-rdf "ovopub")
  (owl-comment (literal "not included in standard distribution" :type :RDF_PLAIN_LITERAL)))

(as-inverse
 (sio-oproperty "has attribute" "has attribute is a relation that associates a entity with an attribute where an attribute is an intrinsic characteristic such as a quality, capability, disposition, function, or is an externally derived attribute determined from some descriptor (e.g. a quantity, position, label/identifier) either directly or indirectly through generalization of entities of the same type."
                :super is_related_to
                :annotation
                core)
 (sio-oproperty "is attribute of" "is attribute of is a relation that associates an attribute with an entity where an attribute is an intrinsic characteristic such as a quality, capability, disposition, function, or is an externally derived attribute determined from some descriptor (e.g. a quantity, position, label/identifier) either directly or indirectly through generalization of entities of the same type."
                :super is_related_to))
(sio-oproperty "is comparable to" "is comparable to is a relation between two entities that share at least one feature whose value can be compared."
               :super is_related_to
               :characteristic :symmetric
               :annotation
               core)
(sio-oproperty "is generically related with" "A is generically related with B iff A is an abstract entity or an information content entity and B is a information content entity or a physical entity, respectively."
               :super is_related_to)
(sio-oproperty "is mutually related to" "a is mutually related to b if and only if the realization of the relation of a necessarily causes the realization of a relation to b."
               :super is_related_to
               :characteristic :symmetric)
(sio-oproperty "is spatiotemporally related to" "A is spatiotemporally related to B iff A is in the spatial or temporal vicinity of B"
               :super is_related_to
               :characteristic :symmetric
               :annotation
               core)
(sio-oproperty "is causally related with" "A transitive, symmetric, temporal relation in which one entity is causally related with another non-identical entity."
               :super is_spatiotemporally_related_to
               :characteristic :transitive :symmetric
               :annotation
               core)
(as-inverse
 (sio-oproperty "is referred to by" "A is referred to by B iff B is an informational entity that makes reference to A."
                :super is_related_to)
 (sio-oproperty "refers to" "refers to is a relation between one entity and the entity that it makes reference to."
                :super is_related_to
                :annotation
                (subset-rdf "nlp+")
                core))
(as-inverse
 (sio-oproperty "has annotation" "has annotation is a relation between an entity and some textual anntotation."
                :super has_attribute
                :range language_entity
                :annotation
                (eg "document x is annotated with the keywords 'ontology','semantic web'")
                (synonym-en "is annotated with")
                (synonym-en "was annotated with"))
 (sio-oproperty "is annotation of" "is annotation of is a relation between some textual entity and the entity that it annotates."
                :super is_attribute_of
                :domain language_entity))
(as-inverse
 (sio-oproperty "has basis" "has basis is a relation between a realizable entity and the quality that forms the basis for it."
                :super has_attribute
                :domain realizable_entity
                :range quality
                :annotation
                (eg "the solubility of salt (the disposition) is based on the molecular structure of NaCl (the quality) that allows the polarized water-molecules (the trigger)  to break the ion bonds (the realization)."))
 (sio-oproperty "is base for" "is base for is a relation between a quality and the realizable entity that it is the basis for."
                :super is_attribute_of
                :domain quality
                :range realizable_entity
                :annotation
                (eg "the solubility of salt (the disposition) is based on the molecular structure of NaCl (the quality) that allows the polarized water-molecules (the trigger)  to break the ion bonds (the realization).")))
;; missing desc
(as-inverse
 (defoproperty has_identifier
   :label "has identifier"
   :super has_attribute
   :range identifier)
 (defoproperty is_identifier_for
   :label "is identifier for"
   :super is_attribute_of
   :domain identifier))
(as-inverse
 (sio-oproperty "has implementation" "has implementation is a relation between a specification and an implementation that conforms to it."
                :super has_attribute)
 (sio-oproperty "is implementation of" "is implementation of is a relation between an information entity and a specification that it conforms to."
                :super is_attribute_of))
(as-inverse
 (sio-oproperty "has measurement value" "has measurement value is a relation between a quality/realizable and a measurement value."
                :super has_attribute
                :range measurement_value
                :annotation
                core)
 (sio-oproperty "is measurement value of" "is measurement value of is a relation between a value and the entity that it is a measurement of."
                :super is_attribute_of
                :domain measurement_value))
(as-inverse
 (sio-oproperty "has member" "has member is a mereological relation between a collection and an item."
                :super has_attribute
                :characteristic :irreflexive
                :annotation
                core
                (eg "a collection of cars has as a car as a member"))
 (sio-oproperty "is member of" "is member of is a mereological relation between a item and a collection."
                :super is_attribute_of))
(as-inverse
 (sio-oproperty "has property" "has property is a relation between an entity and the quality, capability or role that it and it alone bears."
                :super has_attribute
                :characteristic
                :inversefunctional
                :annotation
                (eg "michel's hair has the quality of being brown in colour."))
 (sio-oproperty "is property of" "is property of is a relation betweena  quality, capability or role and the entity that it and it alone bears."
                :super is_attribute_of
                :characteristic :functional))
(as-inverse
 (sio-oproperty "has source" "has source is a relation between an entity and another entity from which it stems from."
                :super has_attribute)
 (sio-oproperty "is source of" "is source of is a relation between a source of information about some entity."
                :super is_attribute_of))
(as-inverse
 (sio-oproperty "has unit" "has unit is a relation between a quantity and the unit it is a multiple of."
                :super has_attribute
                :range unit_of_measurement
                :characteristic :functional
                :annotation
                core)
 (sio-oproperty "is unit of" "is unit of is a relation between a unit and a quantity that it is a multiple of."
                :super is_attribute_of
                :domain unit_of_measurement))
(as-inverse
 (sio-oproperty "satisfies" "satisfies is a relation between an entity and the specification or objective that it conforms to."
                :super has_attribute)
 (defoproperty is_satisfied_by
   :label "is satisfied by"
   :super is_attribute_of))

(as-inverse
 (sio-oproperty "has unique identifier" "has unique identifier is an inverse functional relation between an entity and an identifier that uniquely identifies it."
                :super has_identifier
                :range unique_identifier
                :characteristic
                :inversefunctional)
 (sio-oproperty "is unique identifier for" "is unique identifier for is a relation between an identifier and an entity that it uniquely identifies"
                :super is_identifier_for
                :domain unique_identifier
                :characteristic :functional))
(as-inverse
 (sio-oproperty "has frequency" "has frequency is a relation that specifies the occurence of an object or event"
                :super has_measurement_value)
 (sio-oproperty "is frequency of" "is frequency of is a relation between a temporal unit and an object or event"
                :super is_measurement_value_of))
(as-inverse
 (sio-oproperty "has quality" "has quality is a relation between an entity and the quality that it bears."
                :super has_property
                :range quality
                :characteristic
                :inversefunctional)
 (sio-oproperty "is quality of" "is quality of is a relation between a quality and the entity that it is a property of."
                :super is_property_of
                :domain quality))
;; missing desc
(as-inverse
 (defoproperty has_realizable_property
   :label "has realizable property"
   :super has_property
   :characteristic
   :inversefunctional)
 (defoproperty is_realizable_property_of
   :label "is realizable property of"
   :super is_property_of
   :characteristic :functional))
(as-inverse
 (sio-oproperty "has capability" "has capability is a relation between an entity and the capability that it bears."
                :super has_realizable_property
                :range capability
                :characteristic
                :inversefunctional
                :annotation
                (eg "SIO has the capability to semantically integrate data."))
 (sio-oproperty "is capability of" "is capability of is a relation between a capability and the entity that bears it."
                :super is_realizable_property_of
                :domain capability
                :characteristic :functional))
(as-inverse
 (sio-oproperty "has role" "has role is a relation between an entity and a role that it bears."
                :super has_realizable_property
                :range role
                :characteristic
                :inversefunctional)
 (sio-oproperty "is role of" "is role of is a relation between a role and the entity that it is a property of."
                :super is_realizable_property_of
                :domain role
                :characteristic :functional))
(as-inverse
 (sio-oproperty "has disposition" "has disposition is the relation between an entity and a disposition that it bears."
                :super has_capability
                :range disposition
                :characteristic
                :inversefunctional)
 (sio-oproperty "is disposition of" "is disposition of is a relation between a disposition and the entity that it is a property of."
                :super is_capability_of
                :domain disposition
                :characteristic :functional))
(as-inverse
 (sio-oproperty "has function" "has function is the relation between an entity and a function that is ascribed to it."
                :super has_disposition
                :range function
                :characteristic
                :inversefunctional)
 (sio-oproperty "is function of" "is function of is a relation between a function and an entity that it is a property of."
                :super is_disposition_of
                :domain function
                :characteristic :functional))
(as-inverse
 (sio-oproperty "has creator" "has creator is a relation between an entity and that which created it."
                :super has_source
                :annotation
                (equivalent-rdf "dc:creator")
                (synonym-en "was created by")
                (eg "the iPhone was created by Apple Inc")
                (synonym-en "is created by"))
 (sio-oproperty "is created by" "is created by is a relation between an entity and its creator."
                :super is_source_of))
(as-inverse
 (sio-oproperty "has provider" "is provided by is a relation between an entity and the entity that provides it."
                :super has_source
                :annotation
                (equivalent-rdf "dc:source"))
 (sio-oproperty "is provider of" "is provider of is a relation between a source and the entity it makes available."
                :super is_source_of))
(sio-oproperty "is identical to" "is identical to is a relation between two objects that are conceptually the same notwithstanding provenance or other non-intrinsic attributes."
               :super is_comparable_to
               :characteristic :symmetric)
(sio-oproperty "is numerically comparable to" "is numerically comparable to is a comparison relation between two quantities whose datatype value can be compared."
               :super is_comparable_to
               :domain quantity
               :range quantity
               :characteristic :symmetric)
(sio-oproperty "is variant of" "is variant of: a relationship indicating that two entities are different (by some measure), but either achieve the same objectives in different ways or are permutations of one another (temporal, logical or otherwise)"
               :super is_comparable_to
               :characteristic :transitive :symmetric
               :annotation
               core)
(sio-oproperty "is equal to" "is equal to is a comparison relation between two quantities whose value are exactly the same."
               :super is_numerically_comparable_to
               :characteristic :symmetric)
(sio-oproperty "is inequal to" "is inequal to is a comparison relation between two quantities whose value are not the same."
               :super is_numerically_comparable_to
               :characteristic :symmetric)
(as-inverse
 (sio-oproperty "is greater than" "is greater than to is a comparison relation between two quantities in which the first has a value larger than the second."
                :super is_numerically_comparable_to)
 (sio-oproperty "is lesser than" "is lesser than to is a comparison relation between two quantities in which the first has a value smaller than the second."
                :super is_numerically_comparable_to))
(as-inverse
 (sio-oproperty "is greater than or equal to" "is greater than or equal to is a comparison relation between two quantities in which the first has a value larger or equal to the second."
                :super is_numerically_comparable_to)
 (sio-oproperty "is lesser than or equal to" "is greater than to is a comparison relation between two quantities in which the first has a value smaller or equal to the second."
                :super is_numerically_comparable_to))
(sio-oproperty "is similar to" "is similar to is a relation between two entities that share one or more features."
               :super is_variant_of
               :characteristic :symmetric)
(sio-oproperty "is dissimilar to" "is dissimilar to is a relation between two entities in which one is considered dissimilar to the other based on some criteria."
               :super is_variant_of
               :characteristic :symmetric)
(sio-oproperty "is homologous to" "a relation between two entities which indicates their common ancestry."
               :super is_variant_of
               :characteristic :symmetric)
(sio-oproperty "is version of" "is version of is a relation that holds between any two versions in which one is a subsequent or alternate version of (through a branch)."
               :super is_variant_of
               :characteristic :transitive :symmetric)

(as-inverse
 (sio-oproperty "is specialization of" "is specialization of is a relation between a more specific instance (in terms of spatial/temporal localization & other attributres) than the other."
                :super is_variant_of
                :annotation
                (equivalent-uri "http://www.w3.org/ns/prov#specializationOf")
                (eg "a weather forecast for the city of London produced at a specific time is a specialization of a weather forecast for the city of London."))
 (sio-oproperty "is generalization of" "is generalization of is a relation between a more general instance (in terms of spatial/temporal localization & other attributres) than the other."
                :super is_variant_of))
(sio-oproperty "is alternate of" "is alternate of relates two specialized instances."
               :super is_variant_of
               :characteristic :symmetric
               :annotation
               (eg "two weather forecasts for the city of London produced at different times or by different organizations (etc) are alternates of each other, and instances of / specializations of forecasts for the city of London.")
               (equivalent-uri "http://www.w3.org/ns/prov#alternateOf"))
(add-inverse is_alternate_of is_alternate_of)

(sio-oproperty "is opposite to" "is opposite to is a relation between two entities in which one is diametrically opposed to the other. "
               :super is_dissimilar_to
               :characteristic :symmetric
               :annotation
               (eg "hot is the opposite of cold; left is opposite of right"))
(sio-oproperty "is orthologous to" "is orthologous to is a relation between two biological entities that share a common ancestor and occur in different species."
               :super is_homologous_to
               :characteristic :symmetric)
(sio-oproperty "is paralogous to" "is paralogous to is a relation between two entities which indicates their common ancestry as a result of a gene duplication."
               :super is_homologous_to
               :characteristic :symmetric)
(sio-oproperty "is xenologous to" "a relation between two entities which indicates their common ancestry but due to horizontal gene transfer."
               :super is_orthologous_to
               :characteristic :symmetric)
(as-inverse
 (sio-oproperty "is broader than (t)" "A is broader than B (t) iff for A is broader than B."
                :super is_similar_to
                :domain term
                :range term
                :characteristic :transitive
                :annotation
                (equivalent-rdf "http://www.w3.org/2004/02/skos/core#narrowerTransitive"))
 (sio-oproperty "is narrower than (t)" "A is narrower than B (t) iff for A is narrower than B."
                :super is_similar_to
                :domain term
                :range term
                :characteristic :transitive
                :annotation
                (equivalent-rdf "http://www.w3.org/2004/02/skos/core#broaderTransitive")))
(sio-oproperty "is match to" "A is match to B iff A and B are terms with similar (related, broad, close, exact) meanings"
               :super is_similar_to
               :domain term
               :range term
               :characteristic :symmetric
               :annotation
               (equivalent-rdf "http://www.w3.org/2004/02/skos/core#relatedMatch"))
(as-inverse
 (defoproperty is_broader_than
   :label "is broader than"
   :super is_broader_than__t_
   :annotation
   (equivalent-rdf "http://www.w3.org/2004/02/skos/core#narrower"))
 (sio-oproperty "is narrower than" "A is narrower than B iff the meaning of term A is narrower in scope than the meaning of term B"
                :super is_narrower_than__t_
                :annotation
                (equivalent-rdf "http://www.w3.org/2004/02/skos/core#broader"))) ;;uri?

(defoproperty is_broad_match_to
  :label "is broad match to"
  :super is_match_to
  :characteristic :symmetric
  :annotation
  (equivalent-rdf "http://www.w3.org/2004/02/skos/core#broadMatch")) ;;uri?
(defoproperty is_exact_match_to
  :label "is exact match to"
  :super is_match_to
  :characteristic :symmetric
  :annotation
  (equivalent-rdf "http://www.w3.org/2004/02/skos/core#exactMatch")) ;;uri?
(defoproperty is_close_match_to
  :label "is close match to"
  :super is_match_to
  :characteristic :symmetric
  :annotation
  (equivalent-rdf "http://www.w3.org/2004/02/skos/core#closeMatch")) ;uri?
(as-inverse
 (sio-oproperty "is expression of" "is expression of is a relation between more concrete expression of some conceptualization."
                :super is_generically_related_with
                :annotation
                (eg "every language edition of 'war of the worlds' are expressions of the story of 'war of the worlds'"))
 (defoproperty has_expression
   :label "has expression"
   :super is_generically_related_with
   :annotation
   (eg "the story 'war of worlds' is expressed as every language edition of the book 'war of the worlds'")))
(as-inverse
 (defoproperty is_manifestation_of
   :label "is manifestation of"
   :super is_generically_related_with)
 (sio-oproperty "is manifested as" "is manifested as is a relation between an expression and its manifestations."
                :super is_generically_related_with
                :annotation
                (eg "the english language version of 'war of the worlds' is manifested as a collection of english language versions of the book.")))
(as-inverse
 (sio-oproperty "has concretization" "A has concretization B iff A is an informational entity and B is the a quality of some material entity."
                :super is_generically_related_with)
 (sio-oproperty "is concretization of" "A is concretization of B iff A is a quality of a material entity and B is an informational entity."
                :super is_generically_related_with))
(sio-oproperty "is mutual capability of" "a is mutual capability of b if and only if the realization of capability  a necessarily causes the realization of capability b."
               :super is_mutually_related_to
               :characteristic :symmetric)
(sio-oproperty "is mutual disposition of" "a is mutual disposition of b if and only if the realization of the disposition  a necessarily causes the realization of the disposition b."
               :super is_mutually_related_to
               :characteristic :symmetric)
(sio-oproperty "is mutual role of" "a is mutual role of b if and only if the realization of role  a necessarily causes the realization of role b."
               :super is_mutually_related_to
               :characteristic :symmetric)
(as-inverse
 (sio-oproperty "in relation from" "a in relation from b is a comparative relation in a is the object of reference in a relation with b. "
                :super is_referred_to_by)
 (sio-oproperty "in relation to" "in relation to is a comparative relation to indicate that the instance of the class holding the relation exists in relation to another entity."
                :super refers_to
                :annotation
                core))
(as-inverse
 (sio-oproperty "is represented by" "is represented by: a relation between an entity and some symbol."
                :super is_referred_to_by)
 (sio-oproperty "represents" "a represents b when a serves as a sign, symbol or model of b."
                :super refers_to
                :annotation
                core))
(as-inverse
 (sio-oproperty "references" "references is a relation between one entity and the entity that it makes reference to by name, but is not described by it."
                :super refers_to
                :annotation
                (synonym-rdf "mentions")
                core)
 (sio-oproperty "is referenced by" "is reference for is a relation between a document that provides information about an entity."
                :super is_referred_to_by))
(as-inverse
 (sio-oproperty "describes" "describes is a relation between one entity and another entity that it provides a description (detailed account of)."
                :super refers_to
                :annotation
                core)
 (sio-oproperty "is described by" "is described by is a relation between one entity and another entity that provides a description (detailed account) of it."
                :super is_referred_to_by))
(as-inverse
 (defoproperty is_subject_of
   :label "is subject of"
   :super is_described_by)
 (sio-oproperty "is about" "is about is a relation between an information content entity and the entity that its primary subject."
                :super describes))
(as-inverse
 (sio-oproperty "is specified by" "a relation between a product and the information content entity that specifies it."
                :super is_causally_related_with is_described_by)
 (sio-oproperty "specifies" "A relation between an information content entity and a product that it (directly/indirectly) specifies"
                :super is_causally_related_with describes))
(as-inverse
 (sio-oproperty "is cited by" "is cited by is a relation from an object that is referred to by way of example, authority or proof."
                :super is_referenced_by)
 (sio-oproperty "cites" "cites is a relation to refer to by way of example, authority or proof."
                :super references))
(as-inverse
 (defoproperty is_evidence_for
   :label "is evidence for"
   :super is_referenced_by)
 (sio-oproperty "has evidence" "has evidence is a relation between a proposition and something that demonstrates the truth of the assertion."
                :super references))
(as-inverse
 (sio-oproperty "is disputed by" "has disputing evidence is a relation between a proposition and something that disputes (does not directly support) the truth of the assertion."
                :super has_evidence)
 (defoproperty is_disputing_evidence_for
   :label "is disputing evidence for"
   :super is_evidence_for))
(as-inverse
 (defoproperty is_supporting_evidence_for
   :label "is supporting evidence for"
   :super is_evidence_for)
 (sio-oproperty "is supported by" "is supported by is a relation between a proposition and something that supports the truth of the assertion."
                :super has_evidence))
(as-inverse
 (sio-oproperty "is refuted by" "has evidence is a relation between a proposition and something that refutes (is incompatible with) the truth of the assertion."
                :super has_evidence)
 (defoproperty is_refuting_evidence_for
   :label "is refuting evidence for"
   :super is_evidence_for))
(as-inverse
 (sio-oproperty "denotes" "denotes is a relation between an entity and what it is a sign or indication of, or what specifically means."
                :super represents
                :annotation
                (eg "the symbol '%' denotes a proportion in relation to a whole."))
 (defoproperty is_denoted_by
   :label "is denoted by"
   :super is_represented_by))
(as-inverse
 (defoproperty is_modelled_by
   :label "is modelled by"
   :super is_represented_by)
 (sio-oproperty "is model of" "is model of is a relation between a model (an artifact) and the entity it purports to represent."
                :super represents
                :annotation
                (eg "the architect builds a model that represents the building she envisions.")))
(sio-oproperty "exists at" "exists at is a relation between an entity and a time measurement."
               :super is_spatiotemporally_related_to
               :range time_measurement
               :annotation
               core)
(sio-oproperty "is adjacent to" "A is adjacent to B iff there is a small, but non-zero distance between A and B"
               :super is_spatiotemporally_related_to
               :characteristic :symmetric
               :annotation
               (equivalent-rdf "OBO_REL:adjacent_to")
               core
               (subset-rdf "nlp"))
(sio-oproperty "overlaps with" "A overlaps with B iff there is some C that is part of both A and B. [S][R]"
               :super is_spatiotemporally_related_to
               :characteristic :symmetric :reflexive)
(sio-oproperty "is connected to" "A is connected to B iff there exists a fiat, material or temporal path between A and B. [S][T]"
               :super is_spatiotemporally_related_to
               :characteristic :transitive :symmetric
               :annotation
               core
               (subset-rdf "nlp"))
(as-inverse
 (sio-oproperty "is location of" "A is location of B iff the spatial region occupied by A has the spatial region occupied by B as a part. [T][R]"
                :super is_spatiotemporally_related_to
                :characteristic :transitive
                :annotation
                (subset-rdf "nlp+"))
 (sio-oproperty "is located in" "A is located in B iff the spatial region occupied by A is part of the spatial region occupied by B. [T][R]"
                :super is_spatiotemporally_related_to
                :characteristic :transitive
                :annotation
                core
                (eg "A parasite in the interior of a person's intestine is located in their intestinal lumen.")
                (equivalent-rdf "OBO_REL:located_in")))
(as-inverse
 (sio-oproperty "is participant in" "is participant in is a relation that describes the participation of the subject in the (processual) object."
                :super is_spatiotemporally_related_to
                :range process
                :annotation
                (eg "a car is a participant in a car race; glucose is a participant in glycolysis"))
 (sio-oproperty "has participant" "has participant is a relation that describes the participation of the object in the (processual) subject."
                :super is_spatiotemporally_related_to
                :domain process
                :annotation
                core
                (equivalent-rdf "OBO_REL:has_participant")))
(sio-oproperty "measured at" "measured at is a relation between a measurement value and the time measurement."
               :super exists_at
               :range time_measurement
               :annotation
               core)
(as-inverse
 (sio-oproperty "has output" "has output is a relation between an process and an entity, where the entity is present at the end of the process."
                :super has_participant
                :annotation
                core
                sadi)
 (sio-oproperty "is output of" "is output of is a relation between an entity and a process, where the entity is present at the end of the process."
                :super is_participant_in
                :annotation
                (eg "glucose-6-phosphate and ADP are outputs in the hexokinase-mediated conversion of glucose to glucose-6-phosphate.")
                sadi))
(as-inverse
 (sio-oproperty "has agent" "has agent is a relation between a process and an entity, where the entity is present throughout the process and is a causal participant in the process."
                :super has_participant
                :annotation
                (equivalent-rdf "OBO_REL:has_agent")
                core
                sadi)
 (sio-oproperty "is agent in" "is agent in is a relation between an entity and a process, where the entity is present throughout the process, no permanent material change occurs, and is a causal participant in the process."
                :super is_participant_in
                :annotation
                sadi
                (eg "hexokinase is an agent in the conversion of glucose to glucose-6-phosphate")))
(as-inverse
 (sio-oproperty "is input in" "is input in is a relation between an entity and a process, where the entity is present at the beginning of the process."
                :super is_participant_in
                :annotation
                sadi
                (eg "glucose and ATP are inputs in the hexokinase-mediated conversion of glucose to glucose-6-phosphate."))
 (sio-oproperty "has input" "has input is a relation between a process and an entity, where the entity is present at the beginning of the process."
                :super has_participant
                :annotation
                core
                sadi))
(as-inverse
 (sio-oproperty "has target" "has target is a relation between a process and an entity, where the entity is present at the beginning  of the process and undergoes a change or transformation in the process."
                :super has_input)
 (sio-oproperty "is target in" "is target in is a relation between an entity and a process, where the entity is present at the beginning  of the process and undergoes a change or transformation in the process."
                :super is_input_in
                :annotation
                (eg "glucose is a target in the hexokinase-mediated conversion of glucose to glucose-6-phosphate.")))
(as-inverse
 (sio-oproperty "is parameter in" "is parameter in is a relation between a data item and some data transformation process."
                :super is_input_in)
 (sio-oproperty "has parameter" "has parameter is a relation between a process and an information content entity which modulates the behaviour of some participant."
                :super has_input
                :annotation
                sadi))
(sio-oproperty "has substrate" "has substrate is a relation between a process and an object where the object is destroyed by its participation in the process."
               :super has_target)
(as-inverse
 (sio-oproperty "has product" "has product is a relation between an process and an entity, where a new entity exists at the end of the process."
                :super has_output
                :annotation
                sadi)
 (sio-oproperty "is product of" "is product of is a relation between an entity and a process, where the entity is present at the end  of the process as a result of a transformation in the process target."
                :super is_output_of
                :annotation
                sadi
                (eg "glucose-6-phosphate is the product of the hexokinase-mediated conversion of glucose to glucose-6-phosphate.")))
(as-inverse
 (defoproperty affects
   :label "affects"
   :super has_output)
 (defoproperty is_affected_by
   :label "is affected by"
   :super is_participant_in))
(as-inverse
 (defoproperty is_realized_in
   :label "is realized in"
   :super is_participant_in
   :annotation
   (eg "the role of a patient is realized in medical examination"))
 (sio-oproperty "realizes" "realizes is a relation between a process and a realizable entity (role, function, disposition)."
                :super has_output
                :annotation
                core))
(as-inverse
 (sio-oproperty "is causally related to" "a is causally related to b iff there is a causal chain of events from a to b"
                :super is_causally_related_with)
 (sio-oproperty "is causally related from" "a is causally related from b iff there is a causal chain of events from b to a"
                :super is_causally_related_with
                :annotation
                core))
(as-inverse
 (sio-oproperty "derives into" "a derives to b if and only if a or some part thereof is consumed in the formation of b."
                :super is_causally_related_to
                :domain object
                :range object
                :characteristic :transitive)
 ;; NOTE should be derived_from -> is_derived_from
 ;; HYPERLINK BROKEN
 (sio-oproperty "is derived from" "A transitive temporal relation in which one entity was materially formed from another non-identical entity."
                :super is_causally_related_from
                :characteristic :transitive
                :annotation
                core
                (equivalent-rdf "OBO_REL:derives_from")))
(as-inverse
 (defoproperty is_regulated_by
   :label "is regulated by"
   :super is_causally_related_from)
 (sio-oproperty "regulates" "x regulates y if and only if x is a process and y is either a process or a quality, and the progression of x exerts an effect on the frequency, rate or extent of y"
                :super is_causally_related_to))
(as-inverse
 (defoproperty is_preceded_by
   :label "is preceded by"
   :super is_causally_related_to
   :characteristic :transitive
   :annotation
   (equivalent-rdf "OBO_REL:preceded_by"))
 (sio-oproperty "precedes" "A transitive, temporal relation in which one process precedes (has occured earlier than) another process."
                :super is_causally_related_from
                :characteristic :transitive
                :annotation
                core))
(as-inverse
 (defoproperty results_in
   :label "results in"
   :super has_output is_causally_related_to)
 (defoproperty is_result_of
   :label "is result of"
   :super is_causally_related_from))
(as-inverse
 (sio-oproperty "is transformed from" "A transitive temporal relation in which an entity mainstains identity from one state to another."
                :super is_causally_related_from
                :characteristic :transitive)
 (sio-oproperty "transforms into" "A transitive temporal relation in which an entity mainstains identity from one state to another."
                :super is_causally_related_to
                :characteristic :transitive))
(as-inverse
 (defoproperty is_trigger_for
   :label "is trigger for"
   :super is_causally_related_from)
 (sio-oproperty "has trigger" "has trigger is a relation between a realizable and the factor that causes it to be realized."
                :super is_causally_related_to))
(as-inverse
 (sio-oproperty "is immediately derived from" "A non-transitive temporal relation in which one entity is immediately derived from a non-identical entity such that there are no intermediate entities between them."
                :super is_derived_from)
 (sio-oproperty "immediately derives into" "a immediately derives into b if and only if a or some significant part thereof is consumed in the formation of b and there are no identifiable intermediate entities between a and b."
                :super derives_into))
(as-inverse
 (sio-oproperty "is subsequent version of" "A is subsequent version of B iff A is a variant of B and A derives from B."
                :super is_version_of)
 (sio-oproperty "is prior version of" "A is prior version of B iff A is a variant of B and B derives from A."
                :super is_derived_from is_version_of))
(as-inverse
 (sio-oproperty "is immediately transformed from" "A  temporal relation in which an entity mainstains identity from one state to another."
                :super is_transformed_from)
 (sio-oproperty "immediately transforms into" "A  temporal relation in which an entity mainstains identity from one state to another."
                :super transforms_into))
(as-inverse
 (sio-oproperty "is immediately preceded by" "A non-transitive temporal relation in which one process is immediately preceded by another process, such that there is no interval of time between the two processes."
                :super is_preceded_by)
 (sio-oproperty "immediately precedes" "A non-transitive temporal relation in which one process immediately precedes another process, such that there is no interval of time between the two processes."
                :super precedes))
(as-inverse
 (sio-oproperty "has part" "has part is a transitive, reflexive and antisymmetric relation between a whole and itself or a whole and its part."
                :super is_location_of
                :characteristic :transitive :reflexive
                :annotation
                (eg "a collection of cars has as a car as a part;a car has an engine as a part; a car has a piston as a part;"))
 (sio-oproperty "is part of" "is part of is a transitive, reflexive and anti-symmetric mereological relation between a whole and itself or a part and its whole."
                :super is_located_in
                :characteristic :transitive :reflexive
                :annotation
                core
                (equivalent-rdf "OBO_REL:part_of")))
(add-subchain overlaps_with [overlaps_with is_part_of])
(as-inverse
 (sio-oproperty "is encoded by" "A relation between two objects, in which the first object is produced from the information contained in the second object."
                :super is_specified_by)
 (sio-oproperty "encodes" "A relation between two objects, in which the first object contains information that is used to produce the second object."
                :super specifies
                :domain (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))
                :range (owl-or ribonucleic_acid lipid protein (owl-some is_part_of ribonucleic_acid) (owl-some is_part_of lipid) (owl-some is_part_of protein))
                :annotation
                (eg "an open reading frame encodes a protein (it contains information that specifies the amino acid composition and topology)")))
(as-inverse
 (sio-oproperty "is transcribed into" "a relation between two information content entities in which one is transcribed into (an exact or similar kind) another through some process."
                :super encodes
                :domain (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))
                :range (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
                :annotation
                (eg "DNA is transcribed into RNA"))
 (defoproperty is_transcribed_from
   :label "is transcribed from"
   :super is_encoded_by
   :domain (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
   :range (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))))
(as-inverse
 (defoproperty is_translated_from
   :label "is translated from"
   :super is_encoded_by
   :range (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid)))
 (defoproperty is_translated_into
   :label "is transcribed into"
   :super encodes
   :domain (owl-or ribonucleic_acid (owl-some is_part_of ribonucleic_acid))
   :range polypeptide
   :annotation
   (eg "a relation between two information content entities in which one is translated into (a completely different kind of entity) another through some process.")
   (eg "RNA is translated into Protein")))
(as-inverse
 (sio-oproperty "has proper part" "has proper part is an antisymmetric, irreflexive (normally transitive) relation between a whole and a distinct part."
                :super has_part
                :characteristic :asymmetric :irreflexive
                :annotation
                (eg "a car has an engine as a proper part; a car has a piston as a proper part;"))
 (sio-oproperty "is proper part of" "is proper part of is an asymmetric, irreflexive (normally transitive) relation between a part and its distinct whole."
                :super is_part_of
                :characteristic :asymmetric :irreflexive
                :annotation
                core
                (equivalent-rdf "OBO_REL:proper_part_of")))
(as-inverse
 (sio-oproperty "has direct part" "has direct part is a relation to specify a part at a particular level of granularity"
                :super has_proper_part
                :annotation
                (eg "a car has an engine as a direct part"))
 (sio-oproperty "is direct part of" "is direct part of is a relation between a specific part (at some level of granularity) and its whole."
                :super is_proper_part_of
                :annotation
                core))
(as-inverse
 (sio-oproperty "has boundary" "has boundary is a mereological relation between a whole and boundary located at its periphery."
                :super has_proper_part
                :annotation
                (eg "the surface of an apple is a 2D boundary of the apple."))
 (sio-oproperty "is boundary of" "is boundary of is a mereological relation between a boundary located at the periphery of a whole."
                :super is_proper_part_of))
;; missing inverse???
(sio-oproperty "has ordered part" "has ordered part of is a proper part relation in which the part is one of a set of linearly ordered parts resulting from the projection of the whole on a linear coordinate system."
               :super has_proper_part)
(sio-oproperty "is ordered part of" "is ordered part of is a proper part relation in which the part is one of a set of linearly ordered parts resulting from the projection of the whole on a linear coordinate system."
               :super is_proper_part_of)
(as-inverse
 (sio-oproperty "has first part" "has first part is a proper part relation in which the part is the first of a set of linearly ordered parts resulting from the projection of the whole on a linear coordinate system."
                :super has_ordered_part)
 (sio-oproperty "is first part of" "is first part of is a proper part relation in which the part is the first of a set of linearly ordered parts resulting from the projection of the whole on a linear coordinate system."
                :super is_ordered_part_of))
(as-inverse
 (sio-oproperty "has last part" "has last part is a proper part relation in which the part is the last of a set of linearly ordered parts resulting from the projection of the whole on a linear coordinate system."
                :super has_ordered_part)
 (sio-oproperty "is last part of" "is last part of is a proper part relation in which the part is the last of a set of linearly ordered parts resulting from the projection of the whole on a linear coordinate system."
                :super is_ordered_part_of))
(as-inverse
 (sio-oproperty "has component part" "has component part is a relation between a whole and a component part where the component is instrinsic to the whole, and loss of the part would change the kind that it is."
                :super has_direct_part
                :annotation
                (eg "a human has blood as a component part"))
 (sio-oproperty "is component part of" "is component part of is a relation between a component and a whole, where the component is instrinsic to the whole, and loss of the part would change the kind that the whole is."
                :super is_direct_part_of
                :annotation
                core))
(as-inverse
 (sio-oproperty "is contained in" "A is contained in B iff the spatial region occupied by A is part of the spatial region occupied by B and A is not part of B. [T]"
                :super is_located_in
                :characteristic :transitive
                :annotation
                (equivalent-rdf "OBO_REL:contained_in")
                core)
 (sio-oproperty "contains" "A contains B iff the spatial region occupied by A has the spatial region occupied by B as a part, and B is not part of A. [T]"
                :super is_location_of
                :characteristic :transitive))
(as-inverse
 (sio-oproperty "is surrounded by" "A 'is surrounded by' B iff the A 'is contained by' B and A 'is adjacent to' B or A 'is directly connected to' B."
                :super is_contained_in)
 (sio-oproperty "surrounds" "A 'surrounds' B iff the A 'contains' B and A 'is adjacent to' B or A 'is directly connected to' B."
                :super contains))
(as-inverse
 (defoproperty is_time_boundary_of
   :label "is boundary of"
   :super is_boundary_of
   :domain time_instant)
 (defoproperty has_time_boundary
   :label "has boundary"
   :super has_boundary
   :range time_instant
   :characteristic :functional))
(as-inverse
 (defoproperty has_start_time
   :label "has time boundary"
   :super has_time_boundary
   :range time_instant
   :characteristic :functional)
 (defoproperty is_start_time_of
   :label "is start time of"
   :super is_time_boundary_of
   :domain time_instant))
(as-inverse
 (defoproperty has_end_time
   :label "has end time"
   :super has_time_boundary
   :range time_instant
   :characteristic :functional)
 (defoproperty is_end_time_of
   :label "is end time of"
   :super is_time_boundary_of
   :domain time_instant))
(as-inverse
 (sio-oproperty "is positionally before" "is positionally before is a relation between entities placed on a dimensional axis in which the projection of the position of the first entity is numerically less than the projection of the position of the second entity."
                :super is_connected_to
                :characteristic :transitive)
 (sio-oproperty "is positionally after" "is positionally after is a relation between entities placed on a dimensional axis in which the projection of the position of the first entity is numerically greater than the projection of the position of the second entity."
                :super is_connected_to
                :characteristic :transitive))
(sio-oproperty "is directly connected to" "A is directly connected to B iff there exists a path direclty between A and B."
               :super is_connected_to
               :characteristic :symmetric)
(defoproperty is_covalently_connected_to__transitive_
  :label "is covalently connected to (transitive)"
  :super is_connected_to
  :domain atom
  :range atom
  :characteristic :transitive :symmetric)
(sio-oproperty "is covalently connected to" "is covalently connected to is a relation between an atom and another atom."
               :super is_covalently_connected_to__transitive_ is_directly_connected_to
               :characteristic :symmetric)
(sio-oproperty "is weakly interacting with (transitive)" "is weakly interacting with is a symmetric relation between two molecular entities (or any part thereof) that are interacting through some weak force (van der waals, hydrogen bonds, electrostatic interactions)"
               :super is_connected_to
               :domain (owl-or molecule (owl-some is_part_of molecule))
               :range (owl-or molecule (owl-some is_part_of molecule))
               :characteristic :transitive :symmetric)
(defoproperty is_weakly_interacting_with
  :label "is weakly interacting with"
  :super is_directly_connected_to is_weakly_interacting_with__transitive_
  :characteristic :symmetric)
(as-inverse
 (sio-oproperty "is directly after" "is directly after is a relation between entities placed on a dimensional axis in which the projection of the position of the first entity is numerically greater than the projection of the position of the second entity, and the entities are adjacent to one another."
                :super is_positionally_after is_directly_connected_to
                :characteristic :functional)
 (sio-oproperty "is directly before" "is directly before is a relation between entities placed on a dimensional axis in which the projection of the position of the first entity is numerically less than the projection of the position of the second entity, and the entities are adjacent to one another."
                :super is_positionally_before is_directly_connected_to
                :characteristic :functional))