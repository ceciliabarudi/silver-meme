package com.petproject.henlofren;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HenloController {

    @RequestMapping("/")
    public String index() {
        return "Henlo fren";
    }

}