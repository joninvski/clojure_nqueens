(ns Nqueens.core
  (:require [ring.adapter.jetty :as jetty]))

(use '[clojure.contrib.seq :only (positions)])
(use 'clojure.contrib.combinatorics)

; Constants:

; SIZE 4
; N-QUEENS 4

;; Data types

;; Val is Natural[0, 1]

;; Board is (listof Val|false)   that is 81 elements long
;; Pos is  Natural[0, SIZE^2[

;; size -> boards
;; makes board of size n
(defn make-board [size]
  (vec (repeat (* size size) 0)))

;; board number-queens -> boolean
;; checks if a board is solved
(defn solved? [b n-queens]
  (<= n-queens
      (count (filter #(= 1 % ) b))))

;; board -> pos or -1
;; gets the last pos where a queen is present
(defn get-last-queen [b]
  (.lastIndexOf b 1))

;; pos board -> board
;; inserts a queen at the given position
(defn insert-queen [pos board]
  (assoc board pos 1))

;; listofPos board -> board
;; inserts a queens at various positions passed as a list (lop -> list of positions)
(defn insert-queens [lop board]
  (if (list? lop)
    board
    (let [new-board (insert-queen (first lop) board)]
      (insert-queens (rest lop) new-board))))

;; board position board_size -> boolean
;; checks if a queen is not attacking another
;; only checks positions forward of the queen
(defn valid-queen-pair? [p1 p2 size]
  (let [x1 (rem p1 size)
        x2 (rem p2 size)
        y1 (int (/ p1 size))
        y2 (int (/ p2 size))
        ]
    (and
      (not= x1 x2)
      (not= y1 y2)
      (not= (- x2 x1) (- y2 y1))
      (not= (- x1 y2) (- x2 y1)))
    ))

;; board -> boolean
;; checks if all queens in the board are not attacking another queen
(defn valid? [b size]
  (let [queen-positions (map first (filter #(= 1 (second %)) (map-indexed vector b)))
        all-combinations (vec (map vec (combinations queen-positions 2)))]
    ; (println queen-positions)
    ; (println all-combinations)
    ; (if (< 4 (count queen-positions)) (System/exit 0))
    ; all-combinations))
    (every? #(valid-queen-pair? (first %) (second %) size) all-combinations))) ; checks if it is valid for every queen on board

(defn print-board [board size]
  (letfn [(aux [b]
            (when (seq b)
              (print (first b) ": " )
              (when (= (rem (count b) size) 1)
                (println ""))
              (recur (rest b))))]
    (println "")
    (when (seq board)
      (do
        (println "")
        (aux board))))
    (println "---" (count board))
  )

;; board -> listofBoards
;; get all valid boards which have one more queen
;; that the passed boars
(defn get-valid-boards-one-more-queen [b size]
  (let [last-queen-pos (get-last-queen b)]
    (for [x (range (inc last-queen-pos) (count b))
          :let [new-board (insert-queen x b)]
          :when (or (= last-queen-pos -1)
                    (and (valid-queen-pair? last-queen-pos x size)
                         (valid? new-board size)))]
      (when (seq new-board)
        new-board))))

;; size n-queens -> listofBoard
;; produces the complete list size length boards where
;; n-queens of queens are in non-attacking positions
;; if no solution is possible, nil is returned
(defn solve [size n-queens]
  (letfn [(solve--listofBoards [lob acum]                                        ; solves list of board
            ; (print "-")
            (if (seq lob)
              (let [[got_result? new-board] (solve--board (first lob) acum)]     ; goes to each board and solves it at a time
                  (solve--listofBoards (rest lob) (if got_result?
                                                    (conj acum new-board)
                                                    (concat acum new-board))))
              acum
              ))
          (solve--board [b acum]                                                 ; solves individual board
            ; (println "Got board" acum)
            ; (print "\n.")
            (if (solved? b n-queens)
              [true b]
              [false (solve--listofBoards (get-valid-boards-one-more-queen b size) [])]))
          ]  ; lets descend it to the board children
    (second (solve--board (make-board size) []))))

(defn app [req]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world"})


(defn -main [port]
  (jetty/run-jetty app {:port (Integer. port) :join? false}))
