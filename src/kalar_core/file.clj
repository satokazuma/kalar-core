(ns kalar-core.file
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [kalar-core.config :as config]))

(defn find-resources-dir []
  "return the absolute path of resources"
  (.getAbsolutePath (io/file "resources")))

(defn find-dest []
  "returns a build destination as string."
  (let [f (io/file (find-resources-dir) (:dest (config/read-config)))]
    (.getAbsolutePath f)))

(defn touch [maybe-unexist-file]
  "Refactoring: rename to create-empty-file"
  (.mkdirs (.getParentFile maybe-unexist-file))
  (.createNewFile maybe-unexist-file))


(defn get-dst [file-path]
  "return java.io.File"
  (io/file (find-dest) (str/replace file-path (re-pattern (str "^" (find-resources-dir) "/")) "")))