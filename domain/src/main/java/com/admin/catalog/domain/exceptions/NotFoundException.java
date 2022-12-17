package com.admin.catalog.domain.exceptions;

import com.admin.catalog.domain.AggregateRoot;
import com.admin.catalog.domain.Identifier;
import com.admin.catalog.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException{
    protected NotFoundException(final String aMessage, final List<Error> anErros) {
        super(aMessage, anErros);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier identifier
            ){

        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                identifier.getValue()
        );

        return new NotFoundException(anError, Collections.emptyList());
    }
}
