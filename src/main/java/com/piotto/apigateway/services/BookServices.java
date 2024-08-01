package com.piotto.apigateway.services;

import com.piotto.apigateway.controller.BookController;
import com.piotto.apigateway.dto.BookDTO;
import com.piotto.apigateway.exceptions.ResourceNotFoundException;
import com.piotto.apigateway.mapper.BookMapper;
import com.piotto.apigateway.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private final Logger logger = Logger.getLogger(BookServices.class.getName());

    private final BookRepository repository;

    @Autowired
    public BookServices(BookRepository repository) {
        this.repository = repository;
    }

    public BookDTO findById(Long id) {

        logger.info("Finding one book!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Book found with id: " + id));
        BookDTO dto = BookMapper.INSTANCE.BookToBookDTO(entity);
        dto.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<BookDTO> findAll() {
        logger.info("Finding all Books!");

        var books = BookMapper.INSTANCE.BooksToBookDTOs(repository.findAll());
        books
                .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return books;
    }

    public BookDTO create(BookDTO book) {
        logger.info("Creating a new Book!");
        var entity = BookMapper.INSTANCE.BookDTOToBook(book);
        var dto = BookMapper.INSTANCE.BookToBookDTO(repository.save(entity));
        dto.add(linkTo(methodOn(BookController.class).findById(entity.getId())).withSelfRel());
        return dto;
    }

    public BookDTO update(BookDTO book) {
        logger.info("Updating a Book!");

        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No Book found with id: " + book.getKey()));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = BookMapper.INSTANCE.BookToBookDTO(repository.save(entity));
        dto.add(linkTo(methodOn(BookController.class).findById(entity.getId())).withSelfRel());

        return dto;
    }

    public void delete(Long id) {
        logger.info("Deleting a Book!");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Book found with id: " + id));
        repository.delete(entity);
    }
}
