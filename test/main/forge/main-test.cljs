(ns forge.main-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [forge.main :as m]))

(deftest test-numbers
  (is (= 1 1)))
