package de.ait.gr5.bs.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.gr5.bs.dto.UpdateUserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("UsersController is works")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class UsersControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails(value = "test@mail.com")
    @Sql(scripts = "/sql/data_for_users.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void update_current_user() throws Exception {

        String body = objectMapper.writeValueAsString(UpdateUserDto.builder()
                .newRole("USER")
                .build());

        mockMvc.perform(put("/api/users/1")
                        .header("Content-Type", "application/json")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.state", is("NOT_CONFIRMED")))
                .andExpect(jsonPath("$.role", is("USER")));
    }

    @Test
    @WithUserDetails(value = "test@mail.com")
    @Sql(scripts = "/sql/data_for_users.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    void update_another_user() throws Exception {

        String body = objectMapper.writeValueAsString(UpdateUserDto.builder()
                .newRole("USER")
                .firstName("Andy")
                .build());

        mockMvc.perform(put("/api/users/2")
                        .header("Content-Type", "application/json")
                        .content(body))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getProfileReturnsUnauthorizedForNotAuthenticatedUser() throws Exception {
        mockMvc.perform(post("/api/users/me"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = "test@mail.com")
    @Sql(scripts = "/sql/data_for_users.sql")
  //  @WithMockUser(username = "first.user@gmail.com", password = "Qwerty007!")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getProfileReturnsOkForAuthenticatedUser() throws Exception {

        mockMvc.perform(get("/api/users/me"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
