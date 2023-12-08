package com.orero.libraryapp.service.implementation;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.orero.libraryapp.entity.User;
import com.orero.libraryapp.service.BaseService;
import com.orero.libraryapp.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BaseServiceImpl implements BaseService {

    private final UserService userService;

    @Override
    public User getCurrentUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return this.userService.getUser(principal.toString());
    }

}
