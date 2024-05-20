package com.sparta.idg.libraryapp259.controllers.web;

import com.sparta.idg.libraryapp259.model.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookWebController {
    private BookRepository bookRepository;

    @Autowired
    public BookWebController (BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @GetMapping("/web/books")
    public String getAllBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }
}
