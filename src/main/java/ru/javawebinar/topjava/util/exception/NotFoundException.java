package ru.javawebinar.topjava.util.exception;

/**
 * Created by Asset on 09.03.2016.
 */
public class NotFoundException  extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
