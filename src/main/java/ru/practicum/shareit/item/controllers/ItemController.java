package ru.practicum.shareit.item.controllers;

import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.services.ItemService;

import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    //TODO Откуда берем заголовок X-Sharer-User-Id?
    @PostMapping
    public Item save(ItemDto itemDto) {
        return itemService.save(itemDto);
    }

    @PatchMapping("/{itemId}")
    public Item patch(@RequestBody ItemDto itemDto, @PathVariable long itemId) {
        return itemService.put(itemDto, itemId);
    }

    @GetMapping("/{itemId}")
    public Item findById(@PathVariable long itemId) {
        return itemService.findById(itemId);
    }

    @GetMapping
    public List<Item> findAllByUserId(@RequestParam long userId) {
        return itemService.findAllByUserId(userId);
    }
}
