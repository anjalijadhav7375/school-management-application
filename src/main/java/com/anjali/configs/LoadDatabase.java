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
        log.info("Preloading " + studentRepository.save(new Student("Anjali","Jadhav","anjalijadhav753@gmail.com",101,'A',"anjalijadhav","anjali7375")));
        log.info("Preloading " + studentRepository.save(new Student("Rutuja","kamble","rutujakamble7147@gmail.com",102,'B',"rutujakamble","rutuja7147")));
        log.info("Preloading " + studentRepository.save(new Student("Ajay","Jadhav","ajayjadhav6162@gmail.com",103,'C',"ajayjadhav","ajay7375")));
        log.info("Preloading " + studentRepository.save(new Student("Pratik","Shinde","pratikshide1212@gmail.com",104,'D',"pratikshinde","pratik1212")));
        log.info("Preloading " + studentRepository.save(new Student("Mahesh","Keskar","maheshkeskar77930@gmail.com",105,'B',"maheshkeskar77930","mahesh77930")));
        log.info("Preloading " + studentRepository.save(new Student("Sakshi","Kasabe","sakshikasabe1001@gmail.com",106,'A',"sakshikasbe","sakshikasbe1001")));
        log.info("Preloading " + studentRepository.save(new Student("Saurabh","Jagtap","saurabhjagtap7474@gmail.com",107,'C',"saurabhjagtap","saurabh7474")));
        log.info("Preloading " + studentRepository.save(new Student("Gayatri","Gavali","gayatrigavali8585@gmail.com",108,'A',"gayatrigavali","gayatri8585")));
        log.info("Preloading " + studentRepository.save(new Student("Divya","Wadkar","divyawadkar1616@gmail.com",109,'B',"divyawadkar","divya1616")));
        log.info("Preloading " + studentRepository.save(new Student("Vrushab","Kambale","vrushabkamble9999@gmail.com",110,'D',"vrushabkamble","vrushab9999")));

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
