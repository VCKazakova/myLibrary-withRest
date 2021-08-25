package com.kazakova.mylibrarywithrest.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakova.mylibrarywithrest.domain.Book;
import com.kazakova.mylibrarywithrest.domain.Comment;
import com.kazakova.mylibrarywithrest.dto.BookDto;
import com.kazakova.mylibrarywithrest.dto.CommentDto;
import com.kazakova.mylibrarywithrest.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentService commentService;

    @Test
    public void testGetAllComments() throws Exception {
        CommentDto comment1 = new CommentDto(1L, "Brrr", 2L);
        CommentDto comment2 = new CommentDto(2L, "Wow", 1L);
        CommentDto comment3 = new CommentDto(3L, "Omg", 2L);
        CommentDto comment4 = new CommentDto(4L, "Super", 5L);
        CommentDto comment5 = new CommentDto(5L, "Dislike", 4L);
        CommentDto comment6 = new CommentDto(6L, "Sad", 3L);
        CommentDto comment7 = new CommentDto(7L, "Ou my god", 7L);
        CommentDto comment8 = new CommentDto(8L, "Pam-pam", 8L);
        CommentDto comment9 = new CommentDto(9L, "I like it", 9L);
        CommentDto comment10 = new CommentDto(10L, "I want to read again", 10L);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/comments");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.
                        writeValueAsString(Arrays.asList(comment1, comment2, comment3, comment4,
                                comment5, comment6, comment7, comment8, comment9, comment10))));

    }

    @Test
    public void testGetCommentById() throws Exception {

        Long id = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/comment/{id}", id);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.comment").value("Brrr"))
                .andExpect(jsonPath("$.bookId").value(2));
    }

    @Test
    public void testCreateComment() throws Exception {
        Comment comment = new Comment();
        Book book = new Book(1L, "Red sails");
        comment.setComment("Foooo");
        comment.setBook(book);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/createComment");

        mockMvc.perform(request.content(objectMapper.writeValueAsString((comment)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.comment").value("Foooo"));

    }

    @Test
    public void testDeleteComment() throws Exception {

        Long id = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/comment/{id}", id);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void testUpdateComment() throws Exception {

        Long id = 1L;
        Comment comment = commentService.findCommentById(id).get();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/comment/{id}/holder?comment=Br-r-r-r-r", id);

        mockMvc.perform(request.content(objectMapper.writeValueAsString(CommentDto.toDto(comment).toDomainObject()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

    }

}
