package com.piotto.apigateway.mapper;

import com.piotto.apigateway.dto.BookDTO;
import com.piotto.apigateway.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "id", target = "key")
    BookDTO BookToBookDTO(Book book);

    @Mapping(source = "key", target = "id")
    Book BookDTOToBook(BookDTO bookDTO);

    @Mapping(source = "id", target = "key")
    List<BookDTO> BooksToBookDTOs(List<Book> books);
}
