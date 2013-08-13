clojure_nqueens
===============

Solver for the n queens problem in clojure

# Nqueens

 This message contains the instructions for Project 2. All the instructions are in this announcement - read it carefully. There is no starter file. At a later time we will post the self-assessment rubric for Project 2. Expect the rubric to be similar in spirit to the one from Project 1 -- well structured code matters a lot to us in this course!

 This project involves the design of a program to solve the 4 queens puzzle. The key to solving this problem is to follow the recipes! It is a challenging problem, but if you understand how the recipes lead to the design of the Sudoku solver then you can follow the recipes to get to the design for this program. This problem is also easier than Sudoku. In particular, while you will end up needing to design a function like valid-board?, that function is much simpler for the queens problem.

 The four queens problem consists of finding a way to place four chess queens on a 4 by 4 chess board while making sure that none of the queens attack each other. The four queens puzzle is one version of the more general n queens problem of placing n queens on an n by n board. Here's what you need to know about the board and queens to solve the four queens problem:

 - The BOARD consists of 16 individual SQUARES arranged in 4 rows of 4 columns. The colour of the squares does not matter. Each square can either be empty or can contain a queen.
 - A POSITION on the board refers to a specific square.
 - A queen ATTACKS every square in its row, its column, and both of its diagonals.
 - A board is VALID if none of the queens placed on it attack each other.
 - A valid board is SOLVED if it contains 4 queens.

 There are many strategies for solving queens, but you should use the following:

 - Use a backtracking generative search that is trying to add 1 queen at a time to the board. So at each step you generate the next boards by finding all the empty squares, adding a queen to each empty square and discarding invalid boards. If the search reaches a valid board with 4 queens (a solved board) produce that result. So its A LOT like the Sudoku solver.
 - You should design a function that consumes a board - which will initially be empty - and tries to find a solution. Call your function queens.

NOTE 1: You can tell whether two queens are on the same diagonal by comparing the slope of the line between them. If one queen is at row and column (r1, c1) and another queen is at row and column (r2, c2) then the slope of the line between them is: (/ (- r2 r1) (- c2 c1)). If that slope is 1 or -1 then the queens are on the same diagonal.

NOTE 2: We have described the problem as working for 4 queens. Feel free to make your design have a constant, SIZE, that controls how many queens it works for. You can also just make your function work for any board, but that will slightly complicate some of your functions.

A more detailed rubric will be published when the self-assessment period begins, but suffice to say that it will be substantially similar to the rubric for Project 1. Significant weight will be put on following the recipes and producing a program that is easy to read. Less weight will be put on final correctness. Remember, a program that is partially complete and is easy to read is easy to get help with. But a program that almost works and is hard to read is hard to get help with!

## Usage

FIXME: write

### Heroku

   $ heroku apps:create
   $ git push heroku master


## License

Copyright (C) 2013 FIXME

Distributed under the Apache License
