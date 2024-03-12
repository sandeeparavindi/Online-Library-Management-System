package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BorrowingBookDto {
    private String borrowing_id;
    private String book_id;
    private String tittle;
    private String dueDate;
}
