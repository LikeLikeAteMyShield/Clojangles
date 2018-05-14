(ns com.llams.clojangles.rants
  (:require [com.llams.clojangles.html-engine :refer :all]))

(defn- tag-link [tag]
  (a (css "card-link" "text-muted") (str "search?term=" tag) tag))

(defn display-rant [rant]
  (div (css "card" "bg-light" "mt-2" "mb-2")
       (div (css "card-body")
            (h 5 (css "card-title") (:user_username rant))
            (p (css "card-text") (:text rant))
            (p (css "text-success") (strong (str "++" (:score rant)))))
       (div (css "card-footer")
            (apply str (map tag-link (:tags rant))))))
