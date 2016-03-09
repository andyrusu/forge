(ns common.process)

(defn is-browser?
  "Check if the current context is the browser.
   Also accepts the process to check with, defaults to the global process."
  ([] (is-browser? js/process))
  ([proc] (= (.-type proc) "browser")))

(defn is-renderer?
  "Check if the current context is the renderer.
   Also accepts the process to check with, defaults to the global process."
  ([] (is-renderer? js/process))
  ([proc] (not (is-browser? proc))))

(defn get-version
  "Get the version of electron or chrome.
   Also accepts the process to check with, defaults to the global process."
  ([who] (get-version js/process who))
  ([proc who] (aget (.-versions proc) who)))

(defn on-loaded
  "Register a function for the \"loaded\" event.
   Also accepts the process on which to register the event to, defaults to the global process."
  ([func] (on-loaded js/process func))
  ([proc func] (.once proc func)))
