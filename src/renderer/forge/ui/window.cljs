(ns forge.ui.window
  (:import goog.dom.ViewportSizeMonitor
           goog.events.EventType)
  (:require [reagent.core :as r]
            [goog.dom :as d]
            [goog.events :as e]))

(def window (r/atom {:height (d/getDocumentHeight)}))
(def vsm (ViewportSizeMonitor.))
(e/listen vsm (.-RESIZE EventType) (fn [e] (swap! window #(assoc % :height (.-height (.getSize vsm))))))

(defn search-input
  []
  [:input {:class "form-control"
           :type "text"
           :placeholder "What do you want to do?"}])

(defn cards-panel
  []
  [:div.row
    [:div.col-md-12 (search-input)]])

(defn panels
  []
  [:div.container-fluid
    [:div.row
      [:div.col-md-6.with-border {:style {:height (:height @window)}} (cards-panel)]
      [:div.col-md-6.with-border {:style {:height (:height @window)}} "result"]]])

(defn init-gui
  []
  (r/render-component [panels]
    (.getElementById js/document "window"))))
