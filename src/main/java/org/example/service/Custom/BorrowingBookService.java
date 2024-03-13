package org.example.service.Custom;

import org.example.dto.BookDto;
import org.example.dto.BorrowingBookDto;
import org.example.dto.BranchDto;
import org.example.entity.BorrowingBook;
import org.example.service.SuperService;
import org.example.tm.BorrowingBookTm;

import java.sql.SQLException;
import java.util.List;

public interface BorrowingBookService extends SuperService {
    String generateNextBorrowingBookId() throws SQLException;

    List<BookDto> loadAllBook() throws SQLException;

    BookDto searchBookID(String tittle) throws SQLException;
    boolean addBorrowBook(final BorrowingBookDto dto) throws SQLException ;

    List<BorrowingBookDto> loadAllBorrowBook() throws SQLException;

}
