package com.orero.libraryapp.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {

	@NotBlank(message = "name cannot be blank")
	@NonNull
	@Column(nullable = false)
	private String name;

	@NotBlank(message = "description cannot be blank")
	@NonNull
	@Column(nullable = false)
	private String description;

    @ManyToMany
	@JsonIgnore
    @JoinTable(name = "book_categories", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

	@Override
	public String toString() {
		return "Category{" + "name='" + name + '\'' + ", description='" + description + '\'' + '}';
	}
}