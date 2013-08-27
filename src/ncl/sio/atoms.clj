(defn create-atom
  "Creates an atom class with given name"
  [name chebi]
  (let [entity (clojure.string/replace name #" " "_")]
    (tawny.read/intern-entity
     (owlclass entity
               :subclass "atom"
               :annotation
               (label (literal name :lang "en"))))
    (if-not (nil? chebi)
      (add-annotation
       (owlclass entity)
       (clojure.core/list
        (annotation seeAlso (literal chebi :type :RDF_PLAIN_LITERAL)))))))

(create-atom "boron atom" "CHEBI:27560")
(create-atom "carbon atom" "CHEBI:27594")
(create-atom "hydrogen atom" "CHEBI:49637")
(create-atom "helium atom" "CHEBI:30217")
(create-atom "lithium atom" "CHEBI:30145")
(create-atom "nitrogen atom" "CHEBI:25555")
(create-atom "beryllium atom" "CHEBI:30501")
(create-atom "fluorine atom" "CHEBI:24061")
(create-atom "oxygen atom" "CHEBI:25805")
(create-atom "neon atom" "CHEBI:33310")
(create-atom "manganese atom" "CHEBI:18291")
(create-atom "chromium atom" "CHEBI:28073")
(create-atom "cobalt atom" "CHEBI:27638")
(create-atom "iron atom" "CHEBI:18248")
(create-atom "scandium atom" "CHEBI:33330")
(create-atom "vanadium atom" "CHEBI:27698")
(create-atom "titanium atom" "CHEBI:33341")
(create-atom "nickel atom" "CHEBI:28112")
(create-atom "copper atom" "CHEBI:28694")
(create-atom "zinc atom" "CHEBI:27363")
(create-atom "sulfur atom" "CHEBI:26833")
(create-atom "phosphorus atom" "CHEBI:28659")
(create-atom "silicon atom" "CHEBI:27573")
(create-atom "aluminium atom" "CHEBI:28984")
(create-atom "magnesium atom" "CHEBI:25107")
(create-atom "sodium atom" "CHEBI:26708")
(create-atom "potassium atom" "CHEBI:26216")
(create-atom "calcium atom" "CHEBI:22984")
(create-atom "chlorine atom" "CHEBI:23116")
(create-atom "argon atom" "CHEBI:49475")
(create-atom "niobium atom" "CHEBI:33344")
(create-atom "technetium atom" "CHEBI:33353")
(create-atom "molybdemum atom" "CHEBI:28685")
(create-atom "rhodium atom" "CHEBI:33359")
(create-atom "ruthenium atom" "CHEBI:30682")
(create-atom "silver atom" "CHEBI:30512")
(create-atom "palladium atom" "CHEBI:33363")
(create-atom "indium atom" "CHEBI:30430")
(create-atom "cadmium atom" "CHEBI:22977")
(create-atom "tin atom" "CHEBI:27007")
(create-atom "selenium atom" "CHEBI:27568")
(create-atom "arsenic atom" "CHEBI:27563")
(create-atom "germanium atom" "CHEBI:30441")
(create-atom "gallium atom" "CHEBI:49631")
(create-atom "strontium atom" "CHEBI:33324")
(create-atom "rubidium atom" "CHEBI:33322")
(create-atom "krypton atom" "CHEBI:49696")
(create-atom "bromine atom" "CHEBI:22927")
(create-atom "yttrium atom" "CHEBI:33331")
(create-atom "zirconium atom" "CHEBI:33342")
(create-atom "polonium atom" "CHEBI:33313")
(create-atom "lead atom" "CHEBI:25016")
(create-atom "bismuth atom" "CHEBI:33301")
(create-atom "mercury atom" "CHEBI:25195")
(create-atom "thallium atom" "CHEBI:49920")
(create-atom "platinum atom" "CHEBI:33364")
(create-atom "gold atom" "CHEBI:29287")
(create-atom "osmium atom" "CHEBI:30687")
(create-atom "iridium atom" "CHEBI:49666")
(create-atom "rhenium atom" "CHEBI:49882")
(create-atom "lanthanum atom" "CHEBI:33336")
(create-atom "hafnium atom" "CHEBI:33343")
(create-atom "tantalum atom" "CHEBI:33348")
(create-atom "tungsten atom" "CHEBI:27998")
(create-atom "iodine atom" "CHEBI:24859")
(create-atom "xenon atom" "CHEBI:49957")
(create-atom "caesium atom" "CHEBI:30514")
(create-atom "barium atom" "CHEBI:32594")
(create-atom "antimony atom" "CHEBI:30513")
(create-atom "tellurium atom" "CHEBI:30452")
(create-atom "meitnerium atom" "CHEBI:33361")
(create-atom "darmstadtium atom" "CHEBI:33367")
(create-atom "roentgenium atom" "CHEBI:33368")
(create-atom "actinium atom" "CHEBI:33337")
(create-atom "rutherfordium atom" "CHEBI:33346")
(create-atom "dubnium atom" "CHEBI:33349")
(create-atom "seaborgium atom" "CHEBI:33351")
(create-atom "bohrium atom" "CHEBI:33355")
(create-atom "hassium atom" "CHEBI:33357")
(create-atom "astatine atom" "CHEBI:30415")
(create-atom "radon atom" "CHEBI:33314")
(create-atom "francium atom" "CHEBI:33323")
(create-atom "radium atom" "CHEBI:33325")
(create-atom "promethium atom" "CHEBI:33373")
(create-atom "neodymium atom" "CHEBI:33372")
(create-atom "europium atom" "CHEBI:32999")
(create-atom "samarium atom" "CHEBI:33374")
(create-atom "praseodymium atom" "CHEBI:49828")
(create-atom "cerium atom" "CHEBI:33369")
(create-atom "terbium atom" "CHEBI:33376")
(create-atom "gadolinium atom" "CHEBI:33375")
(create-atom "holmium atom" "CHEBI:49648")
(create-atom "dysprosium atom" "CHEBI:33377")
(create-atom "curium atom" "CHEBI:33390")
(create-atom "einsteinium atom" "CHEBI:33393")
(create-atom "fermium atom" "CHEBI:33394")
(create-atom "berkelium atom" "CHEBI:33391")
(create-atom "californium atom" "CHEBI:33392")
(create-atom "lawrencium atom" "CHEBI:33397")
(create-atom "nobelium atom" "CHEBI:33396")
(create-atom "mendelevium atom" "CHEBI:33395")
(create-atom "erbium atom" "CHEBI:33379")
(create-atom "thulium atom" "CHEBI:33380")
(create-atom "ytterbium atom" "CHEBI:33381")
(create-atom "lutetium atom" "CHEBI:33382")
(create-atom "americium atom" "CHEBI:33389")
(create-atom "plutonium atom" "CHEBI:33388")
(create-atom "protactinium atom" "CHEBI:33386")
(create-atom "thorium atom""CHEBI:33385")
(create-atom "neptunium atom" "CHEBI:33387")
(create-atom "uranium atom" "CHEBI:27214")

(create-atom "ununhexium atom" nil)
(create-atom "ununseptium atom" nil)
(create-atom "ununquadium atom" nil)
(create-atom "ununpentium atom" nil)
(create-atom "ununoctium atom" nil)
(create-atom "copernicium atom" nil)
(create-atom "unutrium atom" nil)
