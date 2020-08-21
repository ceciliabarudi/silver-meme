package com.petproject.henlofren;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HenloController {

    @RequestMapping("/")
    public String index() {
        return "Henlo fren";
    }

    @GetMapping("/animals")
    public String all() {
        return "dog";
    }


}