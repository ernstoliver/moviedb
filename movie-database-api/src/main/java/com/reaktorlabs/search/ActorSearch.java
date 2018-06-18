package com.reaktorlabs.search;

import com.reaktorlabs.model.Actor;
import java.util.List;

/**
 *
 * @author ernst
 */
public interface ActorSearch {
    
    String requestBuilder(String tmdbId);
    List<Actor> returnActors(String json);
    
}
