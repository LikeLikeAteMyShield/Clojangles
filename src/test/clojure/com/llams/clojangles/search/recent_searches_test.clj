(ns com.llams.clojangles.search.recent-searches-test
  (:require [clojure.test :refer :all])
  (:require [com.llams.clojangles.search.recent-searches :refer :all])
  (:require [clj-time.core :as time])
  (:require [clj-time.coerce :as c])
  (:require [com.llams.clojangles.html-engine :refer :all]))

(deftest given-no-searches-nothing-should-be-returned
  (let [f (fn [] (vector))]
    (is (= [] (get-recent-searches f)))))

(deftest given-one-search-it-should-be-returned
  (let [f (fn [] [{:tag "rant" :timestamp (time/now)}])]
    (is (= ["rant"] (get-recent-searches f)))))

(deftest given-more-than-5-searches-the-5-most-recent-should-be-returned
  (let [f (fn [] [{:tag "test1" :timestamp (c/to-long (time/date-time 2018 1 1 12 0 0))}
                  {:tag "test2" :timestamp (c/to-long (time/date-time 2018 1 1 12 5 0))}
                  {:tag "test3" :timestamp (c/to-long (time/date-time 2018 1 1 12 2 0))}
                  {:tag "test4" :timestamp (c/to-long (time/date-time 2018 1 1 12 27 0))}
                  {:tag "test5" :timestamp (c/to-long (time/date-time 2018 1 1 12 50 0))}
                  {:tag "test6" :timestamp (c/to-long (time/date-time 2018 1 1 12 34 0))}
                  {:tag "test7" :timestamp (c/to-long (time/date-time 2018 1 1 12 6 0))}])]
    (is (= ["test5" "test6" "test4" "test7" "test2"] (get-recent-searches f)))))
