package org.example.service.Custom;

import org.example.dto.BookDto;
import org.example.dto.BorrowingBookDto;
import org.example.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface BorrowingBookService extends SuperService {
    String generateNextBorrowingBookId() throws SQLException;

    List<BookDto> loadAllBook() throws SQLException;

    BookDto searchBookID(String tittle) throws SQLException;

    boolean BorrowBook(BorrowingBookDto bookDto) throws SQLException;

}
