package com.example.Cars;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CarRepository repository) throws Exception{
        return args -> {
            log.info("Preloading" + repository.save(new Car(1L,1,true)));
            log.info("Preloading" + repository.save(new Car(2L,2,true)));
            log.info("Preloading" + repository.save(new Car(3L,3,true)));
            log.info("Preloading" + repository.save(new Car(4L,4,true)));
            log.info("Preloading" + repository.save(new Car(5L,5,true)));
            log.info("Preloading" + repository.save(new Car(6L,6,false)));
            log.info("Preloading" + repository.save(new Car(7L,7,true)));
            log.info("Preloading" + repository.save(new Car(8L,8,false)));
            log.info("Preloading" + repository.save(new Car(9L,9,false)));
            log.info("Preloading" + repository.save(new Car(10L,10,false)));

        };
    }
}
