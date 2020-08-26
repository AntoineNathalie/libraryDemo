package com.vvits.miw.se9.libraryDemo.controller;

import com.vvits.miw.se9.libraryDemo.model.Book;
import com.vvits.miw.se9.libraryDemo.repository.AuthorRepository;
import com.vvits.miw.se9.libraryDemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class BooksController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping({"/", "/books"})
    protected String showBooks(Model model){
        model.addAttribute("allBooks", bookRepository.findAll());
        return "bookOverview";
    }

    @GetMapping("/books/add")
    protected String showBookForm(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("authorList", authorRepository.findAll());
        return "bookForm";
    }

    @GetMapping("/books/update")
    protected String showBookUpdateForm(Model model){
        Book book = bookRepository.findByTitle("hoi").orElse(new Book());
        model.addAttribute("book", book);
        model.addAttribute("authorList", authorRepository.findAll());
        return "bookForm";
    }

    @GetMapping("/books/{id}")
    protected String showBook(@PathVariable("id") final Integer bookId, Model model){
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "bookDetail";
        }
        return "redirect:/books";
    }

    @PostMapping({"/books/add"})
    protected String saveOrUpdateBook(@ModelAttribute("book") Book book, BindingResult result){
        if (result.hasErrors()) {
            return "bookForm";
        } else {
            bookRepository.save(book);
            return "redirect:/books";
        }
    }
}
