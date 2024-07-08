package org.example.epub_ex.book;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "book_tb")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String author;
    private String fileUrl;

    // Getters and Setters
}