package com.enjoytrip.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class PostDetailResponse {
    @JsonProperty("post_id")
    int postId;
    @JsonProperty("post_text")
    String postText;
    @JsonProperty("post_title")
    String postTitle;
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("created_at")
    Date createdAt;
    List<Comment> comments;
    int liked;
    int hated;
}
