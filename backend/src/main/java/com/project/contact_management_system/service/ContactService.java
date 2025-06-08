package com.project.contact_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.contact_management_system.model.Contact;
import com.project.contact_management_system.repository.ContactRepo;

import jakarta.transaction.Transactional;

@Service
public class ContactService {

    @Autowired
private ContactRepo contactRepo;

    public List<Contact> getAllcontact() {
        return contactRepo.findAll();
    }

    public Contact Save(Contact contact) {
        return contactRepo.save(contact);
        
    }

    // public String RemoveByName(String name) {
    //     return contactRepo.delete(name);
    // }

    public void remove(int id) {
       // throw new UnsupportedOperationException("Not supported yet.");
       contactRepo.deleteById(id);
       System.out.println("Remmoved item"+id);
    }

    @Transactional
    public Contact Update(Contact contact) {
       // throw new UnsupportedOperationException("Not supported yet.");
       System.out.println("Data Updated");
       return contactRepo.save(contact);

    }

}
