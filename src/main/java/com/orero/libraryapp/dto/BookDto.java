package com.orero.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String title;

    private String author;

    private String description;

    private Integer publishYear;

    private String isbn;

    private Double price;
}
