package com.reaktorlabs.comparator;

import com.reaktorlabs.model.Movie;
import java.util.Comparator;

/**
 *
 * @author ernst
 */
public class FilmTitleComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getTitle().compareToIgnoreCase(o2.getTitle());
    }
    
}
