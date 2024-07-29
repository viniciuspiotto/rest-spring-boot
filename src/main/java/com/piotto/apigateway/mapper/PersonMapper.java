package com.piotto.apigateway.mapper;

import com.piotto.apigateway.dto.PersonDTO;
import com.piotto.apigateway.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO personToPersonDTO(Person person);
    Person personDTOToPerson(PersonDTO personDTO);

    List<PersonDTO> personsToPersonDTOs(List<Person> persons);
    List<Person> personDTOsToPersons(List<PersonDTO> personDTOs);
}
