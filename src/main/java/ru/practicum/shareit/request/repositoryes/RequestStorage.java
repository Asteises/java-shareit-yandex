package ru.practicum.shareit.request.repositoryes;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.handler.exceptions.ItemRequestNotFound;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.List;

@Repository
public interface RequestStorage {

    ItemRequest save(ItemRequestDto itemRequestDto);

    ItemRequest put(ItemRequestDto itemRequestDto, long itemRequestId) throws ItemRequestNotFound;

    void delete(long itemRequestId) throws ItemRequestNotFound;

    List<ItemRequest> findAll();

    ItemRequest findById(long itemRequestId) throws ItemRequestNotFound;
}
