(ns mars-rover.core)

(defn move-forward
  [rover]
  {:x-position (:x-position rover)
   :y-position (inc (:y-position rover))
   :orientation (:orientation rover)})

(defn rover-with-orientation
  [rover orientation]
  {:x-position (:x-position rover)
   :y-position (:y-position rover)
   :orientation orientation})

(defn turn-right
  [rover]
  (let [orientation (:orientation rover)]
    (cond
      (= orientation "N") (rover-with-orientation rover "E")
      (= orientation "E") (rover-with-orientation rover "S"))))

(defn turn-left
  [rover])

(defn rover-to-string
  [rover]
  (str (:x-position rover) ":" (:y-position rover) ":" (:orientation rover)))

(defn parse
  [rover input]
  (let [command (str input)]
    (cond (= command "M") (move-forward rover)
          (= command "R") (turn-right rover)
          (= command "L") (turn-left rover))))

(defn execute
  [commands]
  (let [initial-rover {:x-position 0 :y-position 0 :orientation "N"}
        rover (reduce #(parse %1 %2) initial-rover commands)]
    (rover-to-string rover)))
