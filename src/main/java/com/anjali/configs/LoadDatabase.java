package com.anjali.configs;

import com.anjali.model.Admission;
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
        log.info("Preloading " + studentRepository.save(new Student("Ajay","Jadhav",103,'C')));
        log.info("Preloading " + studentRepository.save(new Student("Pratik","Shinde",104,'D')));
        log.info("Preloading " + studentRepository.save(new Student("Mahesh","Keskar",105,'B')));
        log.info("Preloading " + studentRepository.save(new Student("Sakshi","Kasabe",106,'A')));
        log.info("Preloading " + studentRepository.save(new Student("Saurabh","Jagtap",107,'C')));
        log.info("Preloading " + studentRepository.save(new Student("Gayatri","Gavali",108,'A')));
        log.info("Preloading " + studentRepository.save(new Student("Divya","Wadkar",109,'B')));
        log.info("Preloading " + studentRepository.save(new Student("Vrushab","Kambale",110,'D')));

        log.info("Preloading " + admissionRepository.save(new Admission("Rutuja Kamble", Status.COMPLETED)));
        log.info("Preloading " + admissionRepository.save(new Admission("Ajay Jadhav",Status.IN_PROGRESS)));
        log.info("Preloading " + admissionRepository.save(new Admission("Anjali Jadhav",Status.IN_PROGRESS)));
        log.info("Preloading " + admissionRepository.save(new Admission(" Pratik Shinde",Status.CANCELLED)));
        log.info("Preloading " + admissionRepository.save(new Admission(" Saksi Kasabe",Status.COMPLETED)));
        log.info("Preloading " + admissionRepository.save(new Admission("Divya Wadkar ",Status.CANCELLED)));
        log.info("Preloading " + admissionRepository.save(new Admission("Mahesh Keskar",Status.IN_PROGRESS)));

    };
}
}
