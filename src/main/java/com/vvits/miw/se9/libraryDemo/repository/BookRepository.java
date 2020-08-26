package com.vvits.miw.se9.libraryDemo.repository;

import com.vvits.miw.se9.libraryDemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
}
