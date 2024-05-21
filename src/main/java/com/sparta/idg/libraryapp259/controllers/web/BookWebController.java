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

@Controller
public class BookWebController {
    private final AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public BookWebController (BookRepository bookRepository, AuthorRepository authorRepository){
        this.bookRepository=bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/web/books")
    public String getAllBooks(Model model){
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/web/book{id}")
    public String getAuthorById(@PathVariable Integer id, Model model){
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book",book);
        return "book";
    }

    @GetMapping("/web/add-book")
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "add-book";

    }

    @PostMapping("/web/save-book")
    public String saveBook(@ModelAttribute("book")Book book){

        bookRepository.save(book);
        return "redirect:/web/books";
    }

    @GetMapping("/web/books/delete/{id}")
    public String deleteBookById(@PathVariable Integer id, Model model){
        bookRepository.findById(id).ifPresent(bookToDelete->bookRepository.delete(bookToDelete));
        return "redirect:/web/books";
    }

    @GetMapping("/web/books/update/{id}")
    public String editBook(@PathVariable Integer id, Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("id",id);

        return "update-book";
    }

    @PostMapping("/web/update-book/{id}")
    public String updateBook(@PathVariable Integer id, @ModelAttribute("book") Book book){
        Book bookToUpdate =bookRepository.findById(id).orElse(null);
        bookToUpdate.setTitle(book.getTitle());
        bookRepository.save(bookToUpdate);
        return "redirect:/web/books";
    }

}
