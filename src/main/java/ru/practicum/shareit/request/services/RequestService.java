package ru.practicum.shareit.request.services;

import ru.practicum.shareit.handler.exceptions.ItemRequestNotFound;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.List;

public interface RequestService {

    ItemRequest save(ItemRequestDto itemRequestDto);

    ItemRequest put(ItemRequestDto itemRequestDto, long itemRequestId) throws ItemRequestNotFound;

    void delete(long itemRequestId) throws ItemRequestNotFound;

    List<ItemRequest> findAll();

    ItemRequest findById(long itemRequestId) throws ItemRequestNotFound;
}
