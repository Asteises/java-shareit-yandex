package ru.practicum.shareit.request.repositoryes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.handler.exceptions.ItemRequestNotFound;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Getter
@Setter
@RequiredArgsConstructor
public class RequestDao implements RequestStorage {

    private Map<Long, ItemRequest> requests = new HashMap<>();

    @Override
    public ItemRequest save(ItemRequestDto itemRequestDto) {
        return null;
    }

    @Override
    public ItemRequest put(ItemRequestDto itemRequestDto, long itemRequestId) throws ItemRequestNotFound {
        return null;
    }

    @Override
    public void delete(long itemRequestId) throws ItemRequestNotFound {

    }

    @Override
    public List<ItemRequest> findAll() {
        return null;
    }

    @Override
    public ItemRequest findById(long itemRequestId) throws ItemRequestNotFound {
        return null;
    }
}
