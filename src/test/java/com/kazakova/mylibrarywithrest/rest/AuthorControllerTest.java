package com.kazakova.mylibrarywithrest.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.dto.AuthorDto;
import com.kazakova.mylibrarywithrest.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorService authorService;

    @Test
    public void testGetAllAuthors() throws Exception {
        AuthorDto author1 = new AuthorDto(1L, "Green");
        AuthorDto author2 = new AuthorDto(2L, "King");
        AuthorDto author3 = new AuthorDto(3L, "Pushkin");
        AuthorDto author4 = new AuthorDto(4L, "Lermontov");
        AuthorDto author5 = new AuthorDto(5L, "Gogol");
        AuthorDto author6 = new AuthorDto(6L, "Shekspir");
        AuthorDto author7 = new AuthorDto(7L, "Esenin");
        AuthorDto author8 = new AuthorDto(8L, "Bulgakov");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/authors");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.
                        writeValueAsString(Arrays.asList(author1, author2, author3,
                                author4, author5, author6,
                                author7, author8))));

    }

    @Test
    public void testGetAuthorById() throws Exception {

        Long id = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/author/{id}", id);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Green"));
    }

    @Test
    public void testCreateAuthor() throws Exception {
        Author author = new Author();
        author.setName("Karamzin");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/createAuthor");

        mockMvc.perform(request.content(objectMapper.writeValueAsString((author)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Karamzin"));

    }

    @Test
    public void testDeleteAuthor() throws Exception {

        Long id = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/author/{id}", id);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdateNameForAuthor() throws Exception {

        Long id = 1L;
        Author authorForUpdate = authorService.findAuthorById(id);
        authorForUpdate.setName("H.Green");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/author/{id}/holder", id);

        mockMvc.perform(request.content(objectMapper.writeValueAsString(authorForUpdate))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("H.Green"));

    }

}
