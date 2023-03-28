package com.anjali.controller;

import com.anjali.assembler.AdmissionAssembler;
import com.anjali.exception.AdmissionNotFoundException;
import com.anjali.model.Admision;
import com.anjali.model.Status;
import com.anjali.repository.AdmissionRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AdmissionController {
    private final AdmissionRepository admissionRepository;
    private final AdmissionAssembler admissionAssembler;

    public AdmissionController(AdmissionRepository admissionRepository, AdmissionAssembler admissionAssembler) {
        this.admissionRepository = admissionRepository;
        this.admissionAssembler = admissionAssembler;
    }
    @GetMapping("/admissions")
    public CollectionModel<EntityModel<Admision>>all(){
        List<EntityModel<Admision>> orders= admissionRepository.findAll().stream()
                .map(admissionAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(AdmissionController.class).all()).withSelfRel());
    }
   @GetMapping("/admissions{id}")
   public EntityModel<Admision>one(@PathVariable Long id){
        Admision admision = admissionRepository.findById(id)
                .orElseThrow(()->new AdmissionNotFoundException(id));
        return admissionAssembler.toModel(admision);
   }

   @PostMapping("/admissions")
    ResponseEntity<EntityModel<Admision>> newAdmission(@RequestBody Admision admision){
        admision.setStatus(Status.IN_PROGRESS);
        Admision newAdmission=admissionRepository.save(admision);

        return ResponseEntity
                .created(linkTo(methodOn(AdmissionController.class).one(newAdmission.getId())).toUri())
                .body(admissionAssembler.toModel(newAdmission));
    }

    @DeleteMapping("/admissions{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id){
        Admision admision = admissionRepository.findById(id)
                .orElseThrow(()->new AdmissionNotFoundException(id));
        if(admision.getStatus()==Status.CANCELLED){
            admision.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(admissionAssembler.toModel(admissionRepository.save(admision)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE , MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You cant cancel an admission that is in the " + "Status"));
    }

    @PutMapping("admission{id}/complete")
    public ResponseEntity<?>complete(@PathVariable Long id){
        Admision admision = admissionRepository.findById(id)
                .orElseThrow(()->new AdmissionNotFoundException(id));
        if(admision.getStatus()==Status.COMPLETED){
            admision.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(admissionAssembler.toModel(admissionRepository.save(admision)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE,MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You cant cancel an admission that is in the " + "Status"));
    }

}
