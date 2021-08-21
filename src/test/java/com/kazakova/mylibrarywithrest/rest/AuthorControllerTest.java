package com.kazakova.mylibrarywithrest.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakova.mylibrarywithrest.dto.AuthorDto;
import com.kazakova.mylibrarywithrest.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class AuthorControllerTest {

//    @Autowired
//    private ObjectMapper objectMapper;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @Test
    public void getAllAuthors() throws Exception {
//        List<Author> authorList = new ArrayList();
        AuthorDto author1 = new AuthorDto(1L, "Green");
        AuthorDto author2 = new AuthorDto(2L, "King");
        AuthorDto author3 = new AuthorDto(3L, "Pushkin");
        AuthorDto author4 = new AuthorDto(4L, "Lermontov");
        AuthorDto author5 = new AuthorDto(5L, "Gogol");
        AuthorDto author6 = new AuthorDto(6L, "Shekspir");
        AuthorDto author7 = new AuthorDto(7L, "Esenin");
        AuthorDto author8 = new AuthorDto(8L, "Bulgakov");
//        authorList.add(author1);
//        authorList.add(author2);
//        authorList.add(author3);
//        authorList.add(author4);
//        authorList.add(author5);
//        authorList.add(author6);
//        authorList.add(author7);
//        authorList.add(author8);
//        AuthorDto authorDto1 = new AuthorDto(1L, "Green");
//        AuthorDto authorDto2 = new AuthorDto(2L, "King");
//        when(authorService.findAllAuthors()).thenReturn(authorList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/authors");

//        MvcResult mvcResult = mockMvc.perform(request.content(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk()).andReturn();

//        MockHttpServletResponse response = mvcResult.getResponse();
//        response.getContentAsString();

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.
                        writeValueAsString(Arrays.asList(author1, author2, author3,
                                author4, author5, author6,
                                author7, author8))));

    }

}
