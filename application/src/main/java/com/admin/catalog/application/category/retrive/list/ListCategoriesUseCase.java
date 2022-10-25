package com.admin.catalog.application.category.retrive.list;

import com.admin.catalog.application.UseCase;
import com.admin.catalog.domain.category.CategorySearchQuery;
import com.admin.catalog.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
