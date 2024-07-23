package com.piotto.apigateway.services;

import com.piotto.apigateway.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id) {

        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAddress("Uberlandia");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all persons!");

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person create(Person person) {
        logger.info("Creating a new person!");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating a person!");

        return person;
    }

    public void delete(String id) {
        logger.info("Deleting a person!");

    }

    public Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Name " + i);
        person.setLastName("LastName " + i);
        person.setAddress("Address " + i);
        person.setGender("Gender " + i);

        return person;
    }
}
