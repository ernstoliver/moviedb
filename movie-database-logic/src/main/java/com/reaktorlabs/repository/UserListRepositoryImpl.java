package com.reaktorlabs.repository;

import com.reaktorlabs.model.Movie;
import com.reaktorlabs.model.User;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ernst
 */
@Stateless
public class UserListRepositoryImpl implements UserListRepository {

    @PersistenceContext(unitName = "moviedb")
    private EntityManager manager;

    @Override
    public void addMovieToList(Movie movie) {
        manager.persist(movie);
        System.out.println("Persist Completed");
    }

    @Override
    public List<Object[]> checkStatus(Movie movie, String username) {
        Query query = manager.createNativeQuery("select movie.tmdbid from public.movie inner join public.user_favourite on movie.id = user_favourite.movie_id inner join public.app_user on app_user.id = user_favourite.user_id where movie.tmdbid = ? and app_user.username = ?;");
        query.setParameter(1, movie.getTmdbId());
        query.setParameter(2, username);
        return query.getResultList();
    }

    @Override
    public User getUserFromDatabase(String username) {
        TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public List<Movie> getMovieFromDatabase(Long tmdbid) {
        TypedQuery<Movie> query = manager.createQuery("SELECT m FROM Movie m WHERE m.tmdbId = :tmdb", Movie.class);
        query.setParameter("tmdb", tmdbid);
        return query.getResultList();
    }

    @Override
    public List<Movie> loadUserFavourites(String username) {
        Query query = manager.createNativeQuery("select movie.id,imdb_id,poster_path,runtime,title,tmdbid from public.movie inner join public.user_favourite on movie.id = user_favourite.movie_id inner join public.app_user on app_user.id = user_favourite.user_id where app_user.username = ?;");
        query.setParameter(1, username);
        List<Object[]> results = query.getResultList();
        List<Movie> favlist = new ArrayList<>();
        for(Object[] movie : results) {
            Movie newMovie = new Movie();
            BigInteger bigint = (BigInteger) movie[0];
            newMovie.setId(bigint.longValue());
            newMovie.setImdb_id((String) movie[1]);
            newMovie.setPoster_path((String) movie[2]);
            newMovie.setRuntime((Integer) movie[3]);
            newMovie.setTitle((String) movie[4]);
            BigInteger bigint2 = (BigInteger) movie[5];
            newMovie.setTmdbId(bigint2.longValue());
            favlist.add(newMovie);
        }
        return favlist;
    }
}
