package com.reaktorlabs;

import com.reaktorlabs.model.Movie;
import java.io.IOException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import com.reaktorlabs.search.MovieSearchTitle;
import com.reaktorlabs.search.NowPlayingSearch;
import javax.ejb.Asynchronous;

/**
 *
 * @author ernst
 */
@Named(value = "resultListController")
@RequestScoped
public class ResultListController {
    
    private List<Movie> movies;
    private String input;
    private MovieSearchTitle searchEngine;
    private NowPlayingSearch playingSearch;
    
    public ResultListController() {
        
    }
    
    @Inject
    public ResultListController(MovieSearchTitle searchEngine,NowPlayingSearch playingSearch) {
        this.searchEngine = searchEngine;
        this.playingSearch = playingSearch;
    }
    
    public String redirect() throws IOException {
        this.movies = searchEngine.returnMovies(searchEngine.requestBuilderTitle(input));
        if (this.movies.isEmpty()) {
            return "/noresult.xhtml";
        } else {
            return "/resultpage.xhtml";
        }
    }
    
    @Asynchronous
    public List<Movie> getPopularMovies() {
        return playingSearch.returnMovies(playingSearch.requestBuilder());
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public MovieSearchTitle getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(MovieSearchTitle searchEngine) {
        this.searchEngine = searchEngine;
    }

    
}
