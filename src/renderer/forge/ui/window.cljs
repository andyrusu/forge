(ns forge.ui.window
  (:import goog.dom.ViewportSizeMonitor
           goog.events.EventType)
  (:require [reagent.core :as r]
            [goog.events :as e]
            [common.electron :as ele]
            [common.cards :as c]))

(def window (r/atom {}))
(def vsm (ViewportSizeMonitor.))

(defn search-input
  []
  [:input {:class "form-control"
           :type "text"
           :placeholder "What do you want to do?"}])

(defn cards-panel
  []
  [:div.row
    [:div.col-md-12
      (search-input)
      [:h3 "You're program:"]
      (if (and (contains? @window :cards) (not (empty? (:cards @window))))
        (let [h (:height @window)
              w (:width @window)
              c (:cards @window)]
          [:svg.show-cards {:width "100%" :height h}
            (map #(c/render-card % (/ w 2)) c)]))]])

(defn panels
  []
  [:div.container-fluid
    [:div.row
      [:div.col-md-6.with-border {:style {:height (:height @window)}} (cards-panel)]
      [:div.col-md-6.with-border {:style {:height (:height @window)}} "result"]]])

(defn set-size
  [win width height]
  (-> win
    (assoc :width width)
    (assoc :height height)))

(defn init-window
  []
  (let [ipc (ele/get-module "ipcRenderer")
        size (.sendSync ipc "get-window-size")]
    (swap! window set-size (aget size 0) (aget size 1))))

(defn init-gui
  []
  (init-window)
  (e/listen vsm
    (.-RESIZE EventType)
    #(swap! window set-size (.-width (.getSize vsm)) (.-height (.getSize vsm))))

  (r/render-component [panels]
    (.getElementById js/document "window")))
