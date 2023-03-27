package com.anjali.controller;

import com.anjali.assembler.StudentModelAssembler;
import com.anjali.exception.StudentNotFoundException;
import com.anjali.model.Student;
import com.anjali.repository.StudentRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


public class StudentController {
  private final StudentRepository repository;
  private final StudentModelAssembler assembler;
    public StudentController(StudentRepository repository, StudentModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/student")
    public CollectionModel<EntityModel<Student>>all() {


        List<EntityModel<Student>> students = repository.findAll()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(students,linkTo(methodOn(StudentController.class).all()).withRel("student"));
    }



    @PostMapping("/student")
    Student newStudent(@RequestBody Student newStudent){
        return repository.save(newStudent);
    }

    //single item

    @GetMapping("/student{id}")
    public EntityModel<Student> one(@PathVariable Long id){

        Student student = repository.findById(id)
                .orElseThrow(()->new StudentNotFoundException(id));
        return assembler.toModel(student);
    }
    @PutMapping("/student{id}")
    Student replaceStudent(@RequestBody Student newStudent
    ,@PathVariable Long id) {
        return repository.findById(id)
                .map(student -> {
                    student.setFirstname(newStudent.getFirstname());
                    student.setLastname(newStudent.getLastname());
                    student.setRollNum(newStudent.getRollNum());
                    student.setSection(newStudent.getSection());
                    return repository.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return repository.save(newStudent);
                });
    }

        @DeleteMapping("/student{id}")
                void deleteStudent(@PathVariable Long id ){
            repository.deleteById(id);

        }

}
