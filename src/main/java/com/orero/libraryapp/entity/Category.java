package com.orero.libraryapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

}