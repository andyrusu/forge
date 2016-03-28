(ns common.helpers)

(defn genid
  ([] (genid "empty"))
  ([s] (name (gensym (str s "_")))))
