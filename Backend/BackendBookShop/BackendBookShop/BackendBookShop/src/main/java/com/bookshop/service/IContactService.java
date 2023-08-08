package com.bookshop.service;

import com.bookshop.dto.ContactDTO;
import com.bookshop.model.User;

public interface IContactService{
    void createContact(User user, ContactDTO contactDTO);
}
