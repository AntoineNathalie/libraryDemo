package com.vvits.miw.se9.libraryDemo.controller;

import com.vvits.miw.se9.libraryDemo.model.Book;
import com.vvits.miw.se9.libraryDemo.model.Copy;
import com.vvits.miw.se9.libraryDemo.repository.BookRepository;
import com.vvits.miw.se9.libraryDemo.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class CopyController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CopyRepository copyRepository;

    @GetMapping("/copy/add/{bookId}")
    protected String addCopy(@PathVariable("bookId") final Integer bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()){
            Copy copy = new Copy();
            copy.setBook(book.get());
            copyRepository.save(copy);
        }
        return "forward:/books";
    }

    @GetMapping("/copy/delete/{copyId}")
    protected String deleteCopy(@PathVariable("copyId") final Integer copyId) {
        Optional<Copy> copy = copyRepository.findById(copyId);
        if (copy.isPresent()) {
            int bookId = copy.get().getBook().getBookId();
            copyRepository.delete(copy.get());
            return "forward:/books/" + bookId;
        }
        return "forward:/books";
    }
}
