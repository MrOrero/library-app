package com.orero.libraryapp.repository;

import com.orero.libraryapp.entity.Book;
import com.orero.libraryapp.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByUsers(User user, Pageable pageable);
}
