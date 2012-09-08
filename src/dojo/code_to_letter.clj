(ns dojo.code-to-letter)

(def codes {:0 " "
            :2 "a", :22 "b", :222 "c",
            :3 "d", :33 "e", :333 "f",
            :4 "g", :44 "h", :444 "i",
            :5 "j", :55 "k", :555 "l",
            :6 "m", :66 "n", :666 "o",
            :7 "p", :77 "q", :777 "r", :7777 "s",
            :8 "t", :88 "u", :888 "v",
            :9 "w", :99 "x", :999 "y", :9999 "z"})

(def pause "")

(defn- same-group
  "Get keywords from the same identity group"
  [s]
  (let [first-digit (subs s 0 1)
        num-re (re-pattern (str first-digit "+"))
        keywords-same-group (filter #(re-find num-re (name %)) (keys codes))]
    (doall (map (comp read-string name) keywords-same-group))))

(defn- numbers-normalized
  "Normalize identities according to codes"
  [s]
  (for [number s]
    (let [s (same-group number)]
       (if (not-empty s)
         (let [max-number (apply max s)
               lenght-max-number (count (str max-number))
               remainder (last (partition-all lenght-max-number number))
               number-normalized (apply str remainder)]
           number-normalized)
         pause))))
   
(defn- text
  "Build a text from a collection of numbers"
  [coll]
  (if-let [number (first coll)]
    (let [letter ((keyword (str number)) codes)]
      (str letter (text (rest coll))))
    ""))

(defn- grouped-identities
  [s]
  (let [identities-grouped (partition-by identity s)]
    (map (partial apply str) identities-grouped)))

(defn convert-to-text
  "Get chars related to the numbers"
  [expression]
  {:pre [(string? expression)]}
  (->> expression
       grouped-identities
       numbers-normalized
       text))