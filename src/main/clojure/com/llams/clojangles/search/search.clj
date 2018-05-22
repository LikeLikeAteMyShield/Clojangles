(ns com.llams.clojangles.search.search
  (:require [com.llams.clojangles.html-engine :refer :all])
  (:require [com.llams.clojangles.rants :refer :all])
  (:require [clj-http.client :as client])
  (:require [cheshire.core :refer :all])
  (:require [clojure.string :refer :all])
  (:require [com.llams.clojangles.search.recent-searches :as r]))

(defn display-search-results [term]
  (let [response (client/get (str "https://devrant.com/api/devrant/search?app=3&term=" term))
        json (:body response)
        results (parse-string json true)
        rants (:results results)]
    (str
      (div (css "jumbotron")
           (div (css "container")
                (h 1 term)))
      (div (css "container")
           (r/show-recent-searches)
           (apply str (map display-rant rants))))))
