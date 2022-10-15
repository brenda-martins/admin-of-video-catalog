package com.admin.catalog.application.category.update;

import com.admin.catalog.domain.category.Category;
import com.admin.catalog.domain.category.CategoryID;

public record UpdateCategoryOutput(CategoryID id) {

    public static UpdateCategoryOutput from (final Category aCategory){
        return new UpdateCategoryOutput(aCategory.getId());
    }
}
