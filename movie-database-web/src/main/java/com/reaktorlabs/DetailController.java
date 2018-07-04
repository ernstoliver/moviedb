package com.reaktorlabs;

import com.reaktorlabs.comment.CommentService;
import com.reaktorlabs.model.Actor;
import com.reaktorlabs.model.Movie;
import com.reaktorlabs.model.MovieComment;
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
import javax.servlet.http.HttpServletRequest;

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
    private String comment;
    private String title;
    private HttpServletRequest request;
    private CommentService commentService;
    
    public DetailController() {
        
    }
    
    @Inject
    public DetailController(MovieSearchDetail detail,ActorSearch search,HttpServletRequest request,CommentService cs) {
        this.movieSearchDetail = detail;
        this.actorSearch = search;
        this.request = request;
        this.commentService = cs;
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paraMap = context.getExternalContext().getRequestParameterMap();
        tmdbId = paraMap.get("movieId");
        movie = movieSearchDetail.returnMovie(movieSearchDetail.requestBuilderId(tmdbId));
        System.out.println(tmdbId);
    }
    
    public void getActors(String tmdbId) {
        actors = actorSearch.returnActors(actorSearch.requestBuilder(tmdbId));
    }
    
    public void saveComment(String tmdbId) {
        commentService.saveComment(tmdbId, request.getUserPrincipal().getName(), comment, title);
    }
    
    public List<MovieComment> returnComments(String tmdbid) {
        return commentService.returnComments(tmdbid);
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
    
    
}
