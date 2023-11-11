package com.example.contacts.controllers;

import com.example.contacts.data.Contacts;
import com.example.contacts.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ContactsController {

    private final ContactsService contactsService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("contacts", contactsService.findAll());
        return "index";
    }

    @GetMapping("/contact/create")
    public String showCreateFrom(Model model){
        model.addAttribute("contact", new Contacts());
        return "create";
    }

//    @PostMapping("/contact/create")
//    public String createContact(@ModelAttribute Contacts contact){
//        contactsService.save(contact);
//        return "redirect:/";
//    }

    @GetMapping("/contact/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        Contacts contact =contactsService.findById(id);
        if (contact != null){
            model.addAttribute("contact", contact);
            return "create";
        }
        return "redirect:/";
    }

    @PostMapping("/contact/save")
    public String editTsk(@ModelAttribute Contacts contact){
        if (contact.getId() == null){
            contactsService.save(contact);
        } else {
            contactsService.update(contact);
        }
        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id){
        contactsService.delete(id);
        return "redirect:/";
    }
}
