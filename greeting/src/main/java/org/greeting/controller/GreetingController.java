package org.greeting.controller;

import lombok.extern.slf4j.Slf4j;
import org.greeting.model.Greeting;
import org.greeting.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping(GreetingController.PATH)
public class GreetingController {
    protected static final String PATH = "/api/v1/greetings";

    private final AtomicLong counter =  new AtomicLong();

    private GreetingService greetingService;

    @Autowired
    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping()
    public Greeting greeting(){
        return new Greeting(counter.incrementAndGet(),greetingService.greeting());
    }




}
