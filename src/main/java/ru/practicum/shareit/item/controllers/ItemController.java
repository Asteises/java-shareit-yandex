package ru.practicum.shareit.item.controllers;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public Item save(@RequestBody ItemDto itemDto, @RequestHeader("X-Sharer-User-Id") long userId) {
        return itemService.save(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public Item patch(@RequestBody ItemDto itemDto, @PathVariable long itemId, @RequestHeader("X-Sharer-User-Id") long userId) {
        return itemService.put(itemDto, itemId, userId);
    }

    @GetMapping("/{itemId}")
    public Item findById(@PathVariable long itemId) {
        return itemService.findById(itemId);
    }

    @GetMapping
    public List<Item> findAllByUserId(@RequestHeader("X-Sharer-User-Id") long userId) {
        return itemService.findAllByUserId(userId);
    }

    @GetMapping("/search")
    public List<Item> findAllByItemName(@RequestParam String text) {
        return itemService.findAllByItemName(text);
    }
}
