(ns forge.helpers)

(defn genid
  ([] (genid "empty"))
  ([s] (name (gensym (str s "_")))))
