package org.example.repository.Custom;

import org.example.entity.Book;
import org.example.repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository extends CrudRepository<Book>{
    String totalBookCount() throws SQLException;
    List<Book> getAllBooks() throws SQLException;
    Book searchByTitle(String title) throws SQLException;
    boolean updateStatus(String bookId, String status) throws SQLException;
}
