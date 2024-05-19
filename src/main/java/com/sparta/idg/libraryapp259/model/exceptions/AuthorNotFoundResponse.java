package com.sparta.idg.libraryapp259.model.exceptions;

//JSON Body of our ResponseEntity
public class AuthorNotFoundResponse {
   private String message;
   private int statusCode;
   private String url;

    public AuthorNotFoundResponse(String message, int statusCode, String url) {
        this.message = message;
        this.statusCode = statusCode;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
