(defproject Nqueens "0.0.1-alpha"
  :description "Solver to the nqueens problem"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [expectations "1.4.49"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring/ring-jetty-adapter "1.1.6"]]

  :dev-dependencies [[lein-autoexpect "0.2.5"]
                     [lein-expectations "0.0.5"]]

  :uberjar-name "nqueens-standalone.jar"

  :min-lein-version "2.0.0")

