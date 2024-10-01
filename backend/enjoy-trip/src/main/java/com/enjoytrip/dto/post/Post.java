package com.enjoytrip.dto.post;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Post {
    private int postId;
    private String postText;
    private String postTitle;
    private String userId;
    private int views;
    private Date createdAt;
    private boolean isActive;
    private int liked;
    private int hated;
}
