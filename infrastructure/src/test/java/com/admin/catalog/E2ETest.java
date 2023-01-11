package com.admin.catalog;

import com.admin.catalog.CleanUpExtension;
import com.admin.catalog.infrastructure.configuration.WebServerConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@ActiveProfiles("test-e2e")
@AutoConfigureMockMvc
@ExtendWith(CleanUpExtension.class)
@Inherited
@SpringBootTest(classes = WebServerConfig.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface E2ETest {
}
