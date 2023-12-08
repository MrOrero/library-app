package com.orero.libraryapp.service.implementation;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.orero.libraryapp.dto.BookDto;
import com.orero.libraryapp.entity.Book;
import com.orero.libraryapp.entity.Category;
import com.orero.libraryapp.entity.User;
import com.orero.libraryapp.exception.EntityNotFoundException;
import com.orero.libraryapp.mappers.BookMapper;
import com.orero.libraryapp.repository.BookRepository;
import com.orero.libraryapp.service.BookService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryServiceImpl categoryServiceImpl;
    private BaseServiceImpl baseServiceImpl;

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return unwrapBook(book, id);    }

    @Override
    public Book saveBook(Book book) {
        // You might want to perform additional logic/validation before saving
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, BookDto bookDto) {
        // Check if the book with the given id exists
        Book existingBook = getBook(id);

        bookMapper.updateBookFromDto(bookDto, existingBook);

        return bookRepository.save(existingBook);
        
    }

    @Override
    public void deleteBook(Long id) {
        // Check if the book with the given id exists
        Book existingBook = getBook(id);

        // Delete the book
        bookRepository.delete(existingBook);
    }

    @Override
    public Book addBookToCategory(Long bookId, Long categoryId) {
        // Check if the book with the given id exists
        Book existingBook = getBook(bookId);

        // Check if the category with the given id exists
        Category existingCategory = categoryServiceImpl.getCategory(categoryId);

        existingBook.getCategories().add(existingCategory);


        return bookRepository.save(existingBook);
    }

    @Override
    public Book addFavoriteBook(Long bookId) {
        // Check if the book with the given id exists
        Book existingBook = getBook(bookId);

        // Check if the user with the given id exists
        User currentUser = baseServiceImpl.getCurrentUser();

        existingBook.getUsers().add(currentUser);

        return bookRepository.save(existingBook);
    }

    @Override
    public Page<Book> getFavoriteBooks(Pageable pageable) {
        User currentUser = baseServiceImpl.getCurrentUser();

        return bookRepository.findByUsers(currentUser, pageable);
    }

    static Book unwrapBook(Optional<Book> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Book.class);
    }
}
