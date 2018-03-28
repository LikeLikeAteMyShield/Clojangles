(ns com.llams.clojangles.main
  (:require [com.llams.clojangles.html-engine :refer :all]))

(defn foo []
  (html
    (head
      (stylesheet "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"))
    (body
      )))
