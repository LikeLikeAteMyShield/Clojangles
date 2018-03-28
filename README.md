# Clojangles
Basic webapp using Java/Dropwizard to serve HTML generated using Clojure. Functional Programming POC.

`mvn test` runs the Java and Clojure unit tests

`mvn package` builds the app as a fat JAR. To run locally, simply run:  
`java -jar target/clojangles-<VERSION>.jar server clojangles.yml`.   
Then go to `localhost:8090/foo` in your browser.
