(ns mars-rover.core)

(defn execute
  [commands]
  (let [y-position (count (re-seq #"M" commands))]
    (str "0:" y-position ":N")))
