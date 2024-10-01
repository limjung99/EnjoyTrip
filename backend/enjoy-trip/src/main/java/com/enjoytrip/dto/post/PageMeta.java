package com.enjoytrip.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageMeta {
    int total;
    int page;
    int size;
}
