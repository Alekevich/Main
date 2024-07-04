package com.example.SpringSecurity_3.controller;

import com.example.SpringSecurity_3.model.Item;
import com.example.SpringSecurity_3.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/")
    public String main(Model model) {
        model.addAttribute( "items", itemService.getAllItems());
        return "index";
    }

    @GetMapping(value = "/get-item")
    public List<Item> getAll(){
        return itemService.getAllItems();
    }



    @PostMapping(value = "/add-item")
    public String addItem(@RequestBody Item item){
        itemService.addItem(item);
        return "redirect:/";
    }



    @PutMapping(value = "/update-item")
    public Item updateItem(@RequestBody Item item){
        return itemService.updateItem(item);
    }

    @GetMapping(value = "/get-item/{id}")
    public Item getItemById(@PathVariable int id){
        return itemService.getById(id);
    }

    @GetMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable int  id) {
        itemService.deleteItem(id);
        return "redirect:/";
    }

}
