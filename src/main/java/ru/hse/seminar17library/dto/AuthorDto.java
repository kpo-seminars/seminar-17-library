package ru.hse.seminar17library.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.hse.seminar17library.entity.Author;
import ru.hse.seminar17library.entity.Book;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class AuthorDto {
    private Long id;
    private String name;
    private Set<Long> books;

    public static AuthorDto toDto(Author author) {
        return new AuthorDto()
                .setId(author.getId())
                .setName(author.getName())
                .setBooks(author.getBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet()));
    }
}
