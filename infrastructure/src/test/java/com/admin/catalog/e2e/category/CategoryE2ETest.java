package com.admin.catalog.e2e.category;

import com.admin.catalog.E2ETest;
import com.admin.catalog.domain.category.CategoryID;
import com.admin.catalog.infrastructure.category.models.CategoryResponse;
import com.admin.catalog.infrastructure.category.models.CreateCategoryRequest;
import com.admin.catalog.infrastructure.category.persistence.CategoryRepository;
import com.admin.catalog.infrastructure.configuration.json.Json;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@E2ETest
public class CategoryE2ETest {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MockMvc mockMvc;


    @Container
    private static final MySQLContainer MY_SQL_CONTAINER
            = new MySQLContainer("mysql:latest")
            .withPassword("123456")
            .withUsername("root")
            .withDatabaseName("adm_videos");

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry){
        final var mappedPort = MY_SQL_CONTAINER.getMappedPort(3306);
        System.out.printf("MYSQL container  is running on port %s\n", mappedPort);

        registry.add("mysql.port", () -> mappedPort);
    }

    @Test
    void test(){
        assertTrue(MY_SQL_CONTAINER.isRunning());
    }

    @Test
    void shouldBeAbleToCreateANewCategoryWithValidValues() throws Exception {
        assertEquals(0, categoryRepository.count());

        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida.";
        final var expectedIsActive = true;

        final var actualId =  givenACategory(expectedName, expectedDescription, expectedIsActive);
        final var actualCategory = retriveACategory(actualId.getValue());

        assertEquals(expectedName, actualCategory.name());
        assertEquals(expectedDescription, actualCategory.description());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.createdAt());
        assertNotNull(actualCategory.updatedAt());
        assertNull(actualCategory.deletedAt());

    }

    private CategoryID givenACategory(final String aName,
                                      final String aDescription,
                                      final boolean isActive) throws Exception {

       final var aRequestBody = new CreateCategoryRequest(aName, aDescription, isActive);

        final var aRequest = MockMvcRequestBuilders.post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Json.writeValueAsString(aRequestBody));

        final var actualId = this.mockMvc.perform(aRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn()
                .getResponse().getHeader("Location")
                .replace("/categories/", "");

        return CategoryID.from(actualId);
    }

    private CategoryResponse retriveACategory(final String anId) throws Exception{
        final var aRequest = MockMvcRequestBuilders.get("/categories/" + anId)
                .contentType(MediaType.APPLICATION_JSON);

        final var json = this.mockMvc.perform(aRequest)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        return Json.readValue(json, CategoryResponse.class);
    }
}
