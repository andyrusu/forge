(ns forge.ui.window
  (:requie [om.core :as om :include-macros true]
           [om.dom :as dom :include-macros true]))

(def panels
  [data owner]
  (reify
    om/IRender
    (render [this]
      (dom/div #js {:class "container-fluid"}
        (dom/div #js {:class "row"})))

    [:div.container-fluid
      [:div.row
        [:div.col-md-6.with-border {:style {:height (:height @window)}} (cards-panel window)]
        [:div.col-md-6.with-border {:style {:height (:height @window)}} "result"]]]))
