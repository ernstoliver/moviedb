package com.reaktorlabs;

import com.reaktorlabs.auth.Authentication;
import com.reaktorlabs.model.User;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ernst
 */
@Named(value="userController")
@RequestScoped
public class UserController {
    
    private Authentication authentication;
    private User user = new User();
    
    public UserController() {
        
    }
    
    @Inject
    public UserController(Authentication authentication) {
        this.authentication = authentication;
    }
    
    //registration
    public String register() {
        if (authentication.findUserInDatabase(this.user.getUserName()).isEmpty()) {
            this.user.setId(1L);
            authentication.register(user);
            return "/login.xhtml";
        } else {
            addMessage("Username already exist");
            return null;
        }
    }
    
    //login button needed
    public String login(User user) {
        if ("ok".equals(authentication.login(user))) {
            return "/mainpage.xhtml";
        } else {
            return "/error.xhtml";
        }  
    }
    
    //logout button needed
    public void logout(ActionEvent actionEvent) {
        authentication.logout();
        buttonAction(actionEvent);
    }
    
    //primefaces hintbar
    public void buttonAction(ActionEvent actionEvent) {
        addMessage("Successfully logged out");
    }
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Authentication getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
