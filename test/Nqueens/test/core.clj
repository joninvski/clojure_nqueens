(ns Nqueens.test.core
  (:use [Nqueens.core])
  (:use [expectations]))

(def empty-board [0 0 0 0   ;3
                  0 0 0 0   ;7
                  0 0 0 0   ;11
                  0 0 0 0]) ;15

(expect [] (solve 4 5))

(expect 92 (count (solve 8 8)))

(expect [(insert-queens [1 7 8 14] empty-board) (insert-queens [2 4 11 13] empty-board) ]
        (solve 4 4))

(expect  [1 0 0 0
          0 0 0 0
          0 0 0 0
          0 0 0 0]
        (insert-queen 0 empty-board))

(expect  [0 0 0 0
          0 0 1 0
          0 0 0 0
          0 0 0 0]
        (insert-queen 6 empty-board))

(expect  [1 0 0 1
          1 0 0 0
          0 0 0 0
          0 0 0 0]
        (insert-queens [0 4 3] empty-board))

(expect [0 0 0
         0 0 0
         0 0 0] 
        (make-board 3))

(expect true 
        (solved? (insert-queens [0 7 11 14] empty-board) 4))

(expect false
        (solved? (insert-queens [1 7] empty-board) 4))

(expect '([1 0 0 0] 
          [0 1 0 0]
          [0 0 1 0]
          [0 0 0 1])
        (get-valid-boards-one-more-queen [0 0 0 0] 2))

; Board of size 2 does not support two pieces
(expect true 
        (empty? (get-valid-boards-one-more-queen [0 1 0 0] 2)))

(expect '([1 0 0 0
           0 0 1 0
           0 0 0 0
           0 0 0 0]
          [1 0 0 0
           0 0 0 1
           0 0 0 0
           0 0 0 0]
          [1 0 0 0
           0 0 0 0
           0 1 0 0
           0 0 0 0]
          [1 0 0 0
           0 0 0 0
           0 0 0 1
           0 0 0 0]
          [1 0 0 0
           0 0 0 0
           0 0 0 0
           0 1 0 0]
          [1 0 0 0
           0 0 0 0
           0 0 0 0
           0 0 1 0])
        (get-valid-boards-one-more-queen [1 0 0 0
                                          0 0 0 0
                                          0 0 0 0
                                          0 0 0 0] 4))

(expect 13
        (get-last-queen (insert-queens [1 13] empty-board)))

(expect 7
        (get-last-queen (insert-queens [1 7] empty-board)))

(expect 0
        (get-last-queen (insert-queens [0] empty-board)))

(expect -1
        (get-last-queen empty-board))

(expect true 
        (valid? (insert-queens [1 7 8 14] empty-board) 4))

(expect false
        (valid? (insert-queens [0 1] empty-board) 4))

(expect false
        (valid? (insert-queens [1 5] empty-board) 4))

(expect false
        (valid? (insert-queens [0 10] empty-board) 4))

; Only a queen
(expect true
        (valid-queen? (insert-queens [3] empty-board) 3 4))

; Two queens in the same row
(expect false
        (valid-queen? (insert-queens [1 2] empty-board) 1 4))

; Two queens in the same collumn
(expect false
        (valid-queen? (insert-queens [1 5] empty-board) 1 4))

; Two queens in the same diagonal
(expect false
        (valid-queen? (insert-queens [1 6] empty-board) 1 4))


