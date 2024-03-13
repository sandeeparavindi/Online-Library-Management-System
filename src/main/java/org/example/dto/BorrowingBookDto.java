package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data

public class BorrowingBookDto {
    private String borrowing_id;
    private String tittle;
    private String dueDate;

    @ToString.Exclude
    private String book_id;
    @ToString.Exclude
    private String email;

    public BorrowingBookDto(String borrowing_id, String tittle, String dueDate) {
        this.borrowing_id = borrowing_id;
        this.tittle = tittle;
        this.dueDate = dueDate;
    }

}
