package org.example.repository.Custom;

import org.example.entity.BorrowingBook;
import org.example.repository.CrudRepository;

import java.sql.SQLException;

public interface BorrowingBookRepository extends CrudRepository<BorrowingBook> {
    String generateNextBorrowingId() throws SQLException;
    String splitBorrowingId(String currentOrderId);
}
