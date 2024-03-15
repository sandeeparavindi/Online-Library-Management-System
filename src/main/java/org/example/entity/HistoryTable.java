package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class HistoryTable {
    private String userName;
    private int borrowingId;
    private int bookId;
    private String bookTitle;
    private String bookStatus;
}
