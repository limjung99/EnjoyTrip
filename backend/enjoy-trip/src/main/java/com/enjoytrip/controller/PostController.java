package com.enjoytrip.controller;

import com.enjoytrip.dto.post.*;
import com.enjoytrip.dto.auth.User;
import com.enjoytrip.exception.UserInvalidException;
import com.enjoytrip.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/api/v1/posts")
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/{postId}/like")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> likePost(@PathVariable int postId, Authentication authentication){
        User contextUser = (User)authentication.getPrincipal();
        try{
            postService.likePost(postId, contextUser);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (SQLException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/{postID}/dislike")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> dislikePost(@PathVariable int postID, Authentication authentication){
        User contextUser = (User)authentication.getPrincipal();
        try{
            postService.disLikePost(postID, contextUser);
            return ResponseEntity.status(HttpStatus.OK).body("");
        } catch (SQLException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Retrieves post info list by offset and size.
     *
     * @param page
     * @param size
     * @return post page info list by offset and size ( usually size 10 )
     */
    @GetMapping("")
    public ResponseEntity<?> postList(@RequestParam int page,
                                      @RequestParam int size) {
        try {
            List<PostInfo> postInfoList = postService.findPostInfoByOffsetAndSize(page, size);
            int postCount = postService.getPostCount();
            PageMeta pageMeta = PageMeta.builder()
                    .total(postCount)
                    .page(page)
                    .size(size)
                    .build();
            PostPageResponse postPageResponse = PostPageResponse.builder()
                    .meta(pageMeta)
                    .items(postInfoList)
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(postPageResponse);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Post Create API
     *
     * @param post
     * @return created new post detail
     */
    @PostMapping("")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPost(@RequestBody Post post,
                                        Authentication authentication) {
        try {
            User contextUser = (User)authentication.getPrincipal();
            post.setUserId(contextUser.getId());
            postService.createPost(post);
            return ResponseEntity.status(HttpStatus.OK).body("successfully created");
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Post Delete by postId
     *
     * @param postId
     * @param authentication
     * @return delete success message
     */
    @DeleteMapping("/{postId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deletePost(@PathVariable int postId, Authentication authentication) {
        try {
            User contextUser = (User)authentication.getPrincipal();
            postService.deletePostById(postId, contextUser);
            return ResponseEntity.status(HttpStatus.OK).body("successfully delete");
        } catch (SQLException | UserInvalidException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Post Update API
     *
     * @param postId
     * @param updatePost
     * @param authentication
     * @return update success message.
     */
    @PatchMapping("/{postId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updatePost(@PathVariable int postId,
                                        @RequestBody UpdatePostRequest updatePostRequest,
                                        Authentication authentication){
        try {
            User contextUser = (User) authentication.getPrincipal();
            postService.updatePost(postId, updatePostRequest, contextUser);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully updated");
        } catch (SQLException | UserInvalidException e){
            log.debug(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    /**
     * Post Detail Get API
     *
     * @param postId
     * @return post detail include comments.
     */
    @GetMapping("/{postId}")
    @Transactional
    public ResponseEntity<?> getPost(@PathVariable int postId) {
        try {
            Post post = postService.findPostById(postId);
            postService.increasePostView(postId, post);
            List<Comment> comments = postService.findCommentsByPostId(postId);

            PostDetailResponse postDetailResponse = PostDetailResponse.builder()
                    .postId(post.getPostId())
                    .postTitle(post.getPostTitle())
                    .postText(post.getPostText())
                    .createdAt(post.getCreatedAt())
                    .userId(post.getUserId())
                    .comments(comments)
                    .liked(post.getLiked())
                    .hated(post.getHated())
                    .build();

            return ResponseEntity.status(HttpStatus.OK).body(postDetailResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Comment Create API
     *
     * @param postId
     * @param comment
     * @return new comment
     */
    @PostMapping("/{postId}/comment")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createComment(@PathVariable int postId,
                                           @RequestBody Comment comment,
                                           Authentication authentication) {
        try {
            System.out.println(comment);
            User contextUser = (User)authentication.getPrincipal();
            postService.createComment(comment, postId, contextUser);
            return ResponseEntity.status(HttpStatus.OK).body("successfully created");
        } catch (SQLException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    /**
     * Comment Delete API
     *
     * @param commentId
     * @return comment deleted message
     */
    @DeleteMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId,
                                           Authentication authentication){
        try {
            User contextUser = (User)authentication.getPrincipal();
            postService.deleteComment(commentId, contextUser);
            return ResponseEntity.status(HttpStatus.OK).body("comment deleted");
        } catch (SQLException | UserInvalidException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Comment Update API
     *
     * @param commentId
     * @param updateText
     * @return comment update success message
     */
    @PatchMapping("/{postId}/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int commentId,
                                           @RequestBody String updateText){
        try{
            postService.updateComment(commentId, updateText);
            return ResponseEntity.status(HttpStatus.OK).body("comment updated");
        } catch (SQLException e){
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
