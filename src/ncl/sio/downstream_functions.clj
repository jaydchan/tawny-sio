(ns ncl.sio.downstream_functions
  (:use tawny.owl)
  (:require [ncl.sio.mysio :as m]
            [tawny.read]))

(defontology downstream_functions
  :iri "http://ncl.ac.uk/sio/downstream_functions"
  :prefix "down:")

;; AUXILIARY FUNCTIONS
(defn owlclass
  [name & rest]
  (let [safe (tawny.read/stop-characters-transform name)]
    (apply owl-class safe rest)))

;; BIOCHEMICAL PATHWAY
(defclass hexokinase_reaction)
(defclass phosphoglucose_isomerase_reaction)
(defclass third_reaction)

(defn biochemical-pathway0
  [reactions]
  (owl-and (first reactions)
           (owl-some m/precedes (rest reactions))))

(defn biochemical-pathway
  [name reactions]
  (owlclass name
             :equivalent
             (owl-and m/pathway
                      (owl-some m/has_proper_part
                                (biochemical-pathway0 reactions))
                      (owl-some m/has_proper_part reactions))))

(biochemical-pathway "glycosis pathway"
                     [hexokinase_reaction
                      phosphoglucose_isomerase_reaction
                      third_reaction])

(defclass target_role) ;; MISSING

(defn some-target
  [class]
  (owl-some m/realizes (owl-and target_role
                                (owl-some m/is_role_of class))))

(defn some-catalytic
  [class]
  (owl-some m/realizes (owl-and m/catalytic_role
                                (owl-some m/is_role_of class))))

(defn some-product
  [class]
  (owl-some m/realizes (owl-and m/product_role
                                (owl-some m/is_role_of class))))

(defn biochemical-reaction0
  [function values]
  (for [v values]
    (function v)))

(defn biochemical-reaction
  [name target catalytic product]
  (owlclass name
            :equivalent
            (owl-and m/biochemical_reaction
                     (biochemical-reaction0 (first target) (second target))
                     (biochemical-reaction0 (first catalytic) (second catalytic))
                     (biochemical-reaction0 (first product) (second product)))))

(defclass glucose)
(defclass ATP)
(defclass hexokinase)
(defclass glucose-6-phosphate)
(defclass ADP)

(biochemical-reaction "hexokinase_reaction"
                      [some-target [glucose ATP]]
                      [some-catalytic [hexokinase]]
                      [some-product [glucose-6-phosphate ADP]])

;; ENZYME MECHANISM
(defclass complex_formation) ;; MISSING
(defclass to_be_part_of) ;; MISSING

(defn enzyme-part-of
  [name disposition relation]
  (owlclass name
            :equivalent
            (owl-and complex_formation
                     (for [d disposition]
                       (owl-some m/realizes
                                 (owl-and to_be_part_of
                                          (owl-some m/is_disposition_of d)
                                          (owl-some m/in_relation_to relation)))))))

(defclass ATP-enzyme_complex)
(defclass ATP-substrate-enzyme_complex)

(enzyme-part-of "ATP-enzyme complex formation"
                [m/enzyme ATP]
                ATP-enzyme_complex)
(enzyme-part-of "ATP-substrate-enzyme complex formation"
                [ATP-enzyme_complex ATP]
                ATP-substrate-enzyme_complex)

(defn enzyme-role
  [name substrate product]
  (owlclass name
            :equivalent
            (owl-and m/biochemical_reaction
                     (owl-some m/realizes
                               (owl-and m/substrate_role
                                        (owl-some m/is_role_of substrate)))
                     (owl-some m/realizes
                               (owl-and m/product_role
                                        (owl-some m/is_role_of product))))))

;; NOTE ATP-substrate_enzyme_complex -> ATP-substrate-enzyme_complex
(defclass ADP-substrate-phosphorylated-enzyme_complex)
(defclass substrate-phosphorylated-enzyme_complex)
(defclass phosphorylated-substrate-enzyme_complex)

(enzyme-role "substrate-enzyme phosphorylation by ATP"
             ATP-substrate-enzyme_complex
             ADP-substrate-phosphorylated-enzyme_complex)

(enzyme-role "substrate-phosphorylation by phosphorylated enzyme"
             substrate-phosphorylated-enzyme_complex
             phosphorylated-substrate-enzyme_complex)

(defclass complex_dissociation) ;; MISSING
(defclass to_dissociate) ;; MISSING
(defoproperty is_disposition_of) ;; MISSING

(defn enzyme-dissociate
  [name dissociate relation]
  (owlclass name
            :equivalent
            (owl-and complex_dissociation
                     (for [d dissociate]
                       (owl-some m/realizes
                                 (owl-and to_dissociate
                                          (owl-some is_disposition_of d)
                                          (owl-some m/in_relation_to relation)))))))

(defclass phosphorylated-enzyme-substrate_complex)
(defclass phosphorylated-substrate)

(enzyme-dissociate "ADP dissociation from phosphorylated-enzyme substrate complex"
                   [phosphorylated-enzyme-substrate_complex ADP]
                   ADP-substrate-phosphorylated-enzyme_complex)

(enzyme-dissociate "dissociation of phosphorylated substrate from phosphorylated-substrate-enzyme complex"
                   [phosphorylated-substrate m/enzyme]
                   phosphorylated-substrate-enzyme_complex)

;; MEREOTOPOLOGY
;; MOLECULE
(defn molecule-atom-name
  [molecule atom]
  (let [name (last (clojure.string/split (.toString (.getIRI atom)) #"#" ))]
    (str molecule "_" name)))

(defn molecule-atom
  [molecule current other]
  (owlclass (molecule-atom-name molecule (first current))
            :equivalent
            (owl-and (first current)
                     (exactly 1 m/is_component_part_of molecule)
                     (for [o other]
                       (exactly (second o) m/is_covalently_connected_to
                                (molecule-atom-name molecule (first o)))))))

(defoproperty part_of)

;; TOFIX - RESULTS IN DOUBLE BRACKET
(defn molecule0
  [molecule atom]
  (apply owl-or
         (for [a atom]
           (owl-some part_of
                     (molecule-atom-name molecule (first a))))))

(defn molecule
  [name atom]
  (owlclass name)
  (for [a atom]
    (owlclass (molecule-atom-name name a)))

  (owlclass name
            :equivalent
            (owl-and m/molecule
                     (for [a atom]
                       (exactly (second a) m/has_component_part
                                (molecule-atom-name name (first a))))
                     (owl-only m/has_component_part
                               (molecule0 name atom))))

  (doseq [a atom]
    (molecule-atom name a (clojure.set/difference (into #{} atom) #{a}))))

(molecule "methane"
          [[m/hydrogen_atom 3]
           [m/carbon_atom 1]])

;; PROTEIN ...
