;; The contents of this file are subject to the LGPL License, Version 3.0.

;; Copyright (C) 2013-2015, Newcastle University

;; This program is free software: you can redistribute it and/or modify
;; it under the terms of the GNU General Public License as published by
;; the Free Software Foundation, either version 3 of the License, or
;; (at your option) any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program. If not, see http://www.gnu.org/licenses/.

(ns ncl.sio.downstream_usage
  (:use [tawny.owl])
  (:require [ncl.sio.downstream_functions :as d]))

(defontology downstream_usage
  :iri "http://homepages.cs.ncl.ac.uk/jennifer.warrender/mysio/latest/downstream_usage"
  :prefix "downstream:"
  :noname true)

;; BIOCHEMICAL PATHWAY PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry
;; EXAMPLE

(defclass hexokinase_reaction)
(defclass phosphoglucose_isomerase_reaction)
(defclass third_reaction)

(d/biochemical-pathway downstream_usage
                       "glycolsis pathway"
                       [hexokinase_reaction
                        phosphoglucose_isomerase_reaction
                        third_reaction])

;; BIOCHEMICAL REACTION PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry
;; EXAMPLE

;; NOTE target_role updated to reactant_role --> as identified in paper
(def reactant_role (d/get-sio-entity "reactant_role"))
(def catalytic_role (d/get-sio-entity "catalytic_role"))
(def product_role (d/get-sio-entity "product_role"))

(defclass glucose)
(defclass ATP)
(defclass hexokinase)
(defclass glucose-6-phosphate)
(defclass ADP)

(d/biochemical-reaction-pattern
 downstream_usage
 "hexokinase reaction"
 [reactant_role [glucose ATP]]
 [catalytic_role [hexokinase]]
 [product_role [glucose-6-phosphate ADP]])

;; (GENERIC) REACTION_PATTERN -- WORKS
;; https://code.google.com/p/semanticscience/wiki/ODPChange
;; EXAMPLE

;; NOTE target_role should be updated to reactant_role -> as identified in paper
(def reactant_role (d/get-sio-entity "reactant_role"))
(def catalytic_role (d/get-sio-entity "catalytic_role"))
(def product_role (d/get-sio-entity "product_role"))
(def addition_reaction (d/get-sio-entity "addition_reaction"))
(def protein (d/get-sio-entity "protein"))

(defclass phospho-enzyme)
(defclass phosphorylated_protein)
(defclass ATP)
(defclass ADP)

(d/reaction-pattern
 downstream_usage
 "enzyme-catalyzed phosphorylation using ATP"
 addition_reaction
 [reactant_role [protein ATP]]
 [catalytic_role [phospho-enzyme]]
 [product_role [phosphorylated_protein ADP]])

;; ENZYME MECHANISM PATTERNS

;; A. ENZYME-PART-OF PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry
;; EXAMPLE

(def enzyme (d/get-sio-entity "enzyme"))
(defclass ATP)
(defclass ATP-enzyme_complex)
(defclass ATP-substrate-enzyme_complex)

(d/enzyme-part-of downstream_usage
                  "ATP-enzyme complex formation"
                  [enzyme ATP]
                  ATP-enzyme_complex)
(d/enzyme-part-of downstream_usage
                  "ATP-substrate-enzyme complex formation"
                  [ATP-enzyme_complex ATP]
                  ATP-substrate-enzyme_complex)

;; B. ENZYME-ROLE PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry
;; EXAMPLE

;; NOTE should be ATP-substrate_enzyme_complex ->
;; ATP-substrate-enzyme_complex
(defclass ADP-substrate-phosphorylated-enzyme_complex)
(defclass substrate-phosphorylated-enzyme_complex)
(defclass phosphorylated-substrate-enzyme_complex)

(d/enzyme-role downstream_usage
               "substrate-enzyme phosphorylation by ATP"
               ATP-substrate-enzyme_complex
               ADP-substrate-phosphorylated-enzyme_complex)

(d/enzyme-role downstream_usage
               "substrate-phosphorylation by phosphorylated enzyme"
               substrate-phosphorylated-enzyme_complex
               phosphorylated-substrate-enzyme_complex)

;; C. ENZYME-DISSOCIATION PATTERN -- WORKS
;; See https://code.google.com/p/semanticscience/wiki/ODPBiochemistry
;; EXAMPLE

(defclass phosphorylated-enzyme-substrate_complex)
(defclass phosphorylated-enzyme-substrate_complex)
(defclass ADP)
(defclass phosphorylated-substrate)

(d/enzyme-dissociate
 downstream_usage
 "ADP dissociation from phosphorylated-enzyme substrate complex"
 [phosphorylated-enzyme-substrate_complex ADP]
 ADP-substrate-phosphorylated-enzyme_complex)

(d/enzyme-dissociate
 downstream_usage
 "dissociation of phosphorylated substrate from phosphorylated-substrate-enzyme complex"
 [phosphorylated-substrate enzyme]
 phosphorylated-substrate-enzyme_complex)

;; MEREOTOPOLOGY

;; A. MOLECULE -- WORKS
;; https://code.google.com/p/semanticscience/wiki/ODPMereotopology
;; EXAMPLE

(def hydrogen_atom (d/get-sio-entity "hydrogen_atom"))
(def carbon_atom (d/get-sio-entity "carbon_atom"))

(d/molecule-pattern downstream_usage
                    "methane"
                    [[hydrogen_atom 4]
                     [carbon_atom 1]])

;; B. PROTEIN -- WORKS
;; https://code.google.com/p/semanticscience/wiki/ODPMereotopology

;; NOTE should be human p53 isoform 1 methionine residue @ pos1 ->
;; human p53 isoform 1 methionine residue @ p1

;; TODO whats does the chebi comment mean???

;; EXAMPLE

(defclass methionine :label "methionine residue")
(defclass glutamate :label "glutamate residue")
(defclass third_residue :label "third residue")

(d/protein-pattern downstream_usage
                   "human p53 isoform 1"
                   [methionine
                    glutamate
                    third_residue])

;; C. PROTEIN CONTAINMENT -- WORKS
;; https://code.google.com/p/semanticscience/wiki/ODPMereotopology
;; EXAMPLE

(defclass proteosome :label "proteosome")
(defclass peptide_chain :label "peptide chain")

(d/protein-containment downstream_usage
                       proteosome
                       peptide_chain)

;; PARAMETERS
;; See https://code.google.com/p/semanticscience/wiki/ODPParameters
;; EXAMPLE(S)

(def database (d/get-sio-entity "database"))
;; NOTE should be expect_value -> expected_value
(def expected_value (d/get-sio-entity "expected_value"))
;; NOTE subclass of to_filter ???
(defclass low_complexity_filter :label "low complexity filter")

(d/parameter-pattern downstream_usage "BLAST")

(d/parameter-pattern downstream_usage
                     "BLAST"
                     [expected_value :XSD_FLOAT]
                     [database :XSD_STRING]
                     [low_complexity_filter :XSD_BOOLEAN])
