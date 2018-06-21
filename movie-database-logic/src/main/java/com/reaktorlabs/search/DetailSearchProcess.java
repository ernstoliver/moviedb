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
public class DetailSearchProcess implements MovieSearchDetail {

    public HttpRequestService request;

    public DetailSearchProcess() {

    }

    @Inject
    public DetailSearchProcess(HttpRequestService service) {
        this.request = service;
    }

    @Override
    public String requestBuilderId(String id) {
        final StringBuilder builder = new StringBuilder();
        String url = builder.append("https://api.themoviedb.org/3/movie/")
                .append(id).append("?api_key=65f96aec07e9d9e9fdb4a47f6b66e112&language=en-US&include_image_language=en,null&append_to_response=videos,images").toString();
        return request.searchInMovie(url);
    }

    @Override
    public Movie returnMovie(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            JsonNode jsonNodeRoot = objectMapper.readTree(json);
            Movie movie = new Movie();
            List<String> genres = new ArrayList();
            List<String> videos = new ArrayList();
            List<String> pictures = new ArrayList<>();
            movie.setBudget(jsonNodeRoot.get("budget").asInt());
            Iterator<JsonNode> iterator = jsonNodeRoot.get("genres").iterator();
            while (iterator.hasNext()) {
                JsonNode next = iterator.next();
                genres.add(next.get("name").asText());
            }
            movie.setTmdbId(jsonNodeRoot.get("id").asLong());
            movie.setImdb_id(jsonNodeRoot.get("imdb_id").asText());
            movie.setOverview(jsonNodeRoot.get("overview").asText());
            movie.setPoster_path(jsonNodeRoot.get("poster_path").asText());
            movie.setRelease_date(jsonNodeRoot.get("release_date").asText());
            movie.setRevenue(jsonNodeRoot.get("revenue").asInt());
            movie.setRuntime(jsonNodeRoot.get("runtime").asInt());
            movie.setTagline(jsonNodeRoot.get("tagline").asText());
            movie.setTitle(jsonNodeRoot.get("title").asText());
            JsonNode videolinks = jsonNodeRoot.get("videos");
            Iterator<JsonNode> iterator2 = videolinks.get("results").iterator();
            while (iterator2.hasNext()) {
                JsonNode next = iterator2.next();
                videos.add(next.get("key").asText());
            }
            JsonNode piclinks = jsonNodeRoot.get("images");
            Iterator<JsonNode> iterator3 = piclinks.get("posters").iterator();
            while (iterator3.hasNext()) {
                JsonNode next = iterator3.next();
                pictures.add(next.get("file_path").asText());
            }
            movie.setGenres(genres);
            movie.setVideos(videos);
            movie.setImages(pictures);
            return movie;
        } catch (IOException ex) {
            return null;
        }
    }
}
