package com.example.mydemoproject.rabbitmq;

import com.example.mydemoproject.service.BookService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @Autowired
    private BookService bookService;


    @RabbitHandler
    public void process(Long hello) {
        bookService.deleteBook(hello);
    }

}
