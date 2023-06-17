package com.itm.space.taskservice.initializer;

import com.itm.space.taskservice.utils.PropertySource;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
public class MongoInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext>  {

    @Container
    public static final MongoDBContainer container = new MongoDBContainer("mongo:5.0.15");
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        container.start();
        TestPropertyValues.of(

                PropertySource.MONGODB_URL + container.getReplicaSetUrl())
                .applyTo(applicationContext.getEnvironment());

    }
}
