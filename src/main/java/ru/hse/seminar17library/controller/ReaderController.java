package ru.hse.seminar17library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.seminar17library.dto.BookDto;
import ru.hse.seminar17library.dto.ReaderDto;
import ru.hse.seminar17library.entity.Book;
import ru.hse.seminar17library.entity.Reader;
import ru.hse.seminar17library.exception.BookAlreadyUsedException;
import ru.hse.seminar17library.exception.BookIsNotUsedByUserException;
import ru.hse.seminar17library.exception.EntityNotFoundException;
import ru.hse.seminar17library.repository.BookRepository;
import ru.hse.seminar17library.repository.ReaderRepository;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/readers")
public class ReaderController {
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public ReaderController(ReaderRepository readerRepository, BookRepository bookRepository) {
        this.readerRepository = readerRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public Collection<ReaderDto> listReaders() {
        ArrayList<Reader> readers = new ArrayList<>();
        readerRepository.findAll().forEach(readers::add);

        return readers.stream().map(ReaderDto::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReaderDto> getReaderById(@PathVariable Long id) {
        return ResponseEntity.of(
                readerRepository.findById(id).map(ReaderDto::toDto));
    }

    @PutMapping("/{id}/book/{bookId}")
    public ResponseEntity<BookDto> addBook(@PathVariable Long id, @PathVariable Long bookId) {
        Reader reader = readerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);

        if (book.getReader() != null) {
            throw new BookAlreadyUsedException();
        }

        book.setReader(reader);
        bookRepository.save(book);

        return ResponseEntity.ok(BookDto.toDto(book));
    }

    @DeleteMapping("/{id}/book/{bookId}")
    public ResponseEntity<BookDto> removeBook(@PathVariable Long id, @PathVariable Long bookId) {
        Reader reader = readerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);

        if (book.getReader() != reader) {
            throw new BookIsNotUsedByUserException();
        }

        book.setReader(null);
        bookRepository.save(book);

        return ResponseEntity.ok(BookDto.toDto(book));
    }
}
