package com.llams.clojangles;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/pokemon/{id}")
@Produces(MediaType.TEXT_HTML)
public class PokemonResource {

    @GET
    public String getPokemon(@PathParam("id") int id) {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("com.llams.clojangles.pokemon"));

        Object pokemonView = Clojure.var("com.llams.clojangles.pokemon", "display-pokemon").invoke(id);

        return (String)pokemonView;
    }
}
