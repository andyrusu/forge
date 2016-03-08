(ns forge.renderer
  (:require [goog.dom :as dom]))

(dom/setTextContent (dom/getElement "node-ver") "Merge in sfarsit!")
(dom/setTextContent (dom/getElement "chrome-ver") (.. js/process -versions -chrome))
(dom/setTextContent (dom/getElement "electron-ver") (.. js/process -versions -electron))
