package com.reaktorlabs.repository;

import com.reaktorlabs.model.Movie;
import com.reaktorlabs.model.MovieRating;
import com.reaktorlabs.model.User;
import java.util.List;

/**
 *
 * @author ernst
 */
public interface UserListRepository {
    
    void addMovieToList(Movie movie);
    User getUserFromDatabase(String username);
    List<Object[]> checkStatus(Movie movie,String username);
    List<Movie> getMovieFromDatabase(Long tmdbid);
    List<Movie> loadUserFavourites(String username);
    void saveRating(MovieRating rating);
    Integer returnUserRating(User user,Movie movie);
}
