(ns mars-rover.core)

(defn calculate-y-position
  [commands]
  (count (re-seq #"M" commands)))

(defn get-direction
  [commands]
  (if (= commands "R") "E" "N"))


(defn execute
  [commands]
  (str "0:" (calculate-y-position commands) ":" (get-direction commands)))
