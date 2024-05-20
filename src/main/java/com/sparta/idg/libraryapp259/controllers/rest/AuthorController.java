package com.sparta.idg.libraryapp259.controllers.rest;

import com.sparta.idg.libraryapp259.model.entities.Author;
import com.sparta.idg.libraryapp259.model.repositories.AuthorRepository;
import com.sparta.idg.libraryapp259.model.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Locale.filter;
import static java.util.stream.StreamSupport.stream;


@RestController
public class AuthorController {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository){
        this.authorRepository = authorRepository;
    }

    //authors - to return a List (Authors, books, etc.)
    //author - to return an Optional (returns an object e.g. author)


    @GetMapping("get/authors")
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }


    //@GetMapping("/authors") -would cause an error because when this  method gets called will not know which method to call
    //
    @GetMapping("/author/get/name")       //if I am happy with the request param just being called 'name' then I can also do this ((@RequestParam() String name) //path becomes localhost:8080/author/get/name?name=Alex
    public List<Author> getAuthorByName(@RequestParam(name="fullName",required=false) String name){
                                            /*//localhost:8080/author/get/name?fullName=string*/
        return authorRepository.findAll()//List<Author>
        .stream()//Stream<Author>
        .filter(author->author.getFullName().contains(name))
                .toList();
                //.collect(Collectors.toList());
    }


    //
    @PostMapping("/author/post")
    public String addAuthor(@RequestBody Author author){
        authorRepository.save(author);
        return "Author saved: " + author.getFullName();
    }


    //
    @PutMapping("/author/patch/{id}")
    public Author patchAuthorById(@PathVariable Integer id, @RequestBody Author author){
        //Get the author that needs to be updated
        Author authorToUpdate = authorRepository.findById(id).get();
        //Update that author object with the changes
        authorToUpdate.setFullName(author.getFullName());
        //Save them back to the database
        return authorRepository.save(authorToUpdate);
    }

    //
    @DeleteMapping("/author/delete/{id}")
    public String deleteAuthorById(@PathVariable int id){
        if (authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return "Author with Id" + id + "deleted successfully.";
        } else{
            return "Author with ID" + id + " not found.";
        }

    }

    //
    //get author by id
    //get author by book title
    //get the author that has a set number of books

    //create book by author Id method;

    //HATEOS
    @GetMapping("author/{id}")
    public EntityModel<Author> getAuthorById(@PathVariable Integer id) {
        return authorRepository.findById(id)
                .map(author -> EntityModel.of(author,
                        linkTo(methodOn(AuthorController.class).getAuthorById(id)).withSelfRel(),
                        linkTo(methodOn(AuthorController.class).getAllAuthors()).withRel("authors"))).orElseThrow();

                       /* //Link.of("https://www.example.com", "example"))).orElseThrow();
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AuthorController.class).getAuthorById(id)).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AuthorController.class).getAllAuthors()).withRel("all authors"))).orElseThrow();,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AuthorController.class).getBookByAuthorId(id)).withRel("books"))).orElseThrow()
        */
    }
}
