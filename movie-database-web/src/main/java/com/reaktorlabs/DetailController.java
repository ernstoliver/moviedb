package com.reaktorlabs;

import com.reaktorlabs.model.Actor;
import com.reaktorlabs.model.Movie;
import com.reaktorlabs.search.ActorSearch;
import com.reaktorlabs.search.MovieSearchDetail;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ernst
 */
@Named(value = "detailController")
@ViewScoped
public class DetailController implements Serializable {
    
    private String tmdbId;
    private Movie movie;
    private MovieSearchDetail movieSearchDetail;
    private ActorSearch actorSearch;
    private List<Actor> actors;
    
    public DetailController() {
        
    }
    
    @Inject
    public DetailController(MovieSearchDetail detail,ActorSearch search) {
        this.movieSearchDetail = detail;
        this.actorSearch = search;
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paraMap = context.getExternalContext().getRequestParameterMap();
        this.tmdbId = paraMap.get("movieId");
        this.movie = movieSearchDetail.returnMovie(movieSearchDetail.requestBuilderId(tmdbId));
        System.out.println(this.tmdbId);
    }
    
    public void getActors(String tmdbId) {
        actors = actorSearch.returnActors(actorSearch.requestBuilder(tmdbId));
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public MovieSearchDetail getMovieSearchDetail() {
        return movieSearchDetail;
    }

    public void setMovieSearchDetail(MovieSearchDetail movieSearchDetail) {
        this.movieSearchDetail = movieSearchDetail;
    }

    public ActorSearch getActorSearch() {
        return actorSearch;
    }

    public void setActorSearch(ActorSearch actorSearch) {
        this.actorSearch = actorSearch;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    
}
