package com.enjoytrip.mapper;

import com.enjoytrip.dto.post.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Results(id="commentResult",value = {
            @Result(column = "user_id", property = "userId"),
            @Result(column = "comment_id", property = "commentId"),
            @Result(column = "comment_parent", property = "commentParentId"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "post_id", property = "postId"),
            @Result(column = "comment_text", property = "commentText")
    })
    @Select("SELECT * FROM comment")
    List<Comment> findAll();
    @ResultMap("commentResult")
    @Select("SELECT * FROM comment WHERE comment_id=#{commentId}")
    Comment findById(int commentId);
    @ResultMap("commentResult")
    @Select("SELECT * FROM comment WHERE post_id = #{postId}")
    List<Comment> findByPostId(@Param("postId") int postId);

    @Insert("INSERT INTO comment(comment_parent, post_id, comment_text, user_id, created_at)" +
            " VALUES(#{parentId}, #{postId}, #{commentText}, #{userId}, NOW())")
    void createComment(String userId, Integer parentId, int postId, String commentText);

    @Delete("DELETE FROM comment WHERE comment_id = #{commentId}")
    void deleteById(int commentId);

    @Update("UPDATE comment" +
            " SET comment_text = #{updateText}" +
            " where comment_id = #{commentId}")
    void updateComment(int commentId, String updateText);
}
