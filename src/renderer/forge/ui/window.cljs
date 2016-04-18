(ns forge.ui.window
  (:import goog.dom.ViewportSizeMonitor
           goog.events.EventType)
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [om-bootstrap.input :as i]
            [goog.events :as e]))

(def vsm (ViewportSizeMonitor.))

(defn search-input
  [data owner]
  (reify
    om/IRender
    (render [_]
      (i/input
        {:feedback? true
         :type "test"
         :placeholder "What do you want to do?"}))))


(defn cards-panel
  [data owner]
  (reify
    om/IRender
    (render [_]
      (dom/div #js {:className "row"}
        (dom/div #js {:className "col-md-12"}
          (om/build search-input data)
          (dom/h3 "You're Program:")
          (dom/svg #js {:className "show-cards"
                        :width "100%"
                        :height 768}))))))

(defn panels
  [data owner]
  (reify
    om/IRender
    (render [_]
      (dom/div #js {:className "container-fluid"}
        (dom/div #js {:className "row"}
          (dom/div (clj->js {:className "col-md-6 with-border"
                             :style {:height (get-in @data [:window :height])}}) (om/build cards-panel data))
          (dom/div (clj->js {:className "col-md-6 with-border"
                             :style {:height (get-in @data [:window :height])}}) "results"))))))
