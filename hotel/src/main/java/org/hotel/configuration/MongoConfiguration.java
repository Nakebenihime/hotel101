package org.hotel.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {

    @Value("${mongo-atlas-uri}")
    private String uri;


    public @Bean
    MongoClient mongoClient() {
        return MongoClients.create(uri);
    }
}
