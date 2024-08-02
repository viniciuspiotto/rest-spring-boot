package com.piotto.apigateway.mapper;

import com.piotto.apigateway.dto.PersonDTO;
import com.piotto.apigateway.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(source = "id", target = "key")
    PersonDTO personToPersonDTO(Person person);

    @Mapping(source = "key", target = "id")
    Person personDTOToPerson(PersonDTO personDTO);

    @Mapping(source = "id", target = "key")
    List<PersonDTO> personsToPersonDTOs(List<Person> persons);
}
