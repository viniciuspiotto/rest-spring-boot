package com.piotto.apigateway.controller;

import com.piotto.apigateway.dto.PersonDTO;
import com.piotto.apigateway.mapper.PersonMapper;
import com.piotto.apigateway.model.Person;
import com.piotto.apigateway.services.PersonServices;
import com.piotto.apigateway.util.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonServices service;

    @Autowired
    public PersonController(PersonServices service) {
        this.service = service;
    }

    @GetMapping(
            value = "/{id}",
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML }
    )
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML}
    )
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            consumes = MediaType.APPLICATION_JSON
    )
    public PersonDTO create(@RequestBody Person person) {
        var entity = PersonMapper.INSTANCE.personToPersonDTO(person);
        return service.create(entity);
    }

    @PutMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            consumes = MediaType.APPLICATION_JSON
    )
    public PersonDTO update(@RequestBody Person person) {
        var entity = PersonMapper.INSTANCE.personToPersonDTO(person);
        return service.update(entity);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
