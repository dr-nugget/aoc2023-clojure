(ns day1
  [:require clojure.string])

(def input 
  (clojure.string/split-lines 
   (slurp "/workspace/aoc2023-clojure/inputs/day1.txt")))

(defn word->num
  [s]
  (case s
    "one" 1
    "two" 2
    "three" 3
    "four" 4
    "five" 5
    "six" 6
    "seven" 7
    "eight" 8
    "nine" 9
    "zero" 0
    (Integer/parseInt s)))

(defn re-find-all [re s]
  (loop [m (re-matcher re s) pos 0 res nil]
    (if
     (.find m pos)
      (recur m (+ 1 (.start m)) (cons (.group m) res))
      (reverse res))))

(defn part1
  []
  (->> input
       (map (fn [s]
              (let [digits
                    (let [match (re-find #"(\d).*(\d)" s)]
                      (if (some? match)
                        (rest match)
                        (let [new-match (re-find #"\d" s)]
                          [new-match, new-match])))]
                (+ (* 10 (Integer/parseInt (first digits)))
                   (Integer/parseInt (last digits))))))
       (reduce +)))
       

(println (part1))

(defn part2
  [] 
  (reduce +
          (map
           (fn [s]
             (let [digits (map word->num 
                               (re-find-all #"one|two|three|four|five|six|seven|eight|nine|\d" s))]
               (+ (* 10 (first digits))
                  (last digits))))
           input)))

(println (part2))
