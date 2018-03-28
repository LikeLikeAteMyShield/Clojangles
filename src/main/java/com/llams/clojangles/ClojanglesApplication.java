package com.llams.clojangles;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class ClojanglesApplication extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new ClojanglesApplication().run(args);
    }

    public String getName() {
        return "Clojangles";
    }

    public void run(Configuration configuration, Environment environment) {
        final FooResource resource = new FooResource();
        environment.jersey().register(resource);
    }
}
