package com.orero.libraryapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.orero.libraryapp.dto.BookDto;
import com.orero.libraryapp.entity.Book;

public interface BookService {
    Page<Book> getAllBooks(Pageable pageable);

    Book getBook(Long id);

    Book saveBook(Book book);

    Book updateBook(Long id, BookDto book);

    void deleteBook(Long id);

    Book addBookToCategory(Long bookId, Long categoryId);
}
