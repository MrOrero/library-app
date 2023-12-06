package com.orero.libraryapp.repository;

import com.orero.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
    
}
