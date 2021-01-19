(ns mars-rover.core)

(defn- rover-with-orientation
  [rover orientation]
  {:x-position (:x-position rover)
   :y-position (:y-position rover)
   :orientation orientation})

(defn- update-coordinates
  [{x-position :x-position
    y-position :y-position
    orientation :orientation}
   x-operation
   y-operation]
  {:x-position (x-operation x-position)
   :y-position (y-operation y-position)
   :orientation orientation})

(defn- wrap-rover
  [{x-position :x-position
    y-position :y-position
    :as rover}]
  {:x-position (mod x-position 10)
   :y-position (mod y-position 10)
   :orientation (:orientation rover)})

(defn- rover-to-string
  [rover]
  (str (:x-position rover) ":" (:y-position rover) ":" (:orientation rover)))

(defn- move-forward
  [{orientation :orientation
    :as rover}]
  (condp = orientation
    "N" (update-coordinates rover int inc)
    "E" (update-coordinates rover inc int)
    "S" (update-coordinates rover int dec)
    "W" (update-coordinates rover dec int)))

(defn- turn-right
  [orientation]
  (condp = orientation
    "N" "E"
    "E" "S"
    "S" "W"
    "W" "N"))

(defn- turn-left
  [orientation]
  (condp = orientation
    "N" "W"
    "W" "S"
    "S" "E"
    "E" "N"))

(defn- parse
  [rover input]
  (let [command (str input)]
    (condp = command
      "M" (move-forward rover)
      "R" (rover-with-orientation rover (turn-right (:orientation rover)))
      "L" (rover-with-orientation rover (turn-left (:orientation rover))))))

(defn execute
  [commands]
  (let [initial-rover {:x-position 0 :y-position 0 :orientation "N"}
        rover (reduce #(parse %1 %2) initial-rover commands)]
    (rover-to-string (wrap-rover rover))))
