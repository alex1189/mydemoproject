package com.example.mydemoproject.dao;

import com.example.mydemoproject.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface BookDao {

    List<Book> findAllBook();

    Book findById(@Param("id") Long id);

    Long saveBook(Book book);

    void updateBook(Book book);

    Long deleteBook(Long id);

    Long findTotal();

    List<Book> searchBook(String str);
}
