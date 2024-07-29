package com.piotto.apigateway.services;

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
        return PersonMapper.INSTANCE.personToPersonDTO(entity);
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons!");

        return PersonMapper.INSTANCE.personsToPersonDTOs(repository.findAll());
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating a new person!");
        var entity = PersonMapper.INSTANCE.personDTOToPerson(person);
        return PersonMapper.INSTANCE.personToPersonDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating a person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + person.getId()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return PersonMapper.INSTANCE.personToPersonDTO(repository.save(entity));
    }

    public void delete(Long id) {
        logger.info("Deleting a person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + id));
        repository.delete(entity);
    }
}
