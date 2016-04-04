(ns forge.renderer
  (:require [forge.ui.window :as w]
            [common.cards :as cards]
            [reagent.core :as r]))

(def window (r/atom {:cards [(cards/make-value-card 4)]}))

(defn main
  []
  (w/init-gui window))

(main)
