package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class HistoryDto {
    private String userName;
    private String borrowingId;
    private int bookId;
    private String bookTitle;
    private String bookStatus;
}
