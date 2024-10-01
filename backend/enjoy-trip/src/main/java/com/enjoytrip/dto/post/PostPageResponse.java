package com.enjoytrip.dto.post;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PostPageResponse {
    PageMeta meta;
    List<PostInfo> items;
}
