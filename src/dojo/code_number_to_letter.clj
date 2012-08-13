(ns dojo.code-number-to-letter)

(def codes {:pause "",
            :2 "a", :22 "b", :222 "c",
            :3 "d", :33 "e", :333 "f",
            :4 "g", :44 "h", :444 "i",
            :5 "j", :55 "k", :555 "l",
            :6 "m", :66 "n", :666 "o",
            :7 "p", :77 "q", :777 "r", :7777 "s",
            :8 "t", :88 "u", :888 "v",
            :9 "w", :99 "x", :999 "y", :9999 "z"})

(defn- get-numbers-same-group
  "Get number from same identity group. If get '22', so the results will be all numbers that include '2' as yours identity: 2, 22, 222"
  [number]
  (if (not-empty number)
    (let [first-digit (subs number 0 1)
          num-re (java.util.regex.Pattern/compile (str first-digit "+"))
          keywords-same-group (filter #(re-matches num-re (name %)) (keys codes))
          numbers-same-group (doall (map (comp read-string name) keywords-same-group))]
      numbers-same-group)
    :pause))

(defn- normalize
  "Normalize number according to codes allowed. If get '3333' then the function returns '3', since the max map knwon to number '3' is '333', so the result will be the remainder: '3'."
  [number]
  (let [numbers (get-numbers-same-group number)]
    (if (not-empty numbers)
      (let [max-number (apply max numbers)
           lenght-max-number (count (str max-number))
           remainder (last (partition-all lenght-max-number number))
           number-normalized (apply str remainder)]
       number-normalized)
      :pause)))
   
(defn- build-text
  "Build text from numbers"
  [numbers]
  (if-let [number (first numbers)]
    (let [letter ((keyword (str number)) codes)]
      (str letter (build-text (rest numbers))))
    ""))

(defn- group-by-identity
  [expression]
  (let [identities-grouped (partition-by identity expression)]
    (map (partial apply str) identities-grouped)))

(defn convert-code-to-text
  "Convert all numbers to yours respective letters according to data previously specified"
  [expression]
  (let [text
        (->> expression
             group-by-identity
             (map normalize)
             build-text)]
    text))