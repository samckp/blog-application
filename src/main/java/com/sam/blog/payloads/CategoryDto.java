package com.sam.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

    private Long categoryId;
    private String categoryTitle;
    private String categoryDesc;
}
