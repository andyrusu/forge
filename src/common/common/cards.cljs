(ns common.cards
  (:require [common.helpers :refer [genid]]
            [goog.style :as style]
            [goog.dom :as dom]))

(def svg-tag (dom/getElementByClass "show-cards"))

(defprotocol ICardRenderer
  "Protocol to attach to cards"
  (render-card [this])
  (render-result [this]))

(defrecord ValueCard
  [id type value]
  ICardRenderer
  (render-card [this]
    (let [svg-size (style/getSize svg-tag)
          middle (/ (:width svg-size) 2)
          xpos (- middle 150)]
      (list
        [:rect {:width 300 :height 100
                :x xpos :y 0
                :style {:fill "white"
                        :stroke-width 3
                        :stroke "rgb(0,0,0)"}}]
        [:text {:x 315 :y 20
                :style {:fill "black"}} "With:"]
        [:line {:x1 310  :y1 30
                :x2 590  :y2 30
                :style {:stroke-width 2 :stroke "black"}}]
        [:text {:x 10 :y 10
                :style {:fill "red"}} (:value this)])))
  (render-result [this]
    (.log js/console this)))

(defn make-value-card
  ([val] (make-value-card val (cond
                                (integer? val) :integer
                                (string? val) :string
                                :else :ref)))
  ([val type] (ValueCard. "value" type val)))
