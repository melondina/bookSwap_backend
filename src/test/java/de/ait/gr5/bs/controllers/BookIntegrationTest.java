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
@ActiveProfiles("test")
public class BookIntegrationTest {

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
          .andExpect(jsonPath("$.id", is(1L)))
          .andExpect(jsonPath("$.title", is("Braiding Sweetgrass")));

    }
  }
}
