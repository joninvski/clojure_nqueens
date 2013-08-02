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
        (solved? (insert-queens [1 7 11 14] empty_board) 3))

(expect true 
        (solved? (insert-queens [0 7 11 14] empty_board) 4))

(expect false
        (solved? (insert-queens [1 7] empty_board) 3))


(expect ([1 0 0 0] 
         [0 1 0 0]
         [0 0 1 0]
         [0 0 0 1])
        (get-valid-boards-one-more-queen [0 0 0 0]))

(expect ([0 1 1 0]
         [0 1 0 1])
        (get-valid-boards-one-more-queen [0 1 0 0]))

(expect 7
        (get-last-queen (insert-queens [1 7] empty_board)))

(expect 7
        (get-last-queen (insert-queens [0 0] empty_board)))

(expect true 
        (valid? (insert-queens [1 7 8 14] empty_board)))

(expect false
        (valid? (insert-queens [0 1] empty_board)))

(expect false
        (valid? (insert-queens [1 5] empty_board)))

(expect false
        (valid? (insert-queens [0 10] empty_board)))
