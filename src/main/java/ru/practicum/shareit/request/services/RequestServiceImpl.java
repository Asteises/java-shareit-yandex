package ru.practicum.shareit.request.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.handler.exceptions.ItemRequestNotFound;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repositoryes.RequestStorage;

import java.util.List;

@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestStorage requestStorage;

    @Override
    public ItemRequest save(ItemRequestDto itemRequestDto) {
        return requestStorage.save(itemRequestDto);
    }

    @Override
    public ItemRequest put(ItemRequestDto itemRequestDto, long itemRequestId) throws ItemRequestNotFound {
        return requestStorage.put(itemRequestDto, itemRequestId);
    }

    @Override
    public void delete(long itemRequestId) throws ItemRequestNotFound {
        requestStorage.delete(itemRequestId);
    }

    @Override
    public List<ItemRequest> findAll() {
        return requestStorage.findAll();
    }

    @Override
    public ItemRequest findById(long itemRequestId) throws ItemRequestNotFound {
        return requestStorage.findById(itemRequestId);
    }
}
