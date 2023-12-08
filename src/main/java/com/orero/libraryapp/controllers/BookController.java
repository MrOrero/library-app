package com.orero.libraryapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.orero.libraryapp.dto.BookDto;
import com.orero.libraryapp.entity.Book;
import com.orero.libraryapp.service.BookService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Book>> findAll(Pageable pageable) {
        return new ResponseEntity<>(bookService.getAllBooks(pageable), HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody BookDto book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/add-category/{bookId}/{categoryId}")
    public ResponseEntity<Book> addBookToCategory(@PathVariable Long bookId, @PathVariable Long categoryId) {
        return new ResponseEntity<>(bookService.addBookToCategory(bookId, categoryId), HttpStatus.OK);
    }

    @PutMapping("/add-favorite/{bookId}")
    public ResponseEntity<Book> addFavoriteBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(bookService.addFavoriteBook(bookId), HttpStatus.OK);
    }

    @GetMapping("/favorites")
    public ResponseEntity<Iterable<Book>> getFavoriteBooks(Pageable pageable) {
        return new ResponseEntity<>(bookService.getFavoriteBooks(pageable), HttpStatus.OK);
    }

}
