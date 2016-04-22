(defproject forge "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.5.3"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/core.async "0.2.374" :exclusions [org.clojure/tools.reader]]
                 [danlentz/clj-uuid "0.1.6"]
                 [org.omcljs/om "0.9.0"]
                 [racehub/om-bootstrap "0.6.1"]
                 [ankha "0.1.4"]]
  :plugins [[lein-figwheel "0.5.0-6"]
            [lein-cljsbuild "1.1.2" :exclusions [[org.clojure/clojure]]]]
  :main forge.main
  :source-paths ["src/clj"]
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  :cljsbuild {:builds
              [{:id "renderer-dev"
                :source-paths ["src/cljs"]
                :compiler {:main forge.renderer
                           :asset-path "js/compiled/out/renderer"
                           :output-to "resources/public/js/compiled/renderer.js"
                           :output-dir "resources/public/js/compiled/out/renderer"
                           :pretty-print true
                           :source-map-timestamp true}}
               {:id "renderer-min"
                :source-paths ["src/renderer" "src/common"]
                :compiler {:output-to "resources/public/js/compiled/renderer.js"
                           :main forge.renderer
                           :optimizations :simple
                           :pretty-print false}}]}
  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"
             :css-dirs ["resources/public/css"]}) ;; watch and update CSS
