package com.orero.libraryapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

import com.orero.libraryapp.validation.Year;

@Entity
@Table(name = "book")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    @NotBlank(message = "title cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "author cannot be blank")
    @NonNull
    @Column(nullable = false)
    private String author;

    @Column(length = 1000)
    private String description;

    @Year
    @NonNull
    private Integer publishYear;

    @Column(unique = true)
    @NotBlank(message = "isbn cannot be blank")
    @NonNull
    private String isbn;

    @NonNull
    private Double price;

    @ManyToMany
    @JoinTable(name = "book_categories", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(name = "favorite_books", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", publishYear=" + publishYear +
                ", isbn='" + isbn + '\'' +
                ", price=" + price +
                ", categories=" + categories +
                '}';
    }

}
