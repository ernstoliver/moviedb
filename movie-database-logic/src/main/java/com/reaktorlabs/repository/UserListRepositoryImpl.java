package com.reaktorlabs.repository;

import com.reaktorlabs.model.Movie;
import com.reaktorlabs.model.MovieComment;
import com.reaktorlabs.model.MovieRating;
import com.reaktorlabs.model.User;
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
        TypedQuery<Movie> query = manager.createQuery("SELECT m FROM Movie m JOIN m.users u WHERE u.userName = :uname",Movie.class);
        query.setParameter("uname", username);
        return query.getResultList();
    }

    @Override
    public void saveRating(MovieRating rating) {
        manager.merge(rating);
        System.out.println("completed");
    }

    @Override
    public Integer returnUserRating(User user, Movie movie) {
        TypedQuery<Integer> query = manager.createQuery("SELECT r.rating FROM MovieRating r WHERE r.movie.id = :movieid AND r.app_user.id = :userid",Integer.class);
        query.setParameter("movieid", movie.getId());
        query.setParameter("userid", user.getId());
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
            return query.getResultList().get(0);
        }
    }

    @Override
    public Double returnAvgMovieRating(Movie movie) {
        TypedQuery<Double> query = manager.createQuery("SELECT AVG(r.rating) FROM MovieRating r WHERE r.movie.id = :movieid",Double.class);
        query.setParameter("movieid", movie.getId());
        if (query.getResultList().isEmpty()) {
            return null;
        } else {
           return query.getResultList().get(0);
        }
    }

    @Override
    public void saveMovieComment(MovieComment comment) {
        manager.persist(comment);
    }

    @Override
    public List<MovieComment> returnComments(Long tmdbid) {
        TypedQuery<MovieComment> query = manager.createQuery("SELECT c FROM MovieComment c WHERE c.tmdbid = :id ORDER BY c.date DESC",MovieComment.class);
        query.setParameter("id", tmdbid);
        return query.getResultList();
    }
}
