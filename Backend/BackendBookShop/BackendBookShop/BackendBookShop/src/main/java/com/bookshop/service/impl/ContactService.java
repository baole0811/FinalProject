package com.bookshop.service.impl;

import com.bookshop.dto.ContactDTO;
import com.bookshop.model.Contact;
import com.bookshop.model.User;
import com.bookshop.repository.ContactRepository;
import com.bookshop.service.IContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactService implements IContactService {
    private final ContactRepository contactRepository;

    @Override
    public void createContact(User user, ContactDTO contactDTO) {
        Contact contact = new Contact(contactDTO.getName(), contactDTO.getEmail(), contactDTO.getContent(),user);
        contactRepository.save(contact);

    }
}
