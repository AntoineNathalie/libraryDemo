package com.vvits.miw.se9.libraryDemo.controller;

import com.vvits.miw.se9.libraryDemo.model.Author;
import com.vvits.miw.se9.libraryDemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController {

    @Autowired
    AuthorRepository authorRepository;

    @GetMapping("/author")
    protected String showAuthors(Model model){
        model.addAttribute("allAuthors", authorRepository.findAll());
        model.addAttribute("author", new Author());
        return "authorOverview";
    }

    @PostMapping("/author/new")
    protected String saveOrUpdateAuthor(@ModelAttribute("author") Author author, BindingResult result){
        if (result.hasErrors()){
            return "authorOverview";
        } else {
            authorRepository.save(author);
            return "redirect:/author";
        }
    }
}
