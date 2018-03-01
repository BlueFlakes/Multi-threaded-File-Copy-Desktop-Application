package mth.filecopier.exceptions;

public abstract class ApplicationException extends Exception {

    public ApplicationException() {}

    public ApplicationException(String s) {
        super(s);
    }
}
