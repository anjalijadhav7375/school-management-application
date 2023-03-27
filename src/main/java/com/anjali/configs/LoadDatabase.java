package com.anjali.configs;

import com.anjali.model.Student;
import com.anjali.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

@Bean
    CommandLineRunner initDatabase(StudentRepository repository){

    return args -> {
        log.info("Preloading " + repository.save(new Student("Anjali","Jadhav",101,'A')));
        log.info("Preloading " + repository.save(new Student("Rutuja","kamble",102,'B')));
    };
}
}
