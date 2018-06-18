package com.reaktorlabs.search;

import com.reaktorlabs.model.Movie;

/**
 *
 * @author ernst
 */
public interface MovieSearchDetail {
    
    Movie returnMovie(String json);
    String requestBuilderId(String title);
}
