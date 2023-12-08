(ns day1
  [:require clojure.string])

(defn part1
  []
  (->> (slurp "/workspace/aoc2023-clojure/inputs/day1.txt")
       (clojure.string/split-lines)
       (map (fn [s] 
              (let [digits 
                    (let [match (re-find #"(\d).*(\d)" s)]
                      (if (some? match)
                        (rest match)
                        (let [new-match (re-find #"\d" s)]
                          [new-match, new-match])))]
                (+ (* 10 (Integer/parseInt (first digits))) 
                   (Integer/parseInt (last digits))))))
       (reduce +)
       ))