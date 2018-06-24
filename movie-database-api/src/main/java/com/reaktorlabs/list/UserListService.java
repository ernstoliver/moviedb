package com.reaktorlabs.list;

import com.reaktorlabs.model.Movie;
import java.util.List;

/**
 *
 * @author ernst
 */
public interface UserListService {
    
    void addMovieToFavList(Movie movie,String username);
    boolean checkFilmStatus(Movie movie,String username);
    List<Movie> loadUserFavs(String username,String sortType);
    void removeFromFavs(String username,Long tmdbid);
    void saveUserRating(String username,Long tmdbid,Integer rating);
    Integer returnUserRating(String username,Long tmdbid);
}
