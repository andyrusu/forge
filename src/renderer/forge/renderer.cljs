(ns forge.renderer
  (:require [forge.ui.window :as w]
            [common.cards :as cards]))

(swap! w/window assoc :cards [(cards/make-value-card 4)])

(defn main
  []
  (w/init-gui))

(main)
