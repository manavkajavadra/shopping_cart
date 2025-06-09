package com.example.shopping_cart.controllerui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/category")
public class CategoryUIController {

    @GetMapping("/list")
    public String welcome(){
        return "category_list";
    }
}
