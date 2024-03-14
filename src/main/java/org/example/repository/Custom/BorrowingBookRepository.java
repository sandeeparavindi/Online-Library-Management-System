package org.example.repository.Custom;

import org.example.dto.BorrowingBookDto;
import org.example.entity.BorrowingBook;
import org.example.repository.CrudRepository;
import org.example.tm.BorrowingBookTm;

import java.sql.SQLException;
import java.util.List;

public interface BorrowingBookRepository extends CrudRepository<BorrowingBook> {
    String generateNextBorrowingId() throws SQLException;
    String splitBorrowingId(String currentOrderId);
    boolean returnBook(String borrowingId) throws SQLException;
}
