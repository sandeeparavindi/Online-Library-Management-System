package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "book")

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

    @Column(name = "branch_name")
    private String branch;

    @Column(name = "status")
    private String status;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branches;

    @ToString.Exclude
    @OneToMany(mappedBy = "book", targetEntity = BorrowingBook.class)
    List<Book> bookList = new ArrayList<>();

    public Book(int id, String tittle, String genre, String author, String branch, String status) {
        this.id = id;
        this.tittle = tittle;
        this.genre = genre;
        this.author = author;
        this.branch = branch;
        this.status = status;
    }

    public Book() {

    }
}
