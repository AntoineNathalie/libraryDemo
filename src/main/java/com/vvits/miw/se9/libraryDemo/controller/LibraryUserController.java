package com.vvits.miw.se9.libraryDemo.controller;

import com.vvits.miw.se9.libraryDemo.model.LibraryUser;
import com.vvits.miw.se9.libraryDemo.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LibraryUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @GetMapping("/user/new")
    @Secured("ROLE_ADMIN")
    protected String showNewUserForm(Model model){
        model.addAttribute("user", new LibraryUser());
        return "userForm";
    }

    @PostMapping("/user/new")
    @Secured("ROLE_ADMIN")
    protected String saveOrUpdateUser(@ModelAttribute("user") LibraryUser user, BindingResult result){
        if(result.hasErrors()){
            return "userForm";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            libraryUserRepository.save(user);
            return "redirect:/";
        }
    }
}
