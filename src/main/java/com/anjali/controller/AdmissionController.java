package com.anjali.controller;

import com.anjali.assembler.AdmissionAssembler;
import com.anjali.exception.AdmissionNotFoundException;
import com.anjali.model.Admission;
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
    public CollectionModel<EntityModel<Admission>>all(){
        List<EntityModel<Admission>> orders= admissionRepository.findAll().stream()
                .map(admissionAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(AdmissionController.class).all()).withSelfRel());
    }
   @GetMapping("/admissions/{id}")
   public EntityModel<Admission>one(@PathVariable Long id){
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(()->new AdmissionNotFoundException(id));
        return admissionAssembler.toModel(admission);
   }

   @PostMapping("/admissions")
    ResponseEntity<EntityModel<Admission>> newAdmission(@RequestBody Admission admission){
        admission.setStatus(Status.IN_PROGRESS);
        Admission newAdmission=admissionRepository.save(admission);

        return ResponseEntity
                .created(linkTo(methodOn(AdmissionController.class).one(newAdmission.getId())).toUri())
                .body(admissionAssembler.toModel(newAdmission));
    }

    @DeleteMapping("/admissions/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable Long id){
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(()->new AdmissionNotFoundException(id));
        if(admission.getStatus()==Status.CANCELLED){
            admission.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(admissionAssembler.toModel(admissionRepository.save(admission)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE , MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You cant cancel an admission that is in the "+ admission.getStatus() + "Status"));
    }

    @PutMapping("admission/{id}/complete")
    public ResponseEntity<?>complete(@PathVariable Long id){
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(()->new AdmissionNotFoundException(id));
        if(admission.getStatus()==Status.IN_PROGRESS){
            admission.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(admissionAssembler.toModel(admissionRepository.save(admission)));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE,MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                        .withTitle("Method not allowed")
                        .withDetail("You cant Confirm an admission Which is already Complete "+ admission.getStatus()+ "Status"));
    }

}
