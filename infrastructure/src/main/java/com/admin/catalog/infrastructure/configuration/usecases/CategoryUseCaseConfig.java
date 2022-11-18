package com.admin.catalog.infrastructure.configuration.usecases;

import com.admin.catalog.application.category.create.CreateCategoryUseCase;
import com.admin.catalog.application.category.create.DefaultCreateCategoryUseCase;
import com.admin.catalog.application.category.delete.DefaultDeleteCategoryUseCase;
import com.admin.catalog.application.category.delete.DeleteCategoryUseCase;
import com.admin.catalog.application.category.retrive.get.DefaultGetCategoryByIdUseCase;
import com.admin.catalog.application.category.retrive.get.GetCategoryByIdUseCase;
import com.admin.catalog.application.category.retrive.list.DefaultListCategoriesUseCase;
import com.admin.catalog.application.category.retrive.list.ListCategoriesUseCase;
import com.admin.catalog.application.category.update.DefaultUpdateCategoryUseCase;
import com.admin.catalog.application.category.update.UpdateCategoryUseCase;
import com.admin.catalog.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    private CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(final CategoryGateway categoryGateway){
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(){
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(){
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryUseCase(){
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoryUseCase(){
        return new DefaultListCategoriesUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(){
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }
}
