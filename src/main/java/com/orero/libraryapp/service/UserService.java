package com.orero.libraryapp.service;

import com.orero.libraryapp.entity.User;

public interface UserService {
    User getUser(Long id);

    User getUser(String username);

    User saveUser(User user);
}
