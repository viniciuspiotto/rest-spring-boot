package com.piotto.apigateway.services;

import com.piotto.apigateway.exceptions.ResourceNotFoundException;
import com.piotto.apigateway.model.Person;
import com.piotto.apigateway.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id) {

        logger.info("Finding one person!");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + id));
    }

    public List<Person> findAll() {
        logger.info("Finding all persons!");

        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating a new person!");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating a person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + person.getId()));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Deleting a person!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No person found with id: " + id));
        repository.delete(entity);
    }
}
