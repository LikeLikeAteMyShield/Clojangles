(ns com.llams.clojangles.home
  (:require [com.llams.clojangles.html-engine :refer :all])
  (:require [clj-http.client :as client])
  (:require [cheshire.core :refer :all])
  (:require [clojure.string :refer :all]))

(defn type-badge [type]
  (span (css "badge" "badge-pill" "badge-secondary") (get-in type [:type :name])))

(defn type-badges [types]
  (apply str (map type-badge types)))

(defn display-pokemon-card [pokedex-number]
  (let [response (client/get (str "http://pokeapi.co/api/v2/pokemon/" pokedex-number))
        json (get response :body)
        pokemon (parse-string json true)
        sprites (get pokemon :sprites)]
    (div (attributes (css "card") (attribute "style" "width: 18rem;"))
         (div (css "card-header")
              (img (css "card-img-top") {:src (get sprites :front_default) :alt "sprite"}))
         (div (css "card-body")
              (div (css "row")
                   (div (css "col-md-8")
                        (a "bar" (h 5 (capitalize (get pokemon :name)))))
                   (div (css "col-md-4")
                        (h 5 (str "#" (get pokemon :id)))))
              (type-badges (get pokemon :types))))))

(defn home []
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
                     (display-pokemon-card 1))
                (div (css "col-md-4")
                     (display-pokemon-card 2))
                (div (css "col-md-4")
                     (display-pokemon-card 3)))
           (div (css "row" "mt-4")
                (div (css "col-md-4")
                     (display-pokemon-card 4))
                (div (css "col-md-4")
                     (display-pokemon-card 5))
                (div (css "col-md-4")
                     (display-pokemon-card 6)))
           (div (css "row" "mt-4")
                (div (css "col-md-4")
                     (display-pokemon-card 7))
                (div (css "col-md-4")
                     (display-pokemon-card 8))
                (div (css "col-md-4")
                     (display-pokemon-card 9)))))))
