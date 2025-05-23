package com.aliateck.fact.application.rest.controllers.user;


import com.aliateck.fact.domaine.business.object.User;
import com.aliateck.fact.domaine.ports.api.company.CompanyApiService;
import com.aliateck.fact.domaine.ports.api.user.UserApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;


@DisplayName("UserController Test")
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    private static final String ENDPOINT = "/api/users/";

    @Autowired
    private UserApiService userApiService;
    @MockitoBean
    private CompanyApiService companyApiService;
    //@Autowired
    //private MockMvc mockMvc;
    //@Autowired
    //private WebApplicationContext context;

    @MockitoBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    public void init() {
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }


    @Test
    public void should_be_able_to_retrieve_user() throws Exception {
        // Given
        User user = userBuild();
        userApiService.addUser(user);

        // When
        /*
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());*/

        // Then
        List<User> users = userApiService.findAllUsers();
        //assertThat(users.size());
    }

    @Test
    @DisplayName("should be able to delete user")
    public void should_be_able_to_delete_user() throws Exception {
        Long id = 1L;
        /*
        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT + id, 1))
                .andExpect(status().isAccepted()); */
    }

    @Test
    public void should_be_able_to_save_one_user() throws Exception {

        // Given


    }


    private User userBuild() {
        return User.builder()
                .email("allouchi@hotmail.fr")
                .password("123456")
                .firstName("Mustapha")
                .lastName("Aliane")
                .activated(true)
                .id(1L)
                .build();
    }
}
