package com.sparta.idg.libraryapp259.controllers.web;

import com.sparta.idg.libraryapp259.model.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Controller
public class AuthorWebController {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorWebController(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }


    @GetMapping("/welcome")
    public String welcome(Model model){
        model.addAttribute("time", LocalDateTime.now());
        return "welcome";
        // will resolve to welcome.html
    }

    @GetMapping("/web/authors")
    public String getAllAutors(Model model){
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";

    }
}


