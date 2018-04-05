(ns com.llams.clojangles.html-engine
  (:require [clojure.string :refer :all]))

(defn attribute [key value]
  (str key "=\"" value "\""))

(defn attributes [& attrs]
  (join " " attrs))

(defn element
  ([tag attrs content]
   (str "<" tag (if (not (nil? attrs)) (str " " (attributes attrs))) ">" content "</" tag ">"))
  ([tag attrs]
   (str "<" tag (if (not (nil? attrs)) (str " " (attributes attrs))) ">")))

(defn html [& contents]
  (element "html" nil (join "" contents)))

(defn head [& contents]
  (element "head" nil (join "" contents)))

(defn link [{rel :rel resource-type :type resource-href :href}]
  (element "link"
           (attributes
             (attribute "rel" rel)
             (attribute "type" resource-type)
             (attribute "href" resource-href))))

(defn stylesheet [href]
  (link {:rel "stylesheet" :type "text/css" :href href}))

(defn script [src]
  (element "script" (attribute "src" src) nil))

(defn body [& contents]
  (element "body" nil (join "" contents)))

(defn div [attrs & contents]
  (element "div" attrs (join "" contents)))

(defn span
  ([attrs content] (element "span" attrs content))
  ([content] (element "span" nil content)))

(defn id [value]
  (attribute "id" value))

(defn css [& classes]
  (attribute "class" (join " " classes)))

(defn h
  ([size attrs content] (element (str "h" size) attrs content))
  ([size content] (element (str "h" size) nil content)))

(defn p
  ([attrs content] (element "p" attrs content))
  ([content] (element "p" nil content)))

(defn strong
  ([attrs content] (element "strong" attrs content))
  ([content] (element "strong" nil content)))

(defn em
  ([attrs content] (element "em" attrs content))
  ([content] (element "em" nil content)))

(defn- li [content]
  (element "li" nil content))

(defn ul [& items]
  (element "ul" nil (join "" (map #(li %) items))))

(defn ol [& items]
  (element "ol" nil (join "" (map #(li %) items))))

(defn img [attrs {src :src alt :alt}]
  (element "img" (attributes attrs (attribute "src" src) (attribute "alt" alt))))

(defn a
  ([attrs href content] (element "a" (attributes attrs (attribute "href" href)) content))
  ([href content] (element "a" (attribute "href" href) content)))

(defn nav [attrs content]
  (element "nav" attrs content))
