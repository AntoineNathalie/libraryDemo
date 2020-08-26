package com.vvits.miw.se9.libraryDemo.repository;

import com.vvits.miw.se9.libraryDemo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
