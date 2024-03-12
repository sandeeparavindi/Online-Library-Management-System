package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data

@Entity
@Table(name = "borrowing_book")

public class BorrowingBook {

    @Id
    @Column(name = "borrowing_id")
    private String borrowing_id;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "due_date")
    private String dueDate;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinColumn(name = "book_id"
    ,referencedColumnName = "book_id"
    ,insertable = false
    ,updatable = false)
    private Book book;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "email"
    ,referencedColumnName = "email"
    ,insertable = false
    ,updatable = false)
    private User user;

    public BorrowingBook(String borrowing_id, String tittle, String dueDate) {
        this.borrowing_id = borrowing_id;
        this.tittle = tittle;
        this.dueDate = dueDate;
    }
}
