Clojure NQueens
===============

[![Build Status](https://travis-ci.org/joninvski/clojure_nqueens.png?branch=master)](https://travis-ci.org/joninvski/clojure_nqueens)

Solver for the n queens problem in clojure.

## Running

    lein uberjar && java -jar nqueens-standalone.jar

To see the options do:

    java -jar nqueens-standalone.jar --help

## Distributing

    lein uberjar

### Testing

   $ lein autoexpect

### Heroku

   $ heroku apps:create
   $ git push heroku master

## License

Copyright (C) 2013 Joao Trindade

Distributed under the Apache License
