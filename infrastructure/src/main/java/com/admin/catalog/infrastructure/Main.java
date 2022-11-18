package com.admin.catalog.infrastructure;

import com.admin.catalog.application.category.create.CreateCategoryUseCase;
import com.admin.catalog.application.category.delete.DeleteCategoryUseCase;
import com.admin.catalog.application.category.retrive.get.GetCategoryByIdUseCase;
import com.admin.catalog.application.category.retrive.list.ListCategoriesUseCase;
import com.admin.catalog.application.category.update.UpdateCategoryUseCase;
import com.admin.catalog.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
        SpringApplication.run(WebServerConfig.class, args);
    }

    @Bean
    @DependsOnDatabaseInitialization
    ApplicationRunner runner(
            CreateCategoryUseCase createCategoryUseCase,
            UpdateCategoryUseCase updateCategoryUseCase,
            GetCategoryByIdUseCase getCategoryByIdUseCase,
            ListCategoriesUseCase listCategoriesUseCase,
            DeleteCategoryUseCase deleteCategoryUseCase
    ){
        return args -> {

        };
    }

//    @Bean
//    public ApplicationRunner runner(CategoryRepository repository){
//        return args -> {
//            List<CategoryJpaEntity> list = repository.findAll();
//
//            Category movies = Category.newCategory("Movies", "The best category", true);
//            repository.saveAndFlush(CategoryJpaEntity.from(movies));
//
//            List<CategoryJpaEntity> list2 = repository.findAll();
//
//            for(CategoryJpaEntity c : list2){
//                System.out.println(c);
//            }
//
//            repository.deleteAll();
//        };
//    }
}