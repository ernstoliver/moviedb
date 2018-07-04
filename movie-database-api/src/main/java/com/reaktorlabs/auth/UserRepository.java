package com.reaktorlabs.auth;

import com.reaktorlabs.model.User;
import java.util.List;

/**
 *
 * @author ernst
 */
public interface UserRepository {
    
    void saveUser(User user);
    List<User> findUserByName(String username);
}
