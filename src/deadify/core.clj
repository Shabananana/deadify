(ns deadify.core
  (:gen-class)
  (:import java.io.File)
  (:import java.io.FileNotFoundException))


(def view-root-directory "./test/deadify/test-assets/Views")
(def css-root-directory "./test/deadify/test-assets/stylesheets")

(defn get-files [x]
  (file-seq (File. x))
  (doseq [file (file-seq (File. x))]
    (println (clojure.string/replace file #"[\\]" #(str %1 %1)))))

(defn parse-css [x]
  )

(defn read-file-by-line [x]
  (with-open [rdr (java.io.BufferedReader. (java.io.FileReader. x))]
  (let [seq (line-seq rdr)]
    (count seq))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; work around dangerous default behaviour in Clojure
  (alter-var-root #'*read-eval* (constantly false))
  (get-files css-root-directory)
  (get-files view-root-directory))
