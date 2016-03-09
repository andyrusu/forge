(ns forge.main
  (:require [forge.window.main :as mw]
            [common.electron :refer [get-module]]))

(def app (get-module :app))

(.on app "window-all-closed" (fn []
                              (if (not (= "darwin" (.-platform js/process)))
                                (.quit app))))

(.on app "ready" mw/init)

(set! *main-cli-fn* (fn [] (.log js/console "Hello from main process.")))
