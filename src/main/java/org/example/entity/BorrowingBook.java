package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "borrowing_book")

public class BorrowingBook {
    @Id
    @Column(name = "borrowing_id")
    private String borrowing_id;

    @Column(name = "book_id")
    private String book_id;

    @Column(name = "tittle")
    private String tittle;

    @Column(name = "due_date")
    private String dueDate;
}
