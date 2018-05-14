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

    private static final String PAGE = "com.llams.clojangles.page";
    private static final String HOME = "com.llams.clojangles.home";

    @GET
    public String getHomePage() {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read(PAGE));
        require.invoke(Clojure.read(HOME));

        String homePageView = (String) Clojure.var(HOME, "home").invoke();

        return (String) Clojure.var(PAGE, "render").invoke(homePageView);
    }
}
