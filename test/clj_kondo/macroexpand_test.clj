(ns clj-kondo.macroexpand-test
  (:require
   [clj-kondo.test-utils :refer [lint! assert-submaps]]
   [clojure.java.io :as io]
   [clojure.test :refer [deftest testing is]]))

(deftest macroexpand-test
  (assert-submaps
   '({:file "corpus/macroexpand.clj", :row 16, :col 7, :level :error, :message "Expected: number, received: keyword."}
     {:file "corpus/macroexpand.clj", :row 18, :col 1, :level :error, :message "No sym and val provided [at line 4, column 7]"}
     {:file "corpus/macroexpand.clj", :row 29, :col 48, :level :warning, :message "unused binding tree"}
     {:file "corpus/macroexpand.clj", :row 37, :col 1, :level :warning, :message "Missing catch or finally in try"}
     {:file "corpus/macroexpand.clj", :row 69, :col 20, :level :error, :message "Expected: string, received: number."})
   (lint! (io/file "corpus" "macroexpand.clj")
          {:linters {:unused-binding {:level :warning}
                     :type-mismatch {:level :error}}})))
