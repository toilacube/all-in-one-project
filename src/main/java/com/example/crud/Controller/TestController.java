package com.example.crud.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @PostMapping("/test")
    public @ResponseBody String test() {
        return "Hello abc";
    }
}
