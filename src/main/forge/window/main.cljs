(ns forge.window.main
  (:require [common.electron :refer [get-module]]))

(def ipc-main (get-module "ipcMain"))
(def browser-win (get-module "BrowserWindow"))
(def main-win (atom nil))

(defn win-size-ipc
  [event _]
  (aset event "returnValue" (.getContentSize @main-win)))

(defn init
  []
  (reset! main-win (browser-win. #js {:width 1024 :height 768}))
  (.maximize @main-win)
  (.loadURL @main-win "http://localhost:3449/index.html")
  (->
    @main-win
    .-webContents
    .openDevTools)
  (.on ipc-main "get-window-size" win-size-ipc)
  (.on @main-win "closed" (fn [] (reset! main-win nil))))
