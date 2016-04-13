(ns forge.test-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [forge.main-test]))

(doo-tests 'forge.main-test)
