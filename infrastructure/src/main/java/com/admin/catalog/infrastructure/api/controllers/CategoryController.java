package com.admin.catalog.infrastructure.api.controllers;

import com.admin.catalog.application.category.create.CreateCategoryCommand;
import com.admin.catalog.application.category.create.CreateCategoryOutput;
import com.admin.catalog.application.category.create.CreateCategoryUseCase;
import com.admin.catalog.application.category.retrive.get.GetCategoryByIdUseCase;
import com.admin.catalog.application.category.update.UpdateCategoryCommand;
import com.admin.catalog.application.category.update.UpdateCategoryOutput;
import com.admin.catalog.application.category.update.UpdateCategoryUseCase;
import com.admin.catalog.domain.pagination.Pagination;
import com.admin.catalog.domain.validation.handler.Notification;
import com.admin.catalog.infrastructure.api.CategoryAPI;
import com.admin.catalog.infrastructure.category.models.CategoryApiOutput;
import com.admin.catalog.infrastructure.category.models.CreateCategoryApiInput;
import com.admin.catalog.infrastructure.category.models.UpdateCategoryApiInput;
import com.admin.catalog.infrastructure.category.presenters.CategoryApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    public CategoryController(
            final CreateCategoryUseCase createCategoryUseCase,
            final GetCategoryByIdUseCase getCategoryByIdUseCase,
            final UpdateCategoryUseCase updateCategoryUseCase
    ) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
        this.getCategoryByIdUseCase = Objects.requireNonNull(getCategoryByIdUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryApiInput input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);
        return this.createCategoryUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }

    @Override
    public CategoryApiOutput getById(final String id) {
        return CategoryApiPresenter.present(this.getCategoryByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCategoryApiInput input) {
        final var aCommand = UpdateCategoryCommand.with(
                id,
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCategoryOutput, ResponseEntity<?>> onSuccess =
                ResponseEntity::ok;

        return this.updateCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);
    }
}
