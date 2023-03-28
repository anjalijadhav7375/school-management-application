package com.anjali.configs;

import com.anjali.model.Admision;
import com.anjali.model.Status;
import com.anjali.model.Student;
import com.anjali.repository.AdmissionRepository;
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
    CommandLineRunner initDatabase(StudentRepository studentRepository, AdmissionRepository admissionRepository ){

    return args -> {
        log.info("Preloading " + studentRepository.save(new Student("Anjali","Jadhav",101,'A')));
        log.info("Preloading " + studentRepository.save(new Student("Rutuja","kamble",102,'B')));

        log.info("Preloading " + admissionRepository.save(new Admision("Rutuja Kamble", Status.COMPLETED)));
        log.info("Preloading " + admissionRepository.save(new Admision("Anjali Jadhav",Status.IN_PROGRESS)));

    };
}
}
