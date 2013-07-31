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

;; size n_queens -> listofBoard
;; produces the complete list size length boards where 
;; n_queens of queens are in non-attacking positions
;; if no solution is possible, false is returned
(defn solve [size n_queens]
  (let [solve--listofBoards (fn [lob] nil)
        solve--board (fn [b] nil)]
    
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


