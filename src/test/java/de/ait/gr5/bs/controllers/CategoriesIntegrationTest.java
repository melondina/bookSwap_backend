package de.ait.gr5.bs.controllers;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("CategoriesController is works: ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class CategoriesIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Nested
  @DisplayName("GET /api/categories is works: ")
  class GetCategoriesTest {

    @Test
    @Sql(scripts = "/sql/data_for_get_categories.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void get_not_empty_list_positive() throws Exception {

      User userAuthForTest = createdUser(1L, "test@gmail.com",
          "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
          User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(get("/api/categories")
              .header("Content-Type", "application/json")
              .with(SecurityMockMvcRequestPostProcessors.csrf()))
          .andExpect(status().isOk());
    }

    @Test
    @Sql(scripts = "/sql/data_for_get_categories_empty.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    @WithMockUser(username = "test1@gmail.com", password = "Qwerty007!")
    public void get_empty_list_positive() throws Exception {

      User userAuthForTest = createdUser(1L, "test@gmail.com",
          "$2a$10$Vz4mecaJq32jIGzL8dlgW.Xk6suWG1lhHgawSqmcYEc1vDvcRUlMe",
          User.State.NOT_CONFIRMED, User.Role.USER, false);
      userAuthorizationForTest(userAuthForTest);

      mockMvc.perform(get("/api/categories")
              .header("Content-Type", "application/json")
              .with(SecurityMockMvcRequestPostProcessors.csrf()))
          .andExpect(status().isOk());
    }
  }

}
