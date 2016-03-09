(ns common.electron)

(def electron (js/require "electron"))

(defn get-module
  [module]
  (aget electron (name module)))
