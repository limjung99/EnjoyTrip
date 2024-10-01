package com.enjoytrip.mapper;

import com.enjoytrip.dto.post.Post;
import com.enjoytrip.dto.post.PostInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Results(id = "postMap",value = {
        @Result(property = "postId", column = "post_id"),
        @Result(property = "postTitle", column = "post_title"),
        @Result(property = "userId", column = "user_id"),
        @Result(property = "createdAt", column = "created_at"),
        @Result(property = "postText", column = "post_text"),
        @Result(property = "isActive", column = "is_active"),
    })
    @Select("SELECT * FROM post WHERE post_id = #{postId}")
    Post findById(@Param("postId") int postId);

    @Delete("DELETE FROM post where post_id = #{id}")
    void deleteById(int id);

    @Insert("INSERT INTO post(post_text, post_title, user_id, created_at, is_active )" +
            "VALUES (#{postText}, #{postTitle}, #{userId}, NOW(), true)")
    void insert(String postText, String postTitle, String userId);

    @Select("SELECT * FROM post ORDER BY post_id ASC LIMIT #{size} OFFSET #{offset}")
    @Results(id = "postInfoMap",value = {
            @Result(property = "postId", column = "post_id"),
            @Result(property = "postTitle", column = "post_title"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createdAt", column = "created_at")})
    List<PostInfo> findByOffsetNSize(@Param("offset") int offset, @Param("size") int size);

   @Update("UPDATE post \r\n " +
            "SET post_text = #{postText}, \r\n" +
           "post_title = #{postTitle}, \r\n" +
           "views = #{views} \r\n" +
            "WHERE post_id = #{postId}")
    void update(@Param("postText") String postText,
                @Param("postTitle") String postTitle,
                @Param("postId") int postId,
                @Param("views") int views);

    @Update("UPDATE post \r\n " +
            "SET liked = #{liked} \r\n " +
            "WHERE post_id = #{postId}")
    void likePost(@Param("postId") int postId, @Param("liked") int liked);
    @Update("UPDATE post \r\n " +
            "SET hated = #{hated} \r\n " +
            "WHERE post_id = #{postId}")
    void hatePost(@Param("postId") int postId, @Param("hated") int hated);
    @Select("SELECT count(*) FROM post")
    int getAllPostNumber();
}
