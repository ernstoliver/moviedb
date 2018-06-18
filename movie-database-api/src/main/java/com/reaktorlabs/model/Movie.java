package com.reaktorlabs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ernst
 */
@Entity
@Table(name = "movie")
public class Movie implements Serializable {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long tmdbId;
    private String release_date;
    @Transient
    private String overview;
    private String poster_path;
    private Integer budget;
    @ElementCollection
    private List<String> genres; //MtM
    private String imdb_id;
    private Integer revenue;
    private Integer runtime; //stats
    private String tagline;
    @ElementCollection
    private List<String> images; //list of images
    @ElementCollection
    private List<String> videos; //list if videos
    //genres,viewlist,viewedlist,favourites,rating
    @ManyToMany
    @JoinTable(
        name = "user_favourite", 
        joinColumns = { @JoinColumn(name = "movie_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private List<User> users = new ArrayList<>();
    
    
    public Movie() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(Long tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    
    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", tmdbId=" + tmdbId + ", release_date=" + release_date + ", overview=" + overview + ", poster_path=" + poster_path + ", budget=" + budget + ", genres=" + genres + ", imdb_id=" + imdb_id + ", revenue=" + revenue + ", runtime=" + runtime + ", tagline=" + tagline + ", images=" + images + ", videos=" + videos + ", users=" + users + '}';
    }
    
}
