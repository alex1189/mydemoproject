package com.example.mydemoproject.service;

import com.example.mydemoproject.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAllBook( );


    Book findBookById(Long id);


    Long saveBook(Book book);


    void updateBook(Book book);


    Long deleteBook(Long id);

    Long findTotal();

    List<Book> searchBook(String str);
}
