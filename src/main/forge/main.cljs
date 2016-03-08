(ns forge.main)

(def electron (js/require "electron"))
(def app (.-app electron))
(def browser-win (.-BrowserWindow electron))
(def main-win (atom nil))

(.on app "window-all-closed" (fn []
                              (if (not (= "darwin" (.-platform js/process)))
                                (.quit app))))

(.on app "ready" (fn []
                    (reset! main-win (browser-win. #js {:width 800 :height 600}))
                    (.loadURL @main-win "http://localhost:3449/index.html")
                    (->
                      @main-win
                      .-webContents
                      .openDevTools)
                    (.on @main-win "closed" (fn [] (reset! main-win nil)))))

(set! *main-cli-fn* (fn [] (.log js/console "Hello from main process.")))
