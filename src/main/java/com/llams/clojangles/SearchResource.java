package com.llams.clojangles;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/search")
@Produces(MediaType.TEXT_HTML)
public class SearchResource {

    private static final String PAGE = "com.llams.clojangles.page";
    private static final String SEARCH = "com.llams.clojangles.search";

    @GET
    public String search(@QueryParam("term") String term) {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read(PAGE));
        require.invoke(Clojure.read(SEARCH));

        String searchView = (String) Clojure.var(SEARCH, "display-search-results").invoke(term);

        return (String)Clojure.var(PAGE, "render").invoke(searchView);
    }
}
