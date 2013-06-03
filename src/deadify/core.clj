(ns deadify.core
  (:gen-class)
  (:import java.io.File)
  (:import java.io.FileNotFoundException))

(def view-root-directory "./test/deadify/test-assets/Views")
(def css-root-directory "./test/deadify/test-assets/stylesheets")

(def not-nil? (complement nil?))

(defn parse-css [x]
  ((let [selector (re-find #"[(\w+)?(\s*>\s*)?(#\w+)?\s*(\.\w+)?\s*]" x)]
    (if (not-nil? selector)
      (println (apply str selector))))))

(defn read-file-by-line [x]
    (with-open [rdr (java.io.BufferedReader. (java.io.FileReader. x))]
    (let [line (line-seq rdr)]
      (count line)
      (parse-css line))))

(defn get-files [x]
  (file-seq (File. x))
  (doseq [file (file-seq (File. x))]
    (println (clojure.string/replace file #"[\\]" #(str %1 %1)))
    (if (.isFile file)
      (read-file-by-line (clojure.string/replace file #"[\\]" #(str %1 %1)))
      (println "This is a directory."))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (get-files css-root-directory)
  (get-files view-root-directory))
