package com.orero.libraryapp.service.implementation;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.orero.libraryapp.dto.BookDto;
import com.orero.libraryapp.entity.Book;
import com.orero.libraryapp.entity.Category;
import com.orero.libraryapp.exception.EntityNotFoundException;
import com.orero.libraryapp.mappers.BookMapper;
import com.orero.libraryapp.repository.BookRepository;
import com.orero.libraryapp.service.BookService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final CategoryServiceImpl categoryServiceImpl;

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

    static Book unwrapBook(Optional<Book> entity, Long id) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, Book.class);
    }
}
