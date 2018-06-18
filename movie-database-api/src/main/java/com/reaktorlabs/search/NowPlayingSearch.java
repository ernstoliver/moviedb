package com.reaktorlabs.search;

import com.reaktorlabs.model.Movie;
import java.util.List;

/**
 *
 * @author ernst
 */
public interface NowPlayingSearch {
    
    String requestBuilder();
    List<Movie> returnMovies(String json);
}
