package ru.practicum.shareit.handler.exceptions;

public class ItemRequestNotFound extends RuntimeException {

    public ItemRequestNotFound(String message) {
        super(message);
    }
}
