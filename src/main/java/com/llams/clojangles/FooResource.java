package com.llams.clojangles;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/foo")
@Produces(MediaType.TEXT_HTML)
public class FooResource {

    @GET
    public String getFoo() {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("com.llams.clojangles.main"));

        Object foo = Clojure.var("com.llams.clojangles.main", "foo").invoke();

        return (String)foo;
    }
}
