package ru.hse.seminar17library.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.hse.seminar17library.entity.Author;
import ru.hse.seminar17library.entity.Book;
import ru.hse.seminar17library.entity.Reader;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Data
@Accessors(chain = true)
public class BookDto {
    private Long id;
    private String title;
    private String description;
    private Long readerId;
    private Set<Long> authors = new HashSet<>();

    public static BookDto toDto(Book book) {
        return new BookDto()
                .setId(book.getId())
                .setTitle(book.getTitle())
                .setDescription(book.getDescription())
                .setReaderId(ofNullable(book.getReader())
                        .map(Reader::getId)
                        .orElse(null))
                .setAuthors(book.getAuthors().stream()
                        .map(Author::getId)
                        .collect(Collectors.toSet()));
    }
}
