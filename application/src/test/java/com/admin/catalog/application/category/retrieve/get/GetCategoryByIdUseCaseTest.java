package com.admin.catalog.application.category.retrieve.get;


import com.admin.catalog.domain.category.Category;
import com.admin.catalog.domain.category.CategoryGateway;
import com.admin.catalog.domain.category.CategoryID;
import com.admin.catalog.domain.exceptions.DomainException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) public class GetCategoryByIdUseCaseTest {

    @InjectMocks
    private DefaultGetCategoryByIdUseCase useCase;


    @Mock
    private CategoryGateway categoryGateway;


    @BeforeEach
    void cleanUp(){
        Mockito.reset(categoryGateway);
    }


    @Test
    void givenAValidId_whenCallsGetCategory_shouldReturnCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida.";
        final var expectedIsActive = true;

        final var aCategory =
                Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        final var expectedId = aCategory.getId();

        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.of(Category.with(aCategory)));

        final var actualCategory = useCase.execute(expectedId.getValue());

        assertEquals(expectedId, actualCategory.id());
        assertEquals(expectedName, actualCategory.name());
        assertEquals(expectedDescription, actualCategory.description());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(aCategory.getCreatedAt(), actualCategory.createdAt());
        assertEquals(aCategory.getUpdatedAt(), actualCategory.updatedAt());
        assertEquals(aCategory.getDeletedAt(), actualCategory.deletedAt());
    }

    @Test
     void givenAInvalidId_whenCallsGetCategory_shouldReturnNotFound() {
        final var expectedId = CategoryID.from("123");
        final var expectedErrorMessage = "Category with ID 123 was not found";

        when(categoryGateway.findById(eq(expectedId)))
                .thenReturn(Optional.empty());

        final var actualException = assertThrows(
                DomainException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
     void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final var expectedErrorMessage = "Gateway error!";
        final var expectedId = CategoryID.from("123");

        when(categoryGateway.findById(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
