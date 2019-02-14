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
            log.info("Preloading" + repository.save(new Car(1L,"6a6ef1be-30a6-11e9-b210-d663bd873d93",true)));
            log.info("Preloading" + repository.save(new Car(2L,"6a6ef466-30a6-11e9-b210-d663bd873d93",true)));
            log.info("Preloading" + repository.save(new Car(3L,"6a6ef5c4-30a6-11e9-b210-d663bd873d93",true)));
            log.info("Preloading" + repository.save(new Car(4L,"6a6ef902-30a6-11e9-b210-d663bd873d93",true)));
            log.info("Preloading" + repository.save(new Car(5L,"6a6efa56-30a6-11e9-b210-d663bd873d93",true)));
            log.info("Preloading" + repository.save(new Car(6L,"6a6efb8c-30a6-11e9-b210-d663bd873d93",false)));
            log.info("Preloading" + repository.save(new Car(7L,"6a6efcc2-30a6-11e9-b210-d663bd873d93",true)));
            log.info("Preloading" + repository.save(new Car(8L,"6a6efdf8-30a6-11e9-b210-d663bd873d93",false)));
            log.info("Preloading" + repository.save(new Car(9L,"6a6f0690-30a6-11e9-b210-d663bd873d93",false)));
            log.info("Preloading" + repository.save(new Car(10L,"6a6f085c-30a6-11e9-b210-d663bd873d93",false)));

        };
    }
}
