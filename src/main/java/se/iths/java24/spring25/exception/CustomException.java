package se.iths.java24.spring25.exception;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
