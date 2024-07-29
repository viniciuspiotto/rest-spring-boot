package com.piotto.apigateway.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.piotto.apigateway.controller.PersonController;
import com.piotto.apigateway.dto.PersonDTO;
import com.piotto.apigateway.exceptions.ResourceNotFoundException;
import com.piotto.apigateway.mapper.PersonMapper;
import com.piotto.apigateway.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    private final PersonRepository repository;

    @Autowired
    public PersonServices(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonDTO findById(Long id) {

        logger.info("Finding one person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + id));
        PersonDTO dto = PersonMapper.INSTANCE.personToPersonDTO(entity);
        dto.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons!");

        var persons = PersonMapper.INSTANCE.personsToPersonDTOs(repository.findAll());
        persons
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating a new person!");
        var entity = PersonMapper.INSTANCE.personDTOToPerson(person);
        var dto = PersonMapper.INSTANCE.personToPersonDTO(repository.save(entity));
        dto.add(linkTo(methodOn(PersonController.class).findById(entity.getId())).withSelfRel());
        return dto;
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating a person!");

        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + person.getKey()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = PersonMapper.INSTANCE.personToPersonDTO(repository.save(entity));
        dto.add(linkTo(methodOn(PersonController.class).findById(entity.getId())).withSelfRel());

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting a person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + id));
        repository.delete(entity);
    }
}
