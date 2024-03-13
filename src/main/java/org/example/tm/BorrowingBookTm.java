package org.example.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BorrowingBookTm {
    private String borrowing_id;
    private String tittle;
    private String dueDate;
    private String book_id;
    private String email;
}
