(ns com.llams.clojangles.pokemon
  (:require [com.llams.clojangles.html-engine :refer :all])
  (:require [clj-http.client :as client])
  (:require [cheshire.core :refer :all])
  (:require [clojure.string :refer :all]))

(defn display-pokemon [id]
  (let [response (client/get (str "http://pokeapi.co/api/v2/pokemon/" id))
        json (get response :body)
        pokemon (parse-string json true)
        sprites (get pokemon :sprites)]
    (html
      (head
        (stylesheet "https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"))
      (body
        (nav (css "navbar" "navbar-dark" "bg-dark")
             (div (css "container")
                  (a (css "navbar-brand") "/application/home" "Pokedex")))
        (div (css "jumbotron")
             (div (css "container")
                  (h 1 (str (img (attribute "style" "width: 18rem;") {:src (get sprites :front_default) :alt "sprite"}) (strong (capitalize (:name pokemon)))))))))))
