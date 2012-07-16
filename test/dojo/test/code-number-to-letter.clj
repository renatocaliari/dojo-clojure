(ns dojo.test.code-number-to-letter
  (:use [dojo.code-number-to-letter])
  (:use [clojure.test]))

(deftest code-to-letter-test
  (testing "Type code"
    (testing "valide"
      (is (= "a" (code-to-letter "2")))
      (is (= "b" (code-to-letter "22")))
      (is (= "c" (code-to-letter "222")))
      (is (= "a" (code-to-letter "2222"))))))
