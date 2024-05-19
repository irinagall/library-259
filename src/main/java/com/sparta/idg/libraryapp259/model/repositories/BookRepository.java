package com.sparta.idg.libraryapp259.model.repositories;

import com.sparta.idg.libraryapp259.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    //need for hateos get all books by author id
    //List<Book> findBookByAuthor_Id(Integer id);
}