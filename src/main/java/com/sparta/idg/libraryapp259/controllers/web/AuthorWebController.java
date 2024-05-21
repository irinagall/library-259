package com.sparta.idg.libraryapp259.controllers.web;

import com.sparta.idg.libraryapp259.model.entities.Author;
import com.sparta.idg.libraryapp259.model.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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


    @GetMapping("/web/author{id}")
    public String getAuthorById(@PathVariable Integer id, Model model){
        Author author = authorRepository.findById(id).orElse(null);
       model.addAttribute("author",author);
       return "author";
    }


    @GetMapping("/web/add-author")
    public String addAuthor(Model model){
        Author author = new Author();
        model.addAttribute("author",author);
        return "add-author";
    }

    @PostMapping("/web/save-author")
    public String saveAuthor(@ModelAttribute("author")Author author){

        authorRepository.save(author);
        return "redirect:/web/authors";
    }

}


