package ru.practicum.shareit.item.repositoryes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.services.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ItemDao implements ItemStorage {

    private final ItemMapper itemMapper;
    private final UserService userService;

    private final Map<Long, Item> items = new HashMap<>();
    private static long itemId = 0;

    @Override
    public Item save(ItemDto itemDto, long userId) throws UserNotFound {
        if (userService.findById(userId) != null) {
            User user = userService.findById(userId);
            Item item = itemMapper.toItem(itemDto);
            item.setOwner(user);
            item.setId(++itemId);
            items.put(item.getId(), item);
            return item;
        } else {
            throw new UserNotFound("Юзер не найден");
        }
    }

    @Override
    public Item put(ItemDto itemDto, long itemId, long userId) throws ItemNotFound {
        if (items.containsKey(itemId)
                && items.get(itemId).getOwner() != null
                && items.get(itemId).getOwner().getId().equals(userId)) {
            Item item = items.get(itemId);
            item.setId(itemId);
            item.setOwner(items.get(itemId).getOwner());
            if (itemDto.getAvailable() != null) {
                item.setAvailable(itemDto.getAvailable());
            }
            if (itemDto.getName() != null) {
                item.setName(itemDto.getName());
            }
            if (itemDto.getDescription() != null) {
                item.setDescription(itemDto.getDescription());
            }
            items.replace(itemId, item);
            return item;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void delete(long itemId) throws ItemNotFound {
        if (items.containsKey(itemId)) {
            items.remove(itemId, items.get(itemId));
        } else {
            throw new ItemNotFound("Item не найден");
        }
    }

    @Override
    public List<Item> findAll() {
        return items.values().stream().toList();
    }

    @Override
    public Item findById(long itemId) throws ItemNotFound {
        if (items.containsKey(itemId)) {
            return items.get(itemId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Item> findAllByUserId(long userId) throws ItemNotFound, UserNotFound {
        try {
            return items.values().stream()
                    .filter(item -> item.getOwner().getId().equals(userId)).collect(Collectors.toList());
        } catch (UserNotFound e) {
            throw new ItemNotFound("Юзер не найден");
        }
    }

    @Override
    public List<Item> findAllByItemName(String text) {
        List<Item> findItems = new ArrayList<>();
        if (text != null && !text.isEmpty()) {
            Set<Item> buffer = new HashSet<>();
            buffer.addAll(items.values().stream()
                    .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase()))
                    .filter(item -> item.getAvailable().equals(true))
                    .collect(Collectors.toSet()));
            buffer.addAll(items.values().stream()
                    .filter(item -> item.getDescription().toLowerCase().contains(text.toLowerCase()))
                    .filter(item -> item.getAvailable().equals(true))
                    .collect(Collectors.toSet()));
            findItems.addAll(buffer);
        }
        return findItems;
    }
}