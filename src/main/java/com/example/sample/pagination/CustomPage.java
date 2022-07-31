package com.example.sample.pagination;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class CustomPage<T> {

    private final long totalPages;

    private final long totalElements;

    private final long numberOfElements;

    private final long pageSize;

    private final long pageNumber;

    private final List<T> content;

    public CustomPage(Page<T> page) {
        page = (page == null) ? Page.empty() : page;
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.numberOfElements = page.getNumberOfElements();
        this.pageSize = page.getPageable().getPageSize();
        this.pageNumber = page.getPageable().getPageNumber();
    }

}
