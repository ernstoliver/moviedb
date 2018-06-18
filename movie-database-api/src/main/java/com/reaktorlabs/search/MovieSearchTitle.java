package com.reaktorlabs.search;

import com.reaktorlabs.model.Movie;
import java.util.List;

/**
 *
 * @author ernst
 */
public interface MovieSearchTitle {
    
    String requestBuilderTitle(String title);
    List<Movie> returnMovies(String json);
}
