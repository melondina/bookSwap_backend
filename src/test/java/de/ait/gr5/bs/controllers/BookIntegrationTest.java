package de.ait.gr5.bs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.gr5.bs.dto.BookNewDto;
import de.ait.gr5.bs.dto.BookUpdateDto;
import de.ait.gr5.bs.dto.WaitLineRequestDto;
import de.ait.gr5.bs.models.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static de.ait.gr5.bs.controllers.utils.userAuthorization.createdUser;
import static de.ait.gr5.bs.controllers.utils.userAuthorization.userAuthorizationForTest;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("BooksController is works: ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class   BookIntegrationTest {

  private static final BookNewDto NEW_BOOK = BookNewDto.builder()
      .title("Braiding Sweetgrass")
      .author("Robin Wall Kimmerer")
      .description("Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(1L)
      .languageId(1L)
      .pages(408)
      .publisherDate("2015")
      .cover("f:/book_db/1.jpg")
      .owner(1L)
      .build();

  private static final BookNewDto NEW_BOOK_NOT_VALID = BookNewDto.builder()
      .title("  ")
      .author("Robin Wall Kimmerer")
      .description("Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(1L)
      .languageId(1L)
      .pages(408)
      .publisherDate("2015")
      .cover("f:/book_db/1.jpg")
      .owner(1L)
      .build();

  private static final BookNewDto NEW_BOOK_404_CATEGORY = BookNewDto.builder()
      .title("Braiding Sweetgrass")
      .author("Robin Wall Kimmerer")
      .description("Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(19L)
      .languageId(1L)
      .pages(408)
      .publisherDate("2015")
      .cover("f:/book_db/1.jpg")
      .owner(1L)
      .build();

  private static final BookNewDto NEW_BOOK_ANOTHER_USER = BookNewDto.builder()
      .title("Braiding Sweetgrass")
      .author("Robin Wall Kimmerer")
      .description("Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(1L)
      .languageId(1L)
      .pages(408)
      .publisherDate("2015")
      .cover("f:/book_db/1.jpg")
      .owner(2L)
      .build();

  private static final BookUpdateDto UPDATE_BOOK = BookUpdateDto.builder()
      .title("Update - Braiding Sweetgrass")
      .author("Update - Robin Wall Kimmerer")
      .description("Update - Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(1L)
      .languageId(1L)
      .pages(408)
      .publisherDate("2005")
      .cover("f:/book_db1/1.jpg")
      .owner(1L)
      .build();

  private static final BookUpdateDto UPDATE_BOOK_NOT_VALID = BookUpdateDto.builder()
      .title("Update - Braiding Sweetgrass")
      .author("Update - Robin Wall Kimmerer")
      .description("Update - Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(1L)
      .languageId(1L)//тут я не уверена , какое нао значение
      .pages(408)
      .publisherDate("2005")
      .cover("f:/book_db1/1.jpg")
      .owner(1L)
      .build();

  private static final BookUpdateDto UPDATE_BOOK_ANOTHER_USER = BookUpdateDto.builder()
      .title("Update - Braiding Sweetgrass")
      .author("Update - Robin Wall Kimmerer")
      .description("Update - Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")
      .categoryId(1L)
      .languageId(1L)
      .pages(408)
      .publisherDate("2005")
      .cover("f:/book_db1/1.jpg")
      .owner(2L)
      .build();

  private static final WaitLineRequestDto NEW_BOOK_TO_USER_POSITIVE = WaitLineRequestDto.builder()
      .bookId(1L)
      .userId(2L)
      .build();

  private static final WaitLineRequestDto NEW_BOOK_TO_USER_NEGATIVE_SAME_USER = WaitLineRequestDto.builder()
      .bookId(1L)
      .userId(1L)
      .build();

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Nested
  @DisplayName("POST /api/books is works: ")
  class AddBookTest {

    @Test // не работает
    @Sql(scripts = "/sql/data_for_add_book.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void add_new_book_positive() throws Exception {

      String body = objectMapper.writeValueAsString(NEW_BOOK);

      User userAuthForTest = createdUser(1L, "test@gmail.com",
              "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
              User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(post("/api/books")
              .header("Content-Type", "application/json")
              .content(body)
              .with(SecurityMockMvcRequestPostProcessors.csrf()))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.bookId", is(1)))
          .andExpect(jsonPath("$.title", is("Braiding Sweetgrass")));
    }

    @Test
    @Sql(scripts = "/sql/data_for_add_book.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void add_new_book_not_valid_negative() throws Exception {
      String body = objectMapper.writeValueAsString(NEW_BOOK_NOT_VALID);

      mockMvc.perform(post("/api/books")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/sql/data_for_add_book.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void add_new_book_not_exit_category_negative() throws Exception {
      String body = objectMapper.writeValueAsString(NEW_BOOK_404_CATEGORY);

      mockMvc.perform(post("/api/books")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/sql/data_for_add_book_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void add_new_book_another_user_negative() throws Exception {
      String body = objectMapper.writeValueAsString(NEW_BOOK_ANOTHER_USER);

      mockMvc.perform(post("/api/books")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = "/sql/data_for_add_book_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void add_new_book_not_confirmed_user_negative() throws Exception {
      String body = objectMapper.writeValueAsString(NEW_BOOK);

      mockMvc.perform(post("/api/books")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isForbidden());
    }
  }

  @Nested
  @DisplayName("PUT /api/books is works: ")
  class UpdateBookTest {

    @Test  // работает
    @Sql(scripts = "/sql/data_for_update_book.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")
    public void update_exist_book_positive() throws Exception {

      String body = objectMapper.writeValueAsString(UPDATE_BOOK);

      User userAuthForTest = createdUser(1L, "test@gmail.com",
              "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
              User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(put("/api/books/1")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.bookId", is(1)))
          .andExpect(jsonPath("$.title", is("Update - Braiding Sweetgrass")))
          .andExpect(jsonPath("$.author", is("Update - Robin Wall Kimmerer")))
          .andExpect(jsonPath("$.description", is("Update - Drawing on her life as an indigenous scientist, and as a woman, Kimmerer shows how other living beings...")))
          .andExpect(jsonPath("$.language", is("Update - English")))
          .andExpect(jsonPath("$.pages", is("408")))
          .andExpect(jsonPath("$.publisherDate", is("2005")))
          .andExpect(jsonPath("$.cover", is("f:/book_db1/1.jpg")));
    }

    @Test
    @Sql(scripts = "/sql/data_for_update_book.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")
    public void update_exist_book_not_valid_negative() throws Exception {

      String body = objectMapper.writeValueAsString(UPDATE_BOOK_NOT_VALID);

      mockMvc.perform(put("/api/books/1")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(scripts = "/sql/data_for_update_book.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")
    public void update_not_exist_book_negative() throws Exception {

      String body = objectMapper.writeValueAsString(UPDATE_BOOK);

      mockMvc.perform(put("/api/books/10")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isNotFound());
    }

    @Test
    @Sql(scripts = "/sql/data_for_update_book_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void update_book_another_user_negative() throws Exception {
      String body = objectMapper.writeValueAsString(UPDATE_BOOK_ANOTHER_USER);

      mockMvc.perform(put("/api/books/2")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = "/sql/data_for_update_book_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void update_book_not_confirmed_user_negative() throws Exception {
      String body = objectMapper.writeValueAsString(UPDATE_BOOK);

      mockMvc.perform(put("/api/books/1")
              .header("Content-Type", "application/json")
              .content(body))
          .andExpect(status().isForbidden());
    }

  }

  @Nested
  @DisplayName("GET /api/books/history is works: ")
  class MyHistoryTest {

    @Test // работает !!!!
    @Sql(scripts = "/sql/data_for_my_history.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")
    public void get_my_history_not_empty_positive() throws Exception {

      User userAuthForTest = createdUser(1L, "test@gmail.com",
          "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
          User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);
      mockMvc.perform(get("/api/books/history/1")
              .header("Content-Type", "application/json")
              .with(SecurityMockMvcRequestPostProcessors.csrf()))
          .andExpect(status().isOk())
          .andExpect(jsonPath("count", is(2)));
    }

    @Test //не работает
    @Sql(scripts = "/sql/data_for_my_history_empty.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")

    public void get_my_history_empty_positive() throws Exception {
      User userAuthForTest = createdUser(1L, "test@gmail.com",
              "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
              User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(get("/api/books/history/1")
              .header("Content-Type", "application/json"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("count", is(0)));
    }

    @Test
    @Sql(scripts = "/sql/data_for_my_history.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")
    public void get_my_history_negative() throws Exception {

      mockMvc.perform(get("/api/books/history/2")
              .header("Content-Type", "application/json"))
          .andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = "/sql/data_for_my_history.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")
    public void get_my_history_not_exist_user_negative() throws Exception {

      mockMvc.perform(get("/api/books/history/25")
              .header("Content-Type", "application/json"))
          .andExpect(status().isNotFound());
    }
  }

  @Nested
  @DisplayName("POST /api/books/getting is works: ")
  class UserGetBookTest {

    @Test
    @Sql(scripts = "/sql/data_for_add_book_to_the_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void add_new_book_to_user_positive() throws Exception {

      String body = objectMapper.writeValueAsString(NEW_BOOK_TO_USER_POSITIVE);

      mockMvc.perform(post("/api/books/getting")
              .header("Content-Type", "application/json")
              .content(body)
              .with(SecurityMockMvcRequestPostProcessors.csrf()))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.lineId", is(1)))
          .andExpect(jsonPath("$.bookId", is("1")))
          .andExpect(jsonPath("$.userId", is("2")))
          .andExpect(jsonPath("$.numberUserInLine", is(1)));
    }

    @Test
    @Sql(scripts = "/sql/data_for_add_book_to_the_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void add_new_book_to_user_negative_same_user() throws Exception {

      String body = objectMapper.writeValueAsString(NEW_BOOK_TO_USER_NEGATIVE_SAME_USER);

      mockMvc.perform(post("/api/books/getting")
              .header("Content-Type", "application/json")
              .content(body)
              .with(SecurityMockMvcRequestPostProcessors.csrf()))
          .andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = "/sql/data_for_add_book_to_the_user.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void add_new_book_to_user_negative_2_booking() throws Exception {

      String body = objectMapper.writeValueAsString(NEW_BOOK_TO_USER_POSITIVE);

      mockMvc.perform(post("/api/books/getting")
          .header("Content-Type", "application/json")
          .content(body)
          .with(SecurityMockMvcRequestPostProcessors.csrf()));

      mockMvc.perform(post("/api/books/getting")
              .header("Content-Type", "application/json")
              .content(body)
              .with(SecurityMockMvcRequestPostProcessors.csrf()))
          .andExpect(status().isForbidden());
    }
  }

  @Nested
  @DisplayName("GET /api/books/waiting/{userId} is works: ")
  class UserGetListOfWaitingBook {

    @Test
    @Sql(scripts = "/sql/data_for_get_wait_line.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test2@gmail.com", password = "Qwerty007!")
    public void get_book_from_wait_line_positive() throws Exception {

      User userAuthForTest = createdUser(2L, "test2@gmail.com",
              "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
              User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(get("/api/books/waiting/2")
                      .header("Content-Type", "application/json")
                      .with(SecurityMockMvcRequestPostProcessors.csrf()))
              .andExpect(status().isOk())
              .andExpect(jsonPath("count", is(1)));
    }
  }

  @Nested
  @DisplayName("GET /api/books/send/{userId} is works: ")
  class UserGetListOfBooksToSend {

    @Test
    @Sql(scripts = "/sql/data_for_get_wait_line.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")
    public void get_book_to_send_positive() throws Exception {

      User userAuthForTest = createdUser(1L, "test@gmail.com",
              "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
              User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(get("/api/books/send/1")
                      .header("Content-Type", "application/json")
                      .with(SecurityMockMvcRequestPostProcessors.csrf()))
              .andExpect(status().isOk())
              .andExpect(jsonPath("count", is(1)));
    }

    @Test
    @Sql(scripts = "/sql/data_for_get_wait_line.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test2@gmail.com", password = "Qwerty007!")
    public void get_book_to_send_negative_not_permission() throws Exception {

      User userAuthForTest = createdUser(2L, "test@gmail.com",
              "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
              User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(get("/api/books/send/1")
                      .header("Content-Type", "application/json")
                      .with(SecurityMockMvcRequestPostProcessors.csrf()))
              .andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = "/sql/data_for_get_wait_line.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void get_book_to_send_negative_unauthorized() throws Exception {

      mockMvc.perform(get("/api/books/send/1")
                      .header("Content-Type", "application/json")
                      .with(SecurityMockMvcRequestPostProcessors.csrf()))
              .andExpect(status().isForbidden());
    }

    @Test
    @Sql(scripts = "/sql/data_for_get_wait_line.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test@gmail.com", password = "Qwerty007!")
    public void get_book_to_send_negative_user_not_found() throws Exception {

      User userAuthForTest = createdUser(1L, "test@gmail.com",
              "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
              User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(get("/api/books/send/10")
                      .header("Content-Type", "application/json")
                      .with(SecurityMockMvcRequestPostProcessors.csrf()))
              .andExpect(status().isNotFound());
    }
  }
}
