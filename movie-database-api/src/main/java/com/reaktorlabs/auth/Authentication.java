package com.reaktorlabs.auth;

import com.reaktorlabs.model.User;
import java.util.List;

/**
 *
 * @author ernst
 */
public interface Authentication {
    
    String login(User user);
    void logout();
    void register(User user);
    void registerUserWithRole(User user, UserRole role);
    List<User> findUserInDatabase(String username);
}
