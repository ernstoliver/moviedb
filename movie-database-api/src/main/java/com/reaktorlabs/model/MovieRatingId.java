package com.reaktorlabs.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ernst
 */
public class MovieRatingId implements Serializable{

    private Long movie;
    private Long app_user;

    public Long getMovie() {
        return movie;
    }

    public void setMovie(Long movie) {
        this.movie = movie;
    }

    public Long getApp_user() {
        return app_user;
    }

    public void setApp_user(Long app_user) {
        this.app_user = app_user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.movie);
        hash = 53 * hash + Objects.hashCode(this.app_user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MovieRatingId other = (MovieRatingId) obj;
        if (!Objects.equals(this.movie, other.movie)) {
            return false;
        }
        if (!Objects.equals(this.app_user, other.app_user)) {
            return false;
        }
        return true;
    }
}
