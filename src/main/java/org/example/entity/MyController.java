package org.example.entity;

import org.example.annotation.Command;

public class MyController {

    private final Service service;

    @Command("foo")
    public void foo(){
        service.foo();
    }

    @Command("bar")
    public void bar(){
        service.bar();
    }

    @Command("help")
    public void help(){
        service.help();
    }

    public MyController(Service service) {
        this.service = service;
    }
}
