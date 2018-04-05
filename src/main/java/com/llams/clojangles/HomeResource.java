package com.llams.clojangles;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/home")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

    @GET
    public String getHomePage() {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("com.llams.clojangles.home"));

        Object homePageView = Clojure.var("com.llams.clojangles.home", "home").invoke();

        return (String)homePageView;
    }
}
