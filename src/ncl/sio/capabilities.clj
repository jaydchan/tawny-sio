(create-sio-class0 realizable_entity
                    "capability"
                    "A capability is a realizable entity whose basis
  lies in one or more parts or qualities and reflects possility of an
  entity to act in a specified way under certain conditions or in
  response to a certain stimulus (trigger).")
(add-subclass capability
              (owlsome is_attribute_of
                       (owlsome is_agent_in
                                (owland process
                                        (hasself realizes)))))
(add-subclass capability
              (owlsome has_basis quality))
(add-subclass capability
              (owlonly has_trigger process))
(add-annotation capability
                (clojure.core/list
                 (annotation subset (literal "core" :type :RDF_PLAIN_LITERAL))
                 (annotation subset
                             (literal "capability+" :type :RDF_PLAIN_LITERAL))))


(def create-sio-class (partial create-sio-class0 capability))
(create-sio-class "to breathe"
                   "to breathe is the capability to inhale and exhale
air into the body during respiration.")
(create-sio-class "to interact with"
                   "to interact with is a capabililty that involves
another object.")
(add-subclass to_interact_with
              (owlsome in_relation_to entity))
(add-subclass to_interact_with
              (owlsome is_mutual_disposition_of to_be_interacted_with))
(create-sio-class "to be interacted with"
                   "to be interacted with is the capability of an
object to be target of a physical interaction.")
(add-subclass to_be_interacted_with
              (owlsome in_relation_to entity))
(add-subclass to_be_interacted_with
              (owlsome is_mutual_disposition_of to_interact_with))
(create-sio-class "dysfunction"
                   "dysfunction is a capability to act in a manner
that is abnormal or opposite to the object's typical function.")
(add-disjoint dysfunction function)
(create-sio-class "disposition"
                   "A disposition is the tendency of a capability to
be exhibited under certain conditions or in response to a certain
stimulus (trigger)")
(add-subclass disposition
              (owlsome has_attribute probability_measure))
(add-annotation disposition
                (clojure.core/list
                 (annotation example (literal "solubility (to dissolve
when put in fluid); fragility (disposition to break when
dropped)" :lang "en"))
                 (annotation subset (literal "core" :type :RDF_PLAIN_LITERAL))))
(create-sio-class "function"
                   "A function is a capability that satisfies some
agentive objective, or (evolutionary) optimization.")
(add-disjoint function dysfunction)
(add-annotation dysfunction
                (clojure.core/list
                 (annotation subset (literal "core" :type :RDF_PLAIN_LITERAL))))


(create-sio-class0 disposition
                    "mutual disposition"
                    "a mutual disposition is a disposition that
simulataneously invokes another disposition when realized.")


(def create-sio-class (partial create-sio-class0 to_interact_with))
(create-sio-class "to actively interact with"
                   "to actively interact with is the capability to
interact with another entity in a way that requires physical
contact.")
(create-sio-class "to passively interact with"
                   "to passively interact with is the capability to
interact with another entity in a way that does not require physical
contact.")
(create-sio-class "to translocate"
                   "to translocate is the capability to displace
oneself from one location to another.")
(add-subclass to_translocate
              (owlsome is_mutual_disposition_of to_be_translocated))
(add-disjoint to_translocate to_be_translocated)
(add-annotation to_translocate
                (clojure.core/list
                 (annotation hasSynonym (literal "to move" :lang "en"))))


(def create-sio-class (partial create-sio-class0 to_actively_interact_with))
(create-sio-class "to associate"
                   "To associate is the capability to physically
interact with another object.")
(create-sio-class "to investigate"
                   "to investigate is the capability to uncover
facts.")
(create-sio-class "to consume"
                   "to consume is the capability to internalize a
material entity.")
(create-sio-class "to provide"
                   "to provide is the capability to make available
some object to another that requires it.")
(create-sio-class "to transport"
                   "to transport is the capability to displace a
material from one location to another.")
(create-sio-class "to assemble"
                   "to assemble is the capability to combine entities
together into a larger object that persists in time.")
(create-sio-class "to produce"
                   "to produce is the capability to create new objects")
(create-sio-class "to modify"
                   "to modify is the capability to change some entity.")
(create-sio-class "to serve as"
                   "to serve as is the capability to act in a manner
corresponding to some role.")


(def create-sio-class (partial create-sio-class0 to_modify))
(create-sio-class "to regulate"
                   "to regulate is to control or maintain the rate or
speed of an object or process.")
(add-annotation to_regulate
                 (clojure.core/list
                  (annotation hasSynonym (literal "to modulate" :lang "en"))))
(create-sio-class "to change spatially"
                   "to change spatially is the capability to affect
the physical movement of some entity.")
(create-sio-class "to change energetically"
                   "to change energetically is the capability to
change the energetic aspects of an object.")
(create-sio-class "to change appearance"
                   "to change appearance is the capability to change
the visual attributes of an object.")
(create-sio-class "to change materially"
                   "to change appearance is the capability to change
the material composition of an object.")
(create-sio-class "to inject"
                   "To inject is the capability to administer a
substance into some object through its external barrier.")
(create-sio-class "to separate"
                   "To separate is the capability to i) distinguish
some entities based on some attribute(s) and ii) subsequently
physically displace them.")
(create-sio-class "to modify conformation of"
                   "to modify conformation of is to affect the spatial
arrangement of an entity.")
(create-sio-class "to disassociate"
                   "to disassociate is to cease or break association
with some thing.")


(def create-sio-class (partial create-sio-class0 to_modify_electronically))
(create-sio-class "to modify oxidation state of"
                   "to modify the oxidation state of is to change the
number of electrons of a molecule, atom or ion.")
(create-sio-class "to ionize"
                   "To ionize is the capability to physically convert
an atom or molecule into an ion by adding or removing charged
particles such as electrons or other ions.")


(def create-sio-class (partial create-sio-class0 to_modify_conformation_of))
(create-sio-class "to conformationally inhibit"
                   "to conformationally inhibit is to modify the
conformation of an entity in such a way that it functionally is
reduced or inhibited.")
(create-sio-class "to conformationally activate"
                   "to conformationally activate is to modify the
conformation of an entity in such a way that it becomes activated or
functional.")


(def create-sio-class (partial create-sio-class0 to_modify_oxidation_state_of))
(create-sio-class "to oxidize"
                   "to oxidize is the capability to remove an electron
or an increase in oxidation state of a chemical entity.")
(create-sio-class "to reduce"
                   "to reduce is the capability to add an electron or
an decrease in oxidation state of a chemical entity.")


(def create-sio-class (partial create-sio-class0 to_separate))
(create-sio-class "to filter"
                   "To filter is the capability to retain certain
entities based on selected attribute(s) while allowing other entities
to pass through.")
(create-sio-class "to extract"
                   "To extract is the capability to remove certain
entities based on selected attribute(s) while allowing other entities
to remain.")


(def create-sio-class (partial create-sio-class0 to_change_energetically))
(create-sio-class "to modify electronically"
                   "to modify electronically is the capability to
change the electronic properties of an object.")
(create-sio-class "to change the activation energy"
                   "to change the activation energy is to change the
amount of energy required to form or break a chemical bond.")
(create-sio-class "to supply energy"
                   "To supply energy is the capability to transfer
energy from a source to a sink.")
(create-sio-class "to reduce energy"
                   "To reduce energy is the capability to remove
energy from a source.")


(def create-sio-class
  (partial create-sio-class0 to_change_the_activation_energy))
(create-sio-class "to increase the activation energy"
                   "to increase the activation energy is to require a
larger amount of energy in order to form or break a chemical bond.")
(add-annotation to_increase_the_activation_energy
                (clojure.core/list
                 (annotation hasSynonym
                             (literal "to inhibit" :type :RDF_PLAIN_LITERAL))))
(add-disjoint to_increase_the_activation_energy to_reduce_the_activation_energy)
(create-sio-class "to reduce the activation energy"
                   "to reduce the activation energy is to require a
smaller amount of energy in order to form or break a chemical bond.")
;; Need both?
(add-disjoint to_reduce_the_activation_energy to_increase_the_activation_energy)
(add-annotation to_reduce_the_activation_energy
                (clojure.core/list
                 (annotation hasSynonym
                             (literal "to activate" :type :RDF_PLAIN_LITERAL))))


(def create-sio-class (partial create-sio-class0 to_supply_energy))
(create-sio-class "to heat"
                   "To heat is a capability to increase the internal
kinetic energy of a material.")
(create-sio-class "to excite"
                   "To excite is the capability to supply energy to a
materila by bombarding it with energetic particles (e.g., photons).")
(create-sio-class "to supply electricity"
                   "To supply electricity is the capability to
transfer electricity from a source to a sink.")


(def create-sio-class (partial create-sio-class0 to_reduce_energy))
(create-sio-class "to emit"
                   "To emit is the capability to release some physical
entity (light, pollution, etc).")
(create-sio-class "to cool"
                   "To cool is the capability to decrease the internal
kinetic energy of a material.")
(add-annotation to_cool
                (clojure.core/list
                 (annotation
                  (iri "http://www.w3.org/2000/01/rdf-schema#seeAlso")
                  (literal "http://purl.obolibrary.org/obo/OBI_0000387"
                           :type :XSD_ANY_URI))))


(create-sio-class0 to_cool
                    "to freeze"
                    "To freeze is the capability to decrease the
internal kinetic energy of a material such that it changes state from
a gas or liquid to a solid.")
(add-annotation to_freeze
                (clojure.core/list
                 (annotation
                  (iri "http://www.w3.org/2000/01/rdf-schema#seeAlso")
                  (literal "http://purl.obolibrary.org/obo/OBI_0000375"
                           :type :XSD_ANY_URI))))


(def create-sio-class (partial create-sio-class0 to_ionize))
(create-sio-class "to positively charge"
                   "to positively charge is the capability to remove
an electron or add a positively charged ion to a chemical entity.")
(create-sio-class "to negatively charge"
                   "to negatively charge is the capability to add an
electron or negatively charged ion to a chemical entity.")


(create-sio-class0 to_change_spatially
                    "to contain"
                    "To contain is the capability to bound or
constrain a physical entity in some site.")


(create-sio-class0 to_contain
                    "to immobilize"
                    "To immobilize is the capability to contain an
entity in such a way that it may not move in space.")


(def create-sio-class (partial create-sio-class0 to_change_appearance))
(create-sio-class "to distort"
                   "to distort is the capability to change the
appearance of an entity by some transformation.")
(create-sio-class "to demagnify"
                   "To demagnify is the capability to decrease the
appearance of the size of an object.")
(create-sio-class "to magnify"
                   "To magnify is the capability to increase the
appearance of the size of an object.")


(create-sio-class0 to_heat
                    "to boil"
                    "To boil is the capability to increase the
internal kinetic energy of a material such that it changes state from
a solid or liquid to a gas.")


(def create-sio-class (partial create-sio-class0 to_investigate))
(create-sio-class "to measure"
                   "To measure is the capability to obtain information
about some entity by examining its attributes in relation to some
reference metric.")
(create-sio-class "to identify"
                   "to identify is the capability to determine the
identity of somethig.") ;; spelling mistake 'somethig'


(def create-sio-class (partial create-sio-class0 to_passively_interact_with))
(create-sio-class "to be a member of"
                   "to be a member of is the capability to be
considered a part of a collection.")
(create-sio-class "to describe"
                   "to describe is the capabilty to communicate facts
about an entity.")
(create-sio-class "to observe"
                   "to observe is the capability to watch
attentively.")
(create-sio-class "to record"
                   "To record is the capability to detect and
transcribe information in a specified format on some physical
medium.")
(add-annotation to_record
                (clojure.core/list
                 (annotation
                  (iri "http://www.w3.org/2000/01/rdf-schema#seeAlso")
                  (literal "http://purl.obolibrary.org/obo/OBI_0000368"
                           :type :XSD_ANY_URI))))


(def create-sio-class (partial create-sio-class0 to_be_modified))
(create-sio-class "to be conformationally changed"
                   "to be conformationally changed is the capability
to be modified in such a way that the object's conformation is
changed.")
(create-sio-class "to be combined"
                   "to be combined is the capability to be modified in
a way that the object is merged with another object to form a new
object or substance.")
(create-sio-class "to be electronically modified"
                   "to be electronically modified is the capability of
a chemical entity to have electrons added or removed")
(create-sio-class "to be cleaved"
                   "to be cleaved is the capability to be modified in
a way that splits one part of the object from the other.")
(create-sio-class "to be covalently modified"
                   "to be covalently modified is the capability of a
chemical entity to have bonds added or removed")


(def create-sio-class
  (partial create-sio-class0 to_be_conformationally_changed))
(create-sio-class "to be activated"
                   "to be activated is the capability to be modified
in such a way that the conformational change leads to an increase in
another capability.")
(create-sio-class "to be inhibited"
                   "to be inhibited is the capability to be modified
in such a way that the conformational change leads to an decrease in
another capability.")


(def create-sio-class
  (partial create-sio-class0 to_be_electronically_modified))
(create-sio-class "to gain an electron"
                   "to gain an electron is the capability of a
chemical entity to receive an electron.")
(create-sio-class "to lose an electron"
                   "to lose an electron is the capability of a
chemical entity to lose an electron.")


(def create-sio-class
  (partial create-sio-class0 to_be_actively_interacted_with))
(create-sio-class "to be transported"
                   "to be transported is the disposition to undergo
motion.")
(create-sio-class "to be modified"
                   "to be modified is the capability to be actively
interacted with in such a way that it leads to a physical
reconfiguration.")
(create-sio-class "to luminesce"
                   "to luminesce is to emit light through cold body
radiation")


(def create-sio-class (partial create-sio-class0 to_change_materially))
(create-sio-class "to cause disease"
                   "to cause disease is the capability to materially
change a biological object in that it functions abnormally.")
(create-sio-class "to disassemble"
                   "to disassemble is to physically separate the parts
of an object.")
(create-sio-class "to covalently modify"
                   "to covalently modify is to materially change a
molecule by adding or removing covalent bonds between atoms.")
(create-sio-class "to combine"
                   "to combine is the capability to modify a set of
objects in a way that the object is merged with another object to form
a new object or substance.")


(create-sio-class0 to_disassemble
                   "to cleave"
                   "to cleave is to split or sever an object along a
natural line or grain.")


(create-sio-class0 to_associate
                    "to bind to"
                    "to bind to is the capability to physically
interact with another object through a set of non-covalent
interactions.")


(create-sio-class0 to_consume
                    "to ingest"
                    "to ingest is the capability to take into the body
by the mouth for digestion or absorption")


(create-sio-class0  mutual_disposition
                     "to interact and to be interacted with"
                     "to interact and to be interacted with is a
mutual disposition of interacting objects.")
(add-subclass mutual_disposition
              (owland (owlsome has_proper_part to_interact_with)
                      (owlsome has_proper_part to_be_interacted_with)))


(def create-sio-class (partial create-sio-class0 to_change_materially))
(create-sio-class "to lose a covalent bond"
                   "to lose a covalent bond is the capability of a
chemical entity to have bonds removed.")
(create-sio-class "to gain a covalent bond"
                   "to gain a covalent bond is the capability of a
chemical entity to have bonds added.")


(def create-sio-class (partial create-sio-class0 to_examine))
(create-sio-class "to test a hypothesis"
                   "to test a hypothesis is the capability to evaluate
the truth value of a proposition based on gathered evidence.")
(create-sio-class "to compare"
                   "to compare is the capability to examine in order
to note the similarities or differences among a set of objects.")


(create-sio-class0 to_be_examined
                    "to be compared"
                    "to be compared is the capability of an object to
be examined in order to note the similarities or differences among a
set of objects.")


(def create-sio-class (partial create-sio-class0 to_be_observed))
(create-sio-class "to be examined"
                   "to be examined is the capability of an object to
be observed in a detailed manner.")
(create-sio-class "to be recorded"
                   "to be recorded is the capability of an object to
be observed in such a way that information about it can be transcribed
in a specified format on some physical medium.")
(add-subclass to_be_recorded
              (owlsome is_mutual_disposition_of to_record))


(create-sio-class0 to_observe
                    "to examine"
                    "to examine is the capability to make detailed
observation. ")


(def create-sio-class
  (partial create-sio-class0 to_regulate_the_rate_of_formation))
(create-sio-class "to decrease the rate of formation"
                   "to decrease the rate of formation is to regulate
the rate of formation in a manner that decreases this rate relative to
a reference process.")
(create-sio-class "to increase the rate of formation"
                   "to increase the rate of formation is to regulate
the rate of formation in a manner that increases this rate relative to
a reference process.")


(create-sio-class0 to_luminesce
                    "to fluoresce"
                    "to fluoresce is to emit light as a result of
absorbing light or other electromagnetic radiation.")


(def create-sio-class (partial create-sio-class0 to_covalently_modify))
(create-sio-class "to remove a covalent bond"
                   "to remove a covalent bond is the capability to
covalently modify a chemical entity by removing a covalent bond.")
(create-sio-class "to add a covalent bond"
                   "to add a covalent bond is the capability to
covalently modify a chemical entity by adding a covalent bond.")


(def create-sio-class
  (partial create-sio-class0 to_serve_as_a_template_for_molecular_synthesis))
(create-sio-class "to serve as a template for protein synthesis"
                   "to serve as a template for protein synthesis is
the capability of a chemical entity to provide the necessary
information or scaffold by which a protein may be produced.")
(create-sio-class "to serve as a template for DNA synthesis"
                   "to serve as a template for DNA synthesis is the
capability of a chemical entity to provide the necessary information
or scaffold by which a DNA molecule may be produced.")
(create-sio-class "to serve as a template for RNA synthesis"
                   "to serve as a template for RNA synthesis is the
capability of a chemical entity to provide the necessary information
or scaffold by which an RNA molecule may be produced.")


(def create-sio-class (partial create-sio-class0 to_serve_as))
(create-sio-class "to serve as a template for molecular synthesis"
                   "to serve as a template for molecular synthesis is
the capability of a chemical entity to provide the necessary
information or scaffold by which another molecule may be produced.")
(create-sio-class "to serve as a primer for DNA synthesis"
                   "to serve as a primer for DNA synthesis is the
capability of a short nucleic acid to bind to the 5' end of single
strand of DNA template and help initiate DNA replication.")
(create-sio-class "to serve as a host"
                   "to serve as host is the capability to act in a
manner that provides hospitality, serves to harbour an organism in or
on itself.")
(add-subclass to_serve_as_a_host
              (owlsome is_capability_of host))


(create-sio-class0 to_inject
                    "to infect"
                    "To infect is the capability to administer a
disease-causing organism into some object.")


(create-sio-class0 to_regulate
                    "to regulate the rate of formation"
                    "to regulate the rate of formation is to modify
the rate at which an object is formed.")


(create-sio-class0 to_be_combined
                    "to be a part of"
                    "to be a part of is the capability to be assembled
into a larger structure that persists in time.")


(create-sio-class0 to_be_passively_interacted_with
                    "to be observed"
                    "to be observed is the capability of an object to
be perceived.")
(add-subclass to_be_passively_interacted_with
              (owlsome is_mutual_disposition_of to_observe))


(def create-sio-class (partial create-sio-class0 to_be_interacted_with))
(create-sio-class "to be translocated"
                   "to be translocated is the capability to be
physically displaced from one location to another")
(add-subclass to_be_translocated
             (owlsome is_mutual_disposition_of to_translocate))
(add-disjoint to_be_translocated to_translocate)
(add-annotation to_be_translocated
                 (clojure.core/list
                  (annotation hasSynonym (literal "to be moved" :lang "en"))))
(create-sio-class "to be passively interacted with"
                   "to be passively interacted with is the capability
of an object to be observed.")
(add-subclass to_be_passively_interacted_with
              (owlsome is_mutually_related_to to_passively_interact_with))
(add-disjoint to_be_passively_interacted_with to_be_actively_interacted_with)
(create-sio-class "to be actively interacted with"
                   "to be actively interacted with is the capability
to be manipulated by some device or agent.")
(add-subclass to_be_actively_interacted_with
              (owlsome is_mutually_related_to to_actively_interact_with))
(add-disjoint to_be_actively_interacted_with to_be_passively_interacted_with)


(create-sio-class0 to_describe
                    "to characterize"
                    "to characterize is the capability to classify the
attributes or features of an entity against a reference
classification.")