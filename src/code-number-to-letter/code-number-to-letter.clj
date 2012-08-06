(ns dojo.code-number-to-letter)

(def codes {:2 "a", :22 "b", :222 "c", :3 "d", :33 "e", :333 "f", :4 "g", 
            :44 "h", :444 "i", :5 "j", :55 "k", :555 "l", :6 "m", :66 "n", 
            :666 "o", :7 "p", :77 "q", :777 "r", :7777 "s", :8 "t", :88 "u", 
            :888 "v", :9 "w", :99 "x", :999 "y", :9999 "z"})

(defn found-next-code
  [code-keyword]
  (let [code (name code-keyword)
        number (subs code 0 1) 
        k (keys codes)
        my-re (java.util.regex.Pattern/compile (str number "+")) 
        numbers (doall (map (comp read-string name) (filter #(re-matches my-re (name %)) k)))
        max-number (apply max numbers)
        lenght-max-number (count (str max-number))
        next-code-group (last (partition-all lenght-max-number code))
        next-code (apply str next-code-group)]
        (keyword next-code)))

(defn map-letter
  [code-keyword]
  (let [code-found (code-keyword codes)]
    (if (= code-found nil)
      (let [next-code (found-next-code code-keyword)]
        (next-code codes))
      code-found)))

(defn map-code
  [code-keyword]
  (map map-letter code-keyword))

(defn code-to-letter
  [code-str]
  (let [code-grouped (partition-by identity code-str)] ;; try to change to (reduce conj #{} "string")
    (let [code-keyword (map (comp keyword (partial apply str)) code-grouped)]
      (apply str (map-code code-keyword)))))
