package com.kazakova.mylibrarywithrest.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakova.mylibrarywithrest.dto.BookDto;
import com.kazakova.mylibrarywithrest.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Test
    public void testGetAllBooks() throws Exception {
        BookDto book1 = new BookDto(1L, "Red sails", 1L, 1L);
        BookDto book2 = new BookDto(2L, "Kerry", 2L, 1L);
        BookDto book3 = new BookDto(3L, "Mtciri", 4L, 1L);
        BookDto book4 = new BookDto(4L, "Evgeni Onegin", 3L, 1L);
        BookDto book5 = new BookDto(5L, "Cemetery of pets", 2L, 1L);
        BookDto book6 = new BookDto(6L, "Gold cock", 3L, 3L);
        BookDto book7 = new BookDto(7L, "The dead souls", 5L, 2L);
        BookDto book8 = new BookDto(8L, "Romeo and Juliet", 6L, 4L);
        BookDto book9 = new BookDto(9L, "Anna Snegina", 6L, 1L);
        BookDto book10 = new BookDto(10L, "The heart of a dog", 6L, 1L);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/books");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.
                        writeValueAsString(Arrays.asList(book1, book2, book3, book4,
                                book5, book6, book7, book8, book9, book10))));
    }



}
