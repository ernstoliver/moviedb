package com.reaktorlabs.search;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reaktorlabs.model.Actor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ernst
 */
@Stateless
public class ActorSearchImpl implements ActorSearch {
    
    private HttpRequestService requestService;
    
    public ActorSearchImpl() {
        
    }
    
    @Inject
    public ActorSearchImpl(HttpRequestService service) {
        this.requestService = service;
    }

    @Override
    public String requestBuilder(String tmdbId) {
        final StringBuilder builder = new StringBuilder();
        String url = builder.append("https://api.themoviedb.org/3/movie/")
                .append(tmdbId).append("/credits?api_key=65f96aec07e9d9e9fdb4a47f6b66e112").toString();
        return requestService.searchInMovie(url);
    }

    @Override
    public List<Actor> returnActors(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //character,name,profile_path
            
            JsonNode jsonNodeRoot = objectMapper.readTree(json);
            List<Actor> actorList = new ArrayList<>();
            Iterator<JsonNode> iterator = jsonNodeRoot.get("cast").iterator();
            while (iterator.hasNext()) {
                JsonNode next = iterator.next();
                Actor actor = new Actor();
                actor.setCharacter(next.get("character").asText());
                actor.setName(next.get("name").asText());
                actor.setProfile_pic(next.get("profile_path").asText());
                actorList.add(actor);
            }
            return actorList;
        } catch (IOException ex) {
            return null;
        }
    }
    
    
}
