
(defclass interacting
  :subclass
  process
  (owland
   (atleast 2 realizes to_interact_with)
   (atleast 2 realizes to_be_interacted_with))
  (owlsome has_participant object)
  :annotation
  (annotation subset "core")
  (label "interacting" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "interacting
  is a process characterized by the interaction between two or more
  entities." "en"))


(defclass chemical_interaction
  :subclass interacting (owlsome has_participant chemical_entity)
  :annotation
  (annotation equivalentTo "biopax:Interaction")
  (annotation (iri "http://purl.org/dc/terms/description") "A chemical
  interaction is a biochemical process in which chemical entities
  interact through some set of attractive forces." "en")
(defclass communicating
  :subclass interacting
  :annotation
  (annotation hasSynonym "communication")
  (label "communicating" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "communicating
  is the process of conveying information through the exchange of
  thoughts, messages, or information, as by speech, visuals, signals,
  writing, or behaviour." "en"))
(defclass comparing
  :subclass
  interacting
  (owland
   (owlsome realizes to_be_compared)
   (owlsome realizes to_compare))
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "comparing
  is the process of examining a set of objects and determining their
  equality or inequality based on one or more features." "en")
  (annotation hasSynonym "comparison")
  (label "comparing" "en"))
(defclass creating
  :subclass interacting (owlsome has_product object)
  :annotation
  (label "creating" "en")
  (annotation hasSynonym "development")
  (annotation hasSynonym "synthesis")
  (annotation hasSynonym "production")
  (annotation (iri "http://purl.org/dc/terms/description") "creating
  is the process in which an entity comes into existence." "en")
  (annotation hasSynonym "formulation")
  (annotation hasSynonym "creation"))
(defclass destroying
  :subclass interacting (owlsome has_substrate object)
  :annotation
  (annotation hasSynonym "destruction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "destroying
  is a process in which something is broken down and/or ceases to
  exist." "en")
  (label "destroying" "en"))
(defclass measuring
  :subclass
  interacting
  (owlsome has_output measurement_value)
  (owlsome has_input
           (owlor quality process object))
  :annotation
  (annotation hasSynonym "measurement" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "measuring
  is the process of determining the size, amount, or degree
  of (something) by using an instrument or device marked in standard
  units" "en")
  (label "measuring" "en"))
(defclass modifying
  :subclass interacting (owlsome has_output object) (owlsome has_target object)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "modifying
  is the process by which an entity gains or loses parts, qualities,
  roles, dispositions, functions, etc, but maintains their identity
  through these changes." "en")
  (annotation hasSynonym "modification" "en")
  (label "modifying" "en"))
(defclass observing
  :subclass interacting (owlsome has_output entity) (owlsome has_input entity)
  :annotation
  (label "observing" "en")
  (annotation hasSynonym "observation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "observing
  is a process of passive interaction in which one entity makes note
  of attributes of one or more entities." "en"))
(defclass regulating
  :subclass interacting
  :equivalent
  (owlor process_maintenance process_up-regulation process_down-regulation)
  :annotation
  (annotation hasSynonym "regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "regulating
  is a process that modulates the attributes of an object or
  process." "en")
  (label "regulating" "en"))
(defclass sampling
  :subclass
  interacting
  (owland
   (owlsome has_output sample)
   (owlsome has_input object))
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "sampling
  is the act of obtaining a sample, whether through selection,
  collection or preparation." "en")
  (label "sampling" "en"))
(defclass transporting
  :subclass
  interacting
  (owland
   (owlsome realizes to_transport)
   (owlsome realizes to_be_transported))
  :annotation
  (label "transporting" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "transporting
  is a process in which one object physically moves another object
  from one location to another." "en"))


(defclass chemical_reaction
  :subclass
  chemical_interaction
  (owland
   (owlsome has_proper_part chemical_synthesis)
   (owlsome has_proper_part chemical_destruction))
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A chemical
  reaction is a process that leads to the transformation of one set of
  chemical substances to another." "en")
  (label "chemical reaction" "en"))
(defclass drug_effect
  :subclass chemical_interaction (owlsome has_agent drug)
  :annotation
  (label "drug effect" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A drug
  effect is a chemical interaction in which a chemical elicits a
  marked characteristic of a biological system." "en"))
(defclass metabolism
  :subclass chemical_interaction
  :annotation
  (label "metabolism" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Metabolism
  is the set of chemical processes that occur within a living organism
  in order to maintain life." "en"))


(defclass acid-base_reaction
  :subclass chemical_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "an
  acid-base reaction is a chemical reaction between an acid and a
  base." "en")
  (label "acid-base reaction" "en"))
(defclass catalyzed_reaction
  :subclass chemical_reaction (owlsome has_agent catalyst)
  :annotation
  (label "catalyzed reaction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "a
  catalyzed reaction is a chemical reaction that is facilitated by a
  catalyst." "en"))
(defclass organic_reaction
  :subclass chemical_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "An organic
  reaction is a chemical reaction involving at least one organic
  molecule." "en")
  (label "organic reaction" "en"))
(defclass isomerization_reaction
  :subclass chemical_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "An
  isomerization reaction is a chemical reaction in which a molecule is
  converted into its isomer." "en")
  (label "isomerization reaction" "en"))
(defclass inorganic_reaction
  :subclass chemical_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "An
  inorganic reaction is a chemical reaction that involves the
  transformation of inorganic molecules." "en")
  (label "inorganic reaction" "en"))
(defclass molecular_modification
  :subclass chemical_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Molecular
  modification is chemical alteration of a known and previously
  characterized lead compound for the purpose of enhancing its
  usefulness as a drug. This could mean enhancing its specificity for
  a particular body target site, increasing its potency, improving its
  rate and extent of absorption, modifying to advantage its time
  course in the body, reducing its toxicity, changing its physical or
  chemical properties (like solubility) to provide desired
  features." "en")
  (label "molecular modification" "en"))
(defclass redox_reaction
  :subclass
  chemical_reaction
  (owland
   (owlsome realizes
            (owland to_oxidize
                    (owlsome is_disposition_of molecule)))
   (owlsome realizes
            (owland to_reduce
                    (owlsome is_disposition_of molecule))))
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "a redox
  reaction is a chemical reaction in which there is a net movement of
  electrons from one reactant to another." "en")
  (label "redox reaction" "en"))

(defclass biochemical_reaction
  :subclass
  catalyzed_reaction
  (owland catalyzed_reaction
          (owlsome has_agent enzyme)
          (owlsome has_target substrate)
          (owlsome has_product product))
  :annotation
  (annotation equivalentTo "biopax:BiochemicalReaction")
  (annotation (iri "http://purl.org/dc/terms/description") "A
  biochemical reaction is a biochemical process that involves the
  conversion of at least one chemical participant (target) into
  another (product) by an enzyme (agent)." "en")
  (annotation equivalentTo "biopax:Conversion")
  (label "biochemical reaction" "en"))


(defclass synthesis_reaction
  :subclass inorganic_reaction
  :annotation
  (label "synthesis reaction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A
  synthesis reaction is an inorganic reaction in which two or more
  molecules are chemically bonded together to produce a single
  product." "en"))
(defclass displacement_reaction
  :subclass inorganic_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A
  displacement reaction is an inorganic reaction in which a elementary
  substance displaces and sets free a constituent atom from a
  molecule." "en")
  (label "displacement reaction" "en"))
(defclass decomposition_reaction
  :subclass inorganic_reaction
  :annotation
  (label "decomposition reaction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A
  decomposition reaction is an inorganic reaction in which molecule is
  fragmented into submolecules or atoms." "en"))


(defclass single_displacement_reaction
  :subclass displacement_reaction
  :annotation
  (label "single displacement reaction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A single
  displacement reaction where one atom is transferred out of one
  molecule and into another." "en"))
(defclass double_displacement_reaction
  :subclass displacement_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A double
  displacement reaction is a displacement reaction in which two
  molecules swap ions, effectively displacing each other to form two
  new molecules." "en")
  (label "double displacement reaction" "en"))


(defclass addition_reaction
  :subclass organic_reaction
  :annotation
  (label "addition reaction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "An
  addition reaction is an organic reaction where two or more molecules
  combine to form a larger one. Addition reactions are limited to
  chemical compounds that have multiply-bonded atoms:\n * Molecules
  with carbon-carbon double bonds or triple bonds\n * Molecules with
  carbon - hetero double bonds like C=O or C=N" "en"))


(defclass polar_addition_reaction
  :subclass addition_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "a polar
  addition reaction is an addition reaction involving polar
  residues." "en")
  (label "polar addition reaction" "en"))
(defclass non-polar_addition_reaction
  :subclass addition_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "a
  non-polar addition reaction is an addition reaction involving
  non-polar residues." "en")
  (label "non-polar addition reaction" "en"))


(defclass electrophilic_addition_reaction
  :subclass polar_addition_reaction
  :annotation
  (label "electrophilic addition reaction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "an
  electrophilic addition reaction is a polar addition reaction where a
  pi bond is removed by the creation of two new covalent
  bonds." "en"))
(defclass nucleophilic_addition_reaction
  :subclass polar_addition_reaction
  :annotation
  (label "nucleophilic addition reaction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A
  nucleophilic addition reaction is an addition reaction where a pi
  bond is removed by the creation of two new covalent bonds by the
  addition from a nucleophile." "en"))


(defclass free_radical_addition
  :subclass non-polar_addition_reaction
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A free
  radical addition is a non-polar addition reaction involving free
  radicals." "en")
  (label "free radical addition" "en"))


(defclass drug_drug_interaction
  :subclass drug_effect (atleast 2 has_participant drug)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "a
  drug-drug interaction is an interaction in which two drugs interact
  in such a way to produce a non-additive biological response." "en")
  (label "drug drug interaction" "en"))


(defclass catabolism
  :subclass metabolism
  :disjoint anabolism
  :annotation
  (label "catabolism" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Anabolism
  is the set of metabolic processes that take apart larger chemical
  entities units into smaller chemical entities." "en"))
(defclass anabolism
  :subclass metabolism
  :disjoint catabolism
  :annotation
  (label "anabolism" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Anabolism
  is the set of metabolic processes that construct larger chemical
  entities units from smaller chemical entities." "en"))


(defclass conversing
  :subclass communicating
  :annotation
  (label "conversing" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "conversing
  a form of interactive, spontaneous communication between two or more
  agents who are following rules of etiquette." "en"))
(defclass gesturing
  :subclass communicating
  :annotation
  (label "gesturing" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "gesturing
  is a form of non-verbal communication in which visible bodily
  actions communicate particular messages, either in place of speech
  or together and in parallel with spoken words. Gestures include
  movement of the hands, face, or other parts of the body." "en"))


(defclass birthing
  :subclass creating (owlsome has_product biological_entity)
  :annotation
  (label "birthing" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "brirthing
  is the process by which a biological organism is brought into
  existence." "en"))
(defclass chemical_synthesis
  :subclass
  creating
  (owlsome has_agent enzyme)
  (owlsome has_product chemical_entity)
  :annotation
  (label "chemical synthesis" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Chemical
  synthesis is synthesis of a chemical entity from physical precursors
  through one or more chemical interactions or reactions." "en"))
(defclass predicting
  :subclass creating (owlsome has_product proposition)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "predicting
  is the process of formulating a proposition about a state of affairs
  which might be realized in the future." "en")
  (label "predicting" "en"))
(defclass reasoning
  :subclass
  creating
  (owlsome has_product
           (owland truth_value
                   (owlsome is_quality_of proposition)))
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "reasoning
  is the agentive process of using knowledge to evaluate the truth
  value of a proposition." "en")
  (label "reasoning" "en"))
(defclass learning
  :subclass creating (owlsome has_product proposition)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "learning
  is the agentive process of acquiring knowledge." "en")
  (label "learning" "en"))
(defclass planning
  :subclass creating (owlsome has_product (owlor plan design))
  :annotation
  (label "planning" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Planning
  is the agentive process of developing a plan that specifies a set of
  actions in order to meet a set of goals or objectives." "en"))
(defclass evolving
  :subclass creating (owlsome has_product entity)
  :annotation
  (label "evolving" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "evolving
  is a process that elicits change across successive generations in
  the inherited characteristics of biological populations." "en"))
(defclass reproducing
  :subclass
  creating
  (owland
   (owlsome has_input object)
   (owlsome has_product object))
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "reproducing
  is a process characterized by creation of an entity that is similar
  or exactly the same as the template from which it is derived." "en")
  (label "reproducing" "en"))


(defclass biosynthesis
  :subclass chemical_synthesis
  :annotation
  (label "biosynthesis" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Biosynthesis
  is the synthesis of an organic compound in a living organism,
  usually aided by enzymes." "en"))
(defclass molecular_complex_formation
  :subclass chemical_synthesis (owlsome has_product molecular_complex)
  :annotation
  (label "molecular complex formation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "molecular
  complex formation is the process of forming a molecular complex from
  its constituent parts." "en"))


(defclass transcription
  :subclass
  biosynthesis
  (owlsome has_output
           (owland ribonucleic_acid
                   (owlsome is_transcribed_from deoxyribonucleic_acid)))
  (owlsome has_input deoxyribonucleic_acid)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Transcription
  is the process of creating a complementary RNA copy of a sequence of
  DNA." "en")
  (label "transcription" "en"))
(defclass translation
  :subclass
  biosynthesis
  (owlsome has_input ribonucleic_acid)
  (owlsome has_product polypeptide)
  :annotation
  (label "translation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Translation
  is the process of producing a polypeptide from a ribonucleic acid by
  a ribosome." "en"))


(defclass biological_reproduction
  :subclass reproducing
  :annotation
  (label "biological reproduction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Biological
  reproduction is the biological process by which one or more
  biological organisms are produced from their \"parents\". " "en"))


(defclass chemical_destruction
  :subclass destroying (owlsome has_target chemical_entity)
  :annotation
  (label "chemical destruction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Chemical
  destruction is destruction of a chemical entity to its chemical
  constituents through one ormore chemical interactions or
  reactions." "en"))
(defclass dying
  :subclass destroying (owlsome has_substrate biological_entity)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "dying is a
  process in which a biological entity ceases to exist." "en")
  (label "dying" "en"))


(defclass decreased_chemical_destruction
  :subclass
  chemical_destruction
  (owlsome has_attribute
           (owland count
                   (owlsome is_greater_than
                            (owland count
                                    (owlsome is_attribute_of
                                             chemical_destruction)))))
  (owlsome has_quality increased)
  (owlsome in_relation_to chemical_destruction)
  :annotation
  (label "decreased chemical destruction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "decreased
  chemical destruction is a process in which there is a decrease in
  the amount of chemical destroyed relative to some reference
  process" "en"))
(defclass increased_chemical_destruction
  :subclass
  chemical_destruction
  (owlsome has_attribute
           (owland count
                   (owlsome is_greater_than
                            (owland count
                                    (owlsome is_attribute_of
                                             chemical_destruction)))))
 (owlsome has_quality increased)
  :annotation
  (label "increased chemical destruction" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "increased
  chemical destruction is a process in which there is an increase in
  the amount of chemical destroyed relative to some reference
  process" "en"))
(defclass molecular_complex_dissociation
  :subclass chemical_destruction (owlsome has_target molecular_complex)
  :annotation
  (label "molecular complex dissociation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "molecular
  complex disassociation is the process of dissambly of a molecular
  complex into its constitutent parts." "en"))


(defclass perception
  :subclass observing
  :annotation
  (label "perception" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Perception
  is the organization, identification, and interpretation of sensory
  information in order to fabricate a mental representation through
  the process of transduction, which sensors in the body transform
  signals from the environment into encoded neural signals." "en"))


(defclass regulation_of_capability
  :subclass regulating (owlsome affects capability)
  :annotation
  (label "regulation of capability" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of capability is the regulation of the ability of one party by
  another." "en"))
(defclass regulation_of_process
  :subclass regulating (owlsome regulates process)
  :equivalent
  (owlor regulation_of_process_frequency
         regulation_of_process_duration regulation_of_process_spatial_extent
         regulation_of_object_quantity)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of a process is a process that modulates the duration, frequency,
  spatial extent of a target process." "en")
  (label "regulation of process" "en"))


(defclass regulation_of_catalytic_capability
  :subclass regulation_of_capability (owlsome regulates to_change_materially)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "the
  regulation of the enzymatic activity." "en")
  (label "regulation of catalytic capability" "en"))


(defclass biochemical_activation
  :subclass
  regulation_of_catalytic_capability
  (owlsome realizes to_decrease_the_rate_of_formation)
  :disjoint biochemical_inhibition
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Biochemical
  activation is a molecular interaction that increases the catalytic
  rate of the target enzyme." "en")
  (label "biochemical activation" "en"))
(defclass biochemical_inhibition
  :subclass
  regulation_of_catalytic_capability
  (owlsome realizes to_increase_the_activation_energy)
  :disjoint biochemical_activation
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Biochemical
  inhibition is a molecular interaction that decreases the catalytic
  rate of the target enzyme." "en")
  (label "biochemical inhibition" "en"))


(defclass process_up-regulation
  :subclass regulation_of_process
  :disjoint process_down-regulation
  :annotation
  (label "process up-regulation" "en")
  (annotation hasSynonym "positive regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "process
  up-regulation is a process that increases the frequency, rate or
  extent of one or more processes in relation to a reference
  state." "en"))
(defclass process_down-regulation
  :subclass regulation_of_process
  :disjoint process_up-regulation
  :annotation
  (label "process down-regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "process
  down-regulation is a process that decreases the frequency, rate or
  extent of one or more processes in relation to a reference
  state." "en")
  (annotation hasSynonym "negative regulation" "en"))
(defclass process_maintenance
  :subclass regulation_of_process
  :annotation
  (label "process maintenance" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "the
  process of maintaining some the frequency, rate or extent of another
  process." "en"))
(defclass regulation_of_process_frequency
  :subclass regulation_of_process
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of a process duration is a process that modulates the frequency of
  another process relative to some reference process." "en")
  (label "regulation of process frequency" "en"))
(defclass regulation_of_process_duration
  :subclass regulation_of_process
  :annotation
  (label "regulation of process duration" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of a process duration is a process that modulates the duration of
  another process relative to some reference process." "en"))
(defclass regulation_of_process_spatial_extent
  :subclass regulation_of_process
  :annotation
  (annotation example "heating the solution causes greater diffusion
  of a chemical." "en")
  (label "regulation of process spatial extent" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of a process spatial extent is a process that modulates the spatial
  extent of another process relative to some reference
  process." "en"))
(defclass regulation_of_object_quantity
  :subclass regulation_of_process (owlsome regulates quantity)
  :annotation
  (label "regulation of object quantity" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of a participant quantity is the regulation of a process in which
  the quantity of its partcipants is changed." "en"))
(defclass regulation_of_biochemical_process
  :subclass regulation_of_process
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of biochemical process is a process that changes the frequency, rate
  or extent of a target biochemical process." "en")
  (label "regulation of biochemical process" "en"))


(defclass regulation_of_translation
  :subclass regulation_of_biochemical_process
  :annotation
  (label "regulation of translation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that modulates the frequency, rate or extent of translation" "en"))
(defclass regulation_of_transcription
  :subclass regulation_of_biochemical_process
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that modulates the frequency, rate or extent of transcription" "en")
  (label "regulation of transcription" "en"))


(defclass protein_mediated_regulation_of_translation
  :subclass regulation_of_translation (owlsome has_agent protein)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  mediated by a protein that modulates the frequency, rate or extent
  of translation." "en")
  (label "protein mediated regulation of translation" "en"))
(defclass rna_mediated_regulation_of_translation
  :subclass regulation_of_translation (owlsome has_agent RNA_transcript)
  :annotation
  (label "rna mediated regulation of translation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  mediated by rna that modulates the frequency, rate or extent of
  translation." "en"))


(defclass regulation_of_object_consumption
  :subclass regulation_of_object_quantity
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that modulates the frequency, rate or extent of process involved in
  the consumption of an object." "en")
  (label "regulation of object consumption" "en"))
(defclass regulation_of_object_production
  :subclass regulation_of_object_quantity
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of a participant quantity is the regulation of a process in which
  the quantity of a selected participant is increased." "en")
  (label "regulation of object production" "en"))
(defclass regulation_of_molecular_quantity
  :subclass regulation_of_object_quantity
  :annotation
  (label "regulation of molecular quantity" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that modulates the frequency, rate or extent of process involved in
  the creation or destruction of a molecule." "en"))


(defclass regulation_of_molecular_production
  :subclass regulation_of_molecular_quantity
  :annotation
  (label "regulation of molecular production" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that modulates the frequency, rate or extent of process involved in
  the production of a molecule." "en"))
(defclass regulation_of_molecular_degradation
  :subclass
  regulation_of_molecular_quantity
  (owlsome regulates chemical_destruction)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that modulates the frequency, rate or extent of process involved in
  the destruction of a molecule." "en")
  (label "regulation of molecular degradation" "en"))


(defclass increased_molecular_degradation_from_increased_regulation
  :subclass regulation_of_molecular_degradation
  :annotation
  (label "increased molecular degradation from increased regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that increases the frequency, rate or extent of process involved in
  the destruction of a molecule as a result of increased
  regulation." "en"))
(defclass decreased_molecular_degradation_from_decreased_regulation
  :subclass regulation_of_molecular_degradation
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that decreases the frequency, rate or extent of process involved in
  the destruction of a molecule as a result of decreased
  regulation." "en")
  (label "decreased molecular degradation from decreased regulation" "en"))
(defclass increased_molecular_degradation_from_decreased_regulation
  :subclass regulation_of_molecular_degradation
  :annotation
  (label "increased molecular degradation from decreased regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that increases the frequency, rate or extent of process involved in
  the destruction of a molecule as a result of decreased
  regulation." "en"))
(defclass decreased_molecular_degradation_from_increased_regulation
  :subclass
  regulation_of_molecular_degradation
  (owlsome has_quality increased)
  (owlsome results_in
           (owland chemical_destruction
                   (owlsome has_quality decreased)))
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that decreases the frequency, rate or extent of process involved in
  the destruction of a molecule as a result of increased
  regulation." "en")
  (label "decreased molecular degradation from increased regulation" "en"))


(defclass decreased_molecular_production_from_decreased_regulation
  :subclass regulation_of_molecular_production
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that decreases the frequency, rate or extent of process involved in
  the production of a molecule as a result of decreased
  regulation." "en")
  (label "decreased molecular production from decreased regulation" "en"))
(defclass increased_molecular_production_from_increased_regulation
  :subclass regulation_of_molecular_production
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that increases the frequency, rate or extent of process involved in
  the production of a molecule as a result of increased
  regulation." "en")
  (label "increased molecular production from increased regulation" "en"))
(annotation-property (iri "http://www.w3.org/2002/07/owl#versionInfo"))
(defclass increased_molecular_production_from_decreased_regulation
  :subclass regulation_of_molecular_production
  :annotation
  (label "increased molecular production from decreased regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that increases the frequency, rate or extent of process involved in
  the production of a molecule as a result of decreased
  regulation." "en"))
(defclass decreased_molecular_production_from_increased_regulation
  :subclass regulation_of_molecular_production
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A process
  that decreases the frequency, rate or extent of process involved in
  the production of a molecule as a result of increased
  regulation." "en")
  (label "decreased molecular production from increased regulation" "en"))


(defclass maintenance_of_level_of_object_consumption
  :subclass regulation_of_object_consumption
  :annotation
  (label "maintenance of level of object consumption" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "regulation
  of a participant quantity is the regulation of a process in which
  the quantity of a selected participant is maintained at a steady
  level." "en"))
(defclass increased_object_consumption_from_increased_regulation
  :subclass regulation_of_object_consumption
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "increased
  regulation leads to an increase in the consumption of an object of
  specified type." "en")
  (label "increased object consumption from increased regulation" "en"))
(defclass decreased_object_consumption_from_increased_regulation
  :subclass regulation_of_object_consumption
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "increased
  regulation leads to an decrease in the consumption of an object of
  specified type." "en")
  (label "decreased object consumption from increased regulation" "en"))


(defclass decreased_object_production_from_increased_regulation
  :subclass regulation_of_object_production
  :annotation
  (label "decreased object production from increased regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "increased
  regulation leads to a decrease in the number of target objects of a
  specified type." "en"))
(defclass maintenance_of_quantity_of_object_production
  :subclass regulation_of_object_production
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "maintenance
  of quantity of object production is a regulation of object
  production in which the number of objects produced is held more or
  less constant." "en")
  (label "maintenance of quantity of object production" "en"))
(defclass increased_object_production_from_increased_regulation
  :subclass regulation_of_object_production
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "increased
  regulation leads to an increase in the number of target objects of a
  specified type." "en")
  (label "increased object production from increased regulation" "en"))


(defclass maintenance_of_duration_of_process
  :subclass regulation_of_process_duration
  :annotation
  (label "maintenance of duration of process" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "maintenance
  of duration of process is a process that regulates a target process
  to maintain its duration within an expected interval." "en"))
(defclass increased_duration_of_process_from_increased_regulation
  :subclass regulation_of_process_duration
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "increased
  duration of process from increased regulation is a process in which
  the duration of the target process is increased as a result of
  increased regulation." "en")
  (label "increased duration of process from increased regulation" "en"))
(defclass decreased_duration_of_process_from_increased_regulation
  :subclass regulation_of_process_duration
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "decreased
  duration of process from increased regulation is a process in which
  the duration of the target process is decreased as a result of
  increased regulation." "en")
  (label "decreased duration of process from increased regulation" "en"))


(defclass increased_frequency_of_process_from_increased_regulation
  :subclass regulation_of_process_frequency
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "the
  increase of regulation leads to a increased occurence of processes
  of the target type." "en")
  (label "increased frequency of process from increased regulation" "en"))
(defclass decreased_frequency_of_process_from_increased_regulation
  :subclass regulation_of_process_frequency
  :annotation
  (label "decreased frequency of process from increased regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "the
  increase of regulation leads to a decreased occurence of processes
  of the target process type." "en"))
(defclass maintenance_of_frequency_of_process
  :subclass regulation_of_process_frequency
  :annotation
  (label "maintenance of frequency of process" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "maintenance
  of frequency of process is a process that regulates the number of
  occurences of a target process type to a specified number or
  interval." "en"))


(defclass increased_spatial_extent_of_process_from_increased_regulation
  :subclass regulation_of_process_spatial_extent
  :annotation
  (label "increased spatial extent of process from increased regulation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "the
  increase of regulation leads to a increased spatial extent of the
  target process." "en"))
(defclass decreased_spatial_extent_of_process_from_decreased_regulation
  :subclass regulation_of_process_spatial_extent
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "the
  increase of regulation leads to a decreased spatial extent of the
  target process." "en")
  (label "decreased spatial extent of process from decreased
  regulation" "en"))
(defclass maintenance_of_spatial_extent_of_process
  :subclass regulation_of_process_spatial_extent
  :annotation
  (label "maintenance of spatial extent of process" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "maintenance
  of spatial extent of process is a regulation of a process' spatial
  extent within some specified parameter." "en"))


(defclass chemical_transport
  :subclass transporting (owlsome has_participant chemical_entity)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Chemical
  transport is the directed movement of a chemical entity by some
  agent (e.g. transporter)." "en")
  (label "chemical transport" "en")
  (annotation equivalentTo "biopax:Transport"))


(defclass membrane_transport
  :subclass chemical_transport
  :annotation
  (label "membrane transport" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "membrane
  transport is the movement of molecules across a membrane." "en"))

(defclass active_transport
  :subclass membrane_transport
  :disjoint passive_transport
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Active
  transport is the movement of a substance across a membrane against
  its concentration gradient (from low to high concentration) and
  requires chemical energy." "en")
  (label "active transport" "en")
  (annotation equivalentTo "biopax:TransportWithBiochemicalReaction"))
(defclass passive_transport
  :subclass membrane_transport
  :disjoint active_transport
  :annotation
  (label "passive transport" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "Passive
  transport is the movement of a substance across a membrane and does
  not require chemical energy." "en"))


(defclass secondary_active_transport
  :subclass active_transport
  :disjoint primary_active_transport
  :annotation
  (label "secondary active transport" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "secondary
  active transport or co-transport uses electrochemical potential
  difference created by pumping ions out of the cell to transport
  molecules across a membrane." "en"))
(defclass primary_active_transport
  :subclass active_transport
  :disjoint secondary_active_transport
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "Primary
  active transport, also called direct active transport, directly uses
  energy to transport molecules across a membrane." "en")
  (label "primary active transport" "en"))


(defclass antiport_enabled_secondary_active_transport
  :subclass secondary_active_transport
  :disjoint symport_enabled_secondary_active_transport
  :annotation
  (label "antiport enabled secondary active transport" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "antiport
  enabled secondary active transport is a secondary active transfort
  in which both ion and molecule are transported in opposite
  directions simultaneously." "en"))
(defclass symport_enabled_secondary_active_transport
  :subclass secondary_active_transport
  :disjoint antiport_enabled_secondary_active_transport
  :annotation
  (label "symport enabled secondary active transport" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "symport
  enabled secondary active transport is a secondary active transfort
  in which both ion and molecule are transported in the same direction
  simultaneously." "en"))
