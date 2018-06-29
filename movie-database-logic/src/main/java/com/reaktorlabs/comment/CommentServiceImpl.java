package com.reaktorlabs.comment;

import com.reaktorlabs.model.MovieComment;
import com.reaktorlabs.model.User;
import com.reaktorlabs.repository.UserListRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ernst
 */
@Stateless
public class CommentServiceImpl implements CommentService {
    
    private UserListRepository repository;
    
    public CommentServiceImpl() {
        
    }
    
    @Inject
    public CommentServiceImpl(UserListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveComment(String tmdbid, String username, String comment, String title) {
        User user = repository.getUserFromDatabase(username);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String now = LocalDateTime.now().format(formatter);
        LocalDateTime formattedDateTime = LocalDateTime.parse(now,formatter);
        MovieComment commentToSave = new MovieComment();
        commentToSave.setComment(comment);
        commentToSave.setDate(formattedDateTime);
        commentToSave.setTitle(title);
        commentToSave.setTmdbid(Long.parseLong(tmdbid));
        commentToSave.setUser(user);
        repository.saveMovieComment(commentToSave);
    }

    @Override
    public List<MovieComment> returnComments(String tmbdid) {
        return repository.returnComments(Long.parseLong(tmbdid));
    }
    
}
