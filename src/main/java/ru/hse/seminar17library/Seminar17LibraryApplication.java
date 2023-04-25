package ru.hse.seminar17library;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.hse.seminar17library.entity.Author;
import ru.hse.seminar17library.entity.Book;
import ru.hse.seminar17library.entity.Reader;
import ru.hse.seminar17library.repository.AuthorRepository;
import ru.hse.seminar17library.repository.BookRepository;
import ru.hse.seminar17library.repository.ReaderRepository;

@SpringBootApplication
public class Seminar17LibraryApplication {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ReaderRepository readerRepository;

    public Seminar17LibraryApplication(BookRepository bookRepository, AuthorRepository authorRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.readerRepository = readerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Seminar17LibraryApplication.class, args);
    }

    @PostConstruct
    public void loadEntities() {
        Author author1 = new Author()
                .setName("Sergei Lukyanenko");
        Author author2 = new Author()
                .setName("Edgar Allan Poe");
        authorRepository.save(author1);
        authorRepository.save(author2);

        Book book1 = new Book()
                .setTitle("Title1")
                .setDescription("Description1");
        Book book2 = new Book()
                .setTitle("Title2")
                .setDescription("Description2");

        book1.getAuthors().add(author1);
        book2.getAuthors().add(author1);
        book2.getAuthors().add(author2);

        bookRepository.save(book1);
        bookRepository.save(book2);

        Reader reader1 = new Reader()
                .setName("Reader1");
        Reader reader2 = new Reader()
                .setName("Reader1");

        readerRepository.save(reader1);
        readerRepository.save(reader2);
    }
}
