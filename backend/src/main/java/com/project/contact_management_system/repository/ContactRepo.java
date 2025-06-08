package com.project.contact_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.contact_management_system.model.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer>{

//    public List<Contact> deleteByName(String name);
}
