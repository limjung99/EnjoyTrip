package com.enjoytrip.service;

import java.sql.SQLException;
import java.util.List;

import com.enjoytrip.dto.post.Comment;
import com.enjoytrip.dto.post.Post;
import com.enjoytrip.dto.auth.User;
import com.enjoytrip.dto.post.PostInfo;
import com.enjoytrip.dto.post.UpdatePostRequest;
import com.enjoytrip.exception.UserInvalidException;
import org.springframework.cache.annotation.Cacheable;

public interface PostService {

    void likePost(int postId, User user) throws SQLException;

    void disLikePost(int postId, User user) throws SQLException;
    void increasePostView(int postId, Post post) throws SQLException;
    List<PostInfo> findPostInfoByOffsetAndSize(int offset, int size) throws SQLException;

    @Cacheable("totalPostCount")
    int getPostCount();
    Post findPostById(int postId) throws SQLException;

    void deletePostById(int postId, User contextUser) throws SQLException, UserInvalidException;

    void updatePost(int postId,
                    UpdatePostRequest updatePostRequest,
                    User contextUser) throws SQLException, UserInvalidException;

    void createPost(Post post) throws SQLException;

    void createComment(Comment comment, int postId, User contextUser) throws SQLException;

    void deleteComment(int commentId, User contextuser) throws SQLException, UserInvalidException;

    List<Comment> findCommentsByPostId(int postId) throws SQLException;

    void updateComment(int commentId, String updateText) throws SQLException;
}
