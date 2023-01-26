package ru.aasmc.cems.rest.sec.problem;

public class IllegalOperation extends RuntimeException {
    public IllegalOperation(String message) {
        super(message);
    }

    public IllegalOperation(String message, Throwable cause) {
        super(message, cause);
    }
}

