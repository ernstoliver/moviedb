package com.reaktorlabs.search;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reaktorlabs.model.Movie;
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
public class TitleSearchProcess implements MovieSearchTitle {

    private HttpRequestService request;

    public TitleSearchProcess() {

    }

    @Inject
    public TitleSearchProcess(HttpRequestService request) {
        this.request = request;
    }

    @Override
    public String requestBuilderTitle(String title) {
        final StringBuilder builder = new StringBuilder();
        if (title.contains(" ")) {
            title = title.replaceAll(" ", "%20");
        }
        String url = builder.append("https://api.themoviedb.org/3/search/movie?api_key=65f96aec07e9d9e9fdb4a47f6b66e112&language=en-US&query=")
                .append(title).append("&page=1&include_adult=false").toString();
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
                movie.setOverview(next.get("overview").asText());
                movie.setRelease_date(next.get("release_date").asText());
                movie.setPoster_path(next.get("poster_path").asText());
                movie.setTmdbId(next.get("id").asLong());
                movies.add(movie);
            }
            return movies;
        } catch (IOException ex) {
            return null;
        }
    }

}
