;; behaviour hierarchy

(create-sio-class0 process
                 "behaviour"
                 "Behaviour is the set of actions and mannerisms made
by systems (biological or otherwise) in response to stimuli or inputs,
whether internal or external, conscious or subconscious, overt or
covert, and voluntary or involuntary.")
(add-annotation
 behaviour
 (clojure.core/list
  (annotation subset (literal "behaviour+" :type :RDF_PLAIN_LITERAL))
  (annotation subset (literal "core" :type :RDF_PLAIN_LITERAL))))


(create-sio-class0 behaviour
                 "emotion"
                 "An emotion is a process (experience) that arises
internally or from an involuntary physiological response to a
stimulus.")
(add-annotation
 emotion
 (clojure.core/list
  (annotation subset (literal "emotion++" :type :RDF_PLAIN_LITERAL))))


;; PARENTS
(def create-sio-class (partial create-sio-class0 emotion))
(create-sio-class "indifference"
                "indifference is an emotion characterized by lack of interest,
concern, or sympathy.")
(add-subclass indifference
              (owland
               (owlnot
                (owlsome has_quality positive))
               (owlnot
                (owlsome has_quality negative))))

(create-sio-class "negative emotion"
                "negative emotion is an emotion that does not feel
good.")
(add-subclass negative_emotion
              (owlsome has_quality negative))

(create-sio-class "positive emotion"
                "a positive emotion is an emotion that feels good.")
(add-subclass positive_emotion
              (owlsome has_quality positive))


;; SIO_INDIFFERENCE
(create-sio-class0 indifference
                 "apathy"
                 "apathy is an emotion characterized by lack of
interest, enthusiasm, or concern")


;; SIO_POSITIVE
(def create-sio-class (partial create-sio-class0 positive_emotion))
(create-sio-class "interest"
                "interest is the emotion of wanting to know or learn
about something or someone.")
(create-sio-class "happiness"
                "Happiness is an emotion characterized by positive or pleasant
emotions ranging from contentment to intense joy.")
(create-sio-class "surprise"
                "Surprise is a brief emotion experienced as the result
of an unexpected event. ")
(create-sio-class "hope"
                "hope is an emotion of belief in a positive outcome
related to events and circumstances in one's life.")
(create-sio-class "affection"
                "affection is an emotion characterized with a feeling
or type of love for another living thing.")
(create-sio-class "awe"
                "Awe is an emotion produced by that which is grand,
sublime or powerful and is characterized by a combination of joy, fear
and admiration/reverence/respect. ")
(create-sio-class "boldness"
                "boldness is the trait of being willing to undertake
things that involve risk or danger.")
(create-sio-class "arousal"
                "Arousal is an emotion characterized by state of
reactive to stimuli. It involves the activation of the reticular
activating system in the brain stem, the autonomic nervous system and
the endocrine system, leading to increased heart rate and blood
pressure and a condition of sensory alertness, mobility and readiness
to respond.")
(create-sio-class "love"
                "Love is an emotion of a strong affection and personal
attachment.")
(create-sio-class "excitement"
                "excitement is a positive emotion of feeling great
enthusiasm and eagerness.")
(create-sio-class "ecstasy"
                "ecstacy is an emotion characterized by a heightened
state of consciousness with total involvement of a subject.")
(create-sio-class "gratitude"
                "Gratitude, thankfulness, gratefulness, or
appreciation is a feeling, emotion or attitude in acknowledgment of a
benefit that one has received or will receive.")
(add-annotation
 gratitude
 (clojure.core/list
  (annotation hasSynonym (literal "gratefulness" :type :RDF_PLAIN_LITERAL))
  (annotation hasSynonym (literal "appreciation" :type :RDF_PLAIN_LITERAL))
  (annotation hasSynonym (literal "thankfullness" :type :RDF_PLAIN_LITERAL))))


(def create-sio-class (partial create-sio-class0 happiness))
(create-sio-class "pleasure"
                "pleasure is an emotion of happy satisfaction and
enjoyment.")
(create-sio-class "joy"
                "joy is an emotion of intense happiness")
(create-sio-class "euphoria"
                "euphoria is an emotion characterized by intense
feelings of well-being, elation, happiness, ecstasy, excitement, and
joy.")
(create-sio-class "contentment"
                "contentment is an emotion characterized by
acknowledgement and satisfaction of the current state of affairs.")


(create-sio-class0 interest
                 "desire"
                 "Desire is a strong emotion of wanting to have
something or wishing for something to happen.")


(def create-sio-class (partial create-sio-class0 desire))
(create-sio-class "passion"
                "passion is the intense desire for something.")
(create-sio-class "curiosity"
                "curiosity is the strong desire to know or learn something.")
(create-sio-class "lust"
                "lust is the strong desire for sex.")
(create-sio-class "intent"
                "intent is a desire to realize a particular outcome.")



(create-sio-class0 contentment
                 "satisfaction"
                 "satisfaction is an emotion of fulfillment of one's wishes,
expectations, or needs, or the pleasure derived from this.")

(create-sio-class0 satisfaction
                 "pride"
                 "Pride is an emotion of satisfaction of attachment
toward one's own or another's choices and actions, or toward a whole
group of people, and is a product of praise, independent
self-reflection, or a fulfilled feeling of belonging.")

(create-sio-class0 surprise
                 "wonder"
                 "Wonder is an emotion of perceiving something very
rare or unexpected, but not threatening.")


;; SIO_NEGATIVE
(def create-sio-class (partial create-sio-class0 negative_emotion))
(create-sio-class "loneliness"
                "Loneliness is an unpleasant emotion in which a person
feels a strong sense of emptiness, yearning distress and solitude
resulting from inadequate quantity or quality of social
relationships.")
(create-sio-class "pity"
                "Pity is the emotion of sadness or sorrow for another.")
(create-sio-class "hostility"
                "Hostility is the intense negative emotion of being in
                conflict or opposition to someone or something.")
(create-sio-class "guilt"
                "Guilt is the emotion borne from feeling responsible
for the commission of an offense and arises out of public
humiliation.")
(create-sio-class "hysteria"
                "Hysteria is an unmanageable emotion.")
(create-sio-class "hurt"
                "hurt is an unpleasant feeling, emotion or sensation.")
(create-sio-class "shyness"
                "shyness is an emotion of apprehension, lack of
comfort, or awkwardness experienced when in proximity to, approaching,
or being approached by other individuals, especially in new situations
or with unfamiliar individuals.")
(create-sio-class "shock"
                "shock is an emotion of sudden upset or surprise.")
(create-sio-class "shame"
                "shame is the emotion borne from feeling responsible
for the commission of an offense.")
(create-sio-class "sadness"
                "sadness is emotional pain associated with, or
characterized by feelings of disadvantage, loss, despair,
helplessness, sorrow, and rage.")
(create-sio-class "annoyance"
                "Annoyance is an unpleasant emtion that is
characterized by a abnormal or excessive sensitivity to some external
stimulus.")
(create-sio-class "envy"
                "envy is an emotion that occurs when a person lacks
another's (perceived) superior quality, achievement or possession and
wishes that the other lacked it.")
(create-sio-class "apprehension"
                "apprehension is the negative emotion that something
unpleasant will occur.")
(create-sio-class "disappointment"
                "Disappointment is the feeling of dissatisfaction that
follows the failure of expectations or hopes to manifest")
(create-sio-class "boredom"
                "boredom is the emotion experience by those not
interested in their surroundings or available activities.")
(create-sio-class "embarassment"
                "Embarrassment is the emotion of intense discomfort
with oneself, experienced upon having a socially unacceptable act or
condition witnessed by or revealed to other.")


(def create-sio-class (partial create-sio-class0 sadness))
(create-sio-class "misery"
                "Misery is a feeling of great unhappiness, suffering
and/or pain.")
(create-sio-class "grief"
                "Grief is an emotion in response to loss, whether
physical or abstract including death, unemployment, ill health or the
end of a relationship.")
(create-sio-class "depression"
                "depression is an unpleasant emotion linked to
aversion to activity that can affect a person's thoughts, behavior,
feelings and physical well-being. Depressed individuals may feel sad,
anxious, empty, hopeless, worried, helpless, worthless, guilty,
irritable, or restless.")
(create-sio-class "despair"
                "despair is depression, hopelessness or lack of hope")
(create-sio-class "sorrow"
                "Sorrow is the emotion that is characterized by a long
term state of intense sadness, distress and a degree of
resignation (not accepting).")


(def create-sio-class (partial create-sio-class0 apprehension))
(create-sio-class "fear"
                "Fear is a negative emotion induced by a perceived
threat that induces one to hide or move quickly away from the location
of the perceived threat.")
(create-sio-class "worry"
                "worry is the emotion characterized by concer over a
real or imaginary issue.")
(create-sio-class "anxiety"
                "anxiety is an emotion charactersized by intense
feeling of fear and concern coupled with a physical response.")


(def create-sio-class (partial create-sio-class0 fear))
(create-sio-class "panic"
                "Panic is a sudden emotion of fear which is so strong
as to dominate or prevent reason and logical thinking, replacing it
with overwhelming feelings of anxiety and frantic agitation consistent
with an animalistic fight-or-flight reaction.")
(create-sio-class "dread"
                "dread is the instense negative emotion that induces
fear and apprehension.")

(create-sio-class0 hostility
                 "disgust"
                 "Disgust is a feeling of revulsion or profound
disapproval aroused by something unpleasant or offensive.")


(def create-sio-class (partial create-sio-class0 disgust))
(create-sio-class "loathing"
                "loathing is an intense dislike or disgust.")
(create-sio-class "hate"
                "Hate is a deep and emotional extreme dislike,
directed against a certain object or class of objects. ")
(create-sio-class "contempt"
                "contempt is disgust towards a lower status
individual.")
(create-sio-class "resentment"
                "resentment is disgust directed toward a higher status
                individual.")
(create-sio-class "anger"
                "anger is disgust directed toward an equal status
                individual.")


(create-sio-class0 anger
                 "rage"
                 "Rage is a feeling of intense anger that is
associated with the Fight-or-flight response.")


(def create-sio-class (partial create-sio-class0 hurt))
(create-sio-class "suffering"
                "Suffering is the unpleasant emotion and aversion
associated with the perception of harm or threat of harm in an
individual.")
(create-sio-class "pain"
                "Pain is an unpleasant sensory and emotional
experience associated with actual or potential tissue damage, or
described in terms of such damage")


(create-sio-class0 envy
                 "jealousy"
                 "jealousy is an emotion and typically refers to the
negative thoughts and feelings of insecurity, fear, and anxiety over
an anticipated loss of something that the person values, particularly
in reference to a human connection")

(create-sio-class0 dread
                 "terror"
                 "terror is the extreme feeling of fear.")

(create-sio-class0 worry
                 "regret"
                 "regret is a feeling of sadness, repentance, or
disappointment over something that has happened or been done.")

(create-sio-class0 regret
                 "remorse"
                 "remorse is an emotion of personal regret felt by a
person after he or she has committed an act which they deem to be
shameful, hurtful, or violent.")

(create-sio-class0 anxiety
                 "angst"
                 "angst is the intense feeling of apprehension, anxiety
or inner turmoil.")

(create-sio-class0 annoyance
                 "frustration"
                 "Frustration is an emotion that arises from the
perceived resistance to the fulfillment of individual will. ")
