(ns com.llams.clojangles.search.recent-searches
  (:require [com.llams.clojangles.html-engine :refer :all]
            [clj-time.core :as time]
            [clj-time.coerce :as c]))

(defn get-recent-searches [get-searches]
  (mapv :tag (take 5 (sort-by :timestamp #(> %1 %2) (get-searches)))))

(defn- search-link [search]
  (a (css "alert-link") (str "search?term=" search) search))

(defn show-recent-searches []
  (let [f (fn [] [{:tag "node" :timestamp (c/to-long (time/date-time 2018 1 1 12 0 0))}
                  {:tag "gdpr" :timestamp (c/to-long (time/date-time 2018 1 1 12 5 0))}
                  {:tag "rant" :timestamp (c/to-long (time/date-time 2018 1 1 12 2 0))}
                  {:tag "fml" :timestamp (c/to-long (time/date-time 2018 1 1 12 27 0))}
                  {:tag "python" :timestamp (c/to-long (time/date-time 2018 1 1 12 50 0))}
                  {:tag "sql" :timestamp (c/to-long (time/date-time 2018 1 1 12 34 0))}
                  {:tag "boss" :timestamp (c/to-long (time/date-time 2018 1 1 12 6 0))}])

        searches (get-recent-searches f)]

    (div (css "alert" "alert-primary")
         (span
           (str "Recent Searches: "
                (apply str (map #(str (search-link %) ", ") (butlast searches)))
                (search-link (last searches)))))))