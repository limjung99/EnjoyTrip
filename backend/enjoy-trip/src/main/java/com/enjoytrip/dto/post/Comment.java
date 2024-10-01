package com.enjoytrip.dto.post;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@ToString
public class Comment {
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("comment_id")
    Integer commentId;
    @JsonProperty("comment_parent_id")
    Integer commentParentId;
    @JsonProperty("created_at")
    Date createdAt;
    @JsonProperty("post_id")
    Integer postId;
    @JsonProperty("comment_text")
    String commentText;
}
