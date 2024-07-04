package com.example.SpringSecurity_3.controller;

import com.example.SpringSecurity_3.model.Item;
import com.example.SpringSecurity_3.model.User;
import com.example.SpringSecurity_3.repository.UserRepository;
import com.example.SpringSecurity_3.service.ItemService;
import com.example.SpringSecurity_3.service.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ServiceUser serviceUser;
    private final ItemService itemService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/")
    public String mainPage(Model model){
        model.addAttribute( "items", itemService.getAllItems());
        System.out.println(serviceUser.getCurrentUser().getUsername());
        return "index";
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping(value = "/sign-in")
    public String signIn(){
        return "sign-in";
    }


    @GetMapping("/add-page")
    public String showAddPhoneForm(Model model) {
        model.addAttribute("phone", new Item());
        return "add-page";
    }

    @PostMapping("/add-item")
    public String addItem(@ModelAttribute Item item, Model model) {
        itemService.addItem(item);
        model.addAttribute("phone", item);
        return "redirect:/";
    }

    @GetMapping("/delete-item/{id}")
    public String deleteItem(@PathVariable int  id) {
        itemService.deleteItem(id);
        return "redirect:/";
    }





    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin")
    public String adminPage(){
        return "admin";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/change-password")
    public String changePassword(@RequestParam String currentPass,
                                 @RequestParam String newPass,
                                 @RequestParam String reNewPass){

       String result =  serviceUser.changePass(currentPass,newPass,reNewPass);
        return "redirect:/" + result;

    }


    @GetMapping(value = "/forbidden")
    public String forbidden(){
        return "forbidden";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/register")
    public String register(User user, @RequestParam String rePassword){
        String result =  serviceUser.addUser(user, rePassword);
        return "redirect:/sign-in?"+ result;
    }


    @GetMapping(value = "/change-password")
    public String change(){
        return "change-password";
    }

}
