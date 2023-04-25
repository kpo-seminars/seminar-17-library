package ru.hse.seminar17library.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.hse.seminar17library.entity.Book;
import ru.hse.seminar17library.entity.Reader;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class ReaderDto {
    private Long id;
    private String name;
    private Set<Long> books;

    public static ReaderDto toDto(Reader reader) {
        return new ReaderDto()
                .setId(reader.getId())
                .setName(reader.getName())
                .setBooks(reader.getBooks().stream()
                        .map(Book::getId)
                        .collect(Collectors.toSet()));
    }
}
