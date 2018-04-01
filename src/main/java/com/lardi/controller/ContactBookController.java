package com.lardi.controller;

import com.lardi.model.Contact;
import com.lardi.repository.ContactRepository;
import com.lardi.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static org.springframework.web.servlet.view.UrlBasedViewResolver.REDIRECT_URL_PREFIX;

@Controller
public class ContactBookController {

    private static final String CONTACTS = "/contacts";
    private static final String ADD_CONTACT = "add-contact";

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private ContactRepository contactRepository;

    @RequestMapping(value = CONTACTS, method = RequestMethod.GET)
    public String findAllContacts(ModelMap model){
        model.addAttribute("contacts", contactRepository.findContactsByUser_Id(userAuthService.getAuthUser().getId()));
        return "phone-book";
    }

    @RequestMapping(value = "/add-contact", method = RequestMethod.GET)
    public String addContact(ModelMap modelMap){
        modelMap.addAttribute("contact", new Contact());
        return ADD_CONTACT;
    }

    @Transactional
    @RequestMapping(value = "/add-contact", method = RequestMethod.POST)
    public String addContact(@Valid Contact contact, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return ADD_CONTACT;
        contact.setUser(userAuthService.getAuthUser());
        contactRepository.save(contact);
        return REDIRECT_URL_PREFIX + CONTACTS;
    }

    @RequestMapping(value = "/update-contact", method = RequestMethod.GET)
    public String updateContact(@RequestParam Long id, ModelMap modelMap){
        Contact contact = contactRepository.findContactById(id);
           if(userAuthService.getAccess(contact)) {
                modelMap.addAttribute("contact", contact);
                return ADD_CONTACT;
            } else return REDIRECT_URL_PREFIX + "/logout";
    }

    @Transactional
    @RequestMapping(value = "/update-contact", method = RequestMethod.POST)
    public String updateContact(ModelMap modelMap, @Valid Contact contact, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ADD_CONTACT;
        contact.setUser(userAuthService.getAuthUser());
        contactRepository.save(contact);
        modelMap.clear();
        return REDIRECT_URL_PREFIX + CONTACTS;
    }

    @Transactional
    @RequestMapping(value = "/delete-contact", method = RequestMethod.GET)
    public String deleteContact(@RequestParam Long id){
        Contact contact = contactRepository.findContactById(id);
        if(userAuthService.getAccess(contact)) {
            contactRepository.deleteContactById(id);
            return REDIRECT_URL_PREFIX + CONTACTS;
        } else return REDIRECT_URL_PREFIX + "/logout";
    }
}
