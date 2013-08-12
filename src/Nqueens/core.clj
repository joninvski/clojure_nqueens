(ns Nqueens.core)

(use '[clojure.contrib.seq :only (positions)])

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
;; inserts a queens at various positions passed as a list
(defn insert-queens [lop board]
  (if (list? lop)
    board
    (let [new-board (insert-queen (first lop) board)]
      (insert-queens (rest lop) new-board))))

;; checks if a queen is not attacking another
;; only checks positions in forward of the queen
(defn valid-queen? [b p size]
  (letfn [(only-one-queen-horizontal []
            (let [start-row-pos (- p (rem p size))
                  end-row-pos   (+ start-row-pos size)
                  row-positions (subvec (vec b) start-row-pos end-row-pos)]
              (< (count (filter #(= % 1) row-positions)) 2)))
          (only-one-queen-vertical []
            (let [collum-index (rem p size)
                  collum-pieces(for [i (range 0 (* size size))
                                     :when (= collum-index (rem i size))]
                                 (b i))]
              (< (count (filter #(= % 1) collum-pieces)) 2)))
          (only-one-queen-diagonals []
            (let [collum-index (rem p size)
                  diagonal-to-top-left-pos     (filter #(< (rem % size) collum-index) (range p -1            (* (+ size 1) -1)))
                  diagonal-to-top-right-pos    (filter #(> (rem % size) collum-index) (range p -1            (* (- size 1) -1)))
                  diagonal-to-bottom-left-pos  (filter #(< (rem % size) collum-index) (range p (* size size)    (- size 1)   ))
                  diagonal-to-bottom-right-pos (filter #(> (rem % size) collum-index) (range p (* size size)    (+ size 1)   ))]
              (< (+ (count (filter #(= % 1) (for [i diagonal-to-top-left-pos    ] (b i))))
                    (count (filter #(= % 1) (for [i diagonal-to-top-right-pos   ] (b i))))
                    (count (filter #(= % 1) (for [i diagonal-to-bottom-left-pos ] (b i))))
                    (count (filter #(= % 1) (for [i diagonal-to-bottom-right-pos] (b i)))))
                 1)))]
    (and (only-one-queen-vertical)
      (only-one-queen-horizontal)
      (only-one-queen-diagonals))))

;; board -> boolean
;; checks if all queens in the board are not attacking another queen
(defn valid? [b size]
  (let [queen-positions (map first (filter #(= 1 (second %)) (map-indexed vector b)))]
    (every? #(valid-queen? b % size) queen-positions))) ; checks if it is valid for every queen on board

;; board -> listofBoards
;; get all valid boards which have one more queen
;; that the passed boars
(defn get-valid-boards-one-more-queen [b size]
  (let [last-queen-pos (get-last-queen b)]
    (for [x (range (inc last-queen-pos) (count b))
          :let [new-board (insert-queen x b)]
          :when (valid? new-board size)]
        (when (seq new-board)
          new-board))))

;; size n-queens -> listofBoard
;; produces the complete list size length boards where
;; n-queens of queens are in non-attacking positions
;; if no solution is possible, nil is returned
(defn solve [size n-queens]
  (letfn [(solve--listofBoards [lob]                                        ; solves list of board
            (reduce (fn [l b]
                      (let [new-board (solve--board b)]
                        (if(seq new-board)
                          (conj l (flatten (solve--board b)))
                          l)))
                    [] lob))                                                ; goes to each board and solves it at a time
          (solve--board [b]                                                 ; solves individual board
            (if (solved? b n-queens)
              b
              (solve--listofBoards (get-valid-boards-one-more-queen b size))))]  ; lets descend it to the board children
    (solve--board (make-board size))))
