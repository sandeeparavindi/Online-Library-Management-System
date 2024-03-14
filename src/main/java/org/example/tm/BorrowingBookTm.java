package org.example.tm;

import javafx.scene.control.Button;
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
    private int book_id;
    private Button btn;
}
