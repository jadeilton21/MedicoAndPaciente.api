package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public String olaMundo() {
        return "Hello World Spring!";
    }

    @GetMapping
    public String olaMundo2(){
        return "Viva a vida Loucamente";
    }
    @GetMapping
    public String olaMundo3(){
        return "Vida Longa a todos nós";
    }


    @GetMapping
    public String olaMundo4(){
        return "Jah rasta";
    }
}
