(ns com.llams.clojangles.main
  (:require [com.llams.clojangles.html-engine :refer :all])
  (:require [clj-http.client :as client])
  (:require [cheshire.core :refer :all])
  (:require [clojure.string :refer :all]))

(defn type-badge [type]
  (span (css "badge" "badge-pill" "badge-secondary") (get-in type [:type :name])))

(defn type-badges [types]
  (apply str (map type-badge types)))

(defn display-pokemon [pokedex-number]
  (let [response (client/get (str "http://pokeapi.co/api/v2/pokemon/" pokedex-number))
        json (get response :body)
        pokemon (parse-string json true)
        sprites (get pokemon :sprites)]
    (div (attributes (css "card") (attribute "style" "width: 18rem;"))
         (div (css "card-header")
              (img (css "card-img-top") {:src (get sprites :front_default) :alt "sprite"}))
         (div (css "card-body")
              (a "bar" (h 5 (capitalize (get pokemon :name))))
              (type-badges (get pokemon :types))))))

(defn foo []
  (html
    (head
      (stylesheet "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"))
    (body
      (nav (css "navbar" "navbar-dark" "bg-dark")
           (div (css "container")
                (a (css "navbar-brand") "#" "Pokedex")))
      (div (css "container")
           (div (css "row" "mt-4")
                (div (css "col-md-4")
                     (display-pokemon 1))
                (div (css "col-md-4")
                     (display-pokemon 2))
                (div (css "col-md-4")
                     (display-pokemon 3)))
           (div (css "row" "mt-4")
                (div (css "col-md-4")
                     (display-pokemon 4))
                (div (css "col-md-4")
                     (display-pokemon 5))
                (div (css "col-md-4")
                     (display-pokemon 6)))
           (div (css "row" "mt-4")
                (div (css "col-md-4")
                     (display-pokemon 7))
                (div (css "col-md-4")
                     (display-pokemon 8))
                (div (css "col-md-4")
                     (display-pokemon 9)))))))
