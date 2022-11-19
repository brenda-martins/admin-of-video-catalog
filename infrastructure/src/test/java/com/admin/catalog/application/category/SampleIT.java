package com.admin.catalog.application.category;

import com.admin.catalog.IntegrationTest;
import com.admin.catalog.application.category.create.CreateCategoryUseCase;
import com.admin.catalog.infrastructure.category.persistence.CategoryRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest class SampleIT {

    @Autowired
    private CreateCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void test(){
        assertNotNull(useCase);
        assertNotNull(categoryRepository);
    }
}
