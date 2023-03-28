package com.anjali.assembler;

import com.anjali.controller.AdmissionController;
import com.anjali.model.Admision;
import com.anjali.model.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@Configuration
public class AdmissionAssembler implements RepresentationModelAssembler<Admision, EntityModel<Admision>> {
    @Override
    public EntityModel<Admision> toModel(Admision admision) {
        EntityModel<Admision> admissionModel = EntityModel.of(admision,
                linkTo(methodOn(AdmissionController.class).one(admision.getId())).withSelfRel(),
                linkTo(methodOn(AdmissionController.class).all()).withRel("admission"));

        if (admision.getStatus() == Status.IN_PROGRESS) {
            admissionModel.add(linkTo(methodOn(AdmissionController.class).cancel(admision.getId())).withRel("cancel"));
            admissionModel.add(linkTo(methodOn(AdmissionController.class).complete(admision.getId())).withRel("complete"));
        }
        return admissionModel;
    }
}
