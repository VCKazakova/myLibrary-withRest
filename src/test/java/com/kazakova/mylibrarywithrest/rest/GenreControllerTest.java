package com.kazakova.mylibrarywithrest.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kazakova.mylibrarywithrest.domain.Author;
import com.kazakova.mylibrarywithrest.domain.Genre;
import com.kazakova.mylibrarywithrest.dto.AuthorDto;
import com.kazakova.mylibrarywithrest.dto.GenreDto;
import com.kazakova.mylibrarywithrest.service.GenreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GenreService genreService;

    @Test
    public void testGetAllGenres() throws Exception {
        GenreDto genre1 = new GenreDto(1L, "Roman");
        GenreDto genre2 = new GenreDto(2L, "Comedy");
        GenreDto genre3 = new GenreDto(3L, "Tale");
        GenreDto genre4 = new GenreDto(4L, "Tragedy");
        GenreDto genre5 = new GenreDto(5L, "Legend");
        GenreDto genre6 = new GenreDto(6L, "Myth");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/genres");

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.
                        writeValueAsString(Arrays.asList(genre1, genre2, genre3, genre4,
                                genre5, genre6))));

    }

    @Test
    public void testGetGenreById() throws Exception {

        Long id = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/genre/{id}", id);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Roman"));
    }

    @Test
    public void testCreateGenre() throws Exception {
        Genre genre = new Genre();
        genre.setName("Epos");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/genre");

        mockMvc.perform(request.content(objectMapper.writeValueAsString((genre)))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Epos"));

    }

    @Test
    public void testDeleteGenre() throws Exception {

        Long id = 1L;

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/genre/{id}", id);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }





    @Test
    //@Transactional
    public void testUpdateNameForGenre() throws Exception {

        Long id = 1L;
        GenreDto genre = GenreDto.toDto(genreService.findGenreById(id).get());    //.toDomainObject();

//        Genre genreForUpdate = genreService.findGenreById(id).get();
//        genreForUpdate.setName("Love roman");
        genre.setName("Love roman");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/genre/{id}/holder", id);

        mockMvc.perform(request.content(objectMapper.writeValueAsString(genre))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Love roman"));

    }
}
