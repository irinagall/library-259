package com.sparta.idg.libraryapp259.model.repositories;

import com.sparta.idg.libraryapp259.model.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findAuthorByFullName(String fullName);
}