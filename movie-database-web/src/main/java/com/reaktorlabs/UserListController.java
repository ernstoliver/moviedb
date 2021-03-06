package com.reaktorlabs;

import com.reaktorlabs.list.UserListService;
import com.reaktorlabs.model.Movie;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ernst
 */
@Named(value = "userlistController")
@ViewScoped
public class UserListController implements Serializable {
    
    private UserListService service;
    private Movie movie;
    private List<Movie> favlist;
    private Integer rating;
    
    public UserListController() {
        
    }
    
    @Inject
    public UserListController(UserListService service) {
        this.service = service;
    }
    
    public void saveToList(Movie movie,ActionEvent actionEvent,HttpServletRequest request) {
        this.movie = movie;
        if (service.checkFilmStatus(movie,getLoggedinUserName(request))) {
            addMessage(actionEvent,"This movie is already in your list");
        } else {
            addMessage(actionEvent,"Added to your list");
        }
    }
     
    public void addMessage(ActionEvent actionEvent,String text) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, text,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void saveRating(HttpServletRequest request,Long tmdbid) {
        service.saveUserRating(getLoggedinUserName(request), tmdbid, rating);
    }
    
    public Integer returnRating(HttpServletRequest request,Long tmdbid) {
        return service.returnUserRating(getLoggedinUserName(request), tmdbid);
    }
    
    public String returnAvgRating(Long tmdbid) {
        if (service.returnAvgRating(tmdbid) != null) {
            DecimalFormat df = new DecimalFormat("#.#");
            return df.format(service.returnAvgRating(tmdbid));
        }
        return null;
    }
    
    public boolean processRequest(HttpServletRequest request) {
        return request.isUserInRole("user");
    }
    
    public String getLoggedinUserName(HttpServletRequest request) {
        return request.getUserPrincipal().getName();
    }
    
    public void loadUserFavs(HttpServletRequest request,String sortType) {
        String user = getLoggedinUserName(request);
        favlist = service.loadUserFavs(user,sortType);
    }
    
    public void removeFromFavs(HttpServletRequest request,Long tmdbid) {
        service.removeFromFavs(getLoggedinUserName(request), tmdbid);
    }

    public UserListService getService() {
        return service;
    }

    public void setService(UserListService service) {
        this.service = service;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Movie> getFavlist() {
        return favlist;
    }

    public void setFavlist(List<Movie> favlist) {
        this.favlist = favlist;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
}
