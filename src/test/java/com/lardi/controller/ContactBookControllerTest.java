package com.lardi.controller;

import com.lardi.model.Contact;
import com.lardi.model.User;
import com.lardi.repository.ContactRepository;
import com.lardi.service.UserAuthService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.View;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactBookControllerTest {
    @InjectMocks
    private ContactBookController controller;
    @Mock
    private View mockView;
    @Mock
    private UserAuthService userAuthService;
    @Mock
    private ContactRepository contactRepository;
    private MockMvc mockMvc;
    private Contact contactIsValid, contactIsInvalid;
    private List<Contact> contacts;

    @Before
    public void setUp(){
        mockMvc = standaloneSetup(controller)
                .setSingleView(mockView)
                .build();
        contactIsValid = new Contact();
        contactIsInvalid = new Contact();
        contactIsValid.setId(1L);
        contactIsValid.setFirstName("FirstName");
        contactIsValid.setSecondName("SecondName");
        contactIsValid.setMiddleName("MiddleName");
        contactIsValid.setPhone("+380990001100");
        contactIsInvalid.setId(2L);
        contactIsInvalid.setFirstName("F");
        contactIsInvalid.setSecondName("S");
        contactIsInvalid.setMiddleName("M");
        contactIsInvalid.setPhone("102210");
        contacts = new ArrayList<Contact>();
        contacts.add(contactIsValid);
        contacts.add(contactIsInvalid);
    }

    @Test
    @WithMockUser
    public void testShouldReturnContactsPage() throws Exception {
        when(userAuthService.getAuthUser()).thenReturn(new User());
        when(contactRepository.findContactsByUser_Id(anyLong())).thenReturn(contacts);
        mockMvc.perform(get("/contacts"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("contacts", hasSize(2)))
                .andExpect(view().name("phone-book"));
    }

    @Test
    @WithMockUser
    public void testShouldReturnAddContactPage() throws Exception {
        mockMvc.perform(get("/add-contact"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-contact"));
    }

    @Test
    @WithMockUser
    public void testShouldAddContactAndReturnToContactsPage() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        mockMvc.perform(post("/add-contact")
                    .param("firstName", contactIsValid.getFirstName())
                    .param("secondName", contactIsValid.getSecondName())
                    .param("middleName", contactIsValid.getMiddleName())
                    .param("phone", contactIsValid.getPhone()))
                .andExpect(status().isOk())
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/contacts"));
    }

    @Test
    @WithMockUser
    public void testShouldReturnAddContactPageIfContactIsInvalid() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        mockMvc.perform(post("/add-contact")
                    .param("firstName", contactIsInvalid.getFirstName())
                    .param("secondName", contactIsInvalid.getSecondName())
                    .param("middleName", contactIsInvalid.getMiddleName())
                    .param("phone", contactIsInvalid.getPhone()))
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("add-contact"));
    }

    @Test
    @WithMockUser
    public void testShouldReturnAddContactPageForUpdatingUser() throws Exception{
        when(contactRepository.findContactById(contactIsValid.getId())).thenReturn(contactIsValid);
        when(userAuthService.getAccess(contactIsValid)).thenReturn(true);
        mockMvc.perform(get("/update-contact?id=" + contactIsValid.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("add-contact"));
    }

    @Test
    @WithMockUser
    public void testShouldUpdateContactAndReturnContactsPage() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        mockMvc.perform(post("/update-contact?id=" + contactIsValid.getId())
                    .param("firstName", contactIsValid.getFirstName())
                    .param("secondName", contactIsValid.getSecondName())
                    .param("middleName", contactIsValid.getMiddleName())
                    .param("phone", contactIsValid.getPhone()))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/contacts"));
    }

    @Test
    @WithMockUser
    public void testShouldReturnAddContactPageIfContactIsInvalidForUpdating() throws Exception {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);
        mockMvc.perform(post("/update-contact?id=" + contactIsInvalid.getId())
                    .param("firstName", contactIsInvalid.getFirstName())
                    .param("secondName", contactIsInvalid.getSecondName())
                    .param("middleName", contactIsInvalid.getMiddleName())
                    .param("phone", contactIsInvalid.getPhone()))
                .andExpect(model().hasErrors())
                .andExpect(status().isOk())
                .andExpect(view().name("add-contact"));
    }

    @Test
    @WithMockUser
    public void testShouldReturnLogoutFromUpdateWhenUserHaveNotAccess() throws Exception{
        when(contactRepository.findContactById(contactIsValid.getId())).thenReturn(contactIsValid);
        when(userAuthService.getAccess(contactIsValid)).thenReturn(false);
        mockMvc.perform(get("/update-contact?id=" + contactIsValid.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/logout"));
    }

    @Test
    @WithMockUser
    public void testShouldDeleteContact() throws Exception{
        when(contactRepository.findContactById(contactIsValid.getId())).thenReturn(contactIsValid);
        when(userAuthService.getAccess(contactIsValid)).thenReturn(true);
        mockMvc.perform(
                get("/delete-contact?id=" + contactIsValid.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/contacts"));
        verify(contactRepository, times(1)).deleteContactById(contactIsValid.getId());
    }

    @Test
    public void testShouldReturnLogoutFromDeleteWhenUserHaveNotAccess() throws Exception{
        when(contactRepository.findContactById(contactIsValid.getId())).thenReturn(contactIsValid);
        when(userAuthService.getAccess(contactIsValid)).thenReturn(false);

        mockMvc.perform(
                get("/delete-contact?id=" + contactIsValid.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("redirect:/logout"));
    }
}
