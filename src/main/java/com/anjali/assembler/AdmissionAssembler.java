package com.anjali.assembler;

import com.anjali.controller.AdmissionController;
import com.anjali.model.Admission;
import com.anjali.model.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@Configuration
public class AdmissionAssembler implements RepresentationModelAssembler<Admission, EntityModel<Admission>> {
    @Override
    public EntityModel<Admission> toModel(Admission admission) {
        EntityModel<Admission> admissionModel = EntityModel.of(admission,
                linkTo(methodOn(AdmissionController.class).one(admission.getId())).withSelfRel(),
                linkTo(methodOn(AdmissionController.class).all()).withRel("admission"));

        if (admission.getStatus() == Status.IN_PROGRESS) {
            admissionModel.add(linkTo(methodOn(AdmissionController.class).cancel(admission.getId())).withRel("cancel"));
            admissionModel.add(linkTo(methodOn(AdmissionController.class).complete(admission.getId())).withRel("complete"));
        }
        return admissionModel;
    }
}
