(ns forge.window.main
  (:require [common.electron :refer [get-module]]))

(def browser-win (get-module "BrowserWindow"))
(def main-win (atom nil))

(defn init
  []
  (reset! main-win (browser-win. #js {:width 800 :height 600}))
  (.loadURL @main-win "http://localhost:3449/index.html")
  (->
    @main-win
    .-webContents
    .openDevTools)
  (.on @main-win "closed" (fn [] (reset! main-win nil))))
