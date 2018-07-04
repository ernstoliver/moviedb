package com.reaktorlabs.comment;

import com.reaktorlabs.model.MovieComment;
import java.util.List;

/**
 *
 * @author ernst
 */
public interface CommentService {

    public void saveComment(String tmdbid,String username,String comment,String title);
    List<MovieComment> returnComments(String tmbdid);
}
