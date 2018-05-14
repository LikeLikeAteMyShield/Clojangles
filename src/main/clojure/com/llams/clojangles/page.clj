(ns com.llams.clojangles.page
  (:require [com.llams.clojangles.html-engine :refer :all]))

(defn render [page]
  (html
    (head
      (element "meta" (attribute "charset" "UTF-8"))
      (stylesheet "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"))
    (body
      (nav (css "navbar" "navbar-dark" "bg-dark")
           (div (css "container")
                (a (css "navbar-brand") "/application/home" "ClojureRant")))
      (str page))))
