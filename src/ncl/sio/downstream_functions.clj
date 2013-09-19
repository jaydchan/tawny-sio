(ns ncl.sio.downstream_functions
  (:use tawny.owl)
  (:require [ncl.sio.mysio :as m]
            [tawny.read]))


(defontology downstream
  :iri "http://ncl.ac.uk/sio/downstream"
  :prefix "down:")

;; AUXILIARY FUNCTIONS
(defn owlclass
  [name & rest]
  (let [safe (tawny.read/stop-characters-transform name)]
    (owl-class safe (first rest))))

;; BIOCHEMICAL PATHWAY
(defclass hexokinase_reaction)
(defclass phosphoglucose_isomerase_reaction)
(defclass third_reaction)

(defn biochemical-pathway0
  [reactions]
  (apply owl-and
         (first reactions)
         (for [r (rest reactions)]
           (owl-some m/precedes r))))

(defn biochemical-pathway
  [name reactions]
  (owl-class name
             :equivalent
             (owl-and m/pathway
                      (owl-some m/has_proper_part
                                (biochemical-pathway0 reactions))
                      (for [r reactions]
                        (owl-some m/has_proper_part r)))))

(biochemical-pathway "glycosis_pathway"
                     [hexokinase_reaction
                      phosphoglucose_isomerase_reaction
                      third_reaction])

(defclass target_role) ;; MISSING OBJ PROPERTY

(defn sometarget
  [class]
  (owl-some m/realizes (owl-and target_role
                                (owl-some m/is_role_of class))))

(defn somecatalytic
  [class]
  (owl-some m/realizes (owl-and m/catalytic_role
                                (owl-some m/is_role_of class))))

(defn someproduct
  [class]
  (owl-some m/realizes (owl-and m/product_role
                                (owl-some m/is_role_of class))))

(defn biochemical-reaction0
  [function values]
  (for [v values]
    (function v)))

(defn biochemical-reaction
  [name target catalytic product]
  (owl-class name
            :equivalent
            (owl-and m/biochemical_reaction
                     (biochemical-reaction0 (first target) (second target))
                     (biochemical-reaction0 (first catalytic) (second catalytic))
                     (biochemical-reaction0 (first product) (second product)))))

(defclass glucose)
(defclass ATP)
(defclass hexokinase)
(defclass glucose-6-phosphate)

(biochemical-reaction "hexokinase_reaction"
                      [sometarget [glucose ATP]]
                      [somecatalytic [hexokinase]]
                      [someproduct [glucose-6-phosphate]])

;; ENZYME MECHANISM

;; (defn enzyme-part-of...
;; (defn enzyme-role..
;; (defn enzyme-dissociate..

;; 'ATP-enzyme complex formation' equivalentTo:
;; 'complex formation'
;; and 'realizes' some ('to be part of' and 'is disposition of' some 'enzyme' and 'in relation to' some 'ATP-enzyme complex')
;; and 'realizes' some ('to be part of' and 'is disposition of' some 'ATP' and 'in relation to' some 'ATP-enzyme complex')

;; 'ATP-substrate-enzyme complex formation' equivalentTo:
;; 'complex formation'
;; and 'realizes' some ('to be part of' and 'is disposition of' some 'ATP-enzyme complex' and 'in relation to' some 'ATP-substrate-enzyme complex' )
;; and 'realizes' some ('to be part of' and 'is disposition of' some 'ATP' and 'in relation to' some 'ATP-substrate-enzyme complex')

;; 'substrate-enzyme phosphorylation by ATP' equivalentTo:
;; 'biochemical reaction'
;; and 'realizes' some ('substrate role' and 'is role of' some 'ATP-substrate enzyme complex')
;; and 'realizes' some ('product role' and 'is role of' some 'ADP-substrate-phosphorylated-enzyme complex')

;; 'substrate-phosphorylation by phosphorylated enzyme' equivalentTo:
;; 'biochemical reaction'
;; and 'realizes' some ('substrate role' and 'is role of' some 'substrate-phosphorylated-enzyme complex')
;; and 'realizes' some ('product role' and 'is role of' some 'phosphorylated-substrate-enzyme complex')

;; 'ADP dissociation from phosphorylated-enzyme substrate complex' equivalentTo:
;; 'complex dissociation'
;; and 'realizes' some ('to dissociate' and 'is disposition of' some 'phosphorylated-enzyme-substrate complex' and 'in relation to' some 'ADP-substrate-phosphorylated-enzyme complex' )
;; and 'realizes' some ('to dissociate' and 'is disposition of' some 'ADP' and 'in relation to' some 'ADP-substrate-phosphorylated-enzyme complex')

;; 'dissociation of phosphorylated substrate from phosphorylated-substrate-enzyme complex' equivalentTo:
;; 'complex dissociation'
;; and 'realizes' some ('to dissociate' and 'is disposition of' some 'phosphorylated-substrate' and 'in relation to' some 'phosphorylated-substrate-enzyme complex' )
;; and 'realizes' some ('to dissociate' and 'is disposition of' some 'enzyme' and 'in relation to' some 'phosphorylated-substrate-enzyme complex')
