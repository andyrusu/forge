(ns forge.cards
  (:require [forge.helpers :refer [genid]]
            [goog.style :as style]
            [goog.dom :as dom]))

(defprotocol ICardRenderer
  "Protocol to attach to cards"
  (render-card [this width on-change-fn])
  (render-result [this]))

(defrecord ValueCard
  [id type value]
  ICardRenderer
  (render-card [this width on-change-fn]
    (let [middle   (/ width 2)
          x-start  (- middle 150)
          x-end    (+ middle 150)
          c-width  (:width this)
          c-height (:height this)]
      (list
        [:rect {:width c-width :height c-height
                :x x-start :y 0
                :style {:fill "white"
                        :stroke-width 3
                        :stroke "rgb(0,0,0)"}}]
        [:text {:x (+ x-start 15) :y 20
                :style {:fill "black"}} "With:"]
        [:line {:x1 (+ x-start 10)  :y1 30
                :x2 (- x-end 10)  :y2 30
                :style {:stroke-width 2 :stroke "black"}}]
        [:foreignObject {:x (+ x-start 10) :y 40}
          [:input {:id (:id this)
                   :type "text"
                   :defaultValue (:value this)
                   :onChange on-change-fn}]])))
  (render-result [this]
    (.log js/console this)))

(defn make-value-card
  ([val] (make-value-card val (cond
                                (integer? val) :integer
                                (string? val) :string
                                :else :ref)))
  ([val type] (ValueCard. (genid "value") type val)))
