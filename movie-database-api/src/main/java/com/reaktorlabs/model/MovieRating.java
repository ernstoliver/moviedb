package com.reaktorlabs.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ernst
 */
@Entity
@Table(name = "movie_rating")
@IdClass(MovieRatingId.class)
public class MovieRating implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id",referencedColumnName = "id")
    private Movie movie;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User app_user;
    
    @Column(name = "rating")
    private Integer rating;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getApp_user() {
        return app_user;
    }

    public void setApp_user(User app_user) {
        this.app_user = app_user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
}
