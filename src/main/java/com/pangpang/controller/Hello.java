package com.pangpang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leewake on 2017/8/22 0022.
 */

@RestController
public class Hello {

    @GetMapping("/hello")
    public void hello(String name) {
        System.out.println("hello " + name + " !!!!");
    }

}
