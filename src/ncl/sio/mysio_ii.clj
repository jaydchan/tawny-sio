(sio-class "entity" "Every thing is an entity."
           :annotation
           core)

(as-disjoint-subclasses
 entity
 (sio-class "attribute" "an attribute is a characteristic of some entity."
            :super (owl-some is_attribute_of entity)
            :annotation
            core)
 (sio-class "object" "An object is an entity that is wholly identifiable at any instant of time during which it exists."
            :super (only has_proper_part object)
            :annotation
            core)
 (sio-class "process" "A process is an entity that is identifiable only through the unfolding of time, has temporal parts, and unless otherwise specified/predicted, cannot be identified from any instant of time in which it exists."
            :super (only has_proper_part process)
            :annotation
            core
            (subset-rdf "process+")))

(as-disjoint-subclasses
 attribute
 (sio-class "quality" "A quality is an attribute that is intrinsically associated with its bearer (or its parts), but whose presence/absence and observed/measured value may vary."
            :super (owl-some is_quality_of entity)
            :annotation
            core)
 (sio-class "realizable entity" "A realizable entity is an attribute that is exhibited under some condition and is realized in some process."
            :super (owl-some is_realizable_property_of entity) (only in_relation_to entity)
            :annotation
            core))

(as-subclasses
 quality
 (sio-class "assertional qualifier" "an assertional qualifier is the quality of affirmation, either being positive or negative.")
 (sio-class "existence quality" "existence quality is the quality of an entity that describe in what environment it is known to exist.")
 (sio-class "informational quality" "an informational quality is a quality that pertains to information.")
 (sio-class "intensity" "intensity is a quality that represents the strength or degree of something.")
 (sio-class "normality" "normality is the quality in which the value may differ from normal or average")
 (sio-class "object quality" "An object quality is quality of an object."
            :super (owl-some is_quality_of object))
 (sio-class "process quality" "A process quality is quality that is associated with a process."
            :super (owl-some is_quality_of process)))

(as-disjoint-subclasses
 assertional_qualifier
 :cover
 (sio-class "negative" "negative is an assertional qualifier that expresses the falsity or lack of truth of a basic assertion.")
 (sio-class "positive" "positive is an assertional qualifier that expresses the validity or truth of a basic assertion."))

(as-subclasses
 existence_quality
 (sio-class "hypothetical" "hypothetical is the quality of an entity that is conjectured to exist.")
 (sio-class "real" "real is the quality of an entity that exists in real space and time.")
 (sio-class "unsupported" "unsupported is an existence quality in which there is no evidence to support the existence of the entity in any world (real or hypothetical)")
 (sio-class "virtual" "virtual is the quality of an entity that exists only in a virtual setting such as a simulation or game environment."))

(sio-class "fictional" "fictional is the quality of an entity that exists only in a creative work of fiction."
           :super hypothetical)
(as-disjoint real fictional virtual)
(sio-class "predicted" "predicted is the quality of an entity that is thought to exist, as evidenced by some rational procedure."
           :super hypothetical)

(sio-class "agreement quality" "agreement quality is a quality that exhibits the degree of consensus for some set of assertions."
           :super informational_quality)
(sio-class "truth value" "truth value is a quality of information that is claimed/verified to be true or false."
           :super informational_quality)

(as-disjoint-subclasses
 agreement_quality
 :cover
 (sio-class "agreement" "agreement is the result of consensus decision making when members of the group agree.")
 (sio-class "disagreement" "agreement is the result of consensus decision making when members of the group do not all agree."))

(sio-class "consensus" "consensus is an acceptable resolution, one that can be supported, even if not the preferred outcome for each individual."
           :super agreement)
(sio-class "full agreement" "agreement is the result of consensus decision making when members of the group unanimously agree."
           :super agreement)

(sio-class "full disagreement" "agreement is the result of consensus decision making when members of the group unanimously disagree."
           :super disagreement)

(as-disjoint-subclasses
 truth_value
 :cover
 (sio-class "true" "true is a truth value that indicates that it holds under all possible worlds."
            :super (has-value has_value (literal "true" :type :XSD_BOOLEAN)))
 (sio-class "false" "false is a truth value in that indicates that it is not true."
            :super (has-value has_value (literal "false" :type :XSD_BOOLEAN))))

(as-disjoint-subclasses
 intensity
 (sio-class "fatal" "fatal is a qualitative intensity value that is more intense than severe, leading to the death/non-functioning of a system.")
 (sio-class "mild" "mild is a qualitative intensity value that is more intense than weak, but less intense than moderate.")
 (sio-class "moderate" "moderate is a qualitative intensity value that is more intense than mild, but less intense than strong.")
 (sio-class "severe" "severe is a qualitative intensity value that is more intense than strong, but less intense than fatal.")
 (sio-class "strong" "strong is a qualitative intensity value that is more intense than moderate, but less intense than severe.")
 (sio-class "weak" "weak is a qualitative intensity value that is more intense than none, but less intense than mild."))

(as-disjoint-subclasses
 normality
 :cover
 (sio-class "abnormal" "A quality that has a value that is outside normal or average.")
 (sio-class "normal" "A quality that has a value that is normal or average."
            :annotation
            (equivalent-rdf "PATO:0000461")))

(as-disjoint-subclasses
 abnormal
 (sio-class "decreased" "A quality that has a value that is decreased compared to normal or average."
            :annotation
            (equivalent-rdf "PATO:0002301"))
 (sio-class "increased" "A quality that has a value that is increased compared to normal or average."
            :annotation
            (equivalent-rdf "PATO:0002300")))

(as-subclasses
 object_quality
 (sio-class "biological quality" "a biological quality is a quality held by a biological entity.")
 (sio-class "chemical quality" "chemical quality is the quality of a chemical entity.")
 (sio-class "compositional quality" "composition quality is a quality that describes its composition or anatomy.")
 (sio-class "fitness" "fitness is the quality of an object with respect to some stated functions or evolutionary adaptation.")
 (sio-class "shape" "shape is the quality of a bearer that relates to its spatial extent.")
 (sio-class "structural quality" "a structural quality is a quality of an object that describes its structure.")
 (sio-class "text quality" "text quality is the quality of a textual entity."))

(as-disjoint-subclasses
 biological_quality
 (sio-class "biological sex" "biological sex is the quality of a biological organism based on reproductive function or organs.")
 (sio-class "cellular quality" "cellular quality is the quality of a cell")
 (sio-class "disease" "disease is the outward manifestation of one or more disorders.")
 (sio-class "ethnicity" "ethnicity is the biological quality of membership in a social group based on a common heritage.")
 (sio-class "life status" "life status is the quality of whether something is alive or dead.")
 (sio-class "phenotype" "a phenotype is an observable characteristic of an individual.")
 (sio-class "race" "race is a characteristic of an individual by heritable phenotypic characteristics, geographic ancestry, physical appearance, ethnicity, and social status."))

;; FEMALE IS MISSING PATO see-also???
(as-disjoint-subclasses
 biological_sex
 (sio-class "female" "female is a biological sex of an individual with female sexual organs.")
 (sio-class "hermaphrodite" "hermaphrodite is a biological sex of an individual with both male and female sexual organs."
            :annotation
            (equivalent-rdf "PATO:0001827"))
 (sio-class "male" "male is a biological sex of an individual with male sexual organs."
            :annotation
            (equivalent-rdf "PATO:0000384")))

(sio-class "ploidy" "Ploidy is the cellular quality relating to the amount of DNA contained in a cell."
           :super cellular_quality)

(as-disjoint-subclasses
 life_status
 :cover
 (sio-class "alive" "alive is the state of a biological organism that exhibits biological functions.")
 (sio-class "dead" "dead is the quality of an object in which there is a cessation of all biological functions."))

(as-subclasses alive
 (sio-class "healthy" "healthy is an organismal state of complete physical, mental and social well-being.")
 (sio-class "sick" "sick is the status of a living organism that is behaving at a sub-optimal level."))

(as-subclasses
 chemical_quality
 (sio-class "charge quality" "charge quality is the quality pertaining to electric charge.")
 (sio-class "polar quality" "the quality of being polar or not polar.")
 (sio-class "toxicity" "toxicity is the quality of a chemical substance to cause injury to an organism in a dose dependent manner."))

(as-disjoint-subclasses
 charge_quality
 :cover
 (sio-class "charged" "The quality of having a charge")
 (sio-class "uncharged" "the quality of not having a charge"))

(sio-class "complete charge" "a complete charge is a charge where the value of the charge is a multiple of 1."
           :super charged)
(sio-class "partial charge" "the quality of having a charge that is not a full multiple of 1 unit charge."
           :super charged)

(as-disjoint-subclasses
 complete_charge
 :cover
 (sio-class "negative charge" "a negative charge is a charge where the value is negative.")
 (sio-class "positive charge" "a positive charge is a charge where the value is positive."))

(as-disjoint-subclasses
 partial_charge
 :cover
 (sio-class "partial positive charge" "a partial negative charge is a negative charge where the value of the charge is negative.")
 (sio-class "partial negative charge" "a partial positive charge is a partial charge where the value of the charge is positive."))

(as-disjoint-subclasses
 polar_quality
 :cover
 (sio-class "non-polar" "non-polar is the quality of not having a dipole.")
 (sio-class "polar" "polar is the quality of having a dipole."))

(as-disjoint-subclasses
 toxicity
 :cover
 (sio-class "toxic" "toxic is the quality of a substance imparing the normal functioning of a  system.")
 (sio-class "non toxic" "toxic is the quality of a substance having no damaging effect to a system."))

(as-disjoint-subclasses
 compositional_quality
 (sio-class "homogeneous" "homogeneous is a quality that describes the uniform composition of an object.")
 (sio-class "heterogeneous" "homogeneous is a quality that describes the varied composition of an object."))

(sio-class "curvature" "curvature is a quality of a bearer that relates to the presence of curves, bends, or angles."
           :super shape)

;; PATO again?
(as-disjoint-subclasses
 curvature
 (sio-class "straight" "shape is a quality of a bearer that is free of curves, bends, or angles."
            :annotation
            (annotation (iri "http://www.w3.org/2000/01/rdf-schema#seeAlso") (literal "PATO:0002180 [for material entities]" :type :RDF_PLAIN_LITERAL)))
 (sio-class "bent" "bent is the quality of a line being sharply curved or having an angle.")
 (sio-class "curved" "curved is the quality of a line that deviates from straightness in a smooth, continuous fashion."))

(as-disjoint-subclasses
 structural_quality
 (sio-class "disordered" "disordered is a structural quality in which the parts of an object are non-rigid."
            :super (owl-some is_quality_of (owl-and entity (owl-some has_quality abnormal))))
 (sio-class "rigid" "rigid is the quality of maintaining structural integrity (and not bending) under pressure."))

(sio-class "written" "written is the quality of information that is embodied as visual glyphs in some material form."
           :super text_quality)

(as-subclasses
 written
 (sio-class "draft" "draft is the quality of text that has not yet complete."
            :annotation
            (synonym-rdf "drafted"))
 (sio-class "finalized" "finalized is the quality of a textual entity that is in its final form.")
 (sio-class "reviewed" "reviewed is the quality of a textual entity that has been examined and commented on by another party."))

(as-disjoint-subclasses
 reviewed
 (sio-class "peer-reviewed" "reviewed is the quality of a textual entity that has been examined and commented by a peer expert reviewer.")
 (sio-class "editor reviewed" "reviewed is the quality of a textual entity that has been examined and commented on by an editor."))

(sio-class "process status" "process status is a process quality that describes the state of a process."
           :super process_quality (owl-some is_attribute_of process))

(as-disjoint-subclasses
 process_status
 (sio-class "aborted" "aborted is a process status in which a started process will not complete as intended.")
 (sio-class "completed" "completed is that status of a process that successfully unfolds.")
 (sio-class "ongoing" "ongoing is the status of a process that is not yet complete.")
 (sio-class "suspended" "suspended is the status of a process that is no longer progressing towards completion.")
 (sio-class "not started" "not started is the status of a process that is predicted to exist but has not yet begun."))

(sio-class "cancelled" "cancelled is a process status in which the process, while planned to occur, will not occur."
           :super not_started)
(sio-class "planned" "planned is a process status for a process that has not yet started, but is referred to in a plan."
           :super not_started)

(sio-class "capability" "A capability is a realizable entity whose basis lies in one or more parts or qualities and reflects possility of an entity to act in a specified way under certain conditions or in response to a certain stimulus (trigger)."
           :super realizable_entity (owl-some is_attribute_of (owl-some is_agent_in (owl-and process (has-self realizes)))) (owl-some has_basis quality) (only has_trigger process)
           :annotation
           core
           (subset-rdf "capability+"))
(sio-class "role" "A role is a realizable entity that describes behaviours, rights and obligations of an entity in some particular circumstance."
           :super realizable_entity
           :annotation
           core)

(as-subclasses
 capability
 (sio-class "disposition" "A disposition is the tendency of a capability to be exhibited under certain conditions or in response to a certain stimulus (trigger)"
            :annotation
            (eg "solubility (to dissolve when put in fluid); fragility (disposition to break when dropped)")
            core)
 (sio-class "function" "A function is a capability that satisfies some agentive objective, or (evolutionary) optimization."
            :annotation
            core)
 (sio-class "dysfunction" "dysfunction is a capability to act in a manner that is abnormal or opposite  to the object's typical function.")
 (sio-class "to be interacted with" "to be interacted with is the capability of an object to be target of a physical interaction."
            :super (owl-some in_relation_to entity))
 (sio-class "to breathe" "to breathe is the capability to inhale and exhale air into the body during respiration.")
 (sio-class "to interact with" "to interact with is a capabililty that involves another object."
            :super (owl-some in_relation_to entity)))
(as-disjoint function dysfunction)

(sio-class "mutual disposition" "a mutual disposition is a disposition that simulataneously invokes another disposition when realized."
           :super disposition)

(sio-class "to interact and to be interacted with" "to interact and to be interacted with is a mutual disposition of interacting objects."
           :super mutual_disposition (owl-and (owl-some has_proper_part to_interact_with) (owl-some has_proper_part to_be_interacted_with)))

(as-disjoint-subclasses
 to_be_interacted_with
 (sio-class "to be actively interacted with" "to be actively interacted with is the capability to be manipulated by some device or agent.")
 (sio-class "to be passively interacted with" "to be passively interacted with is the capability of an object to be observed."))
(sio-class "to be translocated" "to be translocated is the capability to be physically displaced from one location to another"
           :super to_be_interacted_with
           :annotation
           (synonym-en "to be moved"))

(as-subclasses
 to_be_actively_interacted_with
 (sio-class "to be modified" "to be modified is the capability to be actively interacted with in such a way that it leads to a physical reconfiguration.")
 (sio-class "to be transported" "to be transported is the disposition to undergo motion.")
 (sio-class "to luminesce" "to luminesce is to emit light through cold body radiation"))

(as-subclasses
 to_be_modified
 (sio-class "to be cleaved" "to be cleaved is the capability to be modified in a way that splits one part of the object from the other.")
 (sio-class "to be combined" "to be combined is the capability to be modified in a way that the object is merged with another object to form a new object or substance.")
 (sio-class "to be conformationally changed" "to be conformationally changed is the capability to be modified in such a way that the object's conformation is changed.")
 (sio-class "to be covalently modified" "to be covalently modified is the capability of a chemical entity to have bonds added or removed")
 (sio-class "to be electronically modified" "to be electronically modified is the capability of a chemical entity to have electrons added or removed"))

(sio-class "to be a part of" "to be a part of is the capability to be assembled into a larger structure that persists in time."
	:super to_be_combined)

(sio-class "to be activated" "to be activated is the capability to be modified in such a way that the conformational change leads to an increase in another capability."
	:super to_be_conformationally_changed)
(sio-class "to be inhibited" "to be inhibited is the capability to be modified in such a way that the conformational change leads to an decrease in another capability."
	:super to_be_conformationally_changed)

(sio-class "to lose a covalent bond" "to lose a covalent bond is the capability of a chemical entity to have bonds removed."
	:super to_be_covalently_modified)
(sio-class "to gain a covalent bond" "to gain a covalent bond is the capability of a chemical entity to have bonds added."
	:super to_be_covalently_modified)

(sio-class "to gain an electron" "to gain an electron is the capability of a chemical entity to receive an electron."
	:super to_be_electronically_modified)
(sio-class "to lose an electron" "to lose an electron is the capability of a chemical entity to lose an electron."
	:super to_be_electronically_modified)

(sio-class "to fluoresce" "to fluoresce is to emit light as a result of absorbing light or other electromagnetic radiation."
	:super to_luminesce)

(sio-class "to be observed" "to be observed is the capability of an object to be perceived."
	:super to_be_passively_interacted_with)

(sio-class "to be examined" "to be examined is the capability of an object to be observed in a detailed manner."
	:super to_be_observed)
(sio-class "to be recorded" "to be recorded is the capability of an object to be observed in such a way that information about it can be transcribed in a specified format on some physical medium."
	:super to_be_observed)

(sio-class "to be compared" "to be compared is the capability of an object to be examined in order to note the similarities or differences among a set of objects."
	:super to_be_examined)

(as-subclasses
 to_interact_with
 (sio-class "to actively interact with" "to actively interact with is the capability to interact with another entity in a way that requires physical contact.")
 (sio-class "to passively interact with" "to passively interact with is the capability to interact with another entity in a way that does not require physical contact.")
 (sio-class "to translocate" "to translocate is the capability to displace oneself from one location to another."
            :super (owl-some is_mutual_disposition_of to_be_translocated)
            :annotation
            (synonym-en "to move")))
(as-disjoint to_be_translocated to_translocate)

(as-subclasses
 to_actively_interact_with
 (sio-class "to assemble" "to assemble is the capability to combine entities together into a larger object that persists in time.")
 (sio-class "to associate" "To associate is the capability to physically interact with another object.")
 (sio-class "to consume" "to consume is the capability to internalize a material entity.")
 (sio-class "to investigate" "to investigate is the capability to uncover facts.")
 (sio-class "to modify" "to modify is the capability to change some entity.")
 (sio-class "to produce" "to produce is the capability to create new objects")
 (sio-class "to provide" "to provide is the capability to make available some object to another that requires it.")
 (sio-class "to serve as" "to serve as is the capability to act in a manner corresponding to some role.")
 (sio-class "to transport" "to transport is the capability to displace a material from one location to another."))

(sio-class "to bind to" "to bind to is the capability to physically interact with another object through a set of non-covalent interactions."
	:super to_associate)

(sio-class "to ingest" "to ingest is the capability to take into the body by the mouth for digestion or absorption"
	:super to_consume)

(sio-class "to identify" "to identify is the capability to determine the identity of something."
	:super to_investigate)
(sio-class "to measure" "To measure is the capability to obtain information about some entity by examining its attributes in relation to some reference metric."
	:super to_investigate)

(as-subclasses
 to_modify
 (sio-class "to change energetically" "to change energetically is the capability to change the energetic aspects of an object.")
 (sio-class "to change materially" "to change appearance is the capability to change the material composition of an object.")
 (sio-class "to change appearance" "to change appearance is the capability to change the visual attributes of an object.")
 (sio-class "to change spatially" "to change spatially is the capability to affect the physical movement of some entity.")
 (sio-class "to disassociate" "to disassociate is to cease or break association with some thing.")
 (sio-class "to modify conformation of" "to modify conformation of is to affect the spatial arrangement of an entity.")
(sio-class "to separate" "To separate is the capability to i) distinguish some entities based on some attribute(s) and ii) subsequently physically displace them.")
(sio-class "to inject" "To inject is the capability to administer a substance into some object through its external barrier.")
(sio-class "to regulate" "to regulate is to control or maintain the rate or speed of an object or process."
	:annotation
	(synonym-en "to modulate")))

(as-subclasses
 to_change_appearance
 (sio-class "to demagnify" "To demagnify is the capability to decrease the appearance of the size of an object.")
 (sio-class "to distort" "to distort is the capability to change the appearance of an entity by some transformation.")
 (sio-class "to magnify" "To magnify is the capability to increase the appearance of the size of an object."))

(as-subclasses
 to_change_energetically
 (sio-class "to change the activation energy" "to change the activation energy is to change the amount of energy required to form or break a chemical bond.")
 (sio-class "to modify electronically" "to modify electronically is the capability to change the electronic properties of an object.")
 (sio-class "to reduce energy" "To reduce energy is the capability to remove energy from a source.")
 (sio-class "to supply energy" "To supply energy is the capability to transfer energy from a source to a sink."))

(as-disjoint-subclasses
 to_change_the_activation_energy
 (sio-class "to increase the activation energy" "to increase the activation energy is to require a larger amount of energy in order to form or break a chemical bond."
            :annotation
            (synonym-rdf "to inhibit"))
(sio-class "to reduce the activation energy" "to reduce the activation energy is to require a smaller amount of energy in order to form or break a chemical bond."
           :annotation
           (synonym-rdf "to activate")))

(sio-class "to ionize" "To ionize is the capability to physically convert an atom or molecule into an ion by adding or removing charged particles such as electrons or other ions."
	:super to_modify_electronically)
(sio-class "to modify oxidation state of" "to modify the oxidation state of is to change the number of electrons of a molecule, atom or ion."
	:super to_modify_electronically)

(sio-class "to negatively charge" "to negatively charge is the capability to add an electron or negatively charged ion to a chemical entity."
	:super to_ionize)
(sio-class "to positively charge" "to positively charge is the capability to remove an electron or add a  positively charged ion to a chemical entity."
	:super to_ionize)

(sio-class "to oxidize" "to oxidize is the capability to remove an electron or an increase in oxidation state of a chemical entity."
	:super to_modify_oxidation_state_of)
(sio-class "to reduce" "to reduce is the capability to add an electron or an decrease in oxidation state of a chemical entity."
	:super to_modify_oxidation_state_of)

(sio-class "to cool" "To cool is the capability to decrease the internal kinetic energy of a material."
	:super to_reduce_energy
	:annotation
	(see-also-uri "http://purl.obolibrary.org/obo/OBI_0000387"))
(sio-class "to emit" "To emit is the capability to release some physical entity (light, pollution, etc)."
	:super to_reduce_energy)

(sio-class "to freeze" "To freeze is the capability to decrease the internal kinetic energy of a material such that it changes state from a gas or liquid to a solid."
	:super to_cool
	:annotation
	(see-also-uri "http://purl.obolibrary.org/obo/OBI_0000375"))

(as-subclasses
 to_supply_energy
 (sio-class "to excite" "To excite is the capability to supply energy to a materila by bombarding it with energetic particles (e.g., photons).")
 (sio-class "to heat" "To heat is a capability to increase the internal kinetic energy of a material.")
 (sio-class "to supply electricity" "To supply electricity is the capability to transfer electricity from a source to a sink."))

(sio-class "to boil" "To boil is the capability to increase the internal kinetic energy of a material such that it changes state from a solid or liquid to a gas."
	:super to_heat)

(as-subclasses
 to_change_materially
 (sio-class "to cause disease" "to cause disease is the capability to materially change a biological object in that it functions abnormally.")
 (sio-class "to combine" "to combine is the capability to modify a set of objects in a way that the object is merged with another object to form a new object or substance.")
 (sio-class "to covalently modify" "to covalently modify is to materially change a molecule by adding or removing covalent bonds between atoms.")
 (sio-class "to disassemble" "to disassemble is to physically separate the parts of an object."))

(sio-class "to add a covalent bond" "to add a covalent bond is the capability to covalently modify a chemical entity by adding a covalent bond."
	:super to_covalently_modify)
(sio-class "to remove a covalent bond" "to remove a covalent bond is the capability to covalently modify a chemical entity by removing a covalent bond."
	:super to_covalently_modify)

(sio-class "to cleave" "to cleave is to split or sever an object along a natural line or grain."
	:super to_disassemble)

(sio-class "to contain" "To contain is the capability to bound or constrain a physical entity in some site."
	:super to_change_spatially)

(sio-class "to immobilize" "To immobilize is the capability to contain an entity in such a way that it may not move in space."
	:super to_contain)

(sio-class "to infect" "To infect is the capability to administer a disease-causing organism into some object."
	:super to_inject)

(sio-class "to conformationally activate" "to conformationally activate is to modify the conformation of an entity in such a way that it becomes activated or functional."
	:super to_modify_conformation_of)
(sio-class "to conformationally inhibit" "to conformationally inhibit is to modify the conformation of an entity in such a way that it functionally is reduced or inhibited."
	:super to_modify_conformation_of)

(sio-class "to regulate the rate of formation" "to regulate the rate of formation is to modify the rate at which an object is formed."
	:super to_regulate)

(sio-class "to decrease the rate of formation" "to decrease the rate of formation is to regulate the rate of formation in a manner that decreases this rate relative to a reference process."
	:super to_regulate_the_rate_of_formation)
(sio-class "to increase the rate of formation" "to increase the rate of formation is to regulate the rate of formation in a manner that increases this rate relative to a reference process."
	:super to_regulate_the_rate_of_formation)

(sio-class "to extract" "To extract is the capability to remove certain entities based on selected attribute(s) while allowing other entities to remain."
	:super to_separate)
(sio-class "to filter" "To filter is the capability to retain certain entities based on selected attribute(s) while allowing other entities to pass through."
	:super to_separate)

(as-subclasses
 to_serve_as
 (sio-class "to serve as a host" "to serve as host is the capability to act in a manner that provides hospitality, serves to harbour an organism in or on itself.")
 (sio-class "to serve as a primer for DNA synthesis" "to serve as a primer for DNA synthesis is the capability of a short nucleic acid to bind to the 5' end of single strand of DNA template and help initiate DNA replication.")
 (sio-class "to serve as a template for molecular synthesis" "to serve as a template for molecular synthesis is the capability of a chemical entity to provide the necessary information or scaffold by which another molecule may be produced."))

(as-subclasses
 to_serve_as_a_template_for_molecular_synthesis
 (sio-class "to serve as a template for protein synthesis" "to serve as a template for protein synthesis is the capability of a chemical entity to provide the necessary information or scaffold by which a protein may be produced.")
 (sio-class "to serve as a template for RNA synthesis" "to serve as a template for RNA synthesis is the capability of a chemical entity to provide the necessary information or scaffold by which an RNA molecule may be produced.")
 (sio-class "to serve as a template for DNA synthesis" "to serve as a template for DNA synthesis is the capability of a chemical entity to provide the necessary information or scaffold by which a DNA molecule may be produced."))

(as-subclasses
 to_passively_interact_with
 (sio-class "to be a member of" "to be a member of is the capability to be considered a part of a collection.")
 (sio-class "to describe" "to describe is the capabilty to communicate facts about an entity.")
 (sio-class "to observe" "to observe is the capability to watch attentively.")
 (sio-class "to record" "To record is the capability to detect and transcribe information in a specified format on some physical medium."
            :annotation
            (see-also-uri "http://purl.obolibrary.org/obo/OBI_0000368")))

(sio-class "to characterize" "to characterize is the capability to classify the attributes or features of an entity against a reference classification."
	:super to_describe)

(sio-class "to examine" "to examine is the capability to make detailed observation. "
	:super to_observe)

(sio-class "to compare" "to compare is the capability to examine in order to note the similarities or differences among a set of objects."
	:super to_examine)
(sio-class "to test a hypothesis" "to test a hypothesis is the capability to evaluate the truth value of a proposition based on gathered evidence."
	:super to_examine)

(as-subclasses
 role
 (sio-class "abstract role" "an abstract role is a role whose basis lies in spatial/temporal or comparative relations. ")
 (sio-class "processual role" "a processual role is a role that can only be realized in a process.")
 (sio-class "social role" "a social role is a role that is ascribed to individuals in a community."))

(as-disjoint-subclasses
 abstract_role
 (sio-class "comparative role" "a comparative role is an abstract role which holds by comparing some attribute of another object of reference.")
 (sio-class "positional role" "a positional role is an abstract role which holds by comparing position to another object of reference."))

(sio-class "variant role" "a variant role is a comparative role in which the value of an attribute differs when compared to another entity"
	:super comparative_role)

(sio-class "sequence variant role" "a sequence variant role is a comparative role in which the composition of characters in a sequence differs when compared to another entity of similar type."
	:super variant_role)

(sio-class "deletion variant role" "a deletion variant role is the role of an sequence that lacks a sub-sequence relative to the frame of reference."
	:super sequence_variant_role)
(sio-class "insertion variant role" "an insertion variant role is the role of an sequence that contains a sub-sequence that is considered to be an addition relative to the frame of reference."
	:super sequence_variant_role)

(sio-class "chemical entity role" "a chemical role is a processual role held by a chemical entity"
	:super processual_role)
(sio-class "investigational role" "an investigational role is a role held by participants involved in an investigation."
	:super processual_role)

(sio-class "chemical substance role" "a chemical substance role is a chemical entity role held by a chemical substance"
	:super chemical_entity_role)
(sio-class "molecular entity role" "a molecular entity role is a chemical entity role held by a molecule"
	:super chemical_entity_role)

(sio-class "buffer role" "a buffer role is the role of a chemical substance which maintains a pH at a near constant value."
	:super chemical_substance_role)
(sio-class "host role" "the role of an organism in providing resources to maintain the survival and/or reproduction of another organism."
	:super chemical_substance_role)
(sio-class "reagent role" "a role of a chemical substance that participates in a chemical reaction as part of some scientific investigation."
	:super chemical_substance_role)
(sio-class "toxic role" "a toxic role is the role of a chemical substance that is poisonous"
	:super chemical_substance_role)

(sio-class "poison role" "a poison role is the role of a substance that causes some negative disturbance in an organism."
	:super toxic_role)

(sio-class "toxin role" "a toxin role is a toxic role of a chemical substance that is poisonous and  is produced by an organism"
	:super poison_role)

(as-subclasses
 molecular_entity_role
 (sio-class "cofactor role" "the role of a chemical entity involved in the mechanism for enzyme-mediated catalysis.")
 (sio-class "product role" "the role of a chemical entity present at the end of a chemical reaction.")
 (sio-class "reactant role" "the role of a chemical entity present at the beginning of a chemical reaction.")
 (sio-class "regulator role" "the role of a chemical entity that modifies the rate of reaction."))

(sio-class "molecular tracer role" "a molecular tracer role is a reactant role of a molecular entity that serves as a marker for the presence, abundance, or location of a molecular target that it associates with."
	:super reactant_role)
(sio-class "substrate role" "the role of a chemical entity that is modified in a chemical reaction"
	:super reactant_role)

(sio-class "co-enzyme role" "a co-factor role in which the chemical entity is modified during catalysis and must be regenerated."
	:super substrate_role cofactor_role)

(sio-class "co-substrate role" "a co-enzyme role of a chemical entity that is transiently associated, and is regenerated in a separate reaction."
	:super co-enzyme_role)
(sio-class "prosthetic group role" "a coenzyme role of a chemical entity that is covalently bonded to the  enzyme."
	:super co-enzyme_role)

(as-subclasses
 regulator_role
 (sio-class "activator role" "the role of a chemical entity that increases the rate of reaction.")
 (sio-class "catalytic role" "the role of a chemical participant that serves to increase the rate of reaction by lowering the activiation energy.")
 (sio-class "inhibitor role" "the role of a chemical entity that reduces the rate of reaction."))

(sio-class "evaluation role" "an evaluation role is a processual role held by an entity during some evaluation"
	:super investigational_role)
(sio-class "subject role" "a subject role is the role of an individual that is the target of the study."
	:super investigational_role)

(sio-class "control role" "a control role is the role of an individual that is part of a study, but is not subject to the intervention that is to be tested."
	:super evaluation_role)
(sio-class "test role" "a test role is the role of an individual that is a participant in the study and is the target of the intervention."
	:super evaluation_role)

(sio-class "occupational role" "an occupational role is a social role that pertains to an organizational structure."
	:super social_role)

(as-subclasses
 occupational_role
 (sio-class "academic role" "an academic role is a social role that pertains to the academic institution.")
 (sio-class "administrative role" "an administrative role is the role of an individual that performs administrative tasks for some organization.")
 (sio-class "medical role" "a medical role is the role of an individual that is a participant in the delivery of medical care.")
 (sio-class "publishing role" "a publishing role is the role of an individual that is involved in the preparation and issue of creative works for consumption by a wider audience."))

(as-subclasses
 academic_role
 (sio-class "department chair role" "a department chain role is the role of an individual that heads a department at a academic organization.")
 (sio-class "professor role" "a professor role is the role of an individual that is involved in teaching of students (undergraduate and/or graduate) at a post-secondary academic institution.")
 (sio-class "student advisor role" "a student advisor role is the role of an individual employed at an academic organization that is involved in advising students.")
 (sio-class "student role" "a student role is the role of an individual that is enrolled in courses at an academic institution."))
(as-disjoint student_role professor_role)

(sio-class "undergraduate student advisor role" "an undergraduate student advisor role is the role of an individual employed at an academic organization that is involved in advising undergraduate students."
	:super student_advisor_role)
(sio-class "graduate student advisor role" "a graduate student advisor role is the role of an individual employed at an academic organization that is involved in advising graduate students."
	:super student_advisor_role)

(sio-class "secretary role" "a secretary role is the role of an individual that performs administrative tasks to support one or more individuals of the same organization."
	:super administrative_role)

(as-subclasses
 medical_role
 (sio-class "dentist role" "a dentist role is the role of an individual that that specializes in the diagnosis, prevention, and treatment of diseases and conditions of the oral cavity.")
 (sio-class "doctor role" "A doctor role is the role of an individual who practices medicine, which is concerned with promoting, maintaining or restoring human health through the study, diagnosis, and treatment of disease, injury, and other physical and mental impairments."
            :annotation
            (synonym-en "physician"))
 (sio-class "nurse role" "A nurse role is the role of an individual that is involved in the protection, promotion, and optimization of health and abilities, prevention of illness and injury, alleviation of suffering through the diagnosis and treatment of human response, and advocacy in the care of individuals, families, communities, and populations.")
 (sio-class "patient role" "a patient role is the role of an individual that is the recepient of medical care."))

(sio-class "author role" "an author role is the role of an individual that creates a creative, written work."
	:super publishing_role)
(sio-class "publisher role" "a publisher role is the role of an individual that prepares and issues creative works."
	:super publishing_role)

(as-subclasses
 object
 (sio-class "information content entity" "information content entity is an object that requires some background knowledge or procedure to correctly interpret."
            :annotation
            (subset-rdf "ice+")
            core)
 (sio-class "material entity" "A material entity is a physical entity that is spatially extended, exists as a whole at any point in time and has mass."
            :annotation
            core)
 (sio-class "spatial region" "a spatial region is an object contained in some region of space."
            :annotation
            (synonym-rdf "region")
            (eg "the airspace above Ottawa airport; the band around the northern hemisphere between 50 degrees N and 60 degrees N; the spatial region occupied by a bottle"))
 (sio-class "anatomical entity" "an anatomical entity is an object that is a structural part (material or immaterial) of a biological entity."))

(as-subclasses
 information_content_entity
 (sio-class "computational entity" "a computational entity is an information content entity operated on using some computational system.")
 (sio-class "geometric entity" "a geometric entity is an information content entity that pertains to the structure and topology of a space."
            :annotation
            (subset-rdf "geometry+"))
 (sio-class "language entity" "A language entity implements some language specification for the visual interpretation and is part of some document."
            :super (owl-some has_value :RDFS_LITERAL)
            :annotation
            (subset-rdf "nlp+"))
 (sio-class "mathematical entity" "a mathematical entity is an information content entity that are components of a mathematical system or can be defined in mathematical terms."
            :annotation
            (subset-rdf "math+"))
 (sio-class "media" "media are audo/visual/audiovisual modes of communicating information for mass consumption.")
 (sio-class "representation" "A representation is a entity that in some way represents another entity (or attribute thereof)."
            :super (owl-some is_model_of entity)
            :equivalent (owl-and information_content_entity (only represents entity)))
 (sio-class "social entity" "a social entity pertains to the interaction among individuals and groups."))


(as-subclasses
 computational_entity
 (sio-class "cell (informational)" "The minimal unit of a cellular automaton that can change state and has an associated behavior."
            :super (owl-some refers_to entity))
(sio-class "column" "a column is a vertical sequence of cells in a cellular automata.")
(sio-class "data item" "a data item consists of information that has been collected/generated towards some purpose.")
(sio-class "database" "a database is a set of tables."
	:super computational_entity)
(sio-class "database entry" "A database entry is a single, implicitly structured data item in a table."
	:annotation
	(synonym-rdf "database record"))
(sio-class "database key" "A database key is an informational entity whose value is constructed from one or more database columns."
	:super (owl-some has_value :RDFS_LITERAL))
(sio-class "database table" "a database table is a set of named columns with zero or more rows composed of cells that contain column values and is part of a database.")
(sio-class "file" "a file is an information-bearing object that contains a physical embodiment of some information using a particular character encoding.")
(sio-class "namespace" "A namespace is an informational entity that defines a logical container for a set of symbols or identifiers."
	:super (only has_member identifier))
(sio-class "ovopub" "an ovopub is an information content entity that contains and links to one or more resources and/or statements, including those describing its provenance, and is itself a dereferenceable resource."
	:annotation
	(subset-rdf "ovopub+"))
(sio-class "row" "A row represents a single, implicitly structured data item in a table. "
	:super (owl-some has_identifier identifier))
(sio-class "software entity" "a software entity is a computational entity that can be interpreted by or directly executed by a processing unit.")
(sio-class "user account" "an user account allows a user to authenticate to system services and be granted authorization to access them."))

(as-subclasses
 cell__informational_
(sio-class "referencing cell" "a referenceing cell is a cell of a cellular automata that refers to another cell.")
(sio-class "referent cell" "a referent cell is a cell that is the referent of some function or pointer."
	:super (owl-some is_referred_to_by cell__informational_))
(sio-class "unique cell" "a unique cell is a cell that contains a unique value in the cellular automaton."
	:super (owl-some has_attribute unique_identifier)))

(sio-class "database column" "a database collumn is a column in a database table"
	:super column (owl-some is_proper_part_of database_table))

(sio-class "data set" "A dataset is a data item that is a collection of data items."
	:equivalent (owl-and data_item (only has_member data_item)))
(sio-class "scientific data" "scientific data is data obtained from some scientific procedure."
	:super data_item)

(sio-class "biological data" "Biological data is scientific data relevant to biology."
	:super scientific_data)
(sio-class "chemical data" "A chemical datum is a scientific data item which conforms to some specification, either for how it is calculated or for how it is measured, and is commonly used in the domain of chemistry to name and differentiate different numeric properties (both calculated and measured) which are about chemical entities."
	:super scientific_data
	:annotation
	(see-also-rdf "CHEMINF:000123"))

(as-subclasses
 biological_data
 (sio-class "bioinformatic data" "bioinformatic data is data genereated or used for computer-based investigations of biological phenomena.")
 (sio-class "genetic data" "genetic data is data pertaining to genetics.")
 (sio-class "medical data" "medical data is data of interest to medicine."))

(sio-class "sequence alignment" "a sequence alignment is the character-based alignment of sequences using some method."
	:super bioinformatic_data)
(sio-class "sequence profile" "a sequence profile is provides the preference for a character at each position of an abstracted sequence."
	:super bioinformatic_data)

(sio-class "multiple sequence alignment" "a multiple sequence alignment is a sequence alignment involving more than two sequences."
	:super sequence_alignment)
(sio-class "pairwise sequence alignment" "a pairwise sequence alignment is the alignment of exactly 2 sequences."
	:super sequence_alignment)

(sio-class "collection of 3d molecular structure models" "a collection of 3d molecular structure models is just that."
	:super chemical_data)
(sio-class "molecular structure descriptor" "molecular structure descriptor is data that describes some aspect of the molecular structure (composition) and is about some chemical entity."
	:super chemical_data (owl-some is_about molecule)
	:annotation
	(see-also-rdf "CHEMINF:000085"))

(sio-class "biomolecular structure descriptor" "a biomolecular structure descriptor is structure description for organic compounds."
	:super molecular_structure_descriptor)
(sio-class "molecular orbitral" "a molecular orbital (or MO) is a mathematical function describing the wave-like behavior of an electron in a molecule"
	:super molecular_structure_descriptor)

(as-subclasses
 biomolecular_structure_descriptor
 (sio-class "primary structure descriptor" "a primary structure descriptor describes a biomolecular object in terms of a 1D or 2D topology.")
(sio-class "quaternary structure" "a quaternary structure descriptor describes topological patterns in a multi-unit biopolymer complex.")
(sio-class "secondary structure descriptor" "a secondary structure descriptor describes local topological patterns in  a biopolymer")
(sio-class "tertiary structure descriptor" "a tertiary structure descriptor describes 3D topological patterns in  a biopolymer."))

(sio-class "biopolymer sequence" "A sequence is a primary structure descriptor in which each of the letters in the string represents a monomeric unit (residue) in which adjacent letters represent the connectivity between the monomeric units."
	:super primary_structure_descriptor (owl-some has_value :XSD_STRING))

(as-subclasses
 biopolymer_sequence
(sio-class "nucleic acid sequence" "a nucleic acid sequence is a symbolic representation of the sequence of nucleic acid residues in a nucleic acid.")
(sio-class "protein sequence" "a protein acid sequence is the character representation of the molecular structure of a protein."
	:super (owl-some is_attribute_of polypeptide)
	:annotation
	(synonym-en "amino acid sequence")
	(synonym-en "polypeptide sequence"))
(sio-class "sequence assembly" "a sequence assembly is a sequence that is produced as by the alignment of two or more sequences."))

(sio-class "deoxyribonucleic acid sequence" "a deoxyribonucleic acid sequence is a symbolic representation of the sequence of deoxyribonucleic acid residues in a deoxyribonucleic acid."
	:super nucleic_acid_sequence (owl-some is_about deoxyribonucleic_acid)
	:annotation
	(synonym-en "DNA sequence"))
(sio-class "ribonucleic acid sequence" "a ribonucleic acid sequence is a symbolic representation of the sequence of ribonucleic acid residues in a ribonucleic acid."
	:super nucleic_acid_sequence (owl-some is_about ribonucleic_acid)
	:annotation
	(synonym-en "RNA sequence"))

(sio-class "3d structure model" "a 3D structure model is a representation of the spatial arrangement of one or more chemical entities."
	:super tertiary_structure_descriptor)

(sio-class "foreign database key" "a foreign database key is a database key that refers to a key in some table"
	:super database_key (owl-some references database_key))
(sio-class "primary database key" "A primary database key is a\n        database key that identifies every row of a\n        table."
	:super database_key (owl-not (has-value has_value (literal "null" :type :RDF_PLAIN_LITERAL))) (owl-some is_unique_identifier_for row))

(sio-class "molecular structure file" "a molecular structure file is a file that contains a description of molecular structure."
	:super file (owl-some has_part molecular_structure_descriptor) (owl-some is_concretization_of biomolecular_structure_descriptor))

(sio-class "PDB file" "A PDB file is a molecular structure file specified by the Protein DataBank (PDB)."
	:super molecular_structure_file)

(sio-class "collection ovopub" "a collection ovopub is an ovopub that contains a set of individuals that are linked to the ovopub using rdfs:member."
           :super ovopub)
(sio-class "assertion ovopub" "an assertional ovopub is an ovopub that contains a single assertion which is reified to the ovopub using rdf:subject, rdf:predicate, rdf:object." 
           :super ovopub)

(sio-class "database row" "a database row is a row that is part of a database table."
	:super row (owl-some is_proper_part_of database_table))

(as-subclasses
 software_entity
(sio-class "software application" "A software application is software that can be directly executed by some processing unit.")
(sio-class "software library" "A software library is software composed of a collection of software modules and/or software methods in a form that can be statically or dynamically linked to some software application.")
(sio-class "software method" "A software method (also called subroutine, subprogram, procedure, method, function, or routine) is software designed to execute a specific task")
(sio-class "software module" "A software module is software composed of a collection of software methods.")
(sio-class "software script" "A software script is software whose instructions can be executed using a software interpreter."))

(sio-class "software interpreter" "A software interpreter is a software application that executes some specified input software."
	:super software_application)
(sio-class "web service" "A web service is a software application that can be accessed over a network, such as the Internet, and executed on a remote system hosting the requested services."
	:super software_application)

(as-subclasses
 web_service
(sio-class "REST web service" "a REST web service is a web service that provides functionality according to the Representational State Transfer (REST) specification."
	:annotation
	(see-also-uri "http://en.wikipedia.org/wiki/REST"))
(sio-class "semantic web service" "a semantic web service is a web service that provides a formal, machine understanble description of its functionality.")
(sio-class "SOAP web service" "a SOAP web service is a web service that implements Simple Object Access Protocol (SOAP)."
	:super web_service
	:annotation
	(see-also-uri "http://en.wikipedia.org/wiki/SOAP")))

(sio-class "SADI semantic web service" "a SADI semantic web service is a semantic web service that follows the SADI specification"
           :super semantic_web_service
	:annotation
	(see-also-uri "http://en.wikipedia.org/wiki/SADI")
        (annotation subset (literal "sadi" :lang "en")))

(as-subclasses
 geometric_entity
 (sio-class "collection of points" "a collection of points is a geometric entity that contains a non-zero set of geometric points. ")
(sio-class "curve" "A curve is a geometric entity that may be located in n-dimensional spatial region whose extension may be n-dimensional,  is composed of at least two fully connected points and does not intersect itself.")
(sio-class "point" "A point is a geometric entity that is located in a zero-dimensional spatial region and whose position is defined by its coordinates in some coordinate system.")
(sio-class "polygon" "A polygon is a planar entity that is bounded by a closed path or circuit, composed of a finite connected sequence3 of straight line segments.")
(sio-class "polygonal face" "A polygonal face is a polygon bounded by a circuit of polygon edges, and includes the flat (plane) region inside the boundary.")
(sio-class "polyhedral skeleton" "A polyhedral skeleton is a collection of polygon edges.")
(sio-class "polyhedral surface" "A polyhedral surface is composed of polygonal faces.")
(sio-class "polyline" "A polyline is a connected sequence of line segments."
	:annotation
	(synonym-rdf "polygonal chain, polygonal curve, polygonal path, piecewise linear curve")))

(sio-class "curve segment" "a curve segment is a part of a curve that consists of at least three points."
	:super curve)
(sio-class "line" "A line is curve that extends in a single dimension (e.g. straight line; exhibits no curvature), and is composed of at least two fully connected points."
	:super curve)
;; NOTE does not include positional or statistical
(as-disjoint-subclasses
 line
 :cover
(sio-class "infinite line" "An infinite line is a line that extends outwards in both directions of a single dimensional and is not bounded by terminal points.")
(sio-class "line segment" "A line segment is a line and a part of a curve that is (inclusively) bounded by two terminal points.")
(sio-class "ray" "A ray is a line which that is bounded by a startpoint and extends outwards infinitely along one dimension."))
(sio-class "positionally oriented line" "a positionally oriented line is a line that is positioned against some axis of reference."
	:super line)
(sio-class "statistical graph line" "A statistical graph line is a line used in a statistical graph to communicate some trend or feature of the embedded data."
	:super line)

(as-subclasses
 line_segment
(sio-class "directed line segment" "A directed line segment is a line segment that is contained by an ordered pair \nof endpoints (a start point and an endpoint)."
	:super (exactly 1 has_component_part endpoint) (exactly 1 has_component_part start_point))
(sio-class "polygon edge" "A polygon edge is a line segment joining two polygon vertices."
	:super (owl-some is_part_of polygon) (exactly 2 has_component_part polygon_vertex))
(sio-class "tick mark" "A tick mark is a line segment that is spatially positioned perpendicular to the axis of a statistical graph and indicates the position of a specific numeric value (which may be indicated by an adjacent value label) on a value axis, or is one of a pair of tick marks that delineates the boundary of a categorical value (which may be indicated by an adjacent category label) on the categorical axis."))

(sio-class "arrowed line segment" "An arrowed line is a directed line segment in which one or both endpoints is tangentially part of a triangle that bisects the line."
	:super directed_line_segment (owl-some has_part triangle) (at-most 2 has_component_part triangle))
(sio-class "axis" "An axis is a line segment that is part of a statistical graph in which the \nposition along the line corresponds to a numeric or categorical value."
	:super directed_line_segment (owl-some is_component_part_of statistical_graph)
	:annotation
	(eg "The left vertical and bottom horizontal lines with tickmarks in \nGraph 1 http://tinyurl.com/opwnvm")
	(owl-comment (literal "The value of a plotted geometric objects (e.g. point) can be obtained from the perpendicular projection of the position of the object onto the axis. Axis may also be associated with a textual description of what the values on the axis represents." :type :RDF_PLAIN_LITERAL)))

(as-disjoint-subclasses
 arrowed_line_segment
(sio-class "single arrowed line segment" "A single arrowed line is directed line in which the endpoint is tangentially part of a triangle that bisects the line."
	:super (exactly 1 has_component_part triangle))
(sio-class "double arrowed line segment" "A double arrowed line is an arrowed line in which both terminal points are  tangentially part of different triangles that bisect the line."
	:super (exactly 2 has_component_part triangle)))

(as-disjoint-subclasses
 axis
(sio-class "category axis" "A category axis is an axis in which the position along the line is \npartioned into categories."
	:annotation
	(eg "The horizontal axis corresponding to months of the year in graph 4 \nof http://tinyurl.com/opwnvm"))
(sio-class "value axis" "A value axis is an axis in which the position along the line is \npartioned into numeric values."
	:annotation
	(owl-comment (literal "A value axis holds the properties of having a minimum and a \nmaximum value, and is usually associated with tickmarks that indicate \nintervals along the axis." :type :RDF_PLAIN_LITERAL))
	(eg "The value axis that ranges from 3.0 to 7.4 and corresponds to \nbillions of dollars in Graph 1 of http://tinyurl.com/opwnvm")))

(as-disjoint-subclasses
 category_axis
 :cover
(sio-class "primary category axis" "A primary category axis is a category axis that either defines the sole value range or holds the larger set of categorical values specified by the secondary category axis."
	:annotation
	(eg "The category axis that corresponds to years in Graph 1 of \nhttp://tinyurl.com/opwnvm"))
(sio-class "secondary category axis" "A secondary category axis is a category axis that defines a finer \ngranular part (or subset) of the value range of the primary category axis."
	:annotation
	(eg "The category axis that corresponds to months in Graph 1 of \nhttp://tinyurl.com/opwnvm")))

;; NOTE cartesian and scaled not included
(as-disjoint-subclasses
 value_axis
 :cover
(sio-class "bottom value axis" "A bottom value axis is a value axis that is spatially positioned to the bottom of the plot area.")
(sio-class "left value axis" "A left value axis is a value axis that is spatially positioned to the left of the plot area."
	:annotation
	(eg "The value axis that ranges from 3.0 to 7.4 and corresponds to billions of dollars in Graph 1 of http://tinyurl.com/opwnvm"))
(sio-class "right value axis" "A right value axis is a value axis that is spatially positioned to the right of the plot area.")
(sio-class "top value axis" "A top value axis is a value axis that is spatially positioned to the top of the plot area."))
(sio-class "Cartesian coordinate axis" "A Cartesian coordinate axis is an axis whose behavior follows that of a Cartesian coordinate system."
	:super value_axis)
(sio-class "scaled value axis" "A scaled value axis is a value axis in which the value range was subject to a mathematic transformation."
	:super value_axis)

(as-subclasses
 Cartesian_coordinate_axis
 (sio-class "x-axis" "An x-axis is a Cartesian coordinate axis that is aligned with the horizon."
	:annotation
	(eg "The horizontal axis in http://tinyurl.com/opwnvm")
	(synonym-rdf "horizontal axis")
	(owl-comment (literal "In most figures, this axis represents categories, such as months, \nmarket segments, or other non-numeric data." :type :RDF_PLAIN_LITERAL)))
(sio-class "y-axis" "A y-axis is a Cartesian coordinate axis that is spatially oriented \nperpendicular to the x-axis."
	:annotation
	(synonym-rdf "vertical axis"))
(sio-class "z-axis" "A z-axis is a Cartesian coordinate axis that is spatially oriented \nnormal to the plane formed by the x- and y-axes."))
;; y and z are not disjoint
(as-disjoint x-axis y-axis)
(as-disjoint x-axis z-axis)

(as-disjoint-subclasses
 scaled_value_axis
 (sio-class "linear value axis" "A linear value axis is a value axis that corresponds to a scaling factor of 1 of the value range.")
(sio-class "logarithmic value axis" "A logarithmic value axis is a scaled value axis that corresponds to a scaling factor of the logarithm of the value range."))

(sio-class "major tick mark" "A major tick mark is a tick mark that indicates the position of a \nspecific numeric value and is adjacent to its value label on the value \naxis, or is one of a pair of tick marks that delineates the boundary of a \ncategorical value indicated by an adjacent category label on the \ncategorical axis."
	:super tick_mark)
(sio-class "minor tick mark" "A minor tick mark is a tick mark that indicates the position of a specific numeric value but has no adjacent value label, or is one of a pair of tick marks that delineates the boundary of a categorical value but has no adjacent category label on the categorical axis."
	:super tick_mark)

(sio-class "horizontal line" "A vertical line is a line that is positionally oriented with the horizon."
	:super positionally_oriented_line)
(sio-class "vertical line" "A vertical line is a line that is positionally oriented perpendicular to the horizon."
	:super positionally_oriented_line)

(sio-class "vector" "A ray is a line which that is bounded by a startpoint and extends outwards along one dimension."
	:super ray)

(sio-class "surface normal" "A surface normal is a vector that is perpendicular to a flat surface."
	:super vector)
(sio-class "vertex normal" "A vertext normal is the normalized average of the surface normals of the faces that contain that vertex."
	:super vector)

(sio-class "drop line" "A drop line is a statistical graph line that vertically or horizontally connects a data series line with a value axis in a statistical graph."
	:super statistical_graph_line)
(sio-class "trend line" "A trend line is a line, line segment or ray that is part of a statistical graph which indicates a statistical or visual direction across categorical or value data."
	:super statistical_graph_line)

(as-subclasses
 trend_line
(sio-class "decreasing line" "An decreasing line is a line segment in which the startpoint and endpoint are ordered along one dimension and the difference of values in a second dimension is negative.")
(sio-class "increasing line" "An increasing line is a line segment in which the startpoint and endpoint are ordered along one dimension and the difference of values in a second dimension is positive.")
(sio-class "plateau line" "An plateau line is a line segment in which the startpoint and endpoint are ordered along one dimension and the difference of values in a second dimension is zero."))

(sio-class "1D cartesian point" "a 1D cartesian point is a point whose position is specified along a single dimension using Cartesian coordinates."
	:equivalent (owl-and point (only has_component_part z_cartesian_coordinate) (exactly 1 has_component_part z_cartesian_coordinate)))
(sio-class "2D cartesian point" "a 2D cartesian point is a point whose position is specified along two  dimensions using Cartesian coordinates."
	:equivalent (owl-and point (only has_component_part (owl-or x_cartesian_coordinate y_cartesian_coordinate)) (exactly 1 has_component_part x_cartesian_coordinate) (exactly 1 has_component_part y_cartesian_coordinate)))
(sio-class "3D cartesian point" "a 3D cartesian point is a point whose position is specified along three  dimensions using Cartesian coordinates."
	:equivalent (owl-and point (exactly 1 has_component_part x_cartesian_coordinate) (exactly 1 has_component_part y_cartesian_coordinate) (exactly 1 has_component_part z_cartesian_coordinate)))
(sio-class "data point" "A data point is a point that which corresponds to the projection of the values of measurement data against the axes of a statistical graph."
	:super point (owl-some denotes entity))
(sio-class "terminal point" "A terminal point is a point that defines the finite extension of a line."
	:super point)

(sio-class "stationary point" "a stationary point is a point that is part of a curve in which the derivative at that point is zero, and hence its value is at least a local maximum or minimum."
	:super data_point)

(as-subclasses
 terminal_point
(sio-class "start point" "A start point is a terminal point which is the first of an ordered \npair of points.")
(sio-class "polygon vertex" "A polygon vertex is a terminal point at which two polygon edges meet and are part of a polygon."
	:super (at-least 2 is_component_part_of polygon_edge) (exactly 1 has_component_part vertex_normal))
(sio-class "endpoint" "An endpoint is a terminal point that is the last of an ordered \npair of points."))

(sio-class "local minimum stationary point" "a local minimum stationary point is a point that has a lower value in  some axis than adjacent points."
	:super stationary_point)
(sio-class "local maximum stationary point" "a local maximum stationary point is a point that has a higher value in  some axis than adjacent points."
	:super stationary_point)

(sio-class "global minimal stationary point" "A global minimum data point is a data point that corresponds to a measurement value is smaller than that of all other plotted datapoints."
	:super local_minimum_stationary_point)

(sio-class "global maximal stationary point" "A global maximum stationary point is a data point that corresponds to a measurement value is larger than that of all other plotted datapoints."
	:super local_maximum_stationary_point)

(sio-class "quadrilateral" "A quadrilateral is a polygon with composed of four points and four line segments, in which each point is fully connected to two other points."
	:super polygon)
(sio-class "triangle" "A triangle is a polygon composed of three points and three line segments, in which each point is fully connected to another point along through the line segment."
	:super polygon)

(sio-class "rectangle" "A rectangle is a quadrilateral in which one pair of line segments are  parallel and the other pair are perpendicular to the first pair."
	:super quadrilateral)

(sio-class "bar" "A bar is a rectangle that is located in the plot of a statistical graph in which its length is proportional to the values that it represents."
	:super rectangle)

(as-subclasses
 language_entity
(sio-class "character" "A character is a language symbol used to construct words.")
(sio-class "description" "a description is language entity in which elements of a language (formal or natural) are used to characterize an entity."
	:super (only describes entity))
(sio-class "language" "Language is a language entity which is the result of encoding and decoding information through systematic creation and usage of systems of symbols, each pairing a specific sign with an intended meaning, established through social conventions")
(sio-class "morpheme" "a morpheme is the smallest semantically meaningful unit in a language")
(sio-class "phrase" "A phrase is a group of words functioning as a single unit in the syntax of a sentence."
	:super (at-least 2 has_direct_part word))
(sio-class "verbal language entity" "a verbal language entity is a language entity that is manifested through sound.")
(sio-class "visual language entity" "A visual language entity is a language entity that is expressed through physical expression of manual  ")
(sio-class "vocabulary" "a vocabulary is a collection of terms."
	:super collection)
(sio-class "word" "A word is the smallest free form (an item that may be expressed in isolation with semantic or pragmatic content) in a language."
	:super (owl-some has_part morpheme) (owl-some has_direct_part character)))

(as-subclasses
 description
(sio-class "annotation" "An annotation is a written explanatory or critical description, or other in-context information (e.g., pattern, motif, link), that has been associated with data or other types of information."
	:super (owl-some in_relation_to entity))
(sio-class "answer" "an answer is a reply to a question.")
(sio-class "definition" "A definition is a description that succintly characterizes an entity.")
(sio-class "history" "history is a sequence of past events.")
(sio-class "proposition" "A proposition is a sentence expressing something true or false."
	:super (owl-some has_quality truth_value) (owl-some is_about entity))
(sio-class "specification" "A specification is a description of the essential technical attributes/requirements for an object or procedure, and may be used to determine that the object / procedure meets its requirements/attributes."
	:super (owl-some specifies entity) (owl-some has_expression information_content_entity))
(sio-class "syndrome" "A syndrome is composed of a set of several clinically recognizable features, signs (observed by someone other than the patient), symptoms (reported by the patient), phenomena or characteristics that often occur together."))

(as-subclasses
 history
(sio-class "family history" "family history is the systematic narrative and research of past events relating to a specific family, or specific families.")
(sio-class "medical history" "a medical history is a record of the events of a recipient of medical care.")
(sio-class "evolutionary lineage" "evolutionary lineage is a sequence of species, that form a line of descent, each new species the direct result of speciation from an immediate ancestral species."))

(as-subclasses
 proposition
(sio-class "argument" "An argument is a set of one or more declarative sentences (or propositions) known as the premises along with another declarative sentence (or proposition) known as the conclusion."
	:super proposition (owl-and (owl-some has_proper_part premise) (owl-some has_proper_part conclusion)))
(sio-class "premise" "A premise is a proposition of an argument from which the conclusion is drawn."
	:super proposition)
(sio-class "conclusion" "A conclusion is a proposition which is reached after considering the evidence, arguments or premises."
	:super proposition)
(sio-class "hypothesis" "a hypothesis is a proposed explanation for a phenomenon."
	:super proposition)
(sio-class "objective" "an objective is a proposition that indicates a planned or anticipated outcome."
	:super proposition (only is_specified_by action_specification) (only is_realized_in process))
(sio-class "belief" "a belief is a proposition that is believed to be true."
	:super proposition (owl-some specifies (owl-and _true (owl-some is_quality_of proposition))))
(sio-class "idea" "An idea is a proposition about some object of conceptual thought."
	:super proposition)
(sio-class "justification" "A justification is a proposition that defends, explains or excuses some argument."
	:super proposition)
(sio-class "prognosis" "A prognosis is a proposition about the likely course of a disease, the chance of recovery or recurrence."
	:super proposition)
(sio-class "comment" "a comment is a verbal or written remark often related to an added piece of information, or an observation or statement."
	:super proposition)
(sio-class "statement" "a statement is a proposition that is either (a) a meaningful declarative sentence that is either true or false, or (b) that which a true or false declarative sentence asserts"
	:super proposition))

(as-subclasses
 argument
(sio-class "deductive argument" "A deductive argument is an argument that asserts that the truth of the conclusion is a logical consequence of the premises.")
(sio-class "inductive argument" "An inductive argument is an argument that asserts that the truth of the conclusion is supported by the premises.")
(sio-class "valid argument" "A valid argument is an argument where the truth of the conclusion is a logical consequence of the premises and (consequently) its corresponding conditional is a necessary truth."
	:super (owl-some has_proper_part (owl-and conclusion (owl-some has_quality _false))))
(sio-class "invalid argument" "An invalid argument is an argument where the truth of the conclusion is false because it is not a logical consequence of the premises."
	:super (owl-some has_proper_part (owl-and conclusion (owl-some has_quality _false)))))

(sio-class "sound argument" "A sound argument is a valid argument with true premises."
	:super valid_argument (owl-some has_proper_part (owl-and premise (owl-some has_quality _true))))

(sio-class "opinion" "an opinion is a belief that is the result of emotion or interpretation of facts. "
	:super belief (owl-some is_product_of medical_diagnosis))

(sio-class "diagnostic opinion" "A diagnostic opinion is an opinion resulting from a medical diagnostic procedure."
	:super opinion)
(sio-class "speculation" "speculation is an opinion based on incomplete evidence"
	:super opinion)

(sio-class "reason" "A reason is a justification that specifies the motive for an action or a determination"
	:super justification)

(sio-class "purpose" "purpose is the reason for which something is done or created or for which something exists."
	:super reason)

(as-subclasses
 specification
 (sio-class "action specification" "An action specification is a specification composed of a sequence of instructions to achieve some objective."
	:super (owl-some specifies process) (owl-some satisfies objective) (only is_manifested_as process)
	:annotation
	(synonym-rdf "effective specification"))
(sio-class "functional specification" "A functional specification is a specification that describes the characteristics of an object."
	:super (owl-some specifies (owl-or material_entity information_content_entity)))
(sio-class "standard" "a standard is a socially-agreed upon specification."
	:super (owl-some has_quality agreement)))

(as-subclasses
 action_specification
(sio-class "experimental protocol" "an experimental protocol is an action specification with respect to the design and implementation of experiments. In addition to providing a detailed set of procedures and lists of required equipment and instruments, experimental protocols often include information on safety precautions, the calculation of results and reporting standards, including statistical analysis and rules for predefining and documenting excluded data to avoid bias.")
(sio-class "pathway" "a pathway is an effective specification that outlines a set of actions that forms a way to achieve an objective."
	:annotation
	(synonym-rdf "biological pathway"))

(sio-class "plan" "a plan is a set of intended actions, through which one expects to achieve a goal.")
(sio-class "recipe" "A recipe is a set of instructions that describe how to prepare or make something."))

(sio-class "standard operating procedure" "a standard operating procedure is a specification approved for use in specific environments."
	:super experimental_protocol)
(sio-class "study design" "A study design is a protocol for the proper execution of a study which normally requires a carefullly crafted research question or hypothesis and at least one variable under observation and observed values for that variable."
	:super experimental_protocol (only is_manifested_as (owl-some is_part_of investigation))
	:annotation
	(synonym-rdf "study protocol"))

(sio-class "chemical reaction pathway" "a chemical reaction pathway specifies a series of chemical reactions towards producing some chemical product."
	:super pathway (owl-some specifies (owl-some has_proper_part chemical_reaction)) (owl-some is_model_of process) (only has_member chemical_reaction))

(as-subclasses
 chemical_reaction_pathway
 (sio-class "biochemical pathway" "a biochemical pathway specifies a series of biochemical modifications and transformations towards achieving some biological outcome.")
 (sio-class "chemical degradation pathway" "a chemical degradation pathway is a pathway involved in the disassembly of a chemical.")
 (sio-class "chemical synthesis pathway" "a chemical synthesis pathway is a pathway involved in the assembly of a chemical."))

(as-disjoint-subclasses
 biochemical_pathway
(sio-class "metabolic pathway" "a metabolic pathway is a series of biochemical reactions that begins with one or more substrates and ends with one or more products.")
(sio-class "regulatory pathway" "a regulatory pathway is a series of biochemical reactions that lead to the increase or decrease of activity of participating molecular components."))

(sio-class "pharmacokinetic pathway" "a pharmacokinetic pathway is a metabolic pathway which describes the metabolism of a drug molecule."
	:super metabolic_pathway)

(sio-class "pharmacodynamic pathway" "a pharmacodynamic pathay is a regulatory pathway in which a drug molecule regulates the activity of one or more components organized in a pathway."
	:super regulatory_pathway)

(as-subclasses
 functional_specification
 (sio-class "design specification" "A design specification is a specification that provides precise and explicit information about the requirements for a product design.")
(sio-class "spatial specification" "A specification for spatial location is an effective specification towards representation spatial position or spatial data.")
(sio-class "structure" "structure is the specification that refers to the composition and arrangement of parts of an object.")
(sio-class "formal specification" "A formal specification is a mathematical description of software or hardware that may be used to develop an implementation.")
(sio-class "design" "a specification of an object, manifested by an agent, intended to accomplish goals, in a particular environment, using a set of primitive components, satisfying a set of requirements, subject to constraints.")
(sio-class "measurement scale" "a measurement scale is a functional specification that specifies an allowed range of categories or values.")
(sio-class "genotype" "a genotype is a functional specification of a biological entity in terms of its genetic composition (or lack thereof)."))

(sio-class "criterion" "A criterion is a specification to describe properties used for evaluation."
	:super design_specification)

(as-disjoint-subclasses
 criterion
(sio-class "inclusion criterion" "An inclusion criterion is a criterion that must be present to satisfy some objective.")
(sio-class "exclusion criterion" "An exclusion criterion is a criterion that must be absent to satistify the objective."))

(as-disjoint-subclasses
 measurement_scale
(sio-class "nomimal scale" "A nominal scale of measurement only specifies a limited set of categories.")
(sio-class "binary scale" "a binary scale is a measurement scale that specifies a choice between two values.")
(sio-class "numeric scale" "a numeric scale of measurement is one that only specifies numeric values"
	:super (owl-some specifies (owl-and entity (owl-some has_value :RDFS_LITERAL)))))

(sio-class "decimal scale" "a decimal scale of measurement is one that only specifies decimal values"
	:super numeric_scale (owl-some specifies (owl-and entity (owl-some has_value :XSD_DECIMAL))))
(sio-class "integer scale" "an integer scale of measurement is one that only specifies integer values."
	:super numeric_scale (owl-some specifies (owl-and entity (owl-some has_value :XSD_INTEGER))))

(sio-class "coordinate system" "A coordinate system is a specification for spatial location that uses a set of numbers, or coordinates, to uniquely determine the position of a point or other geometric element."
	:super spatial_specification)

(as-subclasses
 coordinate_system
(sio-class "cartesian coordinate system" "A Cartesian coordinate system specifies each point uniquely in a plane by a pair of numerical coordinates, which are the signed distances from the point to two fixed perpendicular directed lines, measured in the same unit of length.")
(sio-class "polar coordinate system" "a polar coordinate system is a two-dimensional coordinate system in which each point on a plane is determined by a distance from a fixed point and an angle from a fixed direction.")
(sio-class "cylindrical coordinate system" "A cylindrical coordinate system is a three-dimensional coordinate system that specifies point positions by the distance from a chosen reference axis, the direction from the axis relative to a chosen reference direction, and the distance from a chosen reference plane perpendicular to the axis.")
(sio-class "spherical coordinate system" "a spherical coordinate system is a coordinate system for three-dimensional space where the position of a point is specified by three numbers: the radial distance of that point from a fixed origin, its polar angle measured from a fixed zenith direction, and the azimuth angle of its orthogonal projection on a reference plane that passes through the origin and is orthogonal to the zenith, measured from a fixed reference direction on that plane."))

(sio-class "chemical structure" "chemical structure is the structure of a chemical entity in terms of its molecular geometry and electronic structure."
	:super structure)

(sio-class "molecular structure" "Molecular structure is the spatial arrangement of atoms in a molecule and the chemical bonds that hold the atoms together."
	:super chemical_structure
	:annotation
	(synonym-rdf "molecular geometry"))
(sio-class "electronic structure" "electronic structure is the electron configuration is the distribution of electrons of an atom or molecule (or other physical structure) in atomic or molecular orbitals."
	:super chemical_structure
	:annotation
	(synonym-rdf "electronic configuration")
	(eg "the electron configuration of the neon atom is 1s2 2s2 2p6."))

(sio-class "crystal structure" "a crystal structure is the arrangement of atoms or molecules in a crystalline liquid or solid."
	:super molecular_structure)

(as-subclasses
 language
 (sio-class "sign language" "a sign language (also signed language) is a language that involves manual communication and body language to convey meaning. This can involve simultaneously combining hand shapes, orientation and movement of the hands, arms or body, and facial expressions to fluidly express a speaker's thoughts.")
 (sio-class "verbal language" "a verbal language is a language that uses sounds to communicate.")
 (sio-class "written language" "written language is a language that is communicated through a writing system."))

(sio-class "sentence" "A sentence is a grammatical unit consisting of one or more words that bear minimal syntactic relation to the words that precede or follow it"
	:super phrase)
(sio-class "term" "A term is a word or phrase used to denote one or more entities."
	:super phrase (owl-or word phrase))

(sio-class "clause" "A clause consists of a subject and a predicate."
	:super sentence)
(sio-class "question" "A question is a linguistic expression used to make a request for information."
	:super sentence)

(sio-class "concept" "A concept is term that refers to a generalization of a set of attributes or entities."
	:super term)
(sio-class "descriptor" "A descriptor (index term, subject term, subject heading) is a term that captures the essence of the topic of a document."
	:super term)
(sio-class "term variant" "a term variant is a term that is a variant of another term."
	:equivalent (owl-and term (owl-some is_variant_of term)))

(sio-class "category" "a category is a class of entities having particular shared characteristics."
	:super concept)

(sio-class "keyword" "A keyword is a descriptor in which the association of the word with the entity facilitates information retrieval."
	:super descriptor)

(as-subclasses
 term_variant
(sio-class "synonym" "A synonym is a word with the same or very similar meanings."
	:equivalent (owl-and term_variant (owl-some is_similar_to term)))
(sio-class "antonym" "An antonym is a word with opposite or nearly opposite meaning."
	:equivalent (owl-and term_variant (owl-some is_opposite_to term)))
(sio-class "hypernym" "A hypernym is a term with a broader meaning."
	:equivalent (owl-and term_variant (owl-some is_broader_than term)))
(sio-class "hyponym" "A hyponym is a term with a narrower meaning."
	:equivalent (owl-and term_variant (owl-some is_narrower_than term)))
(sio-class "homonym" "A homonym is a word that sounds the same but has different meaning."
	:super (owl-some is_dissimilar_to term)))

(as-subclasses
 verbal_language_entity
 (sio-class "consonant" "a consonant is a verbal entity of language that is articulated with complete or partial closure of the vocal tract.")
 (sio-class "syllable" "A syllable is a verbal entity of language having one vowel sound, with or without surrounding consonants, forming the whole or a part of a word.")
 (sio-class "vowel" "a vowel is a verbal entity of language that is pronounced with an open vocal tract so that there is no build-up of air pressure at any point above the glottis."))

(sio-class "textual entity" "A textual entity is language entity that is manifested as one or more distinct characters."
	:super visual_language_entity)

(as-subclasses
 textual_entity
 ;; NOTE _label is its own parent
(sio-class "label" "a label is a term that is associated with some entity"
	:super _label)
(sio-class "document" "A document is a bounded physical or digital representation of a body of information designed with the capacity (and usually intent) to communicate.")
(sio-class "document component" "A bibliographic attribute is an attribute related to publications.")
(sio-class "excerpt" "An excerpt is a contiguous or discontiguous portion of a document."
	:super (owl-some is_part_of textual_entity))
(sio-class "paragraph" "A paragraph is a self-contained unit of written discourse consisting of one or more sentences."
	:super (owl-some has_direct_part sentence))
(sio-class "text span" "a text span is a subset of contiguous sequence of characters of a textual entity."
	:super (owl-and (owl-some has_attribute text_span_start_position) (owl-some has_attribute text_span_end_position))
	:annotation
	(synonym-rdf "selector")))

(as-subclasses
 _label
 (sio-class "identifier" "An identifier is a label that specifically refers to (identifies) an entity (instance/type)."
	:super (owl-some is_identifier_for entity))
(sio-class "language label" "A language label is a label that denotes the language of a textual entity.")
(sio-class "name" "a name is a label used to identify an entity."
	:super (owl-some has_value :XSD_STRING))
(sio-class "namespace label" "A namespace label is a short name for a namespace.")
(sio-class "numeric label" "a numeric label is a number used as a label.")
(sio-class "title" "A title is a textual entity that summarily describes some entity."))

(as-subclasses
 identifier
(sio-class "informational entity identifier" "an informational entity identifier is an identifier for an informational entity.")
(sio-class "physical entity identifier" "a physical entity identifier is an identifier for a physical entity.")
(sio-class "positional identifier" "a positional description is a description of location using some system or frame of reference."
	:super (owl-some is_attribute_of position))
(sio-class "unique identifier" "a unique identifier is an identifier that uniquely identifies some thing."
	:super (owl-some is_unique_identifier_for entity)
	:equivalent (owl-and identifier (owl-some is_unique_identifier_for entity)))
(sio-class "version label" "a version label is a label for a particular form or variation of an earlier or original type."
	:super (owl-some is_identifier_for (owl-some is_variant_of entity))))

(as-subclasses
 informational_entity_identifier
(sio-class "URL" "a Uniform Resource Locator or Universal Resource Locator (URL) is a specific character string that constitutes a reference to an Internet resource."
	:super (owl-some has_value :XSD_ANY_URI)
	:annotation
	(synonym-en "Uniform Resource Locator"))
(sio-class "record identifier" "a record identifier is an identifier for a database entry."
	:super (owl-some is_identifier_for database_entry))
(sio-class "software process identifier" "a software process identifier is an identifier for a software process in some operating system."))

(sio-class "PDB record identifier" "A PDB record identifier is an identifier for a PDB generated record."
	:super record_identifier)

(sio-class "chemical identifier" "a chemical identifier is an identifier for a chemical entity"
	:super physical_entity_identifier)

(sio-class "molecular identifier" "a molecular identifier is an identifier for a molecular entity."
	:super chemical_identifier)

(sio-class "PDB chain identifier" "A PDB chain identifier is a alphabetical label to identify a molecule in a structure provided by the Protein DataBank ."
	:super molecular_identifier)
(sio-class "microarray probe set identifier" "a microarray probe set identifier is an identifier for a set of probe pairs selected to represent expressed sequences on an array. "
	:super molecular_identifier)

(as-subclasses
 positional_identifier
(sio-class "address" "An address is a position that indicates the physical location of some entity using a social convention.")
(sio-class "apartment number" "an apartment number is the number assigned to identify an apartment in a building of apartments.")
(sio-class "street name" "a street name is the token given to identify a particular street."))

(sio-class "document version" "A document version is a version of a work in some sequence of derivative works."
	:super version_label)
(sio-class "software version label" "a software version label is a version label for a piece of software."
	:super version_label (owl-some is_identifier_for software_entity)
	:annotation
	(eg "major.minor[.build[.revision]]"))

(as-subclasses
 software_version_label
(sio-class "major version number" "a major version number is a version of a software that exhibits a significant change in functionalilty from a prior version.")
(sio-class "minor version number" "a minor version number is a version of a software that exhibits minor features or significant fix from a prior version.")
(sio-class "revision number" "a revision number is a version of a software in which bugs have been fixed from a prior version."))

(as-subclasses
 name
(sio-class "brand name" "a brand name is a trademarked and marketed name of a product.")
(sio-class "common name" "a common name is a name that is commonly used.")
(sio-class "first name" "A first name is a name that denotes a specific individual between members of a group of individuals, whose members usually share the same surname.")
(sio-class "generic name" "A generic name is the preferred name provided by manufacturer")
(sio-class "last name" "A last name (surname) is a name added to a given name and is part of a personal name and is often the family name.")
(sio-class "personal name" "A personal name is a name to identify an individual person and usually comprises of a first name and a last name.")
(sio-class "preferred name" "a preferred name is the name that is generally used by some organization. ")
(sio-class "scientific name" "a scientific name is a name given through scientific nomenclature."))

(sio-class "legal name" "A legal name is a name given at birth, or which appears on their birth certificate, marriage certificate, or change of name certificate."
	:super personal_name)

(as-subclasses
 title
(sio-class "document title" "A document title is a  textual entity that summarizes the topic of the document in one sentence.")
(sio-class "primary title" "A primary title is a title that should be first used in describing some entity.")
(sio-class "secondary title" "A secondary title is a title of lesser importance that should be used after the first title in describing some entity."
	:annotation
	(synonym-rdf "subtitle, alternative title")))
(sio-class "graph title" "A graph title is a title that describes a graph."
	:equivalent (owl-and title (owl-some is_direct_part_of statistical_graph))
	:annotation
	(eg "Total value of permits increased slightly in December\" in Graph 1 of http://tinyurl.com/opwnvm"))

(sio-class "primary graph title" "A primary graph title is a primary title that describes a statistical \ngraph."
	:super primary_title (owl-some is_direct_part_of statistical_graph)
	:annotation
	(eg "Total value of permits increased slightly in December\" in Graph 1 of http://tinyurl.com/opwnvm"))
(sio-class "secondary graph title" "A secondary graph title is a secondary title that describes a statistical graph."
	:super primary_title (owl-some is_direct_part_of statistical_graph))

(as-subclasses
 document
(sio-class "booklet" "A booklet is a document that lacks a named publisher or sponsoring institution.")
(sio-class "diary" "A diary is a document which describes day-to-day experiences.")
(sio-class "email" "Email message is a digital document that is composed of a header and a body and is transmitted using the SMTP protocol.")
(sio-class "letter" "A letter is a document that contains a personal communication from one part to another.")
(sio-class "manuscript" "A manuscript is a document prior to publication.")
(sio-class "note" "A note is a brief document.")
(sio-class "ontology document" "an ontology document is a document that contains an ontology.")
(sio-class "patent" "A patent is an information entity granted by a patent issuing authority which confers upon the patenter the sole right to make, use and sell an invention for a set period of time.")
(sio-class "publication" "A publication is a document that has been made available by a publisher.")
(sio-class "record" "A record is a document containing a collection of statements about some entity."
	:super (owl-some has_unique_identifier identifier))
(sio-class "report" "A report is a textual document made that present focused, salient content to a specific audience.")
(sio-class "web page" "A web page is a document that is published according to World Wide Web standards."))

(as-subclasses
 ontology_document
(sio-class "OBO ontology" "an OBO ontology is an ontology document as specified by the Open Biological Ontology community.")
(sio-class "OWL ontology" "an OWL ontology is an ontology as specified by the W3C OWL specification.")
(sio-class "RDFS ontology" "An RDFS ontology is an ontology that conforms to the syntax and semantics of the Resource Description Framework Schema (RDFS)"))

(as-subclasses
 publication
(sio-class "article" "An article is a publication that is stand-alone section of a larger work.")
(sio-class "blog" "A blog is a publication accessible at some website and is typically about various experiences.")
(sio-class "book" "A book is a publication composed of a large number of entries.")
(sio-class "edited publication" "An edited publication is a publication that has been examined and potentially changed by an editor.")
(sio-class "manual" "A manual is a document that instructs on the usage of a device.")
(sio-class "novel" "A novel is a fictitious prose narrative of book length, typically representing character and action with some degree of realism.")
(sio-class "technical report" "A technical report is a publication published by a school or other institution, usually numbered within a series.")
(sio-class "thesis document" "A tehsis document is the written research component of a post-secondary institution  that contains a statement supported by arguments."))

(sio-class "peer reviewed article" "a peer reviewed article is an article that has undergone peer-review and deemed acceptable for publication."
	:super article (owl-some has_quality peer-reviewed))

(sio-class "book volume" "A book volume is a book that is part of a collection."
	:super book)
(sio-class "conference proceedings" "A conference proceedings is a book composed of papers presented at a conference."
	:super book)

(sio-class "issue" "an issue is a single instance of a periodically published journal, magazine, or newspaper."
	:super edited_publication (owl-some is_proper_part_of periodical))

(as-disjoint-subclasses
 thesis_document
(sio-class "honor's thesis" "An honor's thesis is a thesis prepared as a requirement for an honor's undergraduate degree.")
(sio-class "master's thesis" "A Master's thesis is a thesis prepared as a requirement for a Master's degree.")
(sio-class "phd thesis" "A PhD thesis is a thesis prepared as a requirement for a doctoral degree."))

(sio-class "medical health record" "A medical health record is a record of a single patient's medical history."
	:super record)
(sio-class "versioned record" "a versioned record is a record for which there exists another variant based that was derived via modification of the facts."
	:super record)

(sio-class "medical report" "a medical report is a report prepared by a health care practioner about test outcomes or health status of an individual."
	:super report)

(as-subclasses
 document_component
(sio-class "citation" "A citation is a textual entity which denotes a source described in the bibliography or reference section of a document.")
(sio-class "document section" "a document section is a component of a document."
	:super (owl-some is_proper_part_of document))
(sio-class "reference" "A reference is a textual entity that describes a single source used in the preparation or development of the work."))

(as-subclasses
 document_section
(sio-class "abstract section" "An abstract section is a document section that provides brief summary of a document that explains the main argument(s), topic(s) or findings.")
(sio-class "acknowledgements section" "An acknowledgements section is a document section that identifies individuals, groups or organizations for their support in the development of the work.")
(sio-class "author contribution section" "An author contribution section is a document section that describes the contribution of the authors.")
(sio-class "author section" "An author section is a document section that lists the contributing authors.")
(sio-class "bibliography section" "A bibliography section is a document section that is composed of a list of references used in the development of the work.")
(sio-class "chapter" "A chapter is a document section of a book or thesis.")
(sio-class "copyright section" "A copyright section is a document section that contains a notice of copyright.")
(sio-class "correspondence section" "A correspondence section is a document section that contains the details for contacting the corresponding author.")
(sio-class "discussion section" "The discussion section is a document section containing a summary of the findings, a reflection on the significance of findings, comparison with related work, among others.")
(sio-class "introduction section" "An introduction section is a document section that generally provides background, motivation and goals of the work.")
(sio-class "materials and methods section" "The materials and methods section is a document section containing a description of the materials and methods used in the study.")
(sio-class "materials section" "The materials section is a document section containing a description of the materials used in the study.")
(sio-class "methods section" "The methods section is a document section containing a description of the methods used in the study.")
(sio-class "results section" "The results section is a document section describing the main findings of the study.")
(sio-class "table of contents" "The table of contents is a document section that lists all sections (and optionally subsections) in a sequential order along with their page number."))

(sio-class "quote" "A quote is a excerpt that is attributed to a particular source."
	:super excerpt (owl-some is_derived_from textual_entity))

(as-subclasses
 mathematical_entity
(sio-class "algorithm" "An algorithm is an effective method expressed as a finite list of well-defined instructions for calculating a function.")
(sio-class "association" "an assocation is a relationship between two or more entities derived by some informational anlysis."
	:super (at-least 2 refers_to entity))
(sio-class "equation" "An equation is a mathematical statement that asserts the equality of two expressions.")
(sio-class "interval" "an interval is a set of real numbers that includes all numbers between any two numbers in the set.")
(sio-class "list" "a list is any enumeration of a set of items."
	:super (owl-some has_member entity))
(sio-class "logical operator" "a logical operator is a unary or binary relation to construct logical expressions.")
(sio-class "number" "A number is a mathematical object used to count, label, and measure.")
(sio-class "pattern" "A pattern is a generalized representation of some repeatable concrete or informational item.")
(sio-class "set" "a set is a collection of entities, for which there may be zero members."
	:super (only has_member entity))
(sio-class "variable" "a variable is a value that may change within the scope of a given problem or set of operations.")
(sio-class "set item" "set item is an item in a set."
	:super (owl-some is_member_of set)))

(sio-class "collection item" "a collection item is an item in a collection."
	:super set_item (owl-some is_member_of collection))
(sio-class "list item" "a list item is an item in a list."
	:super set_item (owl-some is_ordered_part_of list))

(sio-class "ordered list item" "an ordered list item is an item in an ordered list."
	:super list_item)

(sio-class "workflow" "A workflow is an algorithm that is is a depiction of a sequence of operations to achieve one or more objectives."
	:super algorithm)

(as-subclasses
 association
(sio-class "chemical-disease association" "a chemical-disease association is an association between a chemical and a disease."
	:super (owl-and (owl-some refers_to chemical_entity) (owl-some refers_to disease)))
(sio-class "database cross-reference" "a database cross-reference is an association between one data item and another"
	:annotation
	(synonym-rdf "dbxref"))
(sio-class "gene-disease association" "a gene-disease association is an association between a gene and a disease"
	:super (owl-some refers_to gene) (owl-some refers_to disease))
(sio-class "statistical association" "a statistical association is any relationship between two measured quantities that renders them statistically dependent.")
(sio-class "chemical-gene assocation" "a chemical-gene assocation is an assocation between a chemical and a gene."
	:super (owl-some refers_to chemical_entity) (owl-some refers_to gene))
(sio-class "chemical-pathway association" "a chemical-pathway association is an association between a chemical and a pathway."
	:super (owl-some refers_to pathway) (owl-some refers_to chemical_entity)
	:annotation
	(synonym-en "drug-pathway association")))

(sio-class "exact cross-reference" "an exact cross-reference is a database cross-reference in which one entity is equivalent to the other based on all the entitie's attributes (minus the source)"
	:super database_cross-reference)

(sio-class "gene-disease biomarker association" "a gene-disease association in which the gene/protein is involved in the etiology or maintenance of the disease."
	:super gene-disease_association)
(sio-class "therapeutic gene-disease association" "a gene disease association in which the gene is a therapeutic marker for the disease."
	:super gene-disease_association
	:annotation
	(synonym-rdf "gene-disease association arising from a therapeutic role of the gene/protein"))

(as-subclasses
 gene-disease_biomarker_association
(sio-class "gene-disease association linked with altered gene expression" "a gene-disease association in which the disease phenotype is associated with an altered expression of the gene.")
(sio-class "gene-disease association linked with genetic variation" "a gene-disease association in which a sequence variation (a mutation, a SNP) is associated with the disease."
	:annotation
	(synonym-rdf "gene variant-disease association"))
(sio-class "gene-disease association linked with post-translational modification" "a gene-disease association in which the disease phenotype is associated with post-translational modifications in the protein product."
	:annotation
	(eg "methylation or phosphorylation of protein product")))

(sio-class "gene-disease association linked with causal mutation" "a gene-variant disease association in which a mutation in the gene/protein results in the development or maintenance of the disease."
	:super gene-disease_association_linked_with_genetic_variation)

(sio-class "correlation" "a correlation is a statistical relationship involving dependence between two random variables or datasets."
	:super statistical_association)

(sio-class "differential equation" "A differential equation is a mathematical equation for an unknown function of one or several variables that relates the values of the function itself and its derivatives of various orders."
	:super equation)
(sio-class "movement equation" "a movement equation describes the displacement of an object in space over time."
	:super equation)

(sio-class "ordinary differential equation" "An ordinary differential equation (ODE) is a differential equation in which the unknown function (also known as the dependent variable) is a function of a single independent variable."
	:super differential_equation)
(sio-class "partial differential equation" "A partial differential equation (PDE) is a differential equation in which the unknown function is a function of multiple independent variables and the equation involves its partial derivatives."
	:super differential_equation)

(sio-class "diffusion equation" "A diffusion equation describes density fluctuations in a material undergoing diffusion."
	:super movement_equation (owl-some specifies diffusion))

(sio-class "page range" "A page range denotes the start and end page in some document."
	:super interval)

(as-subclasses
 list
(sio-class "data series" "A data series is a data set composed of related values displayed in a statistical graph."
	:annotation
	(eg "Example: The two series that correspond to \"Seasonally adjusted\" and \"Trend\" are composed of the seasonally adjusted value of permits in each month and values from a trend derived from some mathematical tranformation across those values, respectively, in  Graph 1 of http://tinyurl.com/opwnvm"))
(sio-class "intersection" "an intersection is a list of only the values of an attribute for the entities in the defined set where all entities have that value.")
(sio-class "sequence" "a sequence is an ordered list of entities. Like a set, it contains members (also called elements, or terms)."
	:super (owl-some has_member (owl-and entity (owl-some has_attribute ordinal_position) (owl-some refers_to entity)))
	:annotation
	(annotation (iri "http://purl.org/dc/terms/alternativeName") (literal "ordered list" :type :RDF_PLAIN_LITERAL))
	(eg "For example, (M, A, R, Y) is a sequence of letters that differs from (A, R, M, Y), as the ordering matters, and (1, 1, 2, 3, 5, 8), which contains the number 1 at two different positions, is a valid sequence. "))
(sio-class "union" "a union is a list of all of the values of an attribute for the entities in the defined set."))
(as-disjoint union intersection)

(as-subclasses
 logical_operator
(sio-class "conjunction (and)" "AND is a logical operator that has the value true if both of its operands are true, otherwise a value of false.")
(sio-class "disjunction (or)" "OR is a logical operator that results in true whenever one or more of its operands are true.")
(sio-class "implies (->)" "implication is a logical operator that holds between a set T of propositions and a proposition B, when every model (or interpretation or valuation) of T is also a model of B.")
(sio-class "negation (not)" "NOT is a logical operator in that has the value true if its operand is false."))

(sio-class "exclusive disjunction (xor)" "XOR, also called exclusive disjunction or (symbolized XOR, EOR, EXOR, or ⊕), is a type of logical disjunction on two operands that results in a value of true if exactly one of the operands has a value of true."
	:super disjunction__or_)

(sio-class "measurement value" "A measurement value is a quantitative description that reflects the magnitude of some attribute."
	:super number (owl-or (owl-some has_value :XSD_DATE_TIME) (owl-some has_value :XSD_DOUBLE) (owl-some has_value :XSD_FLOAT) (owl-some has_value :XSD_INTEGER)))

(sio-class "position" "A measurement of a spatial location relative to a frame of reference or other objects."
	:super measurement_value (owl-some has_attribute positional_identifier))
(sio-class "quantity" "A quantity is an informational entity that gives the magnitude of a property."
	:super measurement_value (owl-some is_attribute_of entity) (owl-some is_measurement_value_of entity)
	:equivalent (owl-or dimensionless_quantity dimensional_quantity))

(as-subclasses
 position
(sio-class "altitude" "Altitude is a distance above sea level.")
(sio-class "center of mass" "the center of mass (aka barycenter) is the weighted average location of all the mass in a body or group of bodies.")
(sio-class "coordinate" "A coordinate is a measurement of position in n-dimensional space.")
(sio-class "geographic position" "a geographic position is the coordinate of an entity against some geographic coordinate system.")
(sio-class "linear position" "a linear position is the position of some object against a linear positioning system.")
(sio-class "orientation" "orientation is an angle between the bearer and an axis, or the angle between the bearer and another object."))

(as-disjoint-subclasses
 coordinate
(sio-class "cartesian coordinate" "A Cartesian coordinate is the signed distance of a point to some referent line."
	:super (owl-some is_proper_part_of cartesian_coordinate_system))
(sio-class "polar coordinate" "a polar coordinate is a position characterized by a distance from a fixed point and an angle from a fixed direction."
	:super (owl-some is_proper_part_of polar_coordinate_system)))

(as-disjoint-subclasses
 cartesian_coordinate
(sio-class "3D cartesian coordinate" "a 3D cartesian coordinate is a coordinate that is composed of an x,y and z coordinate."
	:super (owl-and (exactly 1 has_direct_part x_cartesian_coordinate) (exactly 1 has_direct_part y_cartesian_coordinate) (exactly 1 has_direct_part z_cartesian_coordinate)))
(sio-class "x cartesian coordinate" "an x cartesian coordinate is the coordinate of an object onto the x-axis of a cartesian coordinate system.")
(sio-class "y cartesian coordinate" "an y cartesian coordinate is the coordinate of an object onto the y-axis of a cartesian coordinate system.")
(sio-class "z cartesian coordinate" "a z cartesian coordinate is the coordinate of an object onto the z-axis of a cartesian coordinate system."))

(as-subclasses
 geographic_position
(sio-class "latitude" "Latitude is a geographic coordinate which refers to the angle from a point on the Earth's surface to the equatorial plane")
(sio-class "longitude" "Longitude is a geographic position that refers to  the angle east or west of a reference meridian between the two geographical poles to another meridian that passes through an arbitrary point.")
(sio-class "postal code" "a postal code is a geographic coordinate composed of a series of letters and/or digits appended to a postal address for the purpose of sorting mail."))
(as-disjoint longitude latitude)

(as-subclasses
 linear_position
(sio-class "end position" "an end position is the distal position of an object relative to an origin in a linear system."
	:annotation
	(synonym-rdf "stop position"))
(sio-class "ordinal position" "A ordinal position is a number that designates the position of an entity from the first entity in an ordered sequence of entities."
	:annotation
	(synonym-rdf "offset"))
(sio-class "sequence element position" "a sequence element position is the position of an element of a linear sequence.")
(sio-class "start position" "a start position is the proximal position of an object relative to an origin in a linear system."))

(sio-class "sequence end position" "a sequence end position is the position of the last character in a sequence of characters relative to some linear frame of reference."
	:super sequence_element_position end_position)
(sio-class "text span end position" "text span end position is the position (offset) of the last character of a text span in relation the text it is from."
	:super end_position)

(sio-class "word end position" "word end position is the position of the last character in a word as an offset from the first character of the text in which it is found."
	:super text_span_end_position)

(sio-class "character position" "the ordinal position of a character in a sequence of characters."
	:super ordinal_position (only has_value :XSD_INTEGER)
	:annotation
	(synonym-rdf "character offset"))
(sio-class "process number" "process number is a number associated with a process that denotes its ordinal position in a set of processes."
	:super ordinal_position)

(sio-class "sequence start position" "a sequence start position is the start position for a sequence of characters."
	:super sequence_element_position start_position)
(sio-class "text span start position" "text span start position is the position (offset) of the first character of a text span in relation the text it is from."
	:super start_position)

(sio-class "word start position" "the position of the first character in a word as an offset from the first character of the text in which it is found."
	:super text_span_start_position (owl-some is_part_of textual_entity))

(as-subclasses
 quantity
(sio-class "centrality measure" "a central tendency measure is a central value or a typical value for a probability distribution.")
(sio-class "dimensional quantity" "A dimensional quantity is a quantity that has an associated unit."
	:super (owl-some has_unit unit_of_measurement))
(sio-class "dimensionless quantity" "A dimensionless quantity is a quantity that has no associated unit."
	:super (owl-not (owl-some has_unit unit_of_measurement)))
(sio-class "maximal value" "a maximal value is largest value of an attribute for the entities in the defined set."
	:super (owl-some is_attribute_of collection)
	:annotation
	(synonym-rdf "max"))
(sio-class "minimal value" "a minimal value is smallest value of an attribute for the entities in the defined set."
	:super (owl-some is_attribute_of collection)
	:annotation
	(synonym-rdf "min"))
(sio-class "standard deviation" "a standard deviation (represented by the symbol σ) is the quantity of  variation from the average (mean, or expected value).")
(sio-class "sum" "a sum is the result of adding a set of values together.")
(sio-class "uncertainty value" "the uncertainty value (margin of error) of a measurement is a range of values likely to enclose the true value. ")
(sio-class "unit of measurement" "A unit of measurement is a definite magnitude of a physical quantity, defined and adopted by convention and/or by law, that is used as a standard for measurement of the same physical quantity.")
(sio-class "likelihood" "Likelihood is the hypothetical probability that an event that has already occurred would yield a specific outcome."))
(as-disjoint dimensionless_quantity dimensional_quantity)

(sio-class "log likelihood" "Log likelihood is the natural logarithm of the likelihood function"
	:super likelihood)

(as-subclasses
 centrality_measure
(sio-class "mean" "a mean is the central tendency of a collection of numbers taken as the sum of the numbers divided by the size of the collection."
	:annotation
	(synonym-rdf "average")
	(synonym-rdf "arithmeritic mean"))
(sio-class "median" "a median is the numerical value separating the higher half of a sample, a population, or a probability distribution, from the lower half. ")
(sio-class "mode" "a mode is the value that appears most often in a set of data."))

(as-subclasses
  dimensional_quantity
(sio-class "dose" "A dose is the quantity of a chemical substance administered to a biological system.")
(sio-class "gene expression value" "a gene expression value is a measured value obtained from a gene expression experiment.")
(sio-class "spatial quantity" "a spatial quantity is a quantity obtained from measuring the spatial extent of an entity "
	:super (owl-some is_attribute_of object) (owl-some has_measurement_value quantity)
	:annotation
	(synonym-en "physical dimensional quantity"))
(sio-class "time measurement" "time measurement is a measurement value of the duration of some interval of time or a particular instant of time (against some frame of reference)."
	:super (owl-some has_value :XSD_DATE_TIME)
	:annotation
	(owl-comment (literal "time intervals are specified as date/datetime ranges." :type :RDF_PLAIN_LITERAL))
	(eg "the duration of my life; the duration of a surgical procedure, the moment of death")))

(sio-class "effective dose" "effective dose is the amount of a substance required to produce an effect on a predefined percentage of a population."
	:super dose)

(as-subclasses
 spatial_quantity
(sio-class "1D extent quantity" "a quantity that extends in single dimension")
(sio-class "2D extent quantity" "a quantity that extends in two dimensions")
(sio-class "3D extent quantity" "a quantity that extends in three dimensions")
(sio-class "mass" "mass is the quality of the amount of substance."
	:super (owl-some is_attribute_of material_entity)))

(as-subclasses
 _1D_extent_quantity
(sio-class "depth" "depth is the dimensional extent into a plane of a 3D projection of the object.")
(sio-class "height" "height is the one dimensional extent along the vertical projection of a 3D object from a base plane of reference.")
(sio-class "length" "length is the longer dimensional extent along a 2D projection of the object.")
(sio-class "width" "width is the shorter dimensional extent perpendicular to a 2D projection of the object."))

(sio-class "thickness" "thickness is the shortest dimensional extent of a 3D projection of an object."
	:super depth)

(sio-class "area" "area is a quantity that pertains to the extent of a two-dimensional surface or shape, or planar lamina, in the plane."
	:super _2D_extent_quantity)
(sio-class "length of perimeter" "A perimeter is a length of the outline that surrounds a two-dimensional shape. "
	:super _2D_extent_quantity)

(sio-class "circumference" "circumference is the length of the outline of a circle or ellipse. it is defined as c = 2*pi*r, where r is the radius."
	:super length_of_perimeter)

(sio-class "concentration" "concentration is the quantity of a constituent divided by the total volume of a mixture."
	:super _3D_extent_quantity (owl-some is_attribute_of (owl-and heterogeneous_substance (owl-some is_proper_part_of chemical_substance))))
(sio-class "volume" "volume is the quantity of three-dimensional space enclosed by some closed boundary."
	:super _3D_extent_quantity)

(sio-class "time instant" "a time instant is a temporal region which occurs instantaneously e.g. having no duration."
	:super time_measurement
	:annotation
	(eg "at this moment; the moment at which a finger is detached in an industrial accident; the moment at which a child is born; the moment of death"))
(sio-class "time interval" "a time internval is a contiguous temporal region having some duration."
	:super time_measurement)

(as-subclasses
 time_instant
(sio-class "date of database submission" "a date of database submission refers to the moment in time in which some information was submitted/received to a database system.")
(sio-class "end date" "an end date is a time instant pertaining to date of the end of a process.")
(sio-class "end time" "a end time is a time instant pertaining to the time at which a process ends.")
(sio-class "start date" "a start date is a time instant pertaining to the date of the beginning of a process.")
(sio-class "start time" "a start time is a time instant pertaining to the time at which a process begins."))

(as-disjoint-subclasses
 time_interval
(sio-class "century" "a century is a period of one hundred years.")
(sio-class "day" "A day is a period of 24 hours.")
(sio-class "hour" "an hour is a period of 60 minutes.")
(sio-class "millenium" "a millenium is a period of 1000 years")
(sio-class "minute" "a minute is a period of 60 seconds.")
(sio-class "month" "a month is a period of time that divides the year.")
(sio-class "second" "a second (symbol: s) is the base unit of time in the International System of Units (SI) and is the second division of the hour by sixty, the first division by 60 being the minute.")
(sio-class "year" "a year is a period of time taken by a planet to make one revolution around the sun."))

(as-subclasses
 dimensionless_quantity
(sio-class "count" "The number of elements of a finite set of objects.")
(sio-class "pH" "pH is a measure of the activity of the (solvated) hydrogen ion. "
	:annotation
	(owl-comment (literal "pH is defined as the decimal logarithm of the reciprocal of the hydrogen ion activity, aH+, in a solution." :type :RDF_PLAIN_LITERAL)))
(sio-class "probability measure" "a probability measure is quantity of how likely it is that some event will occur.")
(sio-class "ratio" "a ratio is a relationship between two numbers of the same kind expressed arithmetically as a dimensionless quotient of the two which explicitly indicates how many times the first number contains the second."))

(as-subclasses
 count
(sio-class "age" "age is the length of time that a person has lived or a thing has existed.")
(sio-class "difference in number of objects produced" "a difference in number of objects produced is a count of the number of objects produced with respect to a second variable (space, time, etc)")
(sio-class "edition number" "An edition number is count of a literary work edited and published, as by a certain editor or in a certain manner including being printed during some interval of time.")
(sio-class "generation number" "generation number is a count of the number of biological reproduction events elapsed from some starting reference point.")
(sio-class "member count" "a count of the instances of a class or members in a collection.")
(sio-class "number of objects consumed" "number of objects consumed is a count of objects that were consumed in some process.")
(sio-class "number of objects produced" "number of objects produced is a count of objects that were produced in some process.")
(sio-class "page number" "a page number is the count of a page in a sequence of pages.")
(sio-class "page total" "A page total is a textual entity that is about the number of pages in some informational entity.")
(sio-class "volume number" "volume number is a count of a sequence of periodicals."))

(sio-class "decrease in number of objects produced" "an decrease in the number of objects produced is the negative value of a difference in number of objects produced."
	:super difference_in_number_of_objects_produced)
(sio-class "increase in number of objects produced" "an increase in the number of objects produced is the positive value of a difference in number of objects produced."
	:super difference_in_number_of_objects_produced)

(as-subclasses
 probability_measure
 (sio-class "expected value" "an expected value (or e-value) is the weighted average of all possible values that a random variable can take on.")
(sio-class "probability value" "a p-value or probability value is the probability of obtaining a test statistic at least as extreme as the one that was actually observed, assuming that the null hypothesis is true"
	:super (owl-some has_value (span >=< 0.0 1.0))
	:annotation
	(synonym-en "p-value"))
(sio-class "standard score" "A standard score is the (signed) number of standard deviations an observation or datum is above the mean. "
	:annotation
	(synonym-en "z-value, z-score, normal score, standardadized variable.")))

(as-subclasses
 ratio
(sio-class "differential gene expression ratio" "a differential gene expression ratio is the ratio of gene expression values from a test sample compared to a control sample.")
(sio-class "slope" "a slope or gradient of a line describes its steepness, incline, or grade. A higher slope value indicates a steeper incline. Slope is normally described by the ratio of the 'rise' divided by the 'run' between two points on a line.")
(sio-class "t-statistic" "a t-statistic is a ratio of the departure of an estimated parameter from its notional value and its standard error. "))

(sio-class "t-statistic based decreased differential gene expression" "a t-statistic based decreased differential gene expression is a differential gene expression ratio in which the t-statistic is less than zero."
	:super differential_gene_expression_ratio (owl-some has_attribute (owl-and t-statistic (owl-some has_value (span < 0.0)))))
(sio-class "t-statistic based increased differential gene expression" "a t-statistic based increased differential gene expression is a differential gene expression ratio in which the t-statistic is greater than zero."
	:super differential_gene_expression_ratio (owl-some has_attribute (owl-and t-statistic (owl-some has_value (span > 0.0)))))

(as-disjoint-subclasses
 pattern
(sio-class "sequence motif" "a sequence motif is a pattern of nucleotides in a DNA sequence or amino acids in a protein")
(sio-class "structural motif" "a structural motif is a pattern in a structure formed by the spatial arrangement of atoms."))

(as-subclasses
 set
(sio-class "class" "A class is a collection of sets which can be unambiguously defined by a property that all its members share.")
(sio-class "collection" "A collection is a set for which there exists at least one member, although any member need not to exist at any point in the collection's existence."
	:super (owl-some has_member entity))
(sio-class "empty set" "An empty set is a set for which there are exactly 0 members."
	:super (exactly 0 has_member entity))
(sio-class "set interval" "an interval is a set of real numbers with the property that any number that lies between thwo numbers in the set is also included in the set. "
	:annotation
	(eg "The set of all numbers x satisfying 0<=x<=1 is an interval which contains 0 and 1, as well as numbers between them.")))

(as-subclasses
 set_interval
(sio-class "right closed interval" "a right closed interval is an interval in which there is a real number that is larger than all of its elements.")
(sio-class "left closed interval" "a left closed interval is an interval in which there is a real number that is smaller than all its elements.")
(sio-class "left open interval" "a left open interval is an interval in which there is no element that is smaller than all other elements.")
(sio-class "right open interval" "a right open interval is an interval in which there is no element that is greater than all other elements."))

(sio-class "closed interval" "A closed interval is an interval that includes its endpoints, and is denoted with square brackets."
	:super right_closed_interval left_closed_interval
	:annotation
	(eg "[0,1] is a closed interval that is greater than or equal to 0 and less than or equal to 1."))

(sio-class "open interval" "an open interval is an interval that does not include its endpoints."
	:super left_open_interval right_open_interval
	:annotation
	(eg "(0,1) is an open interval that is greater than 0 and less than 1."))

(as-subclasses
 collection
(sio-class "catalog" "a catalog is a systemic collection of items of the same type."
	:annotation
	(synonym-en "registry"))
(sio-class "collection of documents" "a collection of documents is a non-zero set of documents."
	:super (at-least 2 has_component_part document)
	:annotation
	(equivalent-rdf "http://purl.org/ontology/bibo/Collection")) ;;uri?
(sio-class "collection of points" "a collection of points is a geometric entity that contains a non-zero set of geometric points. "
	:super geometric_entity (only has_member point)))

(as-subclasses
 collection_of_documents
(sio-class "book series" "A book series is a collection of books that have been sequentially published.")
(sio-class "periodical" "A periodical is a publication that appears on a regular schedule."
	:annotation
	(equivalent-rdf "http://purl.org/ontology/bibo/Periodical")) ;;uri?
(sio-class "website" "A website is a collection of documents published on the World Wide Web."
	:super (owl-some has_direct_part web_page)))

(as-subclasses
 periodical
(sio-class "journal" "A journal is a a peer-reviewed periodical in which scholarship relating to a particular academic discipline is published."
	:annotation
	(equivalent-uri "http://purl.org/ontology/bibo/Journal"))
(sio-class "magazine" "A magazine is a periodical that typically contains essays, stories, poems, etc., by many writers, and often photographs and drawings, frequently specializing in a particular subject or area, as hobbies, news, or sports."
	:annotation
	(equivalent-rdf "http://purl.org/ontology/bibo/Magazine")) ;;uri?
(sio-class "newspaper" "a newspaper is a periodical publication containing news regarding current events, informative articles, diverse features, editorials, and advertising."
	:annotation
	(equivalent-rdf "http://purl.org/ontology/bibo/Newspaper"))) ;;uri?

(as-subclasses
 variable
(sio-class "control variable" "A control variable that is believed to alter the dependent or independent variables, but may not actually be the focus of the experiment. So that variable will be kept constant or monitored to try to minimise its effect on the experiment."
	:annotation
	(synonym-rdf "extraneous variable")
	(synonym-rdf "controlled variable"))
(sio-class "dependent variable" "A dependent variable is one whose value changes as a consequence of changes in other values in the system")
(sio-class "independent variable" "an independent variable is a variable that may take on different values independent of other elements in a system."))

(sio-class "parameter" "A parameter is variable whose value changes the characteristics of a system or a function."
	:super independent_variable)

(sio-class "default parameter" "a default parameter is a parameter which has a default value."
	:super parameter)

(sio-class "audio recording" "an audio recording is an electrical or mechanical inscription and re-creation of sound waves, such as spoken voice, singing, instrumental music, or sound effects."
	:super media)

(as-subclasses
 media
(sio-class "figure" "A figure is a graphical entity which consists of a visual (n-dimentional) arrangement of information entities."
	:annotation
	(synonym-en "diagram"))
(sio-class "figure part" "a figure part is a part of a figure."
	:super (owl-some is_proper_part_of figure))
(sio-class "movie" "A movie is a series of images that are displayed in rapid succession  to give the impression of movement."
	:annotation
	(synonym-en "film")
	(synonym-en "moving pictures"))
(sio-class "slideshow" "a slideshow is a visual presentation of information contained within a collection of slides.")
(sio-class "television program" "a television program is a audiovisual media that is produced and broadcast using a television."))
(as-disjoint figure_part figure)

(as-subclasses
 figure
(sio-class "chart" "A chart is a figure that displays the relationship among tabular numeric data, functions or some kinds of qualitative structures.")
(sio-class "image" "An image is an affine projection of a visual entity to a two dimensional surface."
	:annotation
	(synonym-en "depiction"))
(sio-class "table" "A table is a figure that consists of an ordered arrangement of columns and rows."))

(as-subclasses
 chart
(sio-class "Gantt chart" "A Gantt chart is a bar chart that illustrates a project schedule.")
(sio-class "bubble chart" "A bubble chart contains circles whose area corresponds to a value. ")
(sio-class "flowchart" "A flowchart is a diagram that represents an algorithm or process, showing the steps as boxes of various kinds, and their order by connecting these with arrows.")
(sio-class "heatmap" "A heatmap is a graphical representation of data where the individual values contained in a matrix are represented as colors.")
(sio-class "histogram" "a histogram is a graphical representation of data which consists of tabular frequencies, shown as adjacent rectangles, over discrete intervals (bins) , with an area equal to the frequency of the observations in the interval.")
(sio-class "map" "A map is a a visual representation of an area that depicts the relationship between elements of that space.")
(sio-class "matrix chart" "A matrix chart summarizes a multidimensional data set in a grid.")
(sio-class "mereological chart" "a mereological chart is a chart that illustrates the parts in the context of the whole.")
(sio-class "network diagram" "A network diagram consists of a set of vertices connected by edges.")
(sio-class "statistical graph" "A statistical graph is a figure that displays the relationship among numeric data and/or mathematical functions."
	:annotation
	(synonym-rdf "chart"))
(sio-class "textual chart" "a textual chart is a chart containing text"
	:super (owl-some has_direct_part textual_entity))
(sio-class "venn diagram" "A Venn diagram is a chart that illustrates all possible logical relations between a finite collection of sets as overlapping circles."))

(sio-class "geographic heatmap" "A geographic heatmap is a graphical representation of data over a geographic region where individual values are represented as colors."
	:super heatmap)

(sio-class "block histogram" "A block histogram contains an x-axis that is divided into bins which correspond to value ranges. Each item in the data set is drawn as a rectangular block, and the blocks are piled into the bins to show how many values in each range."
	:super histogram)

(sio-class "pie chart" "A pie chart is a circular chart divided into sectors each of whose length  is proportional to the quantity it represents."
	:super mereological_chart)
(sio-class "treemap" "a treemap is a chart that fully partitions the area into a set of rectangles whose area represents its relative value."
	:super mereological_chart)

(sio-class "tree diagram" "A tree diagram is a hierarchical network diagram in which a root vertex is connected to one or more other vertices through a directed edge, which in turn may be connected to other vertices."
	:super network_diagram)

(sio-class "dendrogram" "A dendrogram is a tree diagram used to illustrate the arrangement of the clusters produced by hierarchical clustering."
	:super tree_diagram)

(as-subclasses
 statistical_graph
(sio-class "bar graph" "A bar graph is a statistical graph with rectangular bars of lengths proportional to that value that they represent."
	:annotation
	(eg "Graphs 3,4,5 in http://tinyurl.com/opwnvm"))
(sio-class "line graph" "A line graph is a statistical graph in which lines contains the evaluation of functions or individual points connected by line segments."
	:annotation
	(synonym-en "line chart")
	(eg "Graph 1 in http://tinyurl.com/opwnvm"))
(sio-class "line-bar graph" "A line-bar graph statistical graph that contains both lines and bars."
	:annotation
	(eg "Graph 8 in http://tinyurl.com/opwnvm"))
(sio-class "scatterplot" "A scatterplot is a statistical graph which uses Cartesian coordinates to display values for two variables for a set of data. The data is displayed as a collection of points, each having the value of one variable determining the position on the horizontal axis and the value of the other variable determining the position on the vertical axis."
	:annotation
	(synonym-en "scatter diagram")
	(synonym-en "scatter graph")
	(synonym-en "scatter chart"))
(sio-class "stack graph" "A stack graph is a statistical graph which presents multiple series in which the distance between one series and another indicates the relative contribution to the total for any x-value."))

(as-subclasses
 bar_graph
(sio-class "horizontal bar graph" "A horizontal bar graph is a bar graph in which the rectangular bars \nare horizontally oriented in space."
	:annotation
	(eg "Graph 3 in http://tinyurl.com/opwnvm"))
(sio-class "stacked bar graph" "A stacked bar graph is a bar graph in which each rectangular bar is \npartioned by the categorical value of each series of data."
	:annotation
	(eg "Graphs 6,7 in http://tinyurl.com/opwnvm"))
(sio-class "vertical bar graph" "A vertical bar graph is a bar graph in which the rectangular bars are \nvertically oriented in space."
	:annotation
	(eg "Graph 4,5 in http://tinyurl.com/opwnvm")))

(sio-class "boxplot" "A boxplot (box-and-whisker diagram) is a convenient way of graphically depicting groups of numerical data through their five-number summaries: the smallest observation (sample minimum), lower quartile (Q1), median (Q2), upper quartile (Q3), and largest observation (sample maximum)."
	:super line-bar_graph)

(sio-class "steamgraph" "a steamgraph is a multi-line stacked graph that yields the appearance of continuous y-values across the x-axis."
	:super stack_graph)

(as-subclasses
 textual_chart
(sio-class "phrase net diagram" "A phrase net diagram illustrates the relationship between different words used in a text.")
(sio-class "tag cloud" "a tag cloud is a visualization of word frequencies."
	:annotation
	(synonym-en "word cloud"))
(sio-class "word tree" "a word tree is a chart that links phrases with contexts through a tree-like branching structure."))

(sio-class "photograph" "A photograph is an image created by light falling on a light-sensitive surface."
	:super image
	:annotation
	(synonym-en "photo"))

(sio-class "geographic image" "A geographic image is a photograph of some geographical area."
	:super photograph)

(as-disjoint-subclasses
 figure_part
(sio-class "legend" "A legend is a part of a figure that associates textual descriptions with symbols pertaining to plotted entities."
	:annotation
	(eg "The colored line segments and the adjacent textual descriptions in \nGraph 1 of http://tinyurl.com/opwnvm"))
(sio-class "plot" "A plot is a part of a figure that corresponds to the spatial region between the set of axes."
	:annotation
	(eg "The square portion of the chart bounded by lines that overlap with \nthe two axes in Graph 1 of http://tinyurl.com/opwnvm")))

(sio-class "model" "A model is a representation of some thing."
	:super representation (owl-some represents entity))
(sio-class "symbol" "A symbol is a proposition about what an entity represents."
	:super representation (owl-some represents entity) (owl-some is_about entity))

(as-disjoint-subclasses
 model
(sio-class "process model" "a process model is a representation of some process."
	:super (owl-some is_model_of process))
(sio-class "object model" "an object model is a representation of an object."))

(sio-class "social relation" "a social relation is a social entity that describes a relationship  between two or more individuals or groups. "
	:super social_entity)
(sio-class "social structure" "A social structure is a social entity which consists of relationships between two or more entities."
	:super social_entity (only has_member object))

(sio-class "affiliation" "An affiliation is a social relation which indicates the partnership between two or more entities."
	:super social_relation)

(sio-class "collective" "A collective is a group of entities that share or are motivated by at least one common issue or interest, or work together on a specific project(s) to achieve a common objective."
	:super social_structure
	:annotation
	(synonym-rdf "group"))

(as-subclasses
 collective
(sio-class "community" "a community is a sizeable social unit that shares common values.")
(sio-class "family" "a group of people affiliated by consanguinity, affinity, or co-residence.")
(sio-class "organization" "An organization is a collective with a complex articulation of tasks, roles and responsibilities.")
(sio-class "population" "A population is all the organisms that both belong to the same group or species and live in the same geographical area.")
(sio-class "study group" "a study group is a group of individuals that are subjects in an observational or intervention study."))

(as-subclasses
 organization
(sio-class "academic organization" "an academic organization is a lawfully recognized organization that confers diplomas, degrees and other forms of recognition of academic achievement.")
(sio-class "corporation" "A corporation is an organization that is granted a charter recognizing it as a separate legal entity.")
(sio-class "institute" "institute is a society or organization having a object or common factor, and is normally applied to those with a scientific, educational, or social objective.")
(sio-class "regulatory authority" "A regulatory authority is an organization responsible for  exercising regulatory or supervisory capacity in some area of human activity."))

(sio-class "academic department" "An academic department is a division of a university or school faculty devoted to a particular academic discipline."
	:super academic_organization)
(sio-class "university" "A university is an institution of higher education and research which grants academic degrees in a variety of subjects and provides both undergraduate education and postgraduate education. "
	:super academic_organization)

(sio-class "drug regulatory authority" "A drug regulatory authority is a regulatory authority which acts to control what substances may be used to treat individuals."
	:super regulatory_authority)

(sio-class "human population" "a human population refers to a collection of human beings."
	:super population)

(sio-class "ethnic group" "an ethnic group is a group of people whose members identify with each other through a common heritage, consisting of a common culture, including a shared language or dialect. "
	:super human_population)

(sio-class "control group" "a control group is a group of individuals that are not subject to an intervention of interest, but rather serve as a baseline to compare the outcomes in the intervention group."
	:super study_group)
(sio-class "intervention group" "An intervention group is a group of individuals that are subject to an intervention."
	:super study_group)

(as-subclasses
 material_entity
(sio-class "chemical entity" "A chemical entity is a material entity that pertains to chemistry."
	:annotation
	(subset-rdf "chemical+")
	(eg "atom, ion, molecule, chemical substance,")
	(equivalent-rdf "CHEBI:23367"))
(sio-class "heterogeneous substance" "a heterogeneous substance is a chemical substance that is composed of more than one different kind of component."
	:super chemical_substance
	:disjoint homogeneous_substance)
(sio-class "specialized material entity" "a specialized material entity is a material entity that is defined by having some quality, role or capability"
	:equivalent (owl-and material_entity (owl-some has_realizable_property realizable_entity))))

(as-subclasses
 chemical_entity
(sio-class "atom" "An atom is composed of a core of protons and/or neutrons which may be surrounded by a cloud of electrons.")
(sio-class "chemical substance" "A chemical substance is a chemical complex of weakly interacting molecular entities, and may include bulk solvent."
	:equivalent (owl-or heterogeneous_substance homogeneous_substance)
	:annotation
	(see-also-rdf "CHEMINF:440533"))
(sio-class "covalently connected entity" "A covalently connected molecular entity is the mereological sum of a collection of covalently bonded atoms."
	:super (owl-some has_part (owl-and atom (owl-some is_covalently_connected_to atom))) (owl-some has_component_part covalent_bond)
	:disjoint submolecular_entity chemical_complex)
(sio-class "ion" "An ion is an atom or molecule in which the total number of electrons is not equal to the total number of protons, giving it a net positive or negative electrical charge."
	:equivalent (owl-and (owl-or atom molecule) (owl-some has_quality charge_quality)))
(sio-class "molecular complex" "a molecular complex is a chemical complex composed of weakly interacting molecular entities, and excludes bulk solvent."
	:super (owl-or (owl-some has_direct_part molecular_complex) (at-least 2 has_direct_part molecule)))
(sio-class "submolecular entity" "A submolecular entity is a part of a molecular entity."
	:super (owl-some is_part_of chemical_entity)
	:disjoint covalently_connected_entity chemical_complex))

(sio-class "anion" "An anion is an atom or molecule with a net negative electrical charge."
	:equivalent (owl-and ion (owl-some has_quality negative_charge)))
(sio-class "cation" "An anion is an atom or molecule with a net positive electrical charge."
	:equivalent (owl-and ion (owl-some has_quality positive_charge)))

(as-subclasses
 chemical_substance
(sio-class "binary compound" "a binary compound is a mereological maximum sum of two kinds of weakly connected entities."
	:super (exactly 2 has_direct_part molecule))
(sio-class "centrifugation substance" "a centrifugation substance is a substance that is the target or product of centrifugation.")
(sio-class "chemical complex" "a chemical complex is a chemical entity composed of a weakly connected ions or molecules."
	:super (owl-some has_part (owl-some is_weakly_interacting_with material_entity))
	:disjoint submolecular_entity covalently_connected_entity)
(sio-class "homogeneous substance" "a homogeneous substance is a substance that is composed of a uniform type of entity."
	:disjoint heterogeneous_substance))

(sio-class "ionic compound" "an ionic compound is a mereological maximal sum of weakly connected paired positive and negative ions."
	:super binary_compound (exactly 1 has_direct_part cation) (exactly 1 has_direct_part anion))

(as-disjoint-subclasses
 centrifugation_substance
(sio-class "centrifugation pellet" "a centrifugation pellet is a solid substance that forms as a result of compaction by a centrifuge.")
(sio-class "supernatant" "a supernatent is a liquid substance that remains after centrifugation."))

(as-subclasses
heterogeneous_substance
(sio-class "biological entity" "a biological entity is a heterogeneous substance that contains genomic material or is the product of a biological process."
	:annotation
	(subset-rdf "chemical-"))
(sio-class "device" "a device is usually a constructed tool")
(sio-class "liquid solution" "a liquid solution is a heterogeneous substance in a liquid state.")
(sio-class "liquid solution component" "a liquid solution component is a part of a liquid solution.")
(sio-class "placebo" "A placebo is a medically ineffectual treatment for a medical condition intended to deceive the recipient.")
(sio-class "reagent" "A reagent is a substance that is added to a system in order to bring about a chemical reaction, or added to see if a reaction occurs.")
(sio-class "sample" "a sample is a limited quantity of something (e.g. an individual or set of individuals from a population, or a portion of a substance) to be used for testing, analysis, inspection, investigation, demonstration, or trial use."
	:super (owl-or (owl-some is_member_of collection) (owl-some is_derived_from object)))
(sio-class "wave" "A wave is a physical entity that travels through space and time, consist of oscillations or vibrations and may be accompanied by the transfer of energy."))

(as-subclasses
biological_entity
(sio-class "biological fluid" "a biological fluid is a fluid of biological origin.")
(sio-class "cell" "a cell is a biological entity that is contained by a plasma membrane.")
(sio-class "cell line" "A cell line is a collection of genetically identifical cells.")
(sio-class "genome" "a genome is a collection of nucleic acids.")
(sio-class "organ" "an organ is a collection of tissues joined in structural unit to serve a common function.")
(sio-class "organism" "a biological organisn is a biological entity that consists of one or more cells and is capable of genomic replication (independently or not)."
	:equivalent (owl-or cellular_organism non-cellular_organism))
(sio-class "tissue" "a tissue is a mereologically maximal collection of cells that together perform some function."
	:super (owl-some has_direct_part cell)))

(as-subclasses
 organism
(sio-class "cellular organism" "a cellular organism is an organism that contains one or more cells.")
(sio-class "host" "a host is an organism that harbors a parasite, or a mutual or commensal symbiont, typically providing nourishment and shelter.")
(sio-class "non-cellular organism" "a non-cellular organism is an organism that does not contain a cell.")
(sio-class "pathogen" "A pathogen or infectious agent  is a microorganism that causes disease in its host.")
(sio-class "strain" "A strain is a genetic variant or kind of microorganism."
	:super (owl-some is_variant_of organism)))
(as-disjoint non-cellular_organism cellular_organism)

(as-disjoint-subclasses
 cellular_organism
 :cover
(sio-class "multicellular organism" "a multi-cellular organism is an organism that consists of more than one cell."
	:super (at-least 2 has_proper_part cell))
(sio-class "unicellular organism" "a unicellular organism is a organism that is composed of a single cell."
	:super cell (owl-not (owl-some is_proper_part_of biological_entity))))

(as-subclasses
 multicellular_organism
(sio-class "human" "a human is a primates of the family Hominidae and are characterized by having a large brain relative to body size, with a well developed neocortex, prefrontal cortex and temporal lobes, making them capable of abstract reasoning, language, introspection, problem solving and culture through social learning.")
(sio-class "mouse" "A mouse is a small mammal belonging to the order of rodents, characteristically having a pointed snout, small rounded ears, and a long naked or almost hairless tail. ")
(sio-class "rat" "a rat is a medium-sized, long-tailed rodent of the superfamily Muroidea.")
(sio-class "worm" "a worm is a non-arthropod invertebrate animal that typically have a long cylindrical tube-like body and no legs."))

(sio-class "person" "A person is a being that has certain capacities or attributes constituting personhood"
	:super human)

(as-subclasses
 person
(sio-class "academic" "an academic is an individual that participates in education and scholarship.")
(sio-class "medical practitioner" "a medical practioner is an individual that provides medical care.")
(sio-class "patient" "a patient is an individual that is the recepient of medical care."
	:super (owl-some has_role patient_role))
(sio-class "study subject" "a study subject is an individual that is the subject of the study."))

(sio-class "professor" "a professor is an individual that is a scholarly teacher."
	:super academic)
(sio-class "student" "a student is an individual who is attends an educational institution."
	:super academic)

(sio-class "doctor" "a doctor is an individual who practices medicine, which is concerned with promoting, maintaining or restoring human health through the study, diagnosis, and treatment of disease, injury, and other physical and mental impairments."
	:super medical_practitioner (owl-some has_role doctor_role))
(sio-class "nurse" "A nurse is an individual that is involved in the protection, promotion, and optimization of health and abilities, prevention of illness and injury, alleviation of suffering through the diagnosis and treatment of human response, and advocacy in the care of individuals, families, communities, and populations."
	:super medical_practitioner (owl-some has_role nurse_role))

(sio-class "viroid" "a viroid is a molecule of RNA that does not code for and is not protected by a protein coat."
	:super non-cellular_organism)
(sio-class "virus" "A virus is a non-cellular organism that can replicate only inside the living cells of an organism."
	:super non-cellular_organism)

(as-subclasses
 device
(sio-class "communication device" "a communication device is a device that facilitates the transmission of information through encoded in an audio or digital signal between a sender and a receiver.")
(sio-class "data collection device" "a data collection device is a device that collects information about one or more objects.")
(sio-class "data storage device" "a data storage device is a device that is capable of storing information.")
(sio-class "radar" "a radar is an object-detection system which uses radio waves to determine the range, altitude, direction, or speed of objects."))

(sio-class "radio receiver" "A radio receiver is a communication device that receives its input from an antenna, uses electronic filters to separate a wanted radio signal from all other signals picked up by this antenna, amplifies it to a level suitable for further processing, and finally converts through demodulation and decoding the signal into a form usable for the consumer, such as sound, pictures, digital data, measurement values, navigational positions."
	:super communication_device)
(sio-class "telephone" "The telephone is a communications device that transmits and receives sounds, and are minimally composed of a microphone to speak into, a speaker'which reproduces the voice of the other person and a ringer which makes a sound to alert the owner when a call is coming in."
	:super communication_device)

(as-subclasses
 data_collection_device
(sio-class "mass spectrometer" "a mass spectrometer is a device that identifies ions based on their mass to charge ratio using an electromagnetic field."
	:annotation
	(similar-uri "http://purl.obolibrary.org/obo/OBI_0000049"))
(sio-class "microarray device" "a microarray device is a device that identifies the binding of a target substance to a physically immobile substrate placed in an array or lattice."
	:annotation
	(synonym-en "microarray platform")
	(similar-uri "http://purl.obolibrary.org/obo/OBI_0000052"))
(sio-class "nmr device" "a nuclear magnetic resonance (NMR) device is a device that applies a magnetic field to perturb nuclei with an odd number of protons and/or of neutrons in order to hav them absort and re-emit electromagnetic radiation. "
	:annotation
	(equivalent-uri "http://purl.obolibrary.org/obo/OBI_0000566")
	(synonym-en "nuclear magnetic resonance device")))

(sio-class "hard disk drive" "A hard disk drive (HDD) is a non-volatile, random access device for digital data. It features rotating rigid platters on a motor-driven spindle within a protective enclosure. Data is magnetically read and written on the platter by read/write heads that float on a film of air above the platters."
	:super data_storage_device)

(sio-class "solid state hard drive" "A solid-state drive (SSD) is a data storage device that uses solid-state memory to store persistent data"
	:super hard_disk_drive)

(as-subclasses
 liquid_solution_component
(sio-class "acid" "An acid is a molecular entity in solution capable of donating a hydron (Bronsted acid) or capable of forming a covalent bond with an electron pair (Lewis acid).")
(sio-class "base" "A base is a molecular entity dissolved in a solvent that is capable of accepting a proton (Bronsted base) or forming a covalent bond with a hydron (Lewis base) .")
(sio-class "buffer" "A buffer is a dissolved chemical substance that resists change in pH upon addition of small amounts of acid or base.")
(sio-class "solute" "a solute is a substance that becomes dissolved in a solvent.")
(sio-class "solvent" "A solvent is a substance that can dissolve other substances (solutes)."))

(as-disjoint-subclasses
 solvent
(sio-class "nonpolar solvent" "a non-polar solvent is a solvent that exhibits a non-polar quality."
	:super (owl-some has_quality non-polar))
(sio-class "polar solvent" "a polar solvent is a solvent that exhibits a polar quality."
	:super (owl-some has_quality polar)))

(sio-class "pharmaceutical preparation" "A pharmaceutical preparation is a chemical substance approved for use in the medical diagnosis, cure, treatment, or prevention of disease."
	:super reagent)

(sio-class "specimen" "A specimen is a portion of material for use in testing, examination, or study."
	:super sample)

(sio-class "sound wave" "a sound wave is a mechanical wave that is an oscillation of pressure transmitted through a solid, liquid, or gas, composed of frequencies within the range of hearing."
	:super wave)

(sio-class "chemical element" "a chemical element is a (effectively) homogeneous substance composed of one type of atom."
	:super homogeneous_substance (only has_direct_part atom))

(sio-class "allotrope" "an allotrope is a structural variant of a chemical element."
	:super chemical_element)

(sio-class "carbon allotrope" "a carbon allotrope is a chemical substance composed of carbon"
	:super allotrope (only has_direct_part carbon_atom))

(as-subclasses
 carbon_allotrope
(sio-class "aggregated carbon nanorods" "aggregate of carbon nanorods is an allotrope of carbon considered to be the least compressible material known, as measured by its isothermal bulk modulus; aggregated diamond nanorods have a modulus of 491 gigapascals (GPa), while a conventional diamond has a modulus of 442 GPa. ADNRs are also 0.3% denser than regular diamond.")
(sio-class "amorphous carbon" "Amorphous carbon is an allotrope of carbon that does not have any crystalline structure. As with all glassy materials, some short-range order can be observed, but there is no long-range pattern of atomic positions.")
(sio-class "carbon nanofoam" "carbon nanofoam is an allotrope of carbon that consists of a low-density cluster-assembly of carbon atoms strung together in a loose three-dimensional web. Each cluster is about 6 nanometers wide and consists of about 4000 carbon atoms linked in graphite-like sheets that are given negative curvature by the inclusion of heptagons among the regular hexagonal pattern.")
(sio-class "chaoite" "chaoite is an allotrope of carbon that is slightly harder than graphite with a reflection colour of grey to white.")
(sio-class "diamond" "Diamond is a carbon allotrope in which each carbon atom in diamond is covalently bonded to four other carbons in a tetrahedron. These tetrahedrons together form a 3-dimensional network of puckered six-membered rings of atoms.")
(sio-class "fullerene" "Fullerene is a carbon allotrope which take the form of a hollow sphere, ellipsoid, or tube.")
(sio-class "glassy carbon" "glassy carbon is an allotrope of carbon which is widely used as an electrode material in electrochemistry, as well as for high temperature crucibles and as a component of some prosthetic devices.")
(sio-class "graphite" "graphite is an allotrope of carbon which is a conductor, and is the most stable form of solid carbon.")
(sio-class "ionsdaleite" "ionsdaleite is a hexagonal allotrope of the carbon allotrope diamond."))

(sio-class "molecule" "A molecule is the mereological maximal sum of a collection of covalently bonded atoms."
	:super covalently_connected_entity (owl-some has_part atom)
	:annotation
	(see-also-rdf "CHEBI:23367"))

(as-subclasses
 molecule
(sio-class "antigen" "An antigen is a chemical entity that can be bound by a major histocompatibility complex (MHC) and presented to a T-cell receptor.")
(sio-class "catalyst" "a catalyst is a molecule that has the capability to reduce the activation energy of a reaction and hence increase the overall rate of reaction.")
(sio-class "drug" "A drug is a chemical entity that regulates a biological process.")
(sio-class "molecular regulator" "a molecular regulator is a molecule that regulates the function of another chemical entity."
	:super (owl-some has_capability to_regulate))
(sio-class "organic molecule" "An organic molecular entity is a chemical entity composed of organic atoms (at least carbon, hydrogen, and optionally oxygen, phosphorus, nitrogen)"
	:super (owl-and (owl-some has_part carbon_atom) (owl-some has_part hydrogen_atom)))
(sio-class "pharmaceutical component" "a pharmaceutical component is a part of a pharmaceutical preparation."
	:super (owl-some is_proper_part_of pharmaceutical_preparation))
(sio-class "polymer" "a polymer is a molecule composed of a connected set of monomeric residues."
	:super (owl-some has_direct_part monomer))
(sio-class "primer" "a primer is a nucleic acid that enables the synthesis of a complement strand of DNA by binding to it and acting as a point of transcription initiation."
	:super (owl-and nucleic_acid (owl-some has_capability (owl-and to_bind_to (owl-some has_capability to_serve_as_a_primer_for_DNA_synthesis) (owl-some in_relation_to (owl-and nucleic_acid (owl-some has_capability to_serve_as_a_template_for_DNA_synthesis)))))))
(sio-class "signal" "a signal is an object that initiates a sequence of events.")
(sio-class "signal transducer" "a signal transducer is a molecule that responds to and amplifies a signal in a signalling system."))
(sio-class "isomer" "An isomer is a molecule that is compositionally identical to another molecule as a result of a different atomic connectivity."
	:equivalent (owl-and molecule (owl-some is_variant_of molecule)))
(sio-class "ligand" "a ligand is a molecule that is part of a complex by weakly interacting with another molecule "
	:equivalent (owl-and molecule (owl-and (owl-some is_direct_part_of molecular_complex) (owl-some is_weakly_interacting_with molecule))))

;; NOTE: missing description
(defclass product
  :equivalent (owl-and molecule (owl-some is_output_of chemical_reaction) (owl-some is_derived_from substrate))
  :annotation (label "product"))
;; NOTE: missing description
(defclass target
  :equivalent (owl-and molecule (owl-some is_target_in process))
  :annotation (label "target"))

(sio-class "substrate" "a substrate is a molecule that is consumed in the course of a biochemical reaction."
	:super target
	:equivalent (owl-and molecule (owl-some derives_into product) (owl-some is_target_in chemical_reaction)))

(sio-class "stereoisomer" "A stereoisomer is an isomer in which the atomic connectivity is the same, but differs in its spatial arrangement of atoms."
	:super isomer)
(sio-class "structural isomer" "A structural isomer is an isomer in which the atoms are joined together in different ways."
	:super isomer)

(as-subclasses
 stereoisomer
(sio-class "optical isomer" "An optical isomer is a stereoisomer that rotates the plane of polarization of a beam of plane polarized light.")
(sio-class "diastereomer" "A diastereomer is a stereoisomer that is not a mirror image of its isomer.")
(sio-class "enantiomer" "An enantiomer is a stereoisomer that is a mirror image of its isomer."))

(sio-class "enzyme" "an enzyme is a protein or protein complex that realizes its disposition to covalently modify some molecule during a chemical reaction."
	:super catalyst (owl-or protein protein_complex)
	:equivalent (owl-and (owl-or protein protein_complex) (owl-some has_function (owl-and to_covalently_modify (owl-some is_realized_in chemical_reaction) (owl-some in_relation_to chemical_entity)))))

(sio-class "poison" "A poison is a drug that is harzardous or toxic to an organism when ingested at a certain quantity."
	:super drug)

(sio-class "activator" "a molecular activator is a molecular regulator that realizes its disposition to conformationally change a target molecule and increase its functionality."
	:super molecular_regulator)
(sio-class "inhibitor" "a molecular inhibitor is a molecular regulator that realizes its disposition to conformationally change a target molecule and decrease its functionality."
	:super molecular_regulator)

(as-subclasses
 organic_molecule
(sio-class "amino acid" "an amino acid is an organic molecule composed of a carbon bonded to four different groups: a carboxyl group, an amino group, an R group, and a hydrogen atom. In the case of glycine, the R group is another hydrogen atom.")
(sio-class "lipid" "a lipid is a water-insoluable organic molecule")
(sio-class "monosaccharide" "a monosaccharide is an organic polymer that consists of a single polyhydroxy aldehyde or ketone group.")
(sio-class "organic polymer" "an organic polymer is an organic molecule composed of connected set of monomeric units."
	:super (owl-some has_direct_part organic_submolecule)))
(as-disjoint monosaccharide organic_polymer lipid)

(as-subclasses
 organic_polymer
(sio-class "biopolymer" "A biopolymer is an organic polymer using biological components."
	:super (owl-some has_direct_part (owl-or lipid_residue amino_acid_residue nucleotide_residue carbohydrate_residue)))
(sio-class "nucleic acid" "a nucleic acid is an organic polymer composed of a sequence of nucleotide residues."
	:super (at-least 2 has_component_part nucleotide_residue))
(sio-class "oligosaccharide" "an oligosaccharide is an organic polymer composed of monosaccharides joined by glycosidic bonds."
	:super (at-least 2 has_component_part carbohydrate_residue))
(sio-class "polypeptide" "a polypeptide is an organic polymer composed of amino acid residues."
	:super (at-least 2 has_component_part amino_acid_residue))
(sio-class "protein" "a protein is an organic polymer that is composed of one or more linear polymers of amino acids."
	:super (owl-some has_component_part polypeptide)
	:annotation
	(equivalent-rdf "CHEBI:36080")))
(as-disjoint polypeptide oligosaccharide nucleic_acid)

(as-subclasses
 nucleic_acid
(sio-class "deoxyribonucleic acid" "a deoxyribonucleic acid is an organic polymer composed of a sequence of deoxyribonucleotide residues."
	:super (at-least 2 has_component_part deoxyribonucleotide_residue))
(sio-class "nucleic acid strand" "a nucleic acid strand is a single-stranded nucleic acid that is part of a double stranded nucleic acid complex."
	:super (owl-some is_proper_part_of double_stranded_nucleic_acid)
	:equivalent (owl-or positive_nucleic_acid_strand negative_nucleic_acid_strand))
(sio-class "ribonucleic acid" "a ribonucleic acid is an organic polymer composed of a sequence of ribonucleotide residues."
	:super (at-least 2 has_component_part ribonucleotide_residue)))
(as-disjoint deoxyribonucleic_acid ribonucleic_acid)

(sio-class "deoxyribonucleic acid primer" "a deoxyribonucleic acid primer is a deoxyribonucleic acid that enables the synthesis of a complement strand of DNA by binding to it and acting as a point of transcription initiation."
	:super primer deoxyribonucleic_acid (owl-some has_function to_serve_as_a_template_for_DNA_synthesis))

(sio-class "deoxyribonucleic acid template" "a deoxyribonucleic acid template is a deoxyribonucleic acid that provides the template to synthesize a complementary strand of DNA through transcription."
	:super deoxyribonucleic_acid (owl-some has_function (owl-or to_serve_as_a_template_for_RNA_synthesis to_serve_as_a_template_for_DNA_synthesis)))

(sio-class "negative nucleic acid strand" "the negative nucleic acid strand is the strand that is that is complimentary to the forward strand and appears from 3' to 5'."
	:super nucleic_acid_strand
	:annotation
	(synonym-en "reverse strand"))
(sio-class "positive nucleic acid strand" "the positive nucleic acid strand refers to the strand that is to be read 5' to 3'."
	:super nucleic_acid_strand
	:annotation
	(synonym-en "forward strand"))

(sio-class "RNA transcript" "an RNA transcript is an RNA molecule that is produced from transcription of a nucleic acid template."
	:super ribonucleic_acid (owl-some is_transcribed_from (owl-or deoxyribonucleic_acid (owl-some is_part_of deoxyribonucleic_acid))))

(sio-class "messenger RNA" "a messenger RNA is a ribonucleic acid that contains an untranslated region (UTR) and protein coding sequence and lacks introns."
	:super RNA_transcript (owl-some encodes protein))
(sio-class "non-protein coding RNA (ncRNA)" "a non-protein coding RNA (ncRNA) is a RNA molecular that cannot be used as a template for generating a protein product."
	:super RNA_transcript)

(as-subclasses
 messenger_RNA
(sio-class "mRNA splice variant" "an mRNA splice variant is an mRNA molecule that varies from another mRNA molecule of the same gene origin but having a different final sequence due to differences in its assembly from splice sites."
	:super (owl-some is_derived_from (owl-and messenger_RNA (owl-some has_direct_part splice_site))))
(sio-class "mature mRNA" "a messenger RNA is a ribonucleic acid that contains an untranslated region (UTR) and protein coding sequence and lacks introns."
	:super (owl-not (owl-some has_proper_part intron)) (owl-not (owl-some derives_into RNA_transcript)) (owl-some is_derived_from RNA_transcript) (owl-some is_translated_into protein))
(sio-class "pre-mRNA" "Precursor mRNA (pre-mRNA) is a single strand of messenger ribonucleic acid (mRNA) that is synthesized from a DNA template throught transcription."
	:super (owl-some derives_into mature_mRNA)))

(as-subclasses
 non-protein_coding_RNA__ncRNA_
(sio-class "small cytoplasmic RNA (scRNA)" "a small cytoplasmic RNA (scRNA) molecule is a small (7S; 129 nucleotides) RNA molecule found in the cytosol and rough endoplasmic reticulum that are normally associated with proteins that are involved in specific selection and transport of other proteins."
	:super (owl-some is_encoded_by small_cytoplasmic_RNA__scRNA__gene)
	:annotation
	(synonym-en "scRNA"))
(sio-class "small nuclear RNA (snRNA)" "a small nuclear RNA (snRNA) is a small RNA molecule that is located in the nucleus of a cell."
	:annotation
	(synonym-en "snRNA"))
(sio-class "small nucleolar RNA (snoRNA)" "A small nucleolar RNA (snoRNA) is a small RNA that are associated with the eukaryotic nucleus as components of small nucleolar ribonucleoproteins."
	:annotation
	(synonym-en "snoRNA"))
(sio-class "transfer RNA (tRNA)" "A transfer RNA (tRNA) is an RNA molecule that aids in the translation of a messenger RNA (mRNA) to produce a protein product."
	:annotation
	(synonym-en "tRNA")))

(sio-class "active ingredient" "An active ingredient is a molecular entity that exhibits biological activity."
	:super pharmaceutical_component)
(sio-class "inactive ingredient" "Aninactive ingredient is a molecular entity that does not exhibit biological activity."
	:super pharmaceutical_component)

(sio-class "messenger" "a mesenger is a molecule involved in either signal detection or signal propagation from receptors on the cell surface to target molecules inside the cell, in the cytoplasm or nucleus. "
	:super signal_transducer)
(sio-class "receptor" "a receptor molecule is a molecule that has the capability to bind to a signal and propogate a response to that signal."
	:super signal_transducer)

(sio-class "second messenger" "a second messenger is a molecule that relay signals from receptors on the cell surface to target molecules inside the cell, in the cytoplasm or nucleus. "
	:super messenger)

(as-subclasses
 molecular_complex
(sio-class "chromosome" "a chromosome is a molecular complex of circular or linear DNA and bound proteins.")
(sio-class "double stranded nucleic acid" "double stranded nucleic acid is a molecular complex composed of two weakly connected nucleic acids"
	:super (exactly 2 has_proper_part nucleic_acid))
(sio-class "protein complex" "a protein complex is a molecular complex composed of at least two polypeptide chains."
	:super (at-least 2 has_component_part polypeptide)))

(sio-class "double stranded DNA" "double stranded nucleic acid is a molecular complex composed of two weakly connected deoxyribonucleic acids"
	:super double_stranded_nucleic_acid (exactly 2 has_proper_part deoxyribonucleic_acid))
(sio-class "double stranded RNA" "double stranded nucleic acid is a molecular complex composed of two weakly connected ribonucleic acids"
	:super double_stranded_nucleic_acid (exactly 2 has_proper_part ribonucleic_acid))

(sio-class "antibody" "An antibody (also known as immunoglobulins, abbreviated Ig) are gamma globulin proteins that are used by the immune system to identify and neutralize foreign objects. They are typically made of two large heavy chains and two small light chains."
	:super protein_complex)

(as-subclasses
 submolecular_entity
(sio-class "strong submolecular component" "a strong submolecular component is a submolecular component that strongly connects submolecular components.")
(sio-class "submolecule" "a submolecule is a mereological sum of covalently bonded atoms"
	:super (owl-some is_component_part_of molecule) (owl-some has_component_part atom))
(sio-class "weak submolecular component" "a weak submolecular component is a submolecular component that weakly connects submolecular components."))
(as-disjoint strong_submolecular_component weak_submolecular_component)

(sio-class "covalent bond" "a covalent bond is a strong submolecular interaction between atoms."
	:super strong_submolecular_component (exactly 2 has_component_part atom)
	:annotation
	(see-also-rdf "CHEMINF:000063"))

(as-subclasses
 covalent_bond
(sio-class "aromatic bond" "an aromatic bond is an interaction between a set of atoms across which pairs of electrons are shared.")
(sio-class "double bond" "a double bond is a covalent bond between a pair of atoms in which two pairs of electrons are shared.")
(sio-class "single bond" "a single bond is a covalent bond between a pair of atoms in which one pair of electrons are shared.")
(sio-class "triple bond" "a triple bond is a covalent bond between a pair of atoms in which three pairs of electrons are shared."))

(sio-class "disulfide bond" "a disulfide bond is a bond between two sulfur atoms."
	:super single_bond)

(as-subclasses
 submolecule
(sio-class "chemical functional group" "a chemical functional group is a covalently connected part of a molecule which normally confer specific chemical properties.")
(sio-class "monomer" "A monomer is a submolecule that is proper part of some polymer, and is a building block for such polymer."
	:super (owl-some is_direct_part_of biopolymer))
(sio-class "organic submolecule" "an organic submolecule is connected region of a molecule."
	:super (owl-and (owl-some has_part carbon_atom) (owl-some has_part oxygen_atom)) (owl-some is_part_of organic_molecule)))

(sio-class "ring" "a ring is a submolecule with a circular topology."
	:super chemical_functional_group (at-least 3 has_component_part atom)
	:equivalent (owl-or heterocyclic_ring homocyclic_ring))

(as-subclasses
 ring
(sio-class "aromatic ring" "An aromatic ring is a ring in which the electrons are delocalized across all atoms in the ring.")
(sio-class "heterocyclic ring" "a heterocyclic ring is a ring containing a hetero atom.")
(sio-class "homocyclic ring" "A homocyclic ring is a ring where the atoms are of a single type."))
(as-disjoint homocyclic_ring heterocyclic_ring)

(as-subclasses
 organic_submolecule
(sio-class "carbohydrate residue" "a carbohydrate residue is a part of a molecule that was derived from a monosaccharide molecule."
	:super (owl-some is_derived_from monosaccharide))
(sio-class "lipid residue" "a lipid residue is a part of an organic molecule that was derived from a lipid molecule.")
(sio-class "nucleic acid part" "a nucleic acid part is a component of a nucleic acid."
	:super (owl-some is_part_of nucleic_acid) (owl-some has_direct_part nucleotide_residue))
(sio-class "protein part" "a protein part is any submolecule of a protein."))

(as-subclasses
 nucleic_acid_part
(sio-class "RNA transcript component" "an RNA transcript component is a region of an RNA transcript. "
	:super (owl-some is_part_of messenger_RNA) (owl-some is_proper_part_of RNA_transcript))
(sio-class "cis regulatory element" "A cisregulatory element is a DNA sequence located on the same DNA strand or chromosome as the gene whose expression it affects. ")
(sio-class "gene" "A gene is part of a nucleic acid that contains all the necessary elements to encode a functional transcript."
	:annotation
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0000704")
	(equivalent-rdf "biopax:Gene"))
(sio-class "gene component" "a gene component is a component of a gene"
	:super (owl-some is_proper_part_of gene))
(sio-class "genetic polymorphism" "genetic polymorphism is the description of a difference in genetic composition at some location.")
(sio-class "haplotype" "A haplotype is one of a set of genomic sequence variants.")
(sio-class "nucleotide residue" "a nucleotide residue is a part of a molecule that derives from a nucleotide.")
(sio-class "operon" "An operon is a collection of contiguous genes transcribed as a single (polycistronic) mRNA."
	:super (owl-some is_proper_part_of deoxyribonucleic_acid) (owl-some has_component_part gene) (owl-some is_transcribed_into RNA_transcript)
	:annotation
	(annotation similarTo (literal "SO:0000178" :type :RDF_PLAIN_LITERAL)))
(sio-class "pseudogene" "a pseudo gene is a region of a nucleic acid that either cannot be transcribed, or its rna transcript cannot be translated."
	:annotation
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0000336"))
(sio-class "trans-regulatory element" "A trans-regulatory element is a DNA sequence associated with the regulation of a gene located outside the genomic region supporting the corresponding structural DNA region of the trans-regulatory element (i.e., a different DNA strand or different chromosome)."))

(as-subclasses
  RNA_transcript_component
(sio-class "splice site" "a splice site is a region required for the excision of an intron and connection to another exon.")
(sio-class "start codon" "a start codon is the first codon of a messenger RNA (mRNA) transcript translated by a ribosome. The start codon is almost always preceded by an untranslated region 5' UTR.")
(sio-class "stop codon" "a stop codon (or termination codon) is a nucleotide triplet within messenger RNA that signals a termination of translation."
	:super (owl-some is_part_of messenger_RNA)))
(as-disjoint start_codon stop_codon)

(sio-class "3' splice site" "the 3' splice site is the terminal region of an exon that is 3' to the intron that is to be excised."
	:super splice_site)
(sio-class "5' splice site" "the 5' splice site is the terminal region of an exon that is 5' to the intron that is to be excised."
	:super splice_site)

(as-subclasses
 gene
(sio-class "allele" "an allele is one of a set of sequence variants of a gene."
	:super (owl-some is_variant_of gene))
(sio-class "dna gene" "a gene that is located on DNA."
	:super (owl-some is_proper_part_of deoxyribonucleic_acid))
(sio-class "functional rna coding gene" "a gene that codes for a functional RNA molecule")
(sio-class "non-protein coding RNA (ncRNA) gene" "a non-protein coding RNA (ncRNA) gene is a gene that encodes for a RNA transcript that is not further translated into a protein product."
	:annotation
	(synonym-en "ncRNA gene")
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0001263"))
(sio-class "predicted gene" "a predicted gene is a gene that was identified through computational method but has not been experimentally validated."
	:super (owl-some has_attribute predicted))
(sio-class "protein coding gene" "a gene that contains an open reading frame which codes for a protein."
	:annotation
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0001217"))
(sio-class "rna gene" "a gene that is located on RNA"
	:super (owl-some is_proper_part_of ribonucleic_acid))
(sio-class "validated gene" "an experimentally validated gene is a gene whose existence has been demonstrated through experimental methods."
	:super (owl-some has_attribute real)))
(as-disjoint rna_gene dna_gene)

(as-subclasses
 non-protein_coding_RNA__ncRNA__gene
(sio-class "ribosomal RNA gene" "a ribosomal RNA gene is a gene that codes for a ribosomal RNA molecule."
	:annotation
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0001637")
	(synonym-rdf "rRNA gene"))
(sio-class "small cytoplasmic RNA (scRNA) gene" "a small cytoplasmic RNA (scRNA) gene is a gene that encodes a small (7S; 129 nucleotides) RNA molecule found in the cytosol and rough endoplasmic reticulum that are normally associated with proteins that are involved in specific selection and transport of other proteins."
	:super (owl-some encodes small_cytoplasmic_RNA__scRNA_)
	:annotation
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0001266"))
(sio-class "small nuclear RNA (snRNA) gene" "a small nuclear RNA (snRNA) gene is a gene that encodes a small niuclear RNA molecule."
	:super (owl-some encodes small_nuclear_RNA__snRNA_)
	:annotation
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0001268"))
(sio-class "small nucleolar RNA (snoRNA) gene" "A small nucleolar RNA (snoRNA) gene is a gene that encodes a small RNA that are associated with the eukaryotic nucleus as components of small nucleolar ribonucleoproteins."
	:super (owl-some encodes small_nucleolar_RNA__snoRNA_)
	:annotation
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0001267"))
(sio-class "transfer RNA (tRNA) gene" "A transfer RNA (tRNA) gene is a gene that codes for a tRNA used in the translation of a messenger RNA (mRNA) to produce a protein product."
	:super (owl-some encodes transfer_RNA__tRNA_)
	:annotation
	(synonym-en "tRNA gene")
	(synonym-en "solube RNA (sRNA) gene")
	(equivalent-uri "http://purl.obolibrary.org/obo/SO_0001272")))

(as-subclasses
 gene_component
(sio-class "3' untranslated region" "a three prime untranslated region (3'-UTR) is the section of messenger RNA (mRNA) that immediately follows the translation termination codon. ")
(sio-class "5' untranslated region" "The five prime untranslated region (5' UTR) is a section of messenger RNA (mRNA) and the DNA that codes for it that starts at the +1 position (where transcription begins) and ends one nucleotide before the start codon (usually AUG) of the coding region.")
(sio-class "exon" "An exon is a nucleotide sequence encoded by a gene that remains present within the final mature RNA product of that gene after introns have been removed by RNA splicing. ")
(sio-class "gene regulatory component" "a gene regulatory component is a gene component that exerts a regulatory function."
	:super (owl-some has_function to_regulate))
(sio-class "intron" "an intron is a region of a gene that is removed from the final protein open reading frame."
	:super (owl-some is_proper_part_of gene))
(sio-class "open reading frame" "an open reading frame (ORF) is a part of a gene that encodes a protein but does not contain a stop codon."
	:super (owl-and (exactly 1 has_component_part start_codon) (exactly 1 has_component_part stop_codon)) (owl-some is_transcribed_into (owl-and RNA_transcript (owl-some is_translated_into polypeptide)))))

(sio-class "gene enhancer" "a gene enhancer is a short region of DNA that can be bound with proteins to enhance transcription levels of genes in a gene cluster."
	:super gene_regulatory_component)
(sio-class "gene promoter" "a gene promoter is a region of DNA that initiates transcription of a particular gene."
	:super gene_regulatory_component)

(as-subclasses
 nucleotide_residue
(sio-class "deoxyribonucleotide residue" "a deoxyribonucleotide residue is a part of a molecule that derives from a deoxyribonucleotide."
	:disjoint ribonucleotide_residue)
(sio-class "ribonucleotide residue" "a ribonucleotide residue is a part of a molecule that derives from a ribonucleotide."
	:disjoint deoxyribonucleotide_residue)
(sio-class "snp" "single nucleotide polymorphism (SNP) is a variation in a single base in the genetic composition between different individuals of the same species."
	:super (owl-some is_variant_of nucleotide_residue)
	:annotation
	(annotation (iri "http://purl.org/dc/terms/alternativeName") (literal "single nucleotide polymorphism" :lang "en"))))

(as-subclasses
 protein_part
(sio-class "alpha helix" "an alpha helix is structural region of a protein that is characterized by 3.6 residues per turn,  a translation of 1.5 angstroms along the helical axis in which backbone N-H groups form a hydrogen bond to the backbone carboxyl group of the amino acid four residues prior.")
(sio-class "amino acid residue" "an amino acid residue is a part of a molecule that is derived from an amino acid molecule."
	:super (owl-some is_derived_from amino_acid))
(sio-class "beta strand" "a beta strand is structural region of a protein that is characterized by a roughly planar sequence of amino acid residues forming hydrogen bonds between the N-O and the C=O  of another part of the peptide\nand having their side chains perpendicular to the planar axis."))

(as-subclasses
 weak_submolecular_component
(sio-class "base pair" "a base pair is a weak molecular interaction composed of hydrogen bonds between nucleobases.")
(sio-class "base stack" "a base stack is a stabilizing interaction of DNA and RNA between spatially adjacent nucleotides and possibly involving London dispersion, hydrophobic and electrostatic forces.")
(sio-class "dipole-dipole interaction" "a dipole-dipole interaction is a weak submolecular interaction between strongly electronegative atoms.")
(sio-class "hydrogen bond" "a hydrogen bond is a weak submolecular interaction formed between a hydrogen atom and a electronegative atom.")
(sio-class "ionic interaction" "an ionic interaction is a weak submolecular interaction between a charged submolecules.")
(sio-class "van der Waals interaction" "van der Waals' interaction is an a weak submolecular interaction between an instantaneous dipole and induced dipole."))

(sio-class "low barrier hydrogen bond" "a low barrier hydrogen bond is a shorter, stronger hydrogen bond that is formed between both heteroatoms."
	:super hydrogen_bond)

(sio-class "cation pi interaction" "A cation pi interaction is an ionic interaction between the localized negative charge of π orbital electrons, located above and below the plane of an aromatic ring, and a positive charge."
	:super ionic_interaction)

(sio-class "e.coli" "Escherichia coli (e coli) is a Gram-negative, rod-shaped bacterium that is commonly found in the lower intestine of warm-blooded organisms."
	:super unicellular_organism)

(as-subclasses
 spatial_region
(sio-class "geographic region" "a geographic region is a spatial region whose boundaries are typically defined against some material frame of reference (like the earth)."
	:annotation
	(eg "the spatial region occupied by the province of ontario; the spatial region occupied by a lake."))
(sio-class "site" "A site is a spatial region bounded (in part or in whole) by material entities and may be occupied by material entities."
	:annotation
	(eg "a nostril; the interior of the heart valve; the interior of a bottle;"))
(sio-class "spatial boundary" "a spatial boundary is the closure minus the interior of a subset of a topological space."))

(sio-class "environment" "an environment is a geographic region that hosts certain processes or objects."
	:super geographic_region)
(sio-class "geolegal region" "A geolegal region is a geographic region which has causal powers confered by a legal entity."
	:super geographic_region
	:annotation
	(eg "the 50 kph zones in Ottawa; the geographic region occupied by France."))

(sio-class "geopolitical region" "a geopolitical region is a geographic region recognized by social or legal convention."
	:super geolegal_region
	:annotation
	(eg "the geographic region bounded by Canada; the spatial region occupied by the US embassy in Ottawa"))

(as-subclasses
 geopolitical_region
(sio-class "city" "A city is a relatively large and permanent settlement.")
(sio-class "country" "A country is a region legally identified as a distinct entity in political geography. ")
(sio-class "province" "A province is a territorial unit, almost always an administrative division, within a country or state."
	:super (owl-some is_proper_part_of country))
(sio-class "state" "a state is a set of governing and supportive institutions that have sovereignty over a definite territory and population.")
(sio-class "territory" "a territory is a non-sovereign geographic region.")
(sio-class "township" "a township is a rural or sub-urban settlement."))

(sio-class "hole" "a hole is a site that is opening into or through something."
	:super site)
(sio-class "molecular site" "A moleclar site is a spatial region bounded (in part or in whole) by a molecule and may be occupied by other material entities (e.g. drugs)."
	:super site)

(as-subclasses
 molecular_site
(sio-class "active site" "an active site is a molecular site in which a chemical event occurs (structural transformation or conformational change).")
(sio-class "binding site" "A binding site is a molecular site which when occupied with particular ligands leads to structural transformations that initiatiate new moelcular processes. ")
(sio-class "molecular pocket" "a molecular pocket is a site on a molecule that appears as a depression into the structure."))

(sio-class "allosteric site" "an allosteric site is a binding site that when bound to particular ligand changes the conformational state and affects its functionality."
	:super binding_site)

(sio-class "material boundary" "A material boundary is the boundary of a material entity which exists as a lower dimensional entity at exactly the location where its parts no longer extend into space. Every material entity has a boundary, and a boundary is the boundary of exactly 1 material entity."
	:super spatial_boundary (owl-some is_boundary_of material_entity)
	:annotation
	(eg "the external surface of the window; the surface of the p52 protein"))

(as-subclasses
 process
(sio-class "behaviour" "Behaviour is the set of actions and mannerisms made by systems (biological or otherwise) in response to stimuli or inputs, whether internal or external, conscious or subconscious, overt or covert, and voluntary or involuntary."
	:annotation
	(subset-rdf "behaviour+")
	core)
(sio-class "interacting" "interacting is a process characterized by the interaction between two or more entities."
	:super (owl-and (at-least 2 realizes to_interact_with) (at-least 2 realizes to_be_interacted_with)) (owl-some has_participant object)
	:annotation
	core)
(sio-class "movement" "Movement is the process in which an object is spatially displaced."
	:annotation
	core)
(sio-class "procedure" "a procedure is a process that attempts to achieve one or more objectives by following an established set of actions."
	:super (owl-some is_manifestation_of action_specification) (only realizes (owl-or capability objective))))

(sio-class "emotion" "An emotion is a process (experience) that arises internally or from an involuntary physiological response to a stimulus."
	:super behaviour
	:annotation
	(subset-rdf "emotion++"))

(as-subclasses
 emotion
(sio-class "indifference" "indifference is an emotion characterized by lack of interest, concern, or sympathy."
	:super (owl-and (owl-not (owl-some has_quality positive)) (owl-not (owl-some has_quality negative))))
(sio-class "negative emotion" "negative emotion is an emotion that does not feel good."
	:super (owl-some has_quality negative))
(sio-class "positive emotion" "a positive emotion is an emotion that feels good."
	:super (owl-some has_quality positive)))

(sio-class "apathy" "apathy is an emotion characterized by lack of interest, enthusiasm, or concern"
	:super indifference)

(as-subclasses
 negative_emotion
(sio-class "annoyance" "Annoyance is an unpleasant emtion that is characterized by a abnormal or excessive sensitivity to some external stimulus.")
(sio-class "apprehension" "apprehension is the negative emotion that something unpleasant will occur.")
(sio-class "boredom" "boredom is the emotion experience by those not interested in their surroundings or available activities.")
(sio-class "disappointment" "Disappointment is the feeling of dissatisfaction that follows the failure of expectations or hopes to manifest")
(sio-class "embarassment" "Embarrassment is the emotion of intense discomfort with oneself, experienced upon having a socially unacceptable act or condition witnessed by or revealed to other.")
(sio-class "envy" "envy is an emotion that occurs when a person lacks another's (perceived) superior quality, achievement or possession and wishes that the other lacked it.")
(sio-class "guilt" "Guilt is the emotion borne from feeling responsible for the commission of an offense and arises out of public humiliation.")
(sio-class "hostility" "Hostility is the intense negative emotion of being in conflict or opposition to someone or something.")
(sio-class "hurt" "hurt is an unpleasant feeling, emotion or sensation.")
(sio-class "hysteria" "Hysteria is an unmanageable emotion.")
(sio-class "loneliness" "Loneliness is an unpleasant emotion in which a person feels a strong sense of emptiness, yearning distress and solitude resulting from inadequate quantity or quality of social relationships.")
(sio-class "pity" "Pity is the emotion of sadness or sorrow for another.")
(sio-class "sadness" "sadness is emotional pain associated with, or characterized by feelings of disadvantage, loss, despair, helplessness, sorrow, and rage.")
(sio-class "shame" "shame is the emotion borne from feeling responsible for the commission of an offense.")
(sio-class "shock" "shock is an emotion of sudden upset or surprise.")
(sio-class "shyness" "shyness is an emotion of apprehension, lack of comfort, or awkwardness experienced when in proximity to, approaching, or being approached by other individuals, especially in new situations or with unfamiliar individuals."))

(sio-class "frustration" "Frustration is an emotion that arises from the perceived resistance to the fulfillment of individual will. "
	:super annoyance)

(as-subclasses
 apprehension
(sio-class "anxiety" "anxiety is an emotion charactersized by intense feeling of fear and concern coupled with a physical response.")
(sio-class "fear" "Fear is a negative emotion induced by a perceived threat that induces one to hide or move quickly away from the location of the perceived threat.")
(sio-class "worry" "worry is the emotion characterized by concer over a real or imaginary issue."))

(sio-class "angst" "angst is the intense feeling of apprehension, anxiety or inner turmoil."
	:super anxiety)

(sio-class "dread" "dread is the instense negative emotion that induces fear and apprehension."
	:super fear)
(sio-class "panic" "Panic is a sudden emotion of fear which is so strong as to dominate or prevent reason and logical thinking, replacing it with overwhelming feelings of anxiety and frantic agitation consistent with an animalistic fight-or-flight reaction."
	:super fear)

(sio-class "terror" "terror is the extreme feeling of fear."
	:super dread)

(sio-class "regret" "regret is a feeling of sadness, repentance, or disappointment over something that has happened or been done."
	:super worry)
(sio-class "remorse" "remorse is an emotion of personal regret felt by a person after he or she has committed an act which they deem to be shameful, hurtful, or violent."
	:super regret)

(sio-class "jealousy" "jealousy is an emotion and typically refers to the negative thoughts and feelings of insecurity, fear, and anxiety over an anticipated loss of something that the person values, particularly in reference to a human connection"
	:super envy)

(sio-class "disgust" "Disgust is a feeling of revulsion or profound disapproval aroused by something unpleasant or offensive."
	:super hostility)

(as-subclasses
 disgust
(sio-class "anger" "anger is disgust directed toward an equal status individual.")
(sio-class "contempt" "contempt is disgust towards a lower status individual.")
(sio-class "hate" "Hate is a deep and emotional extreme dislike, directed against a certain object or class of objects. ")
(sio-class "loathing" "loathing is an intense dislike or disgust.")
(sio-class "resentment" "resentment is disgust directed toward a higher status individual."))

(sio-class "rage" "Rage is a feeling of intense anger that is associated with the Fight-or-flight response."
	:super anger)

(sio-class "pain" "Pain is an unpleasant sensory and emotional experience associated with actual or potential tissue damage, or described in terms of such damage"
	:super hurt)
(sio-class "suffering" "Suffering is the unpleasant emotion and aversion associated with the perception of harm or threat of harm in an individual."
	:super hurt)

(as-subclasses
 sadness
(sio-class "depression" "depression is an unpleasant emotion linked to aversion to activity that can affect a person's thoughts, behavior, feelings and physical well-being. Depressed individuals may feel sad, anxious, empty, hopeless, worried, helpless, worthless, guilty, irritable, or restless.")
(sio-class "despair" "despair is depression, hopelessness or lack of hope")
(sio-class "grief" "Grief is an emotion in response to loss, whether physical or abstract including death, unemployment, ill health or the end of a relationship.")
(sio-class "misery" "Misery is a feeling of great unhappiness, suffering and/or pain.")
(sio-class "sorrow" "Sorrow is the emotion that is characterized by a long term state of intense sadness, distress and a degree of resignation (not accepting)."))

(as-subclasses
 positive_emotion
(sio-class "affection" "affection is an emotion characterized with a feeling or type of love for another living thing.")
(sio-class "arousal" "Arousal is an emotion characterized by state of reactive to stimuli. It involves the activation of the reticular activating system in the brain stem, the autonomic nervous system and the endocrine system, leading to increased heart rate and blood pressure and a condition of sensory alertness, mobility and readiness to respond.")
(sio-class "awe" "Awe is an emotion produced by that which is grand, sublime or powerful and is characterized by a combination of joy, fear and admiration/reverence/respect. ")
(sio-class "boldness" "boldness is the trait of being willing to undertake things that involve risk or danger.")
(sio-class "ecstasy" "ecstacy is an emotion characterized by a heightened state of consciousness with total involvement of a subject.")
(sio-class "excitement" "excitement is a positive emotion of feeling great enthusiasm and eagerness.")
(sio-class "gratitude" "Gratitude, thankfulness, gratefulness, or appreciation is a feeling, emotion or attitude in acknowledgment of a benefit that one has received or will receive."
	:annotation
	(synonym-rdf "gratefulness")
	(synonym-rdf "appreciation")
	(synonym-rdf "thankfullness"))
(sio-class "happiness" "Happiness is an emotion characterized by positive or pleasant emotions ranging from contentment to intense joy.")
(sio-class "hope" "hope is an emotion of belief in a positive outcome related to events and circumstances in one's life.")
(sio-class "interest" "interest is the emotion of wanting to know or learn about something or someone.")
(sio-class "love" "Love is an emotion of a strong affection and personal attachment.")
(sio-class "surprise" "Surprise is a brief emotion experienced as the result of an unexpected event. "))

(as-subclasses
 happiness
(sio-class "contentment" "contentment is an emotion characterized by acknowledgement and satisfaction of the current state of affairs.")
(sio-class "euphoria" "euphoria is an emotion characterized by intense feelings of well-being, elation, happiness, ecstasy, excitement, and joy.")
(sio-class "joy" "joy is an emotion of intense happiness")
(sio-class "pleasure" "pleasure is an emotion of happy satisfaction and enjoyment."))

(sio-class "satisfaction" "satisfaction is an emotion of fulfillment of one's wishes, expectations, or needs, or the pleasure derived from this."
	:super contentment)

(sio-class "pride" "Pride is an emotion of satisfaction of attachment toward one's own or another's choices and actions, or toward a whole group of people, and is a product of praise, independent self-reflection, or a fulfilled feeling of belonging."
	:super satisfaction)

(sio-class "desire" "Desire is a strong emotion of wanting to have something or wishing for something to happen."
	:super interest)

(as-subclasses
 desire
(sio-class "curiosity" "curiosity is the strong desire to know or learn something.")
(sio-class "intent" "intent is a desire to realize a particular outcome.")
(sio-class "lust" "lust is the strong desire for sex.")
(sio-class "passion" "passion is the intense desire for something."))

(sio-class "wonder" "Wonder is an emotion of perceiving something very rare or unexpected, but not threatening."
	:super surprise)

(as-subclasses
 interacting
(sio-class "chemical interaction" "A chemical interaction is a biochemical process in which chemical entities interact through some set of attractive forces."
	:super (owl-some has_participant chemical_entity)
	:annotation
	(equivalent-rdf "biopax:Interaction"))
(sio-class "communicating" "communicating is the process of conveying information through the exchange of thoughts, messages, or information, as by speech, visuals, signals, writing, or behaviour."
	:annotation
	(synonym-rdf "communication")) ;;en?
(sio-class "comparing" "comparing is the process of examining a set of objects and determining their equality or inequality based on one or more features."
	:super (owl-and (owl-some realizes to_be_compared) (owl-some realizes to_compare))
	:annotation
	(synonym-rdf "comparison")) ;;en?
(sio-class "creating" "creating is the process in which an entity comes into existence."
	:super (owl-some has_product object)
	:annotation
	(synonym-rdf "development") ;;en?
	(synonym-rdf "synthesis") ;;en?
	(synonym-rdf "production") ;;en?
	(synonym-rdf "formulation") ;;en?
	(synonym-rdf "creation")) ;;en?
(sio-class "destroying" "destroying is a process in which something is broken down and/or ceases to exist."
	:super (owl-some has_substrate object)
	:annotation
	(synonym-en "destruction"))
(sio-class "measuring" "measuring is the process of determining the size, amount, or degree of (something) by using an instrument or device marked in standard units"
	:super (owl-some has_output measurement_value) (owl-some has_input (owl-or quality process object))
	:annotation
	(synonym-en "measurement"))
(sio-class "modifying" "modifying is the process by which an entity gains or loses parts, qualities, roles, dispositions, functions, etc, but maintains their identity through these changes."
	:super (owl-some has_output object) (owl-some has_target object)
	:annotation
	(synonym-en "modification"))
(sio-class "observing" "observing is a process of passive interaction in which one entity makes note of attributes of one or more entities."
	:super (owl-some has_output entity) (owl-some has_input entity)
	:annotation
	(synonym-en "observation"))
(sio-class "regulating" "regulating is a process that modulates the attributes of an object or process."
	:equivalent (owl-or process_maintenance process_up-regulation process_down-regulation)
	:annotation
	(synonym-en "regulation"))
(sio-class "sampling" "sampling is the act of obtaining a sample, whether through selection, collection or preparation."
	:super (owl-and (owl-some has_output sample) (owl-some has_input object)))
(sio-class "transporting" "transporting is a  process in which one object physically moves another object from one location to another."
	:super (owl-and (owl-some realizes to_transport) (owl-some realizes to_be_transported))))

(as-subclasses
 chemical_interaction
(sio-class "chemical reaction" "A chemical reaction is a process that leads to the transformation of one set of chemical substances to another."
	:super (owl-and (owl-some has_proper_part chemical_synthesis) (owl-some has_proper_part chemical_destruction)))
(sio-class "drug effect" "A drug effect is a chemical interaction in which a chemical elicits a marked characteristic of a biological system."
	:super (owl-some has_agent drug))
(sio-class "metabolism" "Metabolism is the set of chemical processes that occur within a living organism in order to maintain life."))

(as-subclasses
 chemical_reaction
(sio-class "acid-base reaction" "an acid-base reaction is a chemical reaction between an acid and a base.")
(sio-class "catalyzed reaction" "a catalyzed reaction is a chemical reaction that is facilitated by a catalyst."
	:super (owl-some has_agent catalyst))
(sio-class "inorganic reaction" "An inorganic reaction is a chemical reaction that involves the transformation of inorganic molecules.")
(sio-class "isomerization reaction" "An isomerization reaction is a chemical reaction in which a molecule is converted into its isomer.")
(sio-class "molecular modification" "Molecular modification is chemical alteration of a known and previously characterized lead compound for the purpose of enhancing its usefulness as a drug. This could mean enhancing its specificity for a particular body target site, increasing its potency, improving its rate and extent of absorption, modifying to advantage its time course in the body, reducing its toxicity, changing its physical or chemical properties (like solubility) to provide desired features.")
(sio-class "organic reaction" "An organic reaction is a chemical reaction involving at least one organic molecule.")
(sio-class "redox reaction" "a redox reaction is a chemical reaction in which there is a net movement of electrons from one reactant to another."
	:super (owl-and (owl-some realizes (owl-and to_oxidize (owl-some is_disposition_of molecule))) (owl-some realizes (owl-and to_reduce (owl-some is_disposition_of molecule))))))

(sio-class "biochemical reaction" "A biochemical reaction is a biochemical process that involves the conversion of at least one chemical participant (target) into another (product) by an enzyme (agent)."
	:super catalyzed_reaction (owl-and catalyzed_reaction (owl-some has_agent enzyme) (owl-some has_target substrate) (owl-some has_product product))
	:annotation
	(equivalent-rdf "biopax:BiochemicalReaction")
	(equivalent-rdf "biopax:Conversion"))

(as-subclasses
 inorganic_reaction
(sio-class "decomposition reaction" "A decomposition reaction is an inorganic reaction in which molecule is fragmented into submolecules or atoms.")
(sio-class "displacement reaction" "A displacement reaction is an inorganic reaction in which a elementary substance displaces and sets free a constituent atom from a molecule.")
(sio-class "synthesis reaction" "A synthesis reaction is an inorganic reaction in which two or more molecules are chemically bonded together to produce a single product."))

(sio-class "double displacement reaction" "A double displacement reaction is a displacement reaction in which two molecules swap ions, effectively displacing each other to form two new molecules."
	:super displacement_reaction)
(sio-class "single displacement reaction" "A single displacement reaction where one atom is transferred out of one molecule and into another."
	:super displacement_reaction)

(sio-class "addition reaction" "An addition reaction is an organic reaction where two or more molecules combine to form a larger one. Addition reactions are limited to chemical compounds that have multiply-bonded atoms:\n    * Molecules with carbon-carbon double bonds or triple bonds\n    * Molecules with carbon - hetero double bonds like C=O or C=N"
	:super organic_reaction)

(sio-class "non-polar addition reaction" "a non-polar addition reaction is an addition reaction involving non-polar residues."
	:super addition_reaction)
(sio-class "polar addition reaction" "a polar addition reaction is an addition reaction involving polar residues."
	:super addition_reaction)

(sio-class "free radical addition" "A free radical addition is a non-polar addition reaction involving free radicals."
	:super non-polar_addition_reaction)

(sio-class "electrophilic addition reaction" "an electrophilic addition reaction is a polar addition reaction where a pi bond is removed by the creation of two new covalent bonds."
	:super polar_addition_reaction)
(sio-class "nucleophilic addition reaction" "A nucleophilic addition reaction is an addition reaction where a pi bond is removed by the creation of two new covalent bonds by the addition from a nucleophile."
	:super polar_addition_reaction)

(sio-class "drug drug interaction" "a drug-drug interaction is an interaction in which two drugs interact in such a way to produce a non-additive biological response."
	:super drug_effect (at-least 2 has_participant drug))

(as-disjoint-subclasses
 metabolism
(sio-class "anabolism" "Anabolism is the set of metabolic processes that construct larger chemical entities units from smaller chemical entities.")
(sio-class "catabolism" "Anabolism is the set of metabolic processes that take apart larger chemical entities units into smaller chemical entities."))

(sio-class "conversing" "conversing a form of interactive, spontaneous communication between two or more agents who are following rules of etiquette."
	:super communicating)
(sio-class "gesturing" "gesturing is a form of non-verbal communication in which visible bodily actions communicate particular messages, either in place of speech or together and in parallel with spoken words. Gestures include movement of the hands, face, or other parts of the body."
	:super communicating)

(as-subclasses
 creating
(sio-class "birthing" "brirthing is the process by which a biological organism is brought into existence."
	:super (owl-some has_product biological_entity))
(sio-class "chemical synthesis" "Chemical synthesis is synthesis of a chemical entity from physical precursors through one or more chemical interactions or reactions."
	:super (owl-some has_agent enzyme) (owl-some has_product chemical_entity))
(sio-class "evolving" "evolving is a process that elicits change across successive generations in the inherited characteristics of biological populations."
	:super (owl-some has_product entity))
(sio-class "learning" "learning is the agentive process of acquiring knowledge."
	:super (owl-some has_product proposition))
(sio-class "planning" "Planning is the agentive process of developing a plan that specifies a set of actions in order to meet a set of goals or objectives."
	:super (owl-some has_product (owl-or plan design)))
(sio-class "predicting" "predicting is the process of formulating a proposition about a state of affairs which might be realized in the future."
	:super (owl-some has_product proposition))
(sio-class "reasoning" "reasoning is the agentive process of using knowledge to evaluate the truth value of a proposition."
	:super (owl-some has_product (owl-and truth_value (owl-some is_quality_of proposition))))
(sio-class "reproducing" "reproducing is a process characterized by creation of an entity that is similar or exactly the same as the template from which it is derived."
	:super (owl-and (owl-some has_input object) (owl-some has_product object))))

(sio-class "biosynthesis" "Biosynthesis is the synthesis of an organic compound in a living organism, usually aided by enzymes."
	:super chemical_synthesis)
(sio-class "molecular complex formation" "molecular complex formation is the process of forming a molecular complex from its constituent parts."
	:super chemical_synthesis (owl-some has_product molecular_complex))

(sio-class "transcription" "Transcription is the process of creating a complementary RNA copy of a sequence of DNA."
	:super biosynthesis (owl-some has_output (owl-and ribonucleic_acid (owl-some is_transcribed_from deoxyribonucleic_acid))) (owl-some has_input deoxyribonucleic_acid))
(sio-class "translation" "Translation is the process of producing a polypeptide from a ribonucleic acid by a ribosome."
	:super biosynthesis (owl-some has_input ribonucleic_acid) (owl-some has_product polypeptide))

(sio-class "biological reproduction" "Biological reproduction is the biological process by which one or more biological organisms are produced from their 'parents'. "
	:super reproducing)

(sio-class "chemical destruction" "Chemical destruction is destruction of a chemical entity to its chemical constituents through one ormore chemical interactions or reactions."
	:super destroying (owl-some has_target chemical_entity))
(sio-class "dying" "dying is a process in which a biological entity ceases to exist."
	:super destroying (owl-some has_substrate biological_entity))

(as-subclasses
 chemical_destruction
(sio-class "decreased chemical destruction" "decreased chemical destruction is a process in which there is a decrease in the amount of chemical destroyed relative to some reference process"
	:super (owl-some has_attribute (owl-and count (owl-some is_greater_than (owl-and count (owl-some is_attribute_of chemical_destruction))))) (owl-some has_quality increased) (owl-some in_relation_to chemical_destruction))
(sio-class "increased chemical destruction" "increased chemical destruction is a process in which there is an increase in the amount of chemical destroyed relative to some reference process"
	:super (owl-some has_attribute (owl-and count (owl-some is_greater_than (owl-and count (owl-some is_attribute_of chemical_destruction))))) (owl-some has_quality increased))
(sio-class "molecular complex dissociation" "molecular complex disassociation is the process of dissambly of a molecular complex into its constitutent parts."
	:super (owl-some has_target molecular_complex)))

(sio-class "perception" "Perception is the organization, identification, and interpretation of sensory information in order to fabricate a mental representation through the process of transduction, which sensors in the body transform signals from the environment into encoded neural signals."
	:super observing)

(sio-class "regulation of capability" "regulation of capability is the regulation of the ability of one party by another."
	:super regulating (owl-some affects capability))
(sio-class "regulation of process" "regulation of a process is a process that modulates the duration, frequency, spatial extent of a target process."
	:super regulating (owl-some regulates process)
	:equivalent (owl-or regulation_of_process_frequency regulation_of_process_duration regulation_of_process_spatial_extent regulation_of_object_quantity))

(sio-class "regulation of catalytic capability" "the regulation of the enzymatic activity."
	:super regulation_of_capability (owl-some regulates to_change_materially))

(as-disjoint-subclasses
 regulation_of_catalytic_capability
(sio-class "biochemical activation" "Biochemical activation is a molecular interaction that increases the catalytic rate of the target enzyme."
	:super (owl-some realizes to_decrease_the_rate_of_formation))
(sio-class "biochemical inhibition" "Biochemical inhibition is a molecular interaction that decreases the catalytic rate of the target enzyme."
	:super (owl-some realizes to_increase_the_activation_energy)))

(as-subclasses
 regulation_of_process
(sio-class "process down-regulation" "process down-regulation is a process that decreases the frequency, rate or extent of one or more processes in relation to a reference state."
	:disjoint process_up-regulation
	:annotation
	(synonym-en "negative regulation"))
(sio-class "process maintenance" "the process of maintaining some the frequency, rate or extent of another process.")
(sio-class "process up-regulation" "process up-regulation is a process that increases the frequency, rate or extent of one or more processes in relation to a reference state."
	:disjoint process_down-regulation
	:annotation
	(synonym-en "positive regulation"))
(sio-class "regulation of biochemical process" "regulation of biochemical process is a process that changes the frequency, rate or extent of a target biochemical process.")
(sio-class "regulation of object quantity" "regulation of a participant quantity is the regulation of a process in which the quantity of its partcipants is changed."
	:super (owl-some regulates quantity))
(sio-class "regulation of process duration" "regulation of a process duration is a process that modulates the duration of another process relative to some reference process.")
(sio-class "regulation of process frequency" "regulation of a process duration is a process that modulates the frequency of another process relative to some reference process.")
(sio-class "regulation of process spatial extent" "regulation of a process spatial extent is a process that modulates the spatial extent of another process relative to some reference process."
	:annotation
	(eg "heating the solution causes greater diffusion of a chemical.")))

(sio-class "regulation of transcription" "A process that modulates the frequency, rate or extent of transcription"
	:super regulation_of_biochemical_process)
(sio-class "regulation of translation" "A process that modulates the frequency, rate or extent of translation"
	:super regulation_of_biochemical_process)

(sio-class "protein mediated regulation of translation" "A process mediated by a protein that modulates the frequency, rate or extent of translation."
	:super regulation_of_translation (owl-some has_agent protein))
(sio-class "rna mediated regulation of translation" "A process mediated by rna that modulates the frequency, rate or extent of translation."
	:super regulation_of_translation (owl-some has_agent RNA_transcript))

(as-subclasses
 regulation_of_object_quantity
(sio-class "regulation of molecular quantity" "A process that modulates the frequency, rate or extent of process involved in the creation or destruction of a molecule.")
(sio-class "regulation of object consumption" "A process that modulates the frequency, rate or extent of process involved in the consumption of an object.")
(sio-class "regulation of object production" "regulation of a participant quantity is the regulation of a process in which the quantity of a selected participant is increased."))

(sio-class "regulation of molecular degradation" "A process that modulates the frequency, rate or extent of process involved in the destruction of a molecule."
	:super regulation_of_molecular_quantity (owl-some regulates chemical_destruction))
(sio-class "regulation of molecular production" "A process that modulates the frequency, rate or extent of process involved in the production of a molecule."
	:super regulation_of_molecular_quantity)

(as-subclasses
 regulation_of_molecular_degradation
(sio-class "decreased molecular degradation from decreased regulation" "A process that decreases the frequency, rate or extent of process involved in the destruction of a molecule as a result of decreased regulation.")
(sio-class "decreased molecular degradation from increased regulation" "A process that decreases the frequency, rate or extent of process involved in the destruction of a molecule as a result of increased regulation."
	:super (owl-some has_quality increased) (owl-some results_in (owl-and chemical_destruction (owl-some has_quality decreased))))
(sio-class "increased molecular degradation from decreased regulation" "A process that increases the frequency, rate or extent of process involved in the destruction of a molecule as a result of decreased regulation.")
(sio-class "increased molecular degradation from increased regulation" "A process that increases the frequency, rate or extent of process involved in the destruction of a molecule as a result of increased regulation."))

(as-subclasses
 regulation_of_molecular_production
(sio-class "decreased molecular production from decreased regulation" "A process that decreases the frequency, rate or extent of process involved in the production of a molecule as a result of decreased regulation.")
(sio-class "decreased molecular production from increased regulation" "A process that decreases the frequency, rate or extent of process involved in the production of a molecule as a result of increased regulation.")
(sio-class "increased molecular production from decreased regulation" "A process that increases the frequency, rate or extent of process involved in the production of a molecule as a result of decreased regulation.")
(sio-class "increased molecular production from increased regulation" "A process that increases the frequency, rate or extent of process involved in the production of a molecule as a result of increased regulation."))

(as-subclasses
 regulation_of_object_consumption
(sio-class "decreased object consumption from increased regulation" "increased regulation leads to an decrease in the consumption of an object of specified type.")
(sio-class "increased object consumption from increased regulation" "increased regulation leads to an increase in the consumption of an object of specified type.")
(sio-class "maintenance of level of object consumption" "regulation of a participant quantity is the regulation of a process in which the quantity of a selected participant is maintained at a steady level."))

(as-subclasses
 regulation_of_object_production
(sio-class "decreased object production from increased regulation" "increased regulation leads to a decrease in the number of target objects of a specified type.")
(sio-class "increased object production from increased regulation" "increased regulation leads to an increase in the number of target objects of a specified type.")
(sio-class "maintenance of quantity of object production" "maintenance of quantity of object production is a regulation of object production in which the number of objects produced is held more or less constant."))

(as-subclasses
 regulation_of_process_duration
(sio-class "decreased duration of process from increased regulation" "decreased duration of process from increased regulation is a process in which the duration of the target process is decreased as a result of increased regulation.")
(sio-class "increased duration of process from increased regulation" "increased duration of process from increased regulation is a process in which the duration of the target process is increased as a result of increased regulation.")
(sio-class "maintenance of duration of process" "maintenance of duration of process is a process that regulates a target process to maintain its duration within an expected interval."))

(as-subclasses
 regulation_of_process_frequency
(sio-class "decreased frequency of process from increased regulation" "the increase of regulation leads to a decreased occurence of processes of the target process type.")
(sio-class "increased frequency of process from increased regulation" "the increase of regulation leads to a increased occurence of processes of the target type.")
(sio-class "maintenance of frequency of process" "maintenance of frequency of process is a process that regulates the number of occurences of a target process type to a specified number or interval."))

(as-subclasses
 regulation_of_process_spatial_extent
(sio-class "decreased spatial extent of process from decreased regulation" "the increase of regulation leads to a decreased spatial extent of the target process.")
(sio-class "increased spatial extent of process from increased regulation" "the increase of regulation leads to a increased spatial extent of the target process.")
(sio-class "maintenance of spatial extent of process" "maintenance of spatial extent of process is a regulation of a process' spatial extent within some specified parameter."))

(sio-class "chemical transport" "Chemical transport is the directed movement of a chemical entity by some agent (e.g. transporter)."
	:super transporting (owl-some has_participant chemical_entity)
	:annotation
	(equivalent-rdf "biopax:Transport"))

(sio-class "membrane transport" "membrane transport is the movement of molecules across a membrane."
	:super chemical_transport)

(as-disjoint-subclasses
 membrane_transport
(sio-class "active transport" "Active transport is the movement of a substance across a membrane against its concentration gradient (from low to high concentration) and requires chemical energy."
	:annotation
	(equivalent-rdf "biopax:TransportWithBiochemicalReaction"))
(sio-class "passive transport" "Passive transport is the movement of a substance across a membrane and does not require chemical energy."))

(as-disjoint-subclasses
 active_transport
(sio-class "primary active transport" "Primary active transport, also called direct active transport, directly uses energy to transport molecules across a membrane.")
(sio-class "secondary active transport" "secondary active transport or co-transport uses electrochemical potential difference created by pumping ions out of the cell to transport molecules across a membrane."))

(as-disjoint-subclasses
 secondary_active_transport
(sio-class "antiport enabled secondary active transport" "antiport enabled secondary active transport is a secondary active transfort in which both ion and molecule are transported in opposite directions simultaneously.")
(sio-class "symport enabled secondary active transport" "symport enabled secondary active transport is a secondary active transfort in which both ion and molecule are transported in the same direction simultaneously."))

(sio-class "active movement" "Active movement is the process in which an object is spatially displaced using some chemical energy."
	:super movement)
(sio-class "passive movement" "Passive movement is the process in which an object is spatially displaced without an expenditure of energy contained in molecular bonds."
	:super movement)

(sio-class "locomotion" "The self-propelled movement of an object."
	:super active_movement
	:annotation
	(annotation broaderThan (literal "GO:0040011" :type :RDF_PLAIN_LITERAL)))

(sio-class "diffusion" "Diffusion is motion of particles at temperatures above absolute zero."
	:super passive_movement)

(sio-class "brownian motion" "Brownian motion is the seemlingly random movement of particles suspended in a fluid."
	:super diffusion)
(sio-class "osmosis" "Osmosis is the movement of water molecules through a selectively-permeable membrane down a water potential gradient."
	:super diffusion)

(as-subclasses
 procedure
(sio-class "assay" "An assay is an investigative (analytic) procedure in laboratory medicine, pharmacology, environmental biology, and molecular biology for qualitatively assessing or quantitatively measuring the presence or amount or the functional activity of a target entity (the analyte) which can be a drug or biochemical substance or a cell in an organism or organic sample."
	:super (owl-some has_output (owl-and (owl-or measurement_value existence_quality) (owl-some is_attribute_of entity))) (owl-some has_input object))
(sio-class "information processing" "information processing is a process that involves the generation or use of information.")
(sio-class "investigation" "investigation is the process of carrying out a plan or procedure so as to discover facts or information about the object of study."
	:super (owl-some has_product object) (owl-some realizes objective)
	:annotation
	(synonym-rdf "study"))
(sio-class "medical procedure" "a medical procedure is a procedure to identify, examine, alleviate or eliminate an undesirable biological disease or disorder."))

(as-subclasses
 information_processing
(sio-class "data analysis" "data analysis is a process of inspecting, cleaning, transforming, and modeling data with the goal of highlighting useful information, suggesting conclusions, and supporting decision making."
	:super (owl-and (owl-some has_output proposition) (owl-some has_input data_item)))
(sio-class "data collection" "data collection is the process of acquiring information."
	:super (owl-some has_product data_item))
(sio-class "data transformation" "data transformation is the process of applying an algorithmic procedure to some input data and producing some output data."
	:super (owl-some has_output data_item) (owl-some has_input data_item))
(sio-class "literature curation" "literature curation is the process of an agent selecting and extracting terms and phrases from a document."
	:super (owl-some has_output phrase) (owl-some has_input document))
(sio-class "sofware execution" "software execution is the process of executing software on a computing device."))

(sio-class "parameterized data transformation" "a parameterized data transformation is a data transformation whose behaviour may be modified by one or more parameters."
	:super data_transformation (owl-some has_parameter parameter))

(sio-class "web service invocation" "a web service invocation involves the execution of a web service."
	:super sofware_execution)

(sio-class "SADI web service invocation" "a SADI web service invocation is the excution of a SADI web service."
	:super web_service_invocation (owl-some has_agent SADI_semantic_web_service)
	:annotation
	(annotation subset (literal "sadi" :lang "en")))

(sio-class "experiment" "An experiment is an investigation that has the goal of verifying, falsifying, or establishing the validity of a hypothesis. "
	:super investigation
	:annotation
	(synonym-rdf "study"))
(sio-class "study" "a study is a process that realizes the steps of a study design."
	:super investigation (owl-some realizes objective) (owl-some is_manifestation_of study_design))

(as-subclasses
 experiment
(sio-class "intervention study" "an intervention study has the objective of improving the condition of an individual or a group of individuals, and demonstrates the magnitude of that capability by comparing it to a control group.")
(sio-class "mass spectrometry experiment" "a mass spectrometry experiment is an experiment that involves the use of a mass spectrometer. ")
(sio-class "microarray experiment" "a microarray experiment is an experiment that involves a microarray device to measure the expression of one or more genes.")
(sio-class "observational study" "observational study draws inferences about the possible effect of a treatment on subjects, where the assignment of subjects into a treated group versus a control group is outside the control of the investigator"
	:super (owl-some has_participant hypothesis) (owl-some specifies (owl-and truth_value (owl-some is_quality_of hypothesis)))))

(sio-class "clinical trial" "a clinical trial is an intervention trial to determine the safety and efficacy of  medical interventions (e.g., drugs, diagnostics, devices, therapy protocols). "
	:super intervention_study (owl-some is_specified_by experimental_protocol))

(sio-class "controlled observational cohort study" "In a controlled observational cohort study, two groups of subjects are selected from two populations that are thought to differ in only one characteristic. The groups of subjects are studied for a specific period and contrasted at the end of the study period."
	:super observational_study)

(as-subclasses
 medical_procedure
(sio-class "diagnostic test" "A diagnostic test is a procedure performed to confirm, or determine the presence of disease in an individual suspected of having the disease, usually following the report of symptoms, or based on the results of other medical tests.")
(sio-class "differential diagnosis" "A differential diagnosis (sometimes abbreviated DDx, ddx, DD, D/Dx, or ΔΔ) is a systematic diagnostic method used to identify the presence of an entity where multiple alternatives are possible (and the process may be termed differential diagnostic procedure), and may also refer to any of the included candidate alternatives (which may also be termed candidate condition).")
(sio-class "medical diagnosis" "A medical diagnosis (often simply termed diagnosis) refers to the process of attempting to determine or identify a possible disease or disorder."
	:super (owl-some has_output diagnostic_opinion) (owl-some has_input (owl-and biological_entity (owl-some has_quality disordered))))
(sio-class "medical screening" "A medical screening is a medical test or series used to detect or predict the presence of disease in individuals at risk for disease within a defined group, such as a population, family, or workforce"))


(add-superclass to_be_interacted_with (owl-some is_mutual_disposition_of to_interact_with))
(add-superclass to_interact_with (owl-some is_mutual_disposition_of to_be_interacted_with))
(add-superclass to_be_translocated (owl-some is_mutual_disposition_of to_translocate))
(add-superclass to_be_observed (owl-some is_mutual_disposition_of to_observe))
(add-superclass to_be_recorded (owl-some is_mutual_disposition_of to_record))

(add-superclass to_be_actively_interacted_with (owl-some is_mutually_related_to to_actively_interact_with))
(add-superclass to_be_passively_interacted_with (owl-some is_mutually_related_to to_passively_interact_with))

(add-superclass chemical_entity_role (owl-some is_role_of chemical_entity))
(add-superclass chemical_substance_role (owl-some is_role_of chemical_substance))
(add-superclass molecular_entity_role (owl-some is_role_of molecule))
(add-superclass buffer_role (owl-some is_role_of buffer))
(add-superclass reagent_role (owl-some is_role_of reagent))
(add-superclass product_role (owl-some is_role_of product))
(add-superclass poison_role (owl-some is_role_of poison))
(add-superclass professor_role (owl-some is_role_of professor))
(add-superclass student_role (owl-some is_role_of student))

(add-superclass professor_role (owl-some is_mutual_role_of student_role))

(add-superclass text_quality (owl-some is_quality_of textual_entity))
(add-superclass informational_quality (owl-some is_quality_of information_content_entity))
(add-superclass cellular_quality (owl-some is_quality_of cell))

(add-superclass information_content_entity (only has_proper_part information_content_entity))
(add-superclass material_entity (only has_proper_part material_entity))

(add-superclass database_table (owl-some is_proper_part_of database))
(add-superclass database_entry (owl-some is_proper_part_of database))

(add-superclass database_key (owl-some has_component_part column))
(add-superclass specification (owl-some has_component_part objective))

(add-superclass material_entity (owl-some has_attribute mass))
(add-superclass disposition (owl-some has_attribute probability_measure))

(add-superclass protein_sequence (owl-some represents (owl-and molecular_structure (owl-some is_attribute_of polypeptide))))
(add-superclass nucleic_acid_sequence (owl-some represents (owl-and molecular_structure (owl-some is_attribute_of nucleic_acid))))
(add-superclass biopolymer_sequence (owl-some represents (owl-and molecular_structure (owl-some is_attribute_of biopolymer))))

(add-superclass line_segment (exactly 2 has_component_part terminal_point))
(add-superclass collection_of_points collection)
(add-superclass collection_of_points (only has_member point))
(add-superclass polygonal_face (owl-some is_part_of polygon))
(add-superclass molecular_structure_file (owl-some has_part cartesian_coordinate))
(add-superclass polyline (at-least 2 has_component_part line_segment))
(add-superclass polyhedral_surface (owl-some has_part polygonal_face))
(add-superclass polyhedral_skeleton (owl-some has_direct_part polygon_edge))
(add-superclass row (owl-some has_proper_part cell__informational_))
(add-superclass spatial_region (only has_part spatial_region))
(add-superclass disease (owl-some is_quality_of (owl-and material_entity (owl-some has_quality abnormal))))
(add-superclass data_item (owl-some is_participant_in (owl-and process (owl-some realizes objective))))
(add-superclass Cartesian_coordinate_axis (owl-some satisfies cartesian_coordinate_system))
(add-superclass process (only exists_at time_interval))
(add-superclass to_serve_as_a_host (owl-some is_capability_of host))
(add-superclass collection_of_3d_molecular_structure_models (owl-some has_member _3d_structure_model))
(add-superclass molecular_structure_descriptor (owl-some represents molecular_structure))
(add-superclass biomolecular_structure_descriptor (owl-some is_about organic_molecule))
(add-superclass column (only specifies (owl-and cell__informational_ (owl-some has_value :RDFS_LITERAL) (only has_value :RDFS_LITERAL))))
(add-superclass nucleic_acid_sequence (owl-some is_attribute_of nucleic_acid))
(add-superclass biopolymer_sequence (owl-some describes biopolymer))
(add-superclass column (at-least 0 has_attribute name))

(add-equivalent anatomical_entity (owl-and (owl-or material_entity spatial_region) (owl-some is_direct_part_of biological_entity)))
