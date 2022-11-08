package com.admin.catalog.infrastructure.category;


import com.admin.catalog.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ComponentScan(includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*[MySQLGateway]"
        )
})
@DataJpaTest
@ExtendWith(CategoryMySQLGatewayTest.CleanUpExtensions.class)
class CategoryMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryGateway;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testInjectedDependencies(){
        assertNotNull(categoryGateway);
        assertNotNull(categoryRepository);
    }

    static class CleanUpExtensions implements BeforeEachCallback{

        @Override
        public void beforeEach(final ExtensionContext context){
            final var repositories = SpringExtension
                    .getApplicationContext(context)
                    .getBeansOfType(CrudRepository.class)
                    .values();

            cleanUp(repositories);
        }

        private void cleanUp(final Collection<CrudRepository> repositories) {
            repositories.forEach(CrudRepository::deleteAll);
        }
    }

}
