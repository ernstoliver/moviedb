package com.reaktorlabs.auth;

import com.reaktorlabs.model.User;
import com.reaktorlabs.util.HashUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ernst
 */
@Stateless
public class AuthService implements Authentication {
    
    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());
    
    private UserRepository repository;
    
    private HttpServletRequest servletRequest;
    
    public AuthService() {
        
    }
    
    @Inject
    public AuthService(UserRepository repository,HttpServletRequest servletRequest) {
        this.repository = repository;
        this.servletRequest = servletRequest;
    }

    @Override
    public String login(User user) {
        try {
            servletRequest.getSession();
            servletRequest.login(user.getUserName(), user.getPassword());
            return "ok";
        } catch (ServletException e) {
            LOGGER.warning(e.getMessage());
            return "error";
        }
    }

    @Override
    public void logout() {
         try {
            servletRequest.getSession();
            servletRequest.logout();
        } catch (ServletException e) {
            LOGGER.warning(e.getMessage());
        }
    }

    @Override
    public void register(User user) {
        if (repository.findUserByName(user.getUserName()).isEmpty()) {
            final UserRole defaultRole = new UserRole();
            defaultRole.setName("user");
            defaultRole.setUser(user);
            registerUserWithRole(user, defaultRole);
        } else {
            //tell user that this user exists
            throw new IllegalStateException("User already exist: " + user.getUserName());
        }
    }

    @Override
    public void registerUserWithRole(User user, UserRole role) {
        //binding UserRole to User
        final User newUser = new User();
        final String password = user.getPassword();
        final String encryptedPassword = HashUtil.encryptSHA512(password);
        newUser.setUserName(user.getUserName());
        newUser.setPassword(encryptedPassword);
        final List<UserRole> roles = new ArrayList<>();
        roles.add(role);
        newUser.setRoles(roles);
        repository.saveUser(newUser);
    }

    @Override
    public List<User> findUserInDatabase(String username) {
        return repository.findUserByName(username);
    }
    
    
}
