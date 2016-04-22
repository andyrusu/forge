(ns forge.main
  (:require [om.core :as om]
            [forge.ui.window :as w]
            [forge.cards :as cards]))

(.. js/nw -Window get showDevTools)

(defonce app-state (atom {:cards [(cards/make-value-card 4)]
                          :window {:width 1024
                                   :height 768}}))

(om/root
  w/panels
  app-state
  {:target (. js/document (getElementById "window"))})
