package com.sparta.idg.libraryapp259.controllers.web;

import com.sparta.idg.libraryapp259.model.entities.Author;
import com.sparta.idg.libraryapp259.model.entities.Book;
import com.sparta.idg.libraryapp259.model.repositories.AuthorRepository;
import com.sparta.idg.libraryapp259.model.repositories.BookRepository;
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
    private final BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorWebController(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @GetMapping("/welcome")
    public String welcome(Model model){
        model.addAttribute("time", LocalDateTime.now());
        return "welcome";
        // will resolve to welcome.html
    }

    @GetMapping("/web/authors")
    public String getAllAuthors(Model model){
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

    @GetMapping("/web/delete/{id}")
    public String deleteAuthorById(@PathVariable Integer id, Model model){
        authorRepository.findById(id).ifPresent(authorToDelete->authorRepository.delete(authorToDelete));
        return "redirect:/web/authors";
    }

    @GetMapping("/web/update/{id}")
    public String editAuthor(@PathVariable Integer id, Model model){
        Author author = new Author();
        model.addAttribute("author",author);
        model.addAttribute("id",id);

        return "update-author";
    }

    @PostMapping("/web/update-author/{id}")
    public String updateAuthor(@PathVariable Integer id, @ModelAttribute("author") Author author){
        Author authorToUpdate = authorRepository.findById(id).orElse(null);
        authorToUpdate.setFullName(author.getFullName());
        authorRepository.save(authorToUpdate);
        return "redirect:/web/authors";
    }


}


