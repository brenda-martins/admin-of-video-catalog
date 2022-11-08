package com.admin.catalog.infrastructure;

import com.admin.catalog.domain.category.Category;
import com.admin.catalog.infrastructure.category.persistence.CategoryJpaEntity;
import com.admin.catalog.infrastructure.category.persistence.CategoryRepository;
import com.admin.catalog.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import java.util.List;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
        SpringApplication.run(WebServerConfig.class, args);
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