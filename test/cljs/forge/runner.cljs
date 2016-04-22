(ns forge.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [forge.helpers-test]))

(doo-tests 'forge.helpers-test)
