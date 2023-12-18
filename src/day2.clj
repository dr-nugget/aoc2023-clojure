(ns day2 
  [:require clojure.string])

(def input-lines
  (clojure.string/split-lines
   (slurp "/workspace/aoc2023-clojure/inputs/day2.txt")))

(defn max-color
  [line color]
  (apply max
         (map #(Integer/parseInt (last %))
              (re-seq (re-pattern (str #"(\d+) " color)) (str line)))))

(defn color-possible?
  [line color maximum]
  (>= maximum
      (max-color line color)))

(defn part1
  [input]
  (->> input
       (filter (fn [line]
                 (and
                  (color-possible? line "red" 12)
                  (color-possible? line "green" 13)
                  (color-possible? line "blue" 14))))
       (map (fn [l] (Integer/parseInt (last (re-find #"Game (\d+)" l)))))
       (reduce +)))

(println (part1 input-lines))

(defn part2
  [input]
  (reduce + (map 
             (fn [l]
               (* 
                (max-color l "red")
                (max-color l "green")
                (max-color l "blue"))) 
             input)))

(println (part2 input-lines))