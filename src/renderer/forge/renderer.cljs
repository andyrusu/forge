(ns forge.renderer
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [common.cards :as cards]))

(defonce app-state (atom {:cards [(cards/make-value-card 4)]
                          :window {:width 1024
                                   :height 768}}))
