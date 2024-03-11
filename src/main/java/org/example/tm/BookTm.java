package org.example.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookTm {
    private int id;
    private String tittle;
    private String genre;
    private String author;
    private String branch;
}
