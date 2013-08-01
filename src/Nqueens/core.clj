(ns Nqueens.core)

; Constants:

; SIZE 4
; N_QUEENS 4

;; Data types

;; Val is Natural[0, 1]
(def Q 1)

;; Board is (listof Val|false)   that is 81 elements long
;; Pos is  Natural[0, SIZE^2[

;; size -> boards
;; makes board of size n
(defn make-board [size]
  (repeat (* size size) 0))

;; board number_queens -> boolean
;; checks if a board is solved
;; TODO
(defn solved? [b n_queens] false)

;; board -> listofBoards
;; get all valid boards which have one more queen
;; that the passed boars
;; TODO
(defn get-valid-boards-one-more-queen [b] '())

;; size n_queens -> listofBoard
;; produces the complete list size length boards where
;; n_queens of queens are in non-attacking positions
;; if no solution is possible, false is returned
(defn solve [size n_queens]
  (letfn [(solve--listofBoards [lob]                                        ; solves list of board
            (reduce (fn [l b] (conj l (solve--board b))) [] lob))           ; goes to each board and solves it at a time
          (solve--board [b]                                                 ; solves individual board
            (if (solved? b n_queens)
              b
              (solve--listofBoards (get-valid-boards-one-more-queen b))))]  ; lets descent it to the board children
    (solve--board (make-board size))))

;; pos board -> board
;; inserts a queen at the given position
(defn insert-queen [pos board]
  (concat (take pos board) '(1) (drop (inc pos) board)))

;; listofPos board -> board
;; inserts a queens at various positions passed as a list
(defn insert-queens [lop board]
  (if (list? lop)
    board
    (let [new_board (insert-queen (first lop) board)]
      (insert-queens (rest lop) new_board))))


