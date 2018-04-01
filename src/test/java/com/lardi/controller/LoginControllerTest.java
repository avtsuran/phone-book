package com.lardi.controller;

import com.lardi.model.User;
import com.lardi.service.UserAuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;
    @Mock
    private View mockView;
    @Mock
    private UserAuthService userAuthService;
    private MockMvc mockMvc;
    private User userIsInvalid, userIsValid;

    @Before
    public void setUp(){
        mockMvc = standaloneSetup(loginController)
                .setSingleView(mockView)
                .build();
        userIsInvalid = new User();
        userIsInvalid.setId(1L);
        userIsInvalid.setFirstName("F");
        userIsInvalid.setSecondName("S");
        userIsInvalid.setMiddleName("M");
        userIsInvalid.setPassword("P");
        userIsValid = new User();
        userIsValid.setId(2L);
        userIsValid.setFirstName("FirstName");
        userIsValid.setSecondName("SecondName");
        userIsValid.setMiddleName("MiddleName");
        userIsValid.setPassword("Password");
    }

    @Test
    public void testShouldReturnLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testShouldReturnRegistrationPage() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void testShouldReturnRegistrationPageIfUserIsInvalid() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(userAuthService.findUserByLogin(userIsInvalid.getLogin())).thenReturn(userIsInvalid);
        when(result.hasErrors()).thenReturn(true);
        mockMvc.perform(post("/registration")
                    .param("firstName", userIsInvalid.getFirstName())
                    .param("secondName", userIsInvalid.getSecondName())
                    .param("middleName", userIsInvalid.getMiddleName())
                    .param("password", userIsInvalid.getPassword()))
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void testShouldReturnLoginPageAfterSuccessfulRegistration() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(userAuthService.findUserByLogin(anyString())).thenReturn(null);
        when(result.hasErrors()).thenReturn(false);
        mockMvc.perform(post("/registration")
                    .param("firstName", userIsValid.getFirstName())
                    .param("secondName", userIsValid.getSecondName())
                    .param("middleName", userIsValid.getMiddleName())
                    .param("password", userIsValid.getPassword()))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
