(ns Nqueens.test.core
  (:use [Nqueens.core])
  (:use [expectations]))

(def empty_board [0 0 0 0
                  0 0 0 0
                  0 0 0 0
                  0 0 0 0])

(expect nil (solve 4 5))
(expect [(insert-queens [1 2 3 4] empty_board)]
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
         0 0 0] (make-board 3))
