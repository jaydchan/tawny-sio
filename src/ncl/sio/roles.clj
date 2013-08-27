(create-sio-class0 realizable_entity
              "role"
              "A role is a realizable entity that describes
behaviours, rights and obligations of an entity in some particular
circumstance.")
(add-annotation role
                (clojure.core/list
                 (annotation subset (literal "core" :type :RDF_PLAIN_LITERAL))))


(def create-sio-class (partial create-sio-class0 role))
(create-sio-class "social role"
             "a social role is a role that is ascribed to individuals
in a community.")
(create-sio-class "processual role"
             "a processual role is a role that can only be realized in
a process.")
(create-sio-class "abstract role"
             "an abstract role is a role whose basis lies in
spatial/temporal or comparative relations. ")


(create-sio-class0 social_role
              "occupational role"
              "an occupational role is a social role that pertains to
an organizational structure.")


(def create-sio-class (partial create-sio-class0 occupational_role))
(create-sio-class "medical role"
             "a medical role is the role of an individual that is a
participant in the delivery of medical care.")
(create-sio-class "publishing role"
             "a publishing role is the role of an individual that is
involved in the preparation and issue of creative works for
consumption by a wider audience.")
(create-sio-class "administrative role"
             "an administrative role is the role of an individual that
performs administrative tasks for some organization.")
(create-sio-class "academic role"
             "an academic role is a social role that pertains to the
academic institution.")


(def create-sio-class (partial create-sio-class0 medical_role))
(create-sio-class "dentist role"
             "a dentist role is the role of an individual that that
specializes in the diagnosis, prevention, and treatment of diseases
and conditions of the oral cavity.")
(create-sio-class "patient role"
             "a patient role is the role of an individual that is the
recepient of medical care.")
(create-sio-class "doctor role"
             "A doctor role is the role of an individual who practices medicine,
which is concerned with promoting, maintaining or restoring human
health through the study, diagnosis, and treatment of disease, injury,
and other physical and mental impairments.")
(add-annotation  doctor_role
                 (clojure.core/list
                  (annotation hasSynonym (literal "physician" :lang "en"))))
(create-sio-class "nurse role"
             "A nurse role is the role of an individual that is
involved in the protection, promotion, and optimization of health and
abilities, prevention of illness and injury, alleviation of suffering
through the diagnosis and treatment of human response, and advocacy in
the care of individuals, families, communities, and populations.")


(create-sio-class0 administrative_role
              "secretary role"
              "a secretary role is the role of an individual that
performs administrative tasks to support one or more individuals of
the same organization.")


(def create-sio-class (partial create-sio-class0 academic_role))
(create-sio-class "student advisor role"
             "a student advisor role is the role of an individual
employed at an academic organization that is involved in advising
students.")
(create-sio-class "department chair role"
             "a department chain role is the role of an individual
that heads a department at a academic organization.")
(create-sio-class "student role"
             "a student role is the role of an individual that is
enrolled in courses at an academic institution.")
(add-subclass student_role
              (owlsome is_role_of student))
(add-disjoint student_role professor_role)
(create-sio-class "professor role"
             "a professor role is the role of an individual that is
involved in teaching of students (undergraduate and/or graduate) at a
post-secondary academic institution.")
(add-subclass professor_role
              (owlsome is_role_of professor)
              (owlsome is_mutual_role_of student_role))
(add-disjoint professor_role student_role)


(def create-sio-class (partial create-sio-class0 student_advisor_role))
(create-sio-class "graduate student advisor role"
             "a graduate student advisor role is the role of an
individual employed at an academic organization that is involved in
advising graduate students.")
(create-sio-class "undergraduate student advisor role"
             "an undergraduate student advisor role is the role of an
individual employed at an academic organization that is involved in
advising undergraduate students.")


(def create-sio-class (partial create-sio-class0 abstract_role))
(create-sio-class "positional role"
             "a positional role is an abstract role which holds by
comparing position to another object of reference.")
(add-disjoint positional_role comparative_role)
(create-sio-class "comparative role"
             "a comparative role is an abstract role which holds by
comparing some attribute of another object of reference.")
(add-disjoint comparative_role positional_role)


(create-sio-class0 comparative_role
              "variant role"
              "a variant role is a comparative role in which the value
of an attribute differs when compared to another entity")


(create-sio-class0 variant_role
              "sequence variant role"
              "a sequence variant role is a comparative role in which
the composition of characters in a sequence differs when compared to
another entity of similar type.")


(def create-sio-class (partial create-sio-class0 sequence_variant_role))
(create-sio-class "insertion variant role"
             "an insertion variant role is the role of an sequence
that contains a sub-sequence that is considered to be an addition
relative to the frame of reference.")
(create-sio-class "deletion variant role"
             "a deletion variant role is the role of an sequence that
lacks a sub-sequence relative to the frame of reference.")


(create-sio-class0 poison_role
              "toxin role"
              "a toxin role is a toxic role of a chemical substance
that is poisonous and is produced by an organism")


(def create-sio-class (partial create-sio-class0 chemical_substance_role))
(create-sio-class "toxic role"
             "a toxic role is the role of a chemical substance that is
poisonous")
(create-sio-class "host role"
             "the role of an organism in providing resources to
maintain the survival and/or reproduction of another organism.")
(create-sio-class "reagent role"
             "a role of a chemical substance that participates in a
chemical reaction as part of some scientific investigation.")
(add-subclass reagent_role
              (owlsome is_role_of reagent))
(create-sio-class "buffer role"
             "a buffer role is the role of a chemical substance which
maintains a pH at a near constant value.")
(add-subclass buffer_role
              (owlsome is_role_of buffer))


(def create-sio-class (partial create-sio-class0 chemical_entity_role))
(create-sio-class "molecular entity role"
             "a molecular entity role is a chemical entity role held
by a molecule")
(add-subclass molecular_entity_role
              (owlsome is_role_of molecule))
(create-sio-class "chemical substance role"
             "a chemical substance role is a chemical entity role held
by a chemical substance")
(add-subclass chemical_substance_role
              (owlsome is_role_of chemical_substance))


(def create-sio-class (partial create-sio-class0 publishing_role))
(create-sio-class "publisher role"
             "a publisher role is the role of an individual that
prepares and issues creative works.")
(create-sio-class "author role"
             "an author role is the role of an individual that creates
a creative, written work.")


(def create-sio-class (partial create-sio-class0 molecular_entity_role))
(create-sio-class "regulator role"
             "the role of a chemical entity that modifies the rate of
reaction.")
(create-sio-class "reactant role"
             "the role of a chemical entity present at the beginning
of a chemical reaction.")
(create-sio-class "cofactor role"
             "the role of a chemical entity involved in the mechanism
for enzyme-mediated catalysis.")
(create-sio-class "product role"
             "the role of a chemical entity present at the end of a
chemical reaction.")
(add-subclass "product role"
              (owlsome is_role_of product))


(def create-sio-class (partial create-sio-class0 investigational_role))
(create-sio-class "evaluation role"
             "an evaluation role is a processual role held by an
entity during some evaluation")
(create-sio-class "subject role"
             "a subject role is the role of an individual that is the
target of the study.")


(def create-sio-class (partial create-sio-class0 processual_role))
(create-sio-class "investigational role"
             "an investigational role is a role held by participants
involved in an investigation.")
(create-sio-class "chemical entity role"
             "a chemical role is a processual role held by a chemical
entity")
(add-subclass chemical_entity_role
              (owlsome is_role_of chemical_entity))


(def create-sio-class (partial create-sio-class0 evaluation_role))
(create-sio-class "test role"
             "a test role is the role of an individual that is a
participant in the study and is the target of the intervention.")
(create-sio-class "control role"
             "a control role is the role of an individual that is part
of a study, but is not subject to the intervention that is to be
tested.")


(create-sio-class0 substrate_role
              "co-enzyme role"
              "a co-factor role in which the chemical entity is
modified during catalysis and must be regenerated.")
(add-subclass co-enzyme_role
              cofactor_role)


(def create-sio-class (partial create-sio-class0 co-enzyme_role))
(create-sio-class "prosthetic group role"
             "a coenzyme role of a chemical entity that is covalently
bonded to the enzyme.")
(create-sio-class "co-substrate role"
             "a co-enzyme role of a chemical entity that is transiently
associated, and is regenerated in a separate reaction.")


(def create-sio-class (partial create-sio-class0 regulator_role))
(create-sio-class "inhibitor role"
             "the role of a chemical entity that reduces the rate of
reaction.")
(create-sio-class "activator role"
             "the role of a chemical entity that increases the rate of
reaction.")
(create-sio-class "catalytic role"
             "the role of a chemical participant that serves to
increase the rate of reaction by lowering the activiation energy.")


(create-sio-class0 toxic_role
              "poison role"
              "a poison role is the role of a substance that causes
some negative disturbance in an organism.")
(add-subclass poison_role
              (owlsome is_role_of poison))


(def create-sio-class (partial create-sio-class0 reactant_role))
(create-sio-class "substrate role"
             "the role of a chemical entity that is modified in a
chemical reaction")
(create-sio-class "molecular tracer role"
             "a molecular tracer role is a reactant role of a
molecular entity that serves as a marker for the presence, abundance,
or location of a molecular target that it associates with.")
