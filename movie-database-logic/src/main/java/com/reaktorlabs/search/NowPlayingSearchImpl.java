package com.reaktorlabs.search;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reaktorlabs.model.Movie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ernst
 */
public class NowPlayingSearchImpl implements NowPlayingSearch {
    
    private HttpRequestService request;

    public NowPlayingSearchImpl() {

    }

    @Inject
    public NowPlayingSearchImpl(HttpRequestService request) {
        this.request = request;
    }

    @Override
    public String requestBuilder() {
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=65f96aec07e9d9e9fdb4a47f6b66e112&language=en-US&page=1";
        return request.searchInMovie(url);
    }

    @Override
    public List<Movie> returnMovies(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            JsonNode jsonNodeRoot = objectMapper.readTree(json);
            List<Movie> movies = new ArrayList<>();
            Iterator<JsonNode> iterator = jsonNodeRoot.get("results").iterator();
            while (iterator.hasNext()) {
                JsonNode next = iterator.next();
                Movie movie = new Movie();
                movie.setTitle(next.get("title").asText());
                movie.setPoster_path(next.get("poster_path").asText());
                movie.setRelease_date(next.get("release_date").asText());
                movies.add(movie);
            }
            return movies;
        } catch (IOException ex) {
            return null;
        }
    }
}
