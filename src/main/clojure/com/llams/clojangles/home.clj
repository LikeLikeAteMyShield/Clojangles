(ns com.llams.clojangles.home
  (:require [com.llams.clojangles.html-engine :refer :all])
  (:require [com.llams.clojangles.rants :refer :all])
  (:require [clj-http.client :as client])
  (:require [cheshire.core :refer :all])
  (:require [clojure.string :refer :all])
  (:require [com.llams.clojangles.search.recent-searches :as r]))

(defn home []
  (let [response (client/get "https://devrant.com/api/devrant/rants?app=3&sort=recent&range=day&limit=20&skip=0")
        json (:body response)
        rants (:rants (parse-string json true))]
    (div (css "container")
         (r/show-recent-searches)
         (apply str (map display-rant rants)))))
