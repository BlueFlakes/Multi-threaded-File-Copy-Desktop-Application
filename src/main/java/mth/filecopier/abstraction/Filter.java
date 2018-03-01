package mth.filecopier.abstraction;

import mth.filecopier.exceptions.ApplicationException;

public interface Filter<T> {
    void validate(T object) throws ApplicationException;
}
