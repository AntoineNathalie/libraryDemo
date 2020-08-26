package com.vvits.miw.se9.libraryDemo.service;

import com.vvits.miw.se9.libraryDemo.model.LibraryUser;
import com.vvits.miw.se9.libraryDemo.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryUserDetailsService implements UserDetailsService {

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return libraryUserRepository.findByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("User " + s + " was not found")
        );
    }
}
