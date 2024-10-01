package com.enjoytrip.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdatePostRequest {
    @JsonProperty("update_title")
    String updateTitle;

    @JsonProperty("update_text")
    String updateText;
}
