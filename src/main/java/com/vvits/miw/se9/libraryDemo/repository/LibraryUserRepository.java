package com.vvits.miw.se9.libraryDemo.repository;

import com.vvits.miw.se9.libraryDemo.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {
    Optional<LibraryUser> findByUsername(String username);
}
