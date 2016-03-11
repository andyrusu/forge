(ns forge.renderer
  (:require [reagent.core :as r]
            [goog.dom :as d]))

(def empty-board [[{:size 100 :x 0   :y 0 :mark nil} {:size 100 :x 0   :y 100 :mark nil} {:size 100 :x 0   :y 200 :mark nil}]
                  [{:size 100 :x 100 :y 0 :mark nil} {:size 100 :x 100 :y 100 :mark nil} {:size 100 :x 100 :y 200 :mark nil}]
                  [{:size 100 :x 200 :y 0 :mark nil} {:size 100 :x 200 :y 100 :mark nil} {:size 100 :x 200 :y 200 :mark nil}]])

(def st (r/atom {:size 3
                 :turn 0
                 :players 2
                 :board empty-board}))

(defn get-key
  [categ]
  (name (gensym categ)))

(defn get-board
  [board i j]
  (get (get (:board @st) i) j))

(defn take-turn
  [x y]
  (let [turn (:turn @st)]
    (swap! st #(assoc % :turn (inc (:turn %))))
    (swap! st (fn [new-st]
                (assoc new-st :board (doall
                                      (for [i (range 0 (:size new-st))]
                                        (for [j (range 0 (:size new-st))
                                              :let [item (get-board (:board new-st) i j)]]
                                          (if (and (= (:x item) x) (= (:y item) y))
                                            (assoc item :mark (mod turn 2))
                                            item)))))))))

(defn do-circle
  [{:keys [x y]}]
  [:circle {:key (get-key "circle")
            :cx (+ x 50)
            :cy (+ y 50)
            :r 45
            :stroke "red"
            :stroke-width "2"
            :fill "white"}])

(defn do-x
  [{:keys [x y]}]
  (list
    [:line {:key (get-key "line")
            :x1 (+ x 5)
            :y1 (+ y  5)
            :x2 (+ x 95)
            :y2 (+ y 95)
            :stroke "blue"
            :stroke-width "2"}]
    [:line {:key (get-key "line")
            :x1 (+ x 5)
            :y1 (+ y 95)
            :x2 (+ x 95)
            :y2 (+ y  5)
            :stroke "blue"
            :stroke-width "2"}]))

(defn show-mark
  [mark coords]
  (cond
    (= mark 0) (do-circle coords)
    (= mark 1) (do-x coords)
    (nil? mark) nil))

(defn rect
  [{:keys [size x y mark]}]
  (list [:rect {:key (get-key "rect")
                :width size
                :height size
                :x x
                :y y
                :stroke "black"
                :stroke-width 2
                :style {:fill "white"}
                :on-click (fn [_] (take-turn x y))}]
        (show-mark mark {:x x :y y})))

(defn board
  []
  [:svg {:width 300 :height 300 :style {:border "4px solid black"}}
    (doall
      (for [i (range 0 (:size @st))]
        (for [j (range 0 (:size @st))]
          (rect (get (get (:board @st) i) j)))))])

(defn settings-form
  []
  [:form.form-inline
    [:div.form-group
      [:label {:for "game-mode"} "Game Mode"]
      [:select#game-mode {:defaultValue (:players @st)}
        [:option {:value 1} "One Player"]
        [:option {:value 2} "Two Players"]]]
    [:button {:type "button" :on-click (fn [] (swap! st #(assoc % :players (.-value (d/getElement "game-mode")) :board [])))} "Start"]])

(defn panel
  []
  [:div.container
    [:div.row
      [:div.col-md-6
        (settings-form)]]
    [:hr]
    [:div.row
      [:div.col-md-6
        [:div.board
          (board)]]]])

(defn init-game
  []
  (r/render-component [panel]
    (.getElementById js/document "window")))

(init-game)
