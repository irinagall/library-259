package com.sparta.idg.libraryapp259;

import com.sparta.idg.libraryapp259.controllers.rest.AuthorController;
import com.sparta.idg.libraryapp259.model.entities.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
class LibraryApp259ApplicationTests {
    private WebTestClient webTestClient;

    @Autowired
    private AuthorController authorController;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:8080").build();
    }

    @Test
    @DisplayName("Check that the status code is 200")
    void checkThatTheStatusCodeIs200() {
        webTestClient.get()
                .uri("http://localhost:8080/get/authors")
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    @DisplayName("Check that the first author is Manish")
    void checkThatTheFirstAuthorIsManish() {
        webTestClient.get().uri("http://localhost:8080/author/1").exchange().expectBody(Author.class).value(author -> Assertions.assertEquals("Manish ", author.getFullName()));
    }
}
