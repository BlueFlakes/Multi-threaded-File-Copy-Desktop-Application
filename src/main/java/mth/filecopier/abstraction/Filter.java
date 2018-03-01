package mth.filecopier.abstraction;

public interface Filter<T> {
    boolean validate(T object);
}
