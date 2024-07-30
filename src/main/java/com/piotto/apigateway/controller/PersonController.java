package com.piotto.apigateway.controller;

import com.piotto.apigateway.dto.PersonDTO;
import com.piotto.apigateway.mapper.PersonMapper;
import com.piotto.apigateway.model.Person;
import com.piotto.apigateway.services.PersonServices;
import com.piotto.apigateway.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Tag(name = "Person", description = "Endpoints for Managing Person")
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
    @Operation(summary = "Finds a Person", description = "Finds a Person by ID",
        tags = {"Person"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200",
            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public PersonDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML}
    )
    @Operation(summary = "Finds all Persons", description = "Find all Persons",
            tags = {"Person"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                            )
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            consumes = MediaType.APPLICATION_JSON
    )
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person by a passing in a JSON, XML or YML representation of a person",
            tags = {"Person"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTO create(@RequestBody Person person) {
        var entity = PersonMapper.INSTANCE.personToPersonDTO(person);
        return service.create(entity);
    }

    @PutMapping(
            produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML },
            consumes = MediaType.APPLICATION_JSON
    )
    @Operation(summary = "Updates a Person", description = "Updates a Person by passing in a JSON, XML or YML representation of the person",
            tags = {"Person"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public PersonDTO update(@RequestBody Person person) {
        var entity = PersonMapper.INSTANCE.personToPersonDTO(person);
        return service.update(entity);
    }

    @DeleteMapping(
            value = "/{id}"
    )
    @Operation(summary = "Deletes a Person",
            description = "Deletes a Person by ID",
            tags = {"Person"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
