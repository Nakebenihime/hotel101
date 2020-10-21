package org.greeting.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GreetingService {

    @Value("${welcome.message}")
    private String welcome;

    public String greeting(){
        log.info("fetching message from property file => {} ", welcome);
        return welcome;
    }
}
