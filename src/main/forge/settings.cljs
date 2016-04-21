(ns forge.settings)

(def ide-settings (atom {}))

(def sql (.verbose (js/require "sqlite3")))

(print (.-Database sql))

;(def db (new (.-Database sql) ":memory:"))
