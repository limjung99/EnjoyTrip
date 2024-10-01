package com.enjoytrip.service;

import com.enjoytrip.dto.post.*;
import com.enjoytrip.dto.auth.User;
import com.enjoytrip.exception.UserInvalidException;
import com.enjoytrip.mapper.CommentMapper;
import com.enjoytrip.mapper.PostMapper;
import com.zaxxer.hikari.util.ClockSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postDao;
    private final CommentMapper commentDao;

    @Override
    public void likePost(int postId, User user) throws SQLException {
        Post post = postDao.findById(postId);
        int liked = post.getLiked();
        liked++;
        postDao.likePost(postId, liked);
    }

    @Override
    public void disLikePost(int postId, User user) throws SQLException {
        Post post = postDao.findById(postId);
        int hated = post.getHated();
        hated++;
        post.setHated(hated);
        postDao.hatePost(postId, hated);
    }

    @Override
    public void increasePostView(int postId, Post post) throws SQLException {
        int views = post.getViews();
        views++;
        post.setViews(views);
        postDao.update(post.getPostText(),
                post.getPostTitle(),
                postId,
                views);
    }

    @Override
    public List<PostInfo> findPostInfoByOffsetAndSize(int offset, int size) throws SQLException {
        offset = (offset - 1) * 10;
        return postDao.findByOffsetNSize(offset, size);
    }

    @Cacheable("totalPostCount")
    @Override
    public int getPostCount() {
        return postDao.getAllPostNumber();
    }

    @Override
    public Post findPostById(int postId) {
        return postDao.findById(postId);
    }

    @Override
    public void deletePostById(int postId, User contextUser) throws UserInvalidException {
        Post deleteCandidatePost = postDao.findById(postId);

        if(!deleteCandidatePost.getUserId().equals(contextUser.getId())){
            throw new UserInvalidException();
        }

        postDao.deleteById(postId);
    }

    @Override
    public void updatePost(int postId, UpdatePostRequest updatePostRequest, User contextUser) throws UserInvalidException {
        Post post = postDao.findById(postId);

        updatePostFields(updatePostRequest, post);

        if(validateUser(contextUser, post))
            throw new UserInvalidException();

        postDao.update(post.getPostText(), post.getPostTitle(), postId, post.getViews());
    }

    private void updatePostFields(UpdatePostRequest updatePostRequest, Post post){
        Optional.ofNullable(updatePostRequest.getUpdateTitle()).ifPresent(post::setPostTitle);
        Optional.ofNullable(updatePostRequest.getUpdateText()).ifPresent(post::setPostText);
    }

    private boolean validateUser(User contextUser, Post post){
        if (!post.getUserId().equals(contextUser.getId()))
            return false;
        return true;
    }

    @Override
    public void createPost(Post post) {
        postDao.insert(post.getPostText(),post.getPostTitle(),post.getUserId());
    }

    @Override
    public void createComment(Comment comment, int postId, User contextUser) throws SQLException {
        String userId = contextUser.getId();

        commentDao.createComment(userId,
                comment.getCommentParentId(),
                postId,
                comment.getCommentText());
    }

    @Override
    public void deleteComment(int commentId, User contextUser) throws SQLException, UserInvalidException {
        Comment comment = commentDao.findById(commentId);
        System.out.println(commentId);
        System.out.println(comment);
        if(!comment.getUserId().equals(contextUser.getId())){
            throw new UserInvalidException();
        }

        commentDao.deleteById(commentId);
    }

    @Override
    public List<Comment> findCommentsByPostId(int postId) throws SQLException {
        return commentDao.findByPostId(postId);
    }

    @Override
    public void updateComment(int commentId, String updateText) throws SQLException {
        commentDao.updateComment(commentId, updateText);
    }
}
