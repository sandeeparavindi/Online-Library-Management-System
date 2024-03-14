package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data

public class BorrowingBookDto {
    private String borrowing_id;
    private String tittle;
    private String dueDate;

    @ToString.Exclude
    private int book_id;
    @ToString.Exclude
    private String email;

    public BorrowingBookDto(String borrowing_id, String title, String dueDate, int book_id) {
        this.borrowing_id = borrowing_id;
        this.tittle = title;
        this.dueDate = dueDate;
        this.book_id = Integer.parseInt(String.valueOf(book_id));
    }


}
