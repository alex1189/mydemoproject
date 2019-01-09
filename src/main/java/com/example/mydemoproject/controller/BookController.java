package com.example.mydemoproject.controller;

import com.alibaba.fastjson.JSON;
import com.example.mydemoproject.model.Book;
import com.example.mydemoproject.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:8000")
@RestController
@Api(description = "Book system")
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;


    @Autowired
    private AmqpTemplate rabbitTemplate;


    /*
    @GetMapping("/books/page/{pageNumber}")
    public HashMap getAllBooks(@PathVariable("pageNumber") Long pageNumber) {
            System.out.println("Get all books");
            Long start = (pageNumber -1)*5 ;
            List<Book> books = bookService.findAllBook(start);
            HashMap map = new HashMap();
            map.put("books", books);
            Long total = bookService.findTotal();
            map.put("total",total);
            return map;
    }
    */
    @GetMapping(value = "/users")
    @ApiOperation("Get all users")
    public String getBooks(@RequestParam("_page") int _page, @RequestParam("_limit") int _limit,@RequestParam("_searchText") String _searchText) {
        PageHelper.startPage(_page, _limit);
        System.out.println(_searchText);
        System.out.println(_page);
        System.out.println(_searchText.getClass());
        List<Book> booksList;
        if(_searchText.equals("undefined")) {
            booksList = bookService.findAllBook(_page, _limit);
            System.out.println("good"+booksList);
        }else{
            booksList = bookService.searchBook(_searchText);
            System.out.println("search");

        }
        PageInfo<Book> booksPageInfo = new PageInfo<>(booksList);
        System.out.println(booksPageInfo);
        return JSON.toJSONString(booksPageInfo);
    }

    /*
    @PostMapping("/books/create")
    public Book createBook(@Valid @RequestBody Book book) {
      bookService.saveBook(book);
      return book;
    }
    */

    @PostMapping("/users")
    @ApiOperation("Create book")
    public void createUser(@RequestBody Book book){
        bookService.saveBook(book);
    }

    /*
    @ApiOperation("Get one book")
    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable("id") Long id) {
       return bookService.findBookById(id);
    }
    */

    /*
    @ApiOperation("edit one book")
    @PutMapping("/books/{id}")
    public void updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        System.out.println(id);
        System.out.println(book);
        book.setId(id);
        System.out.println(book.getId());
        bookService.updateBook(book);
    }
    */

    @ApiOperation("edit one book")
    @PatchMapping("/users/{id}")
    public void updateBook2(@PathVariable("id") Long id, @RequestBody Book book){

        System.out.println("enter");
        bookService.updateBook(book);
        System.out.println("out");

    }



    @ApiOperation("Delete one book")
    @DeleteMapping("/users/{id}")
    public void deleteBook(@PathVariable("id") Long id)
    {
        rabbitTemplate.convertAndSend("hello", id);

        //bookService.deleteBook(id);
    }
}