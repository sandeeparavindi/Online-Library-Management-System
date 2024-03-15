package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.tm.BorrowingBookTm;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data

@Entity
@Table(name = "borrowing_book")

public class BorrowingBook {
    @Id
    @Column(name = "borrowing_id")
    private String borrowing_id;

    @Column(name = "title")
    private String tittle;

    @Column(name = "due_date")
    private String dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User user;

    public BorrowingBook(String borrowing_id, String tittle, String dueDate) {
        this.borrowing_id = borrowing_id;
        this.tittle = tittle;
        this.dueDate = dueDate;
    }
}
