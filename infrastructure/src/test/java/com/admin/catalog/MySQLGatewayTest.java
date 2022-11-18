package com.admin.catalog;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@ActiveProfiles("test")
@ComponentScan(includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.REGEX,
                pattern = ".*[MySQLGateway]"
        )
})
@DataJpaTest
@ExtendWith(CleanUpExtension.class)
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MySQLGatewayTest {

}
