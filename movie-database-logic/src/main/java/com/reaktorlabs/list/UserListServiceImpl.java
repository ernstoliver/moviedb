package com.reaktorlabs.list;

import com.reaktorlabs.comparator.FilmTitleComparator;
import com.reaktorlabs.model.Movie;
import com.reaktorlabs.model.User;
import com.reaktorlabs.repository.UserListRepository;
import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ernst
 */
@Stateless
public class UserListServiceImpl implements UserListService {
    
    private UserListRepository repository;
    
    public UserListServiceImpl() {
        
    }
    
    @Inject
    public UserListServiceImpl(UserListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addMovieToFavList(Movie movie,String username) {
        //save movie object and add to existing user object's list
        //decide whether the movie is present in db or not
        User newUser = repository.getUserFromDatabase(username);
        if (!repository.getMovieFromDatabase(movie.getTmdbId()).isEmpty()) {
            Movie moviedb = repository.getMovieFromDatabase(movie.getTmdbId()).get(0);
            moviedb.getUsers().add(newUser);
            newUser.getMovies().add(moviedb);
        } else {
            Movie newMovie = movie;
            newMovie.getUsers().add(newUser);
            newUser.getMovies().add(newMovie);
            repository.addMovieToList(newMovie);
        }
    }

    @Override
    public boolean checkFilmStatus(Movie movie,String username) {
        boolean isPresent = true;
        if (!repository.checkStatus(movie,username).isEmpty()) {
            addMovieToFavList(movie, username);
            isPresent = false;
        }
        return isPresent;
    }

    @Override
    public List<Movie> loadUserFavs(String username,String sortType) {
        List<Movie> list = repository.loadUserFavourites(username);
        if (sortType.equals("byTitle")) {
            FilmTitleComparator titleComparator = new FilmTitleComparator();
            Collections.sort(list,titleComparator);
        }
        return list;
    }

    @Override
    public void removeFromFavs(String username, Long tmdbid) {
        Movie movieToRemoveFrom = repository.getMovieFromDatabase(tmdbid).get(0);
        User userToRemoveFrom = repository.getUserFromDatabase(username);
        movieToRemoveFrom.getUsers().remove(userToRemoveFrom);
        userToRemoveFrom.getMovies().remove(movieToRemoveFrom);
        System.out.println("remove success, movieid: " + tmdbid);
    }
    
}
