package com.aliateck.fact.application;

import com.aliateck.fact.application.rest.controllers.user.UserController;
import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class ApplicationStarterTests {
    private static final String ENDPOINT = "/api/users/";

    @Autowired
    private UserApiService userApiService;
    @MockitoBean
    private CompanyApiService companyApiService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }


    @Test
    public void should_be_able_to_retrieve_user() throws Exception {
        // Given
        User user = userBuild();
        userApiService.addUser(user);
/*
        // When
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()); */

        // Then
        List<User> users = userApiService.findAllUsers();
        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("should be able to delete user")
    public void should_be_able_to_delete_user() throws Exception {
        Long id = 1L;
        // Given
        User user = userBuild();
        userApiService.addUser(user);

        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT + id, 1))
                .andExpect(status().isAccepted());
    }

    @Test
    public void should_be_able_to_save_one_user() throws Exception {

        // Given


    }


    private User userBuild() {
        return User.builder()
                .userName("allouchi@hotmail.fr")
                .password("123456")
                .firstName("Mustapha")
                .lastName("Aliane")
                .activated(true)
                .id(1L)
                .build();
    }
}
