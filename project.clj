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
            [lein-cljsbuild "1.1.2" :exclusions [[org.clojure/clojure]]]
            [lein-doo "0.1.6"]]
  :main forge.main
  :source-paths ["src/cmd"]
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  :cljsbuild {:builds
              [{:id "main-test"
                :source-paths ["src/main" "src/common" "test/main" "test/common"]
                :compiler {:output-to "resources/public/js/tests/main.test.js"
                           :output-dir "resources/public/js/tests/out/main"
                           :main forge.test-runner
                           :optimizations :none
                           :target :nodejs}}
               {:id "renderer-test"
                :source-paths ["src/renderer" "src/common" "test/renderer" "text/common"]
                :compiler {:output-to "resources/public/js/tests/main.test.js"
                           :output-dir "resources/public/js/tests/out/renderer"
                           :main forge.renderer
                           :optimizations :none}}
               {:id "main-dev"
                :source-paths ["src/main" "src/common"]
                :figwheel true
                :compiler {:main forge.main
                           :asset-path "js/compiled/out/main"
                           :output-to "resources/public/js/compiled/main.js"
                           :output-dir "resources/public/js/compiled/out/main"
                           :source-map-timestamp true
                           :target :nodejs}}
               {:id "renderer-dev"
                 :source-paths ["src/renderer" "src/common"]
                 :figwheel true
                 :compiler {:main forge.renderer
                            :asset-path "js/compiled/out/renderer"
                            :output-to "resources/public/js/compiled/renderer.js"
                            :output-dir "resources/public/js/compiled/out/renderer"
                            :source-map-timestamp true}}
               ;; This next build is an compressed minified build for
               ;; production. You can build this with:
               ;; lein cljsbuild once main-min renderer-min
               {:id "main-min"
                :source-paths ["src/main" "src/common"]
                :compiler {:output-to "resources/public/js/compiled/main.js"
                           :main forge.main
                           :optimizations :advanced
                           :pretty-print false}}
               {:id "renderer-min"
                :source-paths ["src/renderer" "src/common"]
                :compiler {:output-to "resources/public/js/compiled/renderer.js"
                           :main forge.renderer
                           :optimizations :advanced
                           :pretty-print false}}]}
  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"
             :css-dirs ["resources/public/css"]}) ;; watch and update CSS
