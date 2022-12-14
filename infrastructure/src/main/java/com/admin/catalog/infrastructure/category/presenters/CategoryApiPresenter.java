package com.admin.catalog.infrastructure.category.presenters;

import com.admin.catalog.application.category.retrive.get.CategoryOutput;
import com.admin.catalog.application.category.retrive.list.CategoryListOutput;
import com.admin.catalog.infrastructure.category.models.CategoryResponse;
import com.admin.catalog.infrastructure.category.models.CategoryListResponse;

public interface CategoryApiPresenter {

    static CategoryResponse present(final CategoryOutput output){
        return new CategoryResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.updatedAt(),
                output.deletedAt()
        );
    }

    static CategoryListResponse present(final CategoryListOutput output){
        return new CategoryListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.isActive(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
