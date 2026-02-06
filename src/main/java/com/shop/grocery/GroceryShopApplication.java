package com.shop.grocery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class GroceryShopApplication {

    @RequestMapping("/")
    public String home() {
        return "<html>Hello World<html>";
    }

    public static void main(String[] args) {
        SpringApplication.run(GroceryShopApplication.class, args);
    }

}
