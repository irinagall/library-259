package com.sparta.idg.libraryapp259.model.exceptions;

public class AuthorNotFoundException extends Exception {

    public AuthorNotFoundException(String fullName){
        super("Could not find Author: " +fullName);
    }


















}
