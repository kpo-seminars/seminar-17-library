package ru.hse.seminar17library.repository;

import org.springframework.data.repository.CrudRepository;
import ru.hse.seminar17library.entity.Reader;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
}
