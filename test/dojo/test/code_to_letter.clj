(ns dojo.test.code-to-letter
 (:use [dojo.code-to-letter])
 (:use [clojure.test]))


(deftest convert
  (testing "Converting numbers to letters"
    (are [x y] (= x (convert-to-text y))
         "me"  "633"
         "you" "99966688"
         "you" "999a66688"
         ""    "abc"
         "hi"  "44#444")))