package ru.practicum.shareit.item.repositoryes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ItemDao implements ItemStorage {

    private ItemMapper itemMapper;
    private UserService userService;
    private Map<Long, Item> items = new HashMap<>();

    @Override
    public Item save(ItemDto itemDto, long userId) throws UserNotFound {
        try {
            User user = userService.findById(userId);
            Item item = itemMapper.toItem(itemDto);
            item.setOwner(user);
            items.put(item.getId(), item);
            return item;
        } catch (UserNotFound e) {
            throw new UserNotFound("Юзер не найден");
        }
    }

    @Override
    public Item put(ItemDto itemDto, long itemId) throws ItemNotFound {
        if (items.containsKey(itemId)) {
            Item item = items.get(itemId);
            if (itemDto.getName() != null) {
                item.setName(itemDto.getName());
            }
            if (itemDto.getDescription() != null) {
                item.setDescription(itemDto.getDescription());
            }
            if (itemDto.isAvailable()) {
                item.setAvailable(itemDto.isAvailable());
            }
            items.replace(itemId, item);
            return item;
        } else {
            throw new ItemNotFound("Item не найден");
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
            throw new ItemNotFound("Item не найден");
        }
    }

    @Override
    public List<Item> findAllByUserId(long userId) throws ItemNotFound, UserNotFound {
        try {
            User user = userService.findById(userId);
            //TODO Нужно что-то исполнить со стримом, чтобы красиво было
//            return items.entrySet().stream();
        } catch (UserNotFound e) {
            throw new ItemNotFound("Юзер не найден");
        }
    }
}
