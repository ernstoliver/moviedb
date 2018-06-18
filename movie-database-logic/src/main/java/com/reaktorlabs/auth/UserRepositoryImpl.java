package com.reaktorlabs.auth;

import com.reaktorlabs.model.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ernst
 */
@Stateless
public class UserRepositoryImpl implements UserRepository {
    
    @PersistenceContext(unitName = "moviedb")
    private EntityManager manager;

    @Override
    public void saveUser(User user) {
        manager.persist(user);
    }

    @Override
    public List<User> findUserByName(String username) {
        TypedQuery<User> query = manager.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class);
        query.setParameter("username",username);
        return query.getResultList();
    }
    
    
}
