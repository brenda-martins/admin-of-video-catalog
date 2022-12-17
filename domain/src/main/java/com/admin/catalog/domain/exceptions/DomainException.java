package com.admin.catalog.domain.exceptions;

import com.admin.catalog.domain.validation.Error;

import java.util.List;

public class DomainException extends NoStackTraceException{

    private final List<Error> errors;

    protected DomainException(final String aMessage, final List<Error> anErros){
        super(aMessage);
        this.errors = anErros;
    }

    public static DomainException with(final Error anErros){
        return new DomainException(anErros.message(), List.of(anErros));
    }

    public static DomainException with(final List<Error> anErros){
        return new DomainException("", anErros);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
