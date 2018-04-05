(ns com.llams.clojangles.html-engine-test
  (:require [clojure.test :refer :all])
  (:require [com.llams.clojangles.html-engine :refer :all]))

(deftest can-create-html-document
  (is (= (html "") "<html></html>")))

(deftest can-insert-content-to-html
  (is (= (html "foo") "<html>foo</html>")))

(deftest can-create-head
  (is (= (head "") "<head></head>")))

(deftest can-link-resources
  (is (=

        (head
          (link {:rel "stylesheet" :type "text/css" :href "main.css"}))

        (str "<head>"
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">"
             "</head>"))))

(deftest can-link-stylesheet
  (is (=

        (head
          (stylesheet "main.css"))

        (str "<head>"
             "<link rel=\"stylesheet\" type=\"text/css\" href=\"main.css\">"
             "</head>"))))

(deftest can-link-script
  (is (= (script "script.js") "<script src=\"script.js\"></script>")))

(deftest can-nest-tags
  (is (=

        (html
          (head ""))

        (str "<html>"
                "<head>"
                "</head>"
             "</html>"))))

(deftest can-create-body
  (is (= (body "") "<body></body>")))

(deftest can-create-containers
  (is (= (div nil "") "<div></div>"))
  (is (= (span "") "<span></span>")))

(deftest can-add-id
  (is (=
        (div (id "foo") "bar")
        "<div id=\"foo\">bar</div>")))

(deftest can-add-classes
  (is (=
        (div (css "container" "jumbotron") "foo")
        "<div class=\"container jumbotron\">foo</div>")))

(deftest can-add-multiple-attributes
  (is (=
        (div (attributes (id "foo") (css "bar")) "content")
        "<div id=\"foo\" class=\"bar\">content</div>")))

(deftest can-add-headings
  (is (= (h 1 "heading 1") "<h1>heading 1</h1>"))
  (is (= (h 2 (id "main-heading") "heading 2") "<h2 id=\"main-heading\">heading 2</h2>"))

  (is (=
        (h 3 (attributes (id "sub-heading") (css "heading" "emphasised")) "heading 3")
        "<h3 id=\"sub-heading\" class=\"heading emphasised\">heading 3</h3>")))

(deftest can-add-text-elements
  (is (= (p "paragraph") "<p>paragraph</p>"))
  (is (= (p (strong "strong paragraph")) "<p><strong>strong paragraph</strong></p>"))
  (is (= (p (em "emphasised paragraph")) "<p><em>emphasised paragraph</em></p>")))

(deftest can-add-unordered-list
  (is (=
        (ul
          "item 1"
          "item 2"
          "item 3")

        (str "<ul>"
              "<li>item 1</li>"
              "<li>item 2</li>"
              "<li>item 3</li>"
             "</ul>"))))

(deftest can-add-ordered-list
  (is (=
        (ol
          "item 1"
          "item 2"
          "item 3")

        (str "<ol>"
              "<li>item 1</li>"
              "<li>item 2</li>"
              "<li>item 3</li>"
             "</ol>"))))

(deftest can-add-image
  (is (=
        (img (css "style") {:src "img.png" :alt "my-image"})
        "<img class=\"style\" src=\"img.png\" alt=\"my-image\">")))

(deftest can-add-links
  (is (= (a "/foo" "foo") "<a href=\"/foo\">foo</a>")))

(deftest can-add-nav
  (is (= (nav (css "nav") "foo") "<nav class=\"nav\">foo</nav>")))
