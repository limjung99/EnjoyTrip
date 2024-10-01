package com.enjoytrip.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Date;

@Data
public class PostInfo {
    @JsonProperty("post_id")
    int postId;
    @JsonProperty("post_title")
    String postTitle;
    @JsonProperty("user_id")
    String userId;
    @JsonProperty("created_at")
    Date createdAt;
    int hated;
    int liked;
    int views;
}
