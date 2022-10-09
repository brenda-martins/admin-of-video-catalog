package com.admin.catalog.domain.category;

import com.admin.catalog.domain.exceptions.DomainException;
import com.admin.catalog.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
 class CategoryTest {


    @Test
    void giveAValidParams_whenCallNewCategory_thenInstantiateACategory(){
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida.";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertNotNull(actualCategory);
        assertNotNull(actualCategory.getId());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertNotNull(actualCategory.getCreatedAt());
        assertNotNull(actualCategory.getUpdatedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
     void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
        final String expectedName = null;
        final var expectedDescription = "A categoria mais assistida.";
        final var expectedIsActive = true;

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null.";

        final var actualCategory
                = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var actualException = assertThrows(DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        assertEquals(expectedErrorCount, actualException.getErrors().size());
        assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());

    }

     @Test
     void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
         final var expectedName = "  ";
         final var expectedDescription = "A categoria mais assistida.";
         final var expectedIsActive = true;

         final var expectedErrorCount = 1;
         final var expectedErrorMessage = "'name' should not be empty.";

         final var actualCategory
                 = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

         final var actualException = assertThrows(DomainException.class,
                 () -> actualCategory.validate(new ThrowsValidationHandler()));

         assertEquals(expectedErrorCount, actualException.getErrors().size());
         assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
     }

     @Test
     void givenAnInvalidNameLengthLessThen3_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
         final var expectedName = "Se ";
         final var expectedDescription = "A categoria mais assistida.";
         final var expectedIsActive = true;

         final var expectedErrorCount = 1;
         final var expectedErrorMessage = "'name' must be between 3 and 255 characters.";

         final var actualCategory
                 = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

         final var actualException = assertThrows(DomainException.class,
                 () -> actualCategory.validate(new ThrowsValidationHandler()));

         assertEquals(expectedErrorCount, actualException.getErrors().size());
         assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
     }

     @Test
     void givenAnInvalidNameLengthMoreThen255_whenCallNewCategoryAndValidate_thenShouldReceiveError(){
         final var expectedName = """
                Gostaria de enfatizar que o consenso sobre a necessidade de qualificação auxilia a preparação e a
                composição das posturas dos órgãos dirigentes com relação às suas atribuições.
                Do mesmo modo, a estrutura atual da organização apresenta tendências no sentido de aprovar a
                manutenção das novas proposições.
                """;
         final var expectedDescription = "A categoria mais assistida.";
         final var expectedIsActive = true;

         final var expectedErrorCount = 1;
         final var expectedErrorMessage = "'name' must be between 3 and 255 characters.";

         final var actualCategory
                 = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

         final var actualException = assertThrows(DomainException.class,
                 () -> actualCategory.validate(new ThrowsValidationHandler()));

         assertEquals(expectedErrorCount, actualException.getErrors().size());
         assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
     }

     @Test
      void givenAValidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
         final var expectedName = "Filmes";
         final var expectedDescription = "  ";
         final var expectedIsActive = true;

         final var actualCategory =
                 Category.newCategory(expectedName, expectedDescription, expectedIsActive);

         assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

         assertNotNull(actualCategory);
         assertNotNull(actualCategory.getId());
         assertEquals(expectedName, actualCategory.getName());
         assertEquals(expectedDescription, actualCategory.getDescription());
         assertEquals(expectedIsActive, actualCategory.isActive());
         assertNotNull(actualCategory.getCreatedAt());
         assertNotNull(actualCategory.getUpdatedAt());
         assertNull(actualCategory.getDeletedAt());
     }

     @Test
      void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveOK() {
         final var expectedName = "Filmes";
         final var expectedDescription = "A categoria mais assistida";
         final var expectedIsActive = false;

         final var actualCategory =
                 Category.newCategory(expectedName, expectedDescription, expectedIsActive);

         assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

         assertNotNull(actualCategory);
         assertNotNull(actualCategory.getId());
         assertEquals(expectedName, actualCategory.getName());
         assertEquals(expectedDescription, actualCategory.getDescription());
         assertEquals(expectedIsActive, actualCategory.isActive());
         assertNotNull(actualCategory.getCreatedAt());
         assertNotNull(actualCategory.getUpdatedAt());
         assertNotNull(actualCategory.getDeletedAt());
     }

     @Test
      void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactivated() {
         final var expectedName = "Filmes";
         final var expectedDescription = "A categoria mais assistida.";
         final var expectedIsActive = false;

         final var aCategory =
                 Category.newCategory(expectedName, expectedDescription, true);

         Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

         final var createdAt = aCategory.getCreatedAt();
         final var updatedAt = aCategory.getUpdatedAt();

         Assertions.assertTrue(aCategory.isActive());
         Assertions.assertNull(aCategory.getDeletedAt());

         final var actualCategory = aCategory.deactivate();

         Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

         Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
         Assertions.assertEquals(expectedName, actualCategory.getName());
         Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
         Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
         Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
         Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
         Assertions.assertNotNull(actualCategory.getDeletedAt());
     }

     @Test
      void givenAValidInactiveCategory_whenCallActivate_thenReturnCategoryActivated() {
         final var expectedName = "Filmes";
         final var expectedDescription = "A categoria mais assistida";
         final var expectedIsActive = true;

         final var aCategory =
                 Category.newCategory(expectedName, expectedDescription, false);

         Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

         final var createdAt = aCategory.getCreatedAt();
         final var updatedAt = aCategory.getUpdatedAt();

         Assertions.assertFalse(aCategory.isActive());
         Assertions.assertNotNull(aCategory.getDeletedAt());

         final var actualCategory = aCategory.activate();

         Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

         Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
         Assertions.assertEquals(expectedName, actualCategory.getName());
         Assertions.assertEquals(expectedDescription, actualCategory.getDescription());
         Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
         Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());
         Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
         Assertions.assertNull(actualCategory.getDeletedAt());
     }

     @Test
     void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated(){
         final var expectedName = "Filmes";
         final var expectedDescription = "A categoria mais assistida.";
         final var expectedIsActive = true;

         final var aCategory =
                 Category.newCategory("Film", "A categoria", expectedIsActive);

         Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

         final var createdAt = aCategory.getCreatedAt();
         final var updatedAt = aCategory.getUpdatedAt();

         final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

         assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

         assertEquals(aCategory.getId(), actualCategory.getId());
         assertEquals(expectedName, actualCategory.getName());
         assertEquals(expectedDescription, actualCategory.getDescription());
         assertEquals(expectedIsActive, actualCategory.isActive());
         assertEquals(createdAt, actualCategory.getCreatedAt());
         assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
         assertNull(actualCategory.getDeletedAt());
     }

     @Test
      void givenAValidCategory_whenCallUpdateToInactive_thenReturnCategoryUpdated() {
         final var expectedName = "Filmes";
         final var expectedDescription = "A categoria mais assistida.";
         final var expectedIsActive = false;

         final var aCategory =
                 Category.newCategory("Film", "A categoria", true);

         assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
         assertTrue(aCategory.isActive());
         assertNull(aCategory.getDeletedAt());

         final var createdAt = aCategory.getCreatedAt();
         final var updatedAt = aCategory.getUpdatedAt();

         final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

         assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

         assertEquals(aCategory.getId(), actualCategory.getId());
         assertEquals(expectedName, actualCategory.getName());
         assertEquals(expectedDescription, actualCategory.getDescription());
         assertFalse(aCategory.isActive());
         assertEquals(createdAt, actualCategory.getCreatedAt());
         assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
         assertNotNull(aCategory.getDeletedAt());
     }

     @Test
      void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
         final String expectedName = null;
         final var expectedDescription = "A categoria mais assistida";
         final var expectedIsActive = true;

         final var aCategory =
                 Category.newCategory("Filmes", "A categoria", expectedIsActive);

         assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

         final var createdAt = aCategory.getCreatedAt();
         final var updatedAt = aCategory.getUpdatedAt();

         final var actualCategory = aCategory.update(expectedName, expectedDescription, expectedIsActive);

         assertEquals(aCategory.getId(), actualCategory.getId());
         assertEquals(expectedName, actualCategory.getName());
         assertEquals(expectedDescription, actualCategory.getDescription());
         assertTrue(aCategory.isActive());
         assertEquals(createdAt, actualCategory.getCreatedAt());
         assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
         assertNull(aCategory.getDeletedAt());
     }

 }
