(ns com.llams.clojangles.pokemon
  (:require [com.llams.clojangles.html-engine :refer :all])
  (:require [clj-http.client :as client])
  (:require [cheshire.core :refer :all])
  (:require [clojure.string :refer :all]))

(defn display-pokemon [id]
  (h 3 id))
