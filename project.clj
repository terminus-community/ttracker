(defproject ttracker "0.0.1"
  :description "A time tracker app"
  :url "https://github.com/terminus-community/ttracker"
  :license {:name  "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.6.2"]
                 [ring/ring-json "0.5.1"]
                 [org.immutant/immutant "2.1.10"]
                 [compojure "1.6.0"]]
  :plugins [
            [lein-modules "0.3.11"]]
  :main ttracker.server)
