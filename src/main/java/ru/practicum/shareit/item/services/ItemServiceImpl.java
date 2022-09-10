package ru.practicum.shareit.item.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.handler.exceptions.ItemNotFound;
import ru.practicum.shareit.handler.exceptions.UserNotFound;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repositoryes.ItemStorage;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repositoryes.UserStorage;

import java.util.List;

@Service
@RequiredArgsConstructor // Создает конструктор из тех полей которые нужны
public class ItemServiceImpl implements ItemService {

    private final ItemStorage itemStorage; // Если стоит final для неинициализированного поля то конструктор нужен обязательно
    private final UserStorage userStorage;

    private static long itemId = 0;

    @Override
    public ItemDto save(ItemDto itemDto, long userId) throws UserNotFound {
        if (itemStorage.findById(userId) != null) {
            User user = userStorage.findById(userId);
            Item item = ItemMapper.toItem(itemDto);
            item.setId(++itemId);
            item.setOwner(user);
            return itemStorage.save(item);
        } else {
            throw new UserNotFound("Юзер не найден");
        }
    }

    @Override
    public ItemDto put(ItemDto itemDto, long itemId, long userId) throws ItemNotFound, UserNotFound {
        try {
            Item item = itemStorage.findById(itemId);
            if (item.getOwner() != null && item.getOwner().getId().equals(userId)) {
                if (itemDto.getAvailable() != null) {
                    item.setAvailable(itemDto.getAvailable());
                }
                if (itemDto.getName() != null) {
                    item.setName(itemDto.getName());
                }
                if (itemDto.getDescription() != null) {
                    item.setDescription(itemDto.getDescription());
                }
                itemStorage.put(item, itemId);
                return ItemMapper.toItemDto(item);
            } else {
                throw new UserNotFound(String.format("User %s not found", userId));
            }
        } catch (ItemNotFound e) {
            throw new ItemNotFound(String.format("Item %s not found", itemId));
        }
    }

    @Override
    public void delete(long itemId) throws ItemNotFound {
        itemStorage.delete(itemId);
    }

    @Override
    public List<Item> findAll() {
        return itemStorage.findAll();
    }

    @Override
    public Item findById(long itemId) throws ItemNotFound {
        return itemStorage.findById(itemId);
    }

    @Override
    public List<Item> findAllByUserId(long userId) {
        return itemStorage.findAllByUserId(userId);
    }

    @Override
    public List<Item> findAllByItemName(String text) {
        return itemStorage.findAllByItemName(text);
    }
}
