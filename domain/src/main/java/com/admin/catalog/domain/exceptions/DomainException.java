package com.admin.catalog.domain.exceptions;

import com.admin.catalog.domain.validation.Error;

import java.util.List;

public class DomainException extends RuntimeException{

    private final List<Error> errors;

    private DomainException(final List<Error> anErros){
        super("", null, true, false);
        this.errors = anErros;
    }

    public static DomainException with(final List<Error> anErros){
        return new DomainException(anErros);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
