package com.project.contact_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.contact_management_system.model.Contact;
import com.project.contact_management_system.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;




@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://contact-management-system-rho.vercel.app") 

@Tag(name = "Contact API", description = "Manage contact information")
public class ContactController {

    @Autowired
    private ContactService service;

/*
 * CRUD operations
 * 1. Create - Done
 * 2. Update - Not Done
 * 3. Remove - Done
 * 4. Display - Done
 * 
 */


@Operation(summary = "Get all contacts")
@GetMapping("/contacts")
public List<Contact> getAllContacts() {
    return service.getAllcontact();
}

@Operation(summary = "Add a new contact")
@ApiResponse(responseCode = "200", description = "Contact added successfully")
@PostMapping("/add")
public ResponseEntity<Contact> SaveContact(@RequestBody Contact contact) {
    Contact savecontacts = service.Save(contact);
    return new ResponseEntity<>(savecontacts, HttpStatus.CREATED);    
}

@Operation(summary = "Delete a contact")
@DeleteMapping("/remove/{id}")
public void RemoveContact(@PathVariable int id){
    service.remove(id);
}

@Operation(summary = "Update a contact")
@PutMapping("/update/{id}")
public ResponseEntity<Contact> UpdateContact(@PathVariable Long id, @Valid @RequestBody Contact contact) {
    contact.setId(id); // no parsing needed now
    Contact updatedContact = service.Update(contact);
    return new ResponseEntity<>(updatedContact, HttpStatus.OK);
    }
}
