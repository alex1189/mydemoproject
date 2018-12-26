package com.example.mydemoproject.service.impl;

import com.example.mydemoproject.dao.BookDao;
import com.example.mydemoproject.model.Book;
import com.example.mydemoproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public List<Book> findAllBook( ){
        return bookDao.findAllBook();
    }

    public Book findBookById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public Long saveBook(Book book) {
        return bookDao.saveBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Long deleteBook(Long id) {
        return bookDao.deleteBook(id);
    }

    @Override
    public  Long findTotal(){return bookDao.findTotal();}

    @Override
    public List<Book> searchBook(String str){return bookDao.searchBook(str);}

}
