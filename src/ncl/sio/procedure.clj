(defclass procedure
  :subclass
  process
  (owlsome is_manifestation_of action_specification)
  (owlonly realizes (owlor capability objective))
  :annotation
  (label "procedure" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "a
  procedure is a process that attempts to achieve one or more
  objectives by following an established set of actions." "en"))


(defclass information_processing
  :subclass procedure
  :annotation
  (label "information processing" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "information
  processing is a process that involves the generation or use of
  information." "en"))
(defclass investigation
  :subclass procedure (owlsome has_product object) (owlsome realizes objective)
  :annotation
  (annotation hasSynonym "study")
  (label "investigation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "investigation
  is the process of carrying out a plan or procedure so as to discover
  facts or information about the object of study." "en"))
(defclass medical_procedure
  :subclass procedure
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "a medical
  procedure is a procedure to identify, examine, alleviate or
  eliminate an undesirable biological disease or disorder." "en")
  (label "medical procedure" "en"))
(defclass assay
  :subclass
  procedure
  (owlsome has_output
           (owland
            (owlor measurement_value existence_quality)
            (owlsome is_attribute_of entity)))
  (owlsome has_input object)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "An assay
  is an investigative (analytic) procedure in laboratory medicine,
  pharmacology, environmental biology, and molecular biology for
  qualitatively assessing or quantitatively measuring the presence or
  amount or the functional activity of a target entity (the analyte)
  which can be a drug or biochemical substance or a cell in an
  organism or organic sample." "en")
  (label "assay" "en"))


(defclass data_collection
  :subclass information_processing (owlsome has_product data_item)
  :annotation
  (label "data collection" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "data
  collection is the process of acquiring information." "en"))
(defclass data_analysis
  :subclass
  information_processing
  (owland
   (owlsome has_output proposition)
   (owlsome has_input data_item))
  :annotation
  (label "data analysis" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "data
  analysis is a process of inspecting, cleaning, transforming, and
  modeling data with the goal of highlighting useful information,
  suggesting conclusions, and supporting decision making." "en"))
(defclass data_transformation
  :subclass
  information_processing
  (owlsome has_output data_item)
  (owlsome has_input data_item)
  :annotation
  (label "data transformation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "data
  transformation is the process of applying an algorithmic procedure
  to some input data and producing some output data." "en"))
(defclass literature_curation
  :subclass
  information_processing
  (owlsome has_output phrase)
  (owlsome has_input document)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "literature
  curation is the process of an agent selecting and extracting terms
  and phrases from a document." "en")
  (label "literature curation" "en"))
(defclass sofware_execution
  :subclass information_processing
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "software
  execution is the process of executing software on a computing
  device." "en")
  (label "sofware execution" "en"))


(defclass parameterized_data_transformation
  :subclass data_transformation (owlsome has_parameter parameter)
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "a
  parameterized data transformation is a data transformation whose
  behaviour may be modified by one or more parameters." "en")
  (label "parameterized data transformation" "en"))


(defclass web_service_invocation
  :subclass sofware_execution
  :annotation
  (label "web service invocation" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "a web
  service invocation involves the execution of a web service."))


(defclass SADI_web_service_invocation
  :subclass web_service_invocation (owlsome has_agent SADI_semantic_web_service)
  :annotation
  (annotation subset "sadi" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "a SADI web
  service invocation is the excution of a SADI web service.")
  (label "SADI web service invocation" "en"))


(defclass study
  :subclass
  investigation
  (owlsome realizes objective)
  (owlsome is_manifestation_of study_design)
  :annotation
  (label "study" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "a study is
  a process that realizes the steps of a study design." "en"))
(defclass experiment
  :subclass investigation
  :annotation
  (annotation hasSynonym "study")
  (label "experiment" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "An
  experiment is an investigation that has the goal of verifying,
  falsifying, or establishing the validity of a hypothesis. " "en"))


(defclass mass_spectrometry_experiment
  :subclass experiment
  :annotation
  (label "mass spectrometry experiment" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "a mass
  spectrometry experiment is an experiment that involves the use of a
  mass spectrometer. " "en"))
(defclass microarray_experiment
  :subclass experiment
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "a
  microarray experiment is an experiment that involves a microarray
  device to measure the expression of one or more genes." "en")
  (label "microarray experiment" "en"))
(defclass intervention_study
  :subclass experiment
  :annotation
  (label "intervention study" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "an
  intervention study has the objective of improving the condition of
  an individual or a group of individuals, and demonstrates the
  magnitude of that capability by comparing it to a control
  group." "en"))
(defclass observational_study
  :subclass
  experiment
  (owlsome has_participant hypothesis)
  (owlsome specifies
           (owland truth_value
                   (owlsome is_quality_of hypothesis)))
  :annotation
  (label "observational study" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "observational
  study draws inferences about the possible effect of a treatment on
  subjects, where the assignment of subjects into a treated group
  versus a control group is outside the control of the
  investigator" "en"))


(defclass clinical_trial
  :subclass intervention_study (owlsome is_specified_by experimental_protocol)
  :annotation
  (label "clinical trial" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "a clinical
  trial is an intervention trial to determine the safety and efficacy
  of medical interventions (e.g., drugs, diagnostics, devices, therapy
  protocols). " "en"))


(defclass controlled_observational_cohort_study
  :subclass observational_study
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "In a
  controlled observational cohort study, two groups of subjects are
  selected from two populations that are thought to differ in only one
  characteristic. The groups of subjects are studied for a specific
  period and contrasted at the end of the study period." "en")
  (label "controlled observational cohort study" "en"))


(defclass medical_diagnosis
  :subclass
  medical_procedure
  (owlsome has_output diagnostic_opinion)
  (owlsome has_input
           (owland biological_entity
                   (owlsome has_quality disordered)))
  :annotation
  (label "medical diagnosis" "en")
  (annotation (iri "http://purl.org/dc/terms/description") "A medical
  diagnosis (often simply termed diagnosis) refers to the process of
  attempting to determine or identify a possible disease or
  disorder." "en"))
(defclass diagnostic_test
  :subclass medical_procedure
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A
  diagnostic test is a procedure performed to confirm, or determine
  the presence of disease in an individual suspected of having the
  disease, usually following the report of symptoms, or based on the
  results of other medical tests." "en")
  (label "diagnostic test" "en"))
(defclass medical_screening
  :subclass medical_procedure
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A medical
  screening is a medical test or series used to detect or predict the
  presence of disease in individuals at risk for disease within a
  defined group, such as a population, family, or workforce" "en")
  (label "medical screening" "en"))
(defclass differential_diagnosis
  :subclass medical_procedure
  :annotation
  (annotation (iri "http://purl.org/dc/terms/description") "A
  differential diagnosis (sometimes abbreviated DDx, ddx, DD, D/Dx, or
  ΔΔ) is a systematic diagnostic method used to identify the presence
  of an entity where multiple alternatives are possible (and the
  process may be termed differential diagnostic procedure), and may
  also refer to any of the included candidate alternatives (which may
  also be termed candidate condition)." "en")
  (label "differential diagnosis" "en"))