package com.sparta.idg.libraryapp259.controllers;

import com.sparta.idg.libraryapp259.model.entities.Author;
import com.sparta.idg.libraryapp259.model.entities.Book;
import com.sparta.idg.libraryapp259.model.exceptions.AuthorNotFoundException;
import com.sparta.idg.libraryapp259.model.repositories.AuthorRepository;
import com.sparta.idg.libraryapp259.model.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private AuthorRepository authorRepository;

    private final BookRepository bookRepository;

@Autowired
public BookController(BookRepository bookRepository, AuthorRepository authorRepository){
    this.bookRepository=bookRepository;
    this.authorRepository= authorRepository;
}


    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //
    @GetMapping("book/get/title")
    public List<Book> getBookByName(@RequestParam(name="title",required = false)String title){
        return bookRepository.findAll().stream().filter(book -> book.getTitle().contains(title)).toList();
    }

    //
    @PostMapping("/book/post")
    public String addBook(@RequestBody Book book){
    bookRepository.save(book);
    return "Book saved: " + book.getTitle();
    }

    @PostMapping("/book/patch/{id}")
    public Book patchBookById(@PathVariable Integer id,@RequestBody Book book){
        //Get the book that needs to be updated
        Book bookToUpdate = bookRepository.findById(id).get();
        //Update that book object with the changes
        bookToUpdate.setTitle(book.getTitle());
        //Save the book back to the database;
        return bookRepository.save(bookToUpdate);
    }

    //
    @DeleteMapping("/book/delete/{id}")
    public String deleteBookById(@PathVariable int id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return  "Book with Id " + id + " deleted successfully.";

        }else{
            return  "Author with ID" + id + " not found.";
        }
    }

    @PostMapping("/book")
    public String addBookWithAuthorIfAuthorNotPresent(@RequestBody Book book) throws AuthorNotFoundException {
        Optional<Author> author = authorRepository.findAuthorByFullName(book.getAuthor().getFullName());
        if (author.isEmpty()) {
            throw new AuthorNotFoundException(book.getAuthor().getFullName());
        } else {
            book.setAuthor(author.get());
            bookRepository.save(book);
            return "book has been saved";
        }
    }



    @PostMapping("/authors/{authorId}/books")
    public Book createBookByAuthorId(@PathVariable Integer authorId, @RequestBody Book book) throws AuthorNotFoundException {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + authorId));

        book.setAuthor(author);
        return bookRepository.save(book);
    }





}
