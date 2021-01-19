(ns mars-rover.core-test
  (:require
    [clojure.test :refer :all]
    [mars-rover.core :refer :all]))

;(deftest acceptance-test
;  (testing "Expected input for rover returns position after movement"
;    (is (= (execute "MMRMMLM") "2:3:N"))
;    (is (= (execute "MMMMMMMMMM") "0:0:N"))))

(deftest execute-move-commands
  (testing "Without instructions, returns default position of 0:0:N"
    (is (= (execute "") "0:0:N")))
  (testing "Should move forwards when given an instruction of M"
    (is (= (execute "M") "0:1:N"))
    (is (= (execute "MM") "0:2:N"))
    (is (= (execute "MMM") "0:3:N"))
    (is (= (execute "MMMM") "0:4:N"))
    (is (= (execute "MMMMM") "0:5:N"))))

(deftest execute-turn
  (testing "Turn right should update the orientation"
    (is (= (execute "R") "0:0:E"))
    (is (= (execute "RR") "0:0:S"))
    (is (= (execute "RRR") "0:0:W"))
    (is (= (execute "RRRR") "0:0:N")))
  (testing "Turn left should update the orientation"
    (is (= (execute "L") "0:0:W"))
    (is (= (execute "LL") "0:0:S"))
    (is (= (execute "LLL") "0:0:E"))
    (is (= (execute "LLLL") "0:0:N")))
  (testing "Mixed turn commands should update the orientation"
    (is (= (execute "LR") "0:0:N"))
    (is (= (execute "RRL") "0:0:E"))))

(deftest execute-turn-and-move
  (testing "Turn right and move east"
    (is (= (execute "RM") "1:0:E"))))