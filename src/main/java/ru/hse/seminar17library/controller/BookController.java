package ru.hse.seminar17library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.seminar17library.dto.BookDto;
import ru.hse.seminar17library.entity.Book;
import ru.hse.seminar17library.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Collection<BookDto> listBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);

        return books.stream().map(BookDto::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        return ResponseEntity.of(
                bookRepository.findById(id).map(BookDto::toDto));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<BookDto> getBookByTitle(@PathVariable String title) {
        return ResponseEntity.of(
                bookRepository.findByTitle(title).map(BookDto::toDto));
    }
}
