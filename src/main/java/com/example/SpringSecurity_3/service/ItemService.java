package com.example.SpringSecurity_3.service;

import com.example.SpringSecurity_3.model.Item;
import com.example.SpringSecurity_3.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public void addItem(Item item){
        itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        Item item2 =itemRepository.findById(item.getId()).get();

        item2.setId(item.getId());
        item2.setName(item.getName());
        item2.setColor(item.getColor());
        item2.setPrice(item.getPrice());
        item2.setMemory(item.getMemory());
        item2.setImageUrl(item.getImageUrl());


        itemRepository.save(item2);

        return item;

    }

    public Item getById(int id) {

        return itemRepository.findById(id).orElse(null);
    }

    public Item deleteItem(int id) {
        Item item = getById(id);
        itemRepository.deleteById(id);
        return item;
    }


}
