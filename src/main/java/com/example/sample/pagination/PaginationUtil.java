package com.example.sample.pagination;

import org.springframework.data.domain.Page;

public final class PaginationUtil {

    public static <T> CustomPage<T> customPage(Page<T> page) {
        return new CustomPage<>(page);
    }

}
