package de.ait.gr5.bs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.gr5.bs.dto.BookNewDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BooksController is works: ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class BookIntegrationTest {
// Тесты не работают так как пользователь не залогинен - как сюда этот шаг добавить не знаю
  private static final BookNewDto NEW_BOOK = BookNewDto.builder()
      .title("Braiding Sweetgrass")
      .author("Robin Wall Kimmerer")
      .description("Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(1L)
      .language("English")
      .pages(408)
      .publisherDate("2015-04-11")
      .cover("f:/book_db/1.jpg")
      .owner(1L)
      .build();

  private static final BookNewDto UPDATE_BOOK = BookNewDto.builder()
      .title("Update - Braiding Sweetgrass")
      .author("Update - Robin Wall Kimmerer")
      .description("Update - Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(1L)
      .language("Update - English")
      .pages(408)
      .publisherDate("2005-04-11")
      .cover("f:/book_db1/1.jpg")
      .owner(1L)
      .build();


  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Nested
  @DisplayName("POST /api/books is works: ")
  class AddBookTest {

    @Test
    @Sql(scripts = "/sql/data_for_books.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void add_new_book_positive() throws Exception {

      String body = objectMapper.writeValueAsString(NEW_BOOK);

      mockMvc.perform(post("/api/books")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isCreated())
          //.andExpect(jsonPath("$.id", is(1L)))
          .andExpect(jsonPath("$.title", is("Braiding Sweetgrass")));
    }

    @Test
    public void add_new_book_negative() throws Exception {
      String body = objectMapper.writeValueAsString(NEW_BOOK);

      mockMvc.perform(post("/api/books")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("PUT /api/books is works: ")
  class UpdateBookTest {

    @Test
    @Sql(scripts = "/sql/data_for_books.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void update_exist_book_positive() throws Exception {

      String body = objectMapper.writeValueAsString(NEW_BOOK);
      String body1 = objectMapper.writeValueAsString(UPDATE_BOOK);

      mockMvc.perform(post("/api/books")
              .header("Content-Type", "application/json")
              .content(body)
              .content(body1))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.id", is(1L)))
          .andExpect(jsonPath("$.title", is("Update - Braiding Sweetgrass")))
          .andExpect(jsonPath("$.author", is("Update - Robin Wall Kimmerer")))
          .andExpect(jsonPath("$.description", is("Update - Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")))
          .andExpect(jsonPath("$.language", is("Update - English")))
          .andExpect(jsonPath("$.pages", is("4008")))
          .andExpect(jsonPath("$.publisherDate", is("2005-04-11")))
          .andExpect(jsonPath("$.cover", is("f:/book_db1/1.jpg")));
    }
  }

}
