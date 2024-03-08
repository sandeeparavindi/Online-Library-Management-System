package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "book_tittle")
    private String tittle;

    @Column(name = "genre")
    private String genre;

    @Column(name = "author")
    private String author;
}
