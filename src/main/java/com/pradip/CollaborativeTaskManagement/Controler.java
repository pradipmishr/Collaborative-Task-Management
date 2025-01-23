package com.pradip.CollaborativeTaskManagement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controler {
    // This is a dummy class to test the code quality
    @GetMapping("/hello")
    public String test() {
        return "Hello World";
    }
}
