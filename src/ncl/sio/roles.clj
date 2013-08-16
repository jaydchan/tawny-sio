(defn create-role0
  "Creates a role class with given name"
  [parent name description]
  (let [entity (clojure.string/replace name #" " "_")]
    (tawny.read/intern-entity
     (owlclass entity
               :subclass parent
               :annotation
               (label (literal name :lang "en"))))))


(create-role0 realizable_entity
              "role"
              "A role is a realizable entity that describes
behaviours, rights and obligations of an entity in some particular
circumstance.")
(add-annotation role
                (clojure.core/list
                 (annotation subset (literal "core" :type :RDF_PLAIN_LITERAL))))


(def create-role (partial create-role0 role))
(create-role "social role"
             "a social role is a role that is ascribed to individuals
in a community.")
(create-role "processual role"
             "a processual role is a role that can only be realized in
a process.")
(create-role "abstract role"
             "an abstract role is a role whose basis lies in
spatial/temporal or comparative relations. ")


(create-role0 social_role
              "occupational role"
              "an occupational role is a social role that pertains to
an organizational structure.")


(def create-role (partial create-role0 occupational_role))
(create-role "medical role"
             "a medical role is the role of an individual that is a
participant in the delivery of medical care.")
(create-role "publishing role"
             "a publishing role is the role of an individual that is
involved in the preparation and issue of creative works for
consumption by a wider audience.")
(create-role "administrative role"
             "an administrative role is the role of an individual that
performs administrative tasks for some organization.")
(create-role "academic role"
             "an academic role is a social role that pertains to the
academic institution.")


(def create-role (partial create-role0 medical_role))
(create-role "dentist role"
             "a dentist role is the role of an individual that that
specializes in the diagnosis, prevention, and treatment of diseases
and conditions of the oral cavity.")
(create-role "patient role"
             "a patient role is the role of an individual that is the
recepient of medical care.")
(create-role "doctor role"
             "A doctor role is the role of an individual who practices medicine,
which is concerned with promoting, maintaining or restoring human
health through the study, diagnosis, and treatment of disease, injury,
and other physical and mental impairments.")
(add-annotation  doctor_role
                 (clojure.core/list
                  (annotation hasSynonym (literal "physician" :lang "en"))))
(create-role "nurse role"
             "A nurse role is the role of an individual that is
involved in the protection, promotion, and optimization of health and
abilities, prevention of illness and injury, alleviation of suffering
through the diagnosis and treatment of human response, and advocacy in
the care of individuals, families, communities, and populations.")


(create-role0 administrative_role
              "secretary role"
              "a secretary role is the role of an individual that
performs administrative tasks to support one or more individuals of
the same organization.")


(def create-role (partial create-role0 academic_role))
(create-role "student advisor role"
             "a student advisor role is the role of an individual
employed at an academic organization that is involved in advising
students.")
(create-role "department chair role"
             "a department chain role is the role of an individual
that heads a department at a academic organization.")
(create-role "student role"
             "a student role is the role of an individual that is
enrolled in courses at an academic institution.")
(add-subclass student_role
              (owlsome is_role_of student))
(add-disjoint student_role professor_role)
(create-role "professor role"
             "a professor role is the role of an individual that is
involved in teaching of students (undergraduate and/or graduate) at a
post-secondary academic institution.")
(add-subclass professor_role
              (owlsome is_role_of professor)
              (owlsome is_mutual_role_of student_role))
(add-disjoint professor_role student_role)


(def create-role (partial create-role0 student_advisor_role))
(create-role "graduate student advisor role"
             "a graduate student advisor role is the role of an
individual employed at an academic organization that is involved in
advising graduate students.")
(create-role "undergraduate student advisor role"
             "an undergraduate student advisor role is the role of an
individual employed at an academic organization that is involved in
advising undergraduate students.")


(def create-role (partial create-role0 abstract_role))
(create-role "positional role"
             "a positional role is an abstract role which holds by
comparing position to another object of reference.")
(add-disjoint positional_role comparative_role)
(create-role "comparative role"
             "a comparative role is an abstract role which holds by
comparing some attribute of another object of reference.")
(add-disjoint comparative_role positional_role)


(create-role0 comparative_role
              "variant role"
              "a variant role is a comparative role in which the value
of an attribute differs when compared to another entity")


(create-role0 variant_role
              "sequence variant role"
              "a sequence variant role is a comparative role in which
the composition of characters in a sequence differs when compared to
another entity of similar type.")


(def create-role (partial create-role0 sequence_variant_role))
(create-role "insertion variant role"
             "an insertion variant role is the role of an sequence
that contains a sub-sequence that is considered to be an addition
relative to the frame of reference.")
(create-role "deletion variant role"
             "a deletion variant role is the role of an sequence that
lacks a sub-sequence relative to the frame of reference.")


(create-role0 poison_role
              "toxin role"
              "a toxin role is a toxic role of a chemical substance
that is poisonous and is produced by an organism")


(def create-role (partial create-role0 chemical_substance_role))
(create-role "toxic role"
             "a toxic role is the role of a chemical substance that is
poisonous")
(create-role "host role"
             "the role of an organism in providing resources to
maintain the survival and/or reproduction of another organism.")
(create-role "reagent role"
             "a role of a chemical substance that participates in a
chemical reaction as part of some scientific investigation.")
(add-subclass reagent_role
              (owlsome is_role_of reagent))
(create-role "buffer role"
             "a buffer role is the role of a chemical substance which
maintains a pH at a near constant value.")
(add-subclass buffer_role
              (owlsome is_role_of buffer))


(def create-role (partial create-role0 chemical_entity_role))
(create-role "molecular entity role"
             "a molecular entity role is a chemical entity role held
by a molecule")
(add-subclass molecular_entity_role
              (owlsome is_role_of molecule))
(create-role "chemical substance role"
             "a chemical substance role is a chemical entity role held
by a chemical substance")
(add-subclass chemical_substance_role
              (owlsome is_role_of chemical_substance))


(def create-role (partial create-role0 publishing_role))
(create-role "publisher role"
             "a publisher role is the role of an individual that
prepares and issues creative works.")
(create-role "author role"
             "an author role is the role of an individual that creates
a creative, written work.")


(def create-role (partial create-role0 molecular_entity_role))
(create-role "regulator role"
             "the role of a chemical entity that modifies the rate of
reaction.")
(create-role "reactant role"
             "the role of a chemical entity present at the beginning
of a chemical reaction.")
(create-role "cofactor role"
             "the role of a chemical entity involved in the mechanism
for enzyme-mediated catalysis.")
(create-role "product role"
             "the role of a chemical entity present at the end of a
chemical reaction.")
(add-subclass "product role"
              (owlsome is_role_of product))


(def create-role (partial create-role0 investigational_role))
(create-role "evaluation role"
             "an evaluation role is a processual role held by an
entity during some evaluation")
(create-role "subject role"
             "a subject role is the role of an individual that is the
target of the study.")


(def create-role (partial create-role0 processual_role))
(create-role "investigational role"
             "an investigational role is a role held by participants
involved in an investigation.")
(create-role "chemical entity role"
             "a chemical role is a processual role held by a chemical
entity")
(add-subclass chemical_entity_role
              (owlsome is_role_of chemical_entity))


(def create-role (partial create-role0 evaluation_role))
(create-role "test role"
             "a test role is the role of an individual that is a
participant in the study and is the target of the intervention.")
(create-role "control role"
             "a control role is the role of an individual that is part
of a study, but is not subject to the intervention that is to be
tested.")


(create-role0 substrate_role
              "co-enzyme role"
              "a co-factor role in which the chemical entity is
modified during catalysis and must be regenerated.")
(add-subclass co-enzyme_role
              cofactor_role)


(def create-role (partial create-role0 co-enzyme_role))
(create-role "prosthetic group role"
             "a coenzyme role of a chemical entity that is covalently
bonded to the enzyme.")
(create-role "co-substrate role"
             "a co-enzyme role of a chemical entity that is transiently
associated, and is regenerated in a separate reaction.")


(def create-role (partial create-role0 regulator_role))
(create-role "inhibitor role"
             "the role of a chemical entity that reduces the rate of
reaction.")
(create-role "activator role"
             "the role of a chemical entity that increases the rate of
reaction.")
(create-role "catalytic role"
             "the role of a chemical participant that serves to
increase the rate of reaction by lowering the activiation energy.")


(create-role0 toxic_role
              "poison role"
              "a poison role is the role of a substance that causes
some negative disturbance in an organism.")
(add-subclass poison_role
              (owlsome is_role_of poison))


(def create-role (partial create-role0 reactant_role))
(create-role "substrate role"
             "the role of a chemical entity that is modified in a
chemical reaction")
(create-role "molecular tracer role"
             "a molecular tracer role is a reactant role of a
molecular entity that serves as a marker for the presence, abundance,
or location of a molecular target that it associates with.")
