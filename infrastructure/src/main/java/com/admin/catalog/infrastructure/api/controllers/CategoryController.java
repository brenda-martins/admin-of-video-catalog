package com.admin.catalog.infrastructure.api.controllers;

import com.admin.catalog.domain.pagination.Pagination;
import com.admin.catalog.infrastructure.api.CategoryAPI;
import org.springframework.http.ResponseEntity;

public class CategoryController implements CategoryAPI {
    @Override
    public ResponseEntity<?> createCategory() {
        return null;
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }
}
