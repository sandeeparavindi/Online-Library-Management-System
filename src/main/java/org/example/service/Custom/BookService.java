package org.example.service.Custom;

import org.example.dto.BookDto;
import org.example.dto.BranchDto;
import org.example.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface BookService extends SuperService {
    List<BookDto> getAllBooks() throws SQLException;
    boolean addBook(final BookDto dto) throws SQLException ;
    boolean deleteBook(String id) throws SQLException;
    boolean updateBook(final BookDto dto) throws SQLException;
    BookDto searchBook(String id) throws SQLException;
    List<BranchDto> loadAllBranches() throws SQLException;
}
