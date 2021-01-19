(ns mars-rover.core-test
  (:require
    [clojure.test :refer :all]
    [mars-rover.core :refer :all]))

;(deftest acceptance-test
;  (testing "Expected input for rover returns position after movement"
;    (is (= (execute "MMRMMLM") "2:3:N"))
;    (is (= (execute "MMMMMMMMMM") "0:0:N"))))

(deftest execute-accepts-commands
  (testing "Without instructions, returns default position of 0:0:N"
    (is (= (execute "") "0:0:N")))
  (testing "Should move forwards when given an instruction of M"
    (is (= (execute "M") "0:1:N"))
    (is (= (execute "MM") "0:2:N"))
    (is (= (execute "MMM") "0:3:N"))
    (is (= (execute "MMMM") "0:4:N"))))
