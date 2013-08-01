(ns Nqueens.test.core
  (:use [Nqueens.core])
  (:use [expectations]))

(def empty_board [0 0 0 0
                  0 0 0 0
                  0 0 0 0
                  0 0 0 0])

(expect nil (solve 4 5))
(expect [(insert-queens [1 7 8 14] empty_board) (insert-queens [2 4 11 13] empty_board) ]
         (solve 4 3))

(expect  [1 0 0 0
          0 0 0 0
          0 0 0 0
          0 0 0 0]
        (insert-queen 0 empty_board))

(expect  [0 0 0 0
          0 0 1 0
          0 0 0 0
          0 0 0 0]
        (insert-queen 6 empty_board))

(expect  [1 0 0 1
          1 0 0 0
          0 0 0 0
          0 0 0 0]
        (insert-queens [0 4 3] empty_board))

(expect [0 0 0
         0 0 0
         0 0 0] 
        (make-board 3))

(expect true 
        (solved? (insert-queens [1 7 11 14] empty_board) 4))

(expect false
        (solved? (insert-queens [1 7 11 10] empty_board) 4))
