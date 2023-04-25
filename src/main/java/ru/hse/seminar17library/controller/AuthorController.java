package ru.hse.seminar17library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.seminar17library.dto.AuthorDto;
import ru.hse.seminar17library.entity.Author;
import ru.hse.seminar17library.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public Collection<AuthorDto> listAuthors() {
        ArrayList<Author> authors = new ArrayList<>();
        authorRepository.findAll().forEach(authors::add);

        return authors.stream().map(AuthorDto::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.of(
                authorRepository.findById(id).map(AuthorDto::toDto));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AuthorDto> getAuthorByName(@PathVariable String name) {
        return ResponseEntity.of(
                authorRepository.findByName(name).map(AuthorDto::toDto));
    }
}
