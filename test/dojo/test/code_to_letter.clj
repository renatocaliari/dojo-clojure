(ns dojo.test.code-to-letter
 (:use [dojo.code-to-letter])
 (:use [clojure.test]))

(testing "Converting numbers to letters"
  (testing "with valid numbers"
    (is (= "me" (convert-to-text "633"))))
  (testing "with invalid chars"
    (is (= "" (convert-to-text "abc"))))
  (testing "with valid numbers and invalid chars"
    (is (= "you" (convert-to-text "999a66688"))))
  (testing "with pause"
    (is (= "hi" (convert-to-text "44#444")))))