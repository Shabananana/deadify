(ns deadify.core
  (:gen-class)
  (:import java.io.File)
  (:import java.io.FileNotFoundException))


(def view-root-directory "/")
(def css-root-directory "./test/deadify/test-assets/stylesheets")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (file-seq (File. "."))
  (doseq [file (file-seq (File. css-root-directory))]
    (println file)))


(defn get-files [x]
  (file-seq (File. x))
  (doseq [file (file-seq (File. x))]
    (println file)))