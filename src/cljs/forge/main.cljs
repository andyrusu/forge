(ns forge.main
  (:import goog.dom.ViewportSizeMonitor
           goog.events.EventType)
  (:require [om.core :as om]
            [forge.ui.main :as w]
            [forge.cards :as cards]
            [goog.events :as event]))

(.. js/nw -Window get showDevTools)

(defonce app-state (atom {:cards [(cards/make-value-card 4)]
                          :window {:width 1024
                                   :height 768}}))

(defn monitor-window
  [monitor state]
  (.listen event EventType.CLICK #(.log js/console %)))

(defn init
  []
  (monitor-window (ViewportSizeMonitor.) app-state))

;; Show the components
(om/root
  w/panels
  app-state
  {:target (. js/document (getElementById "window"))})
