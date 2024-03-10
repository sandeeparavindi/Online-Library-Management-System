package org.example.service.Custom.Impl;

import org.example.dto.BookDto;
import org.example.entity.Book;
import org.example.repository.Custom.BookRepository;
import org.example.repository.RepositoryFactory;
import org.example.service.Custom.BookService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepo(RepositoryFactory.RepositoryTypes.BOOK);
    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        List<Book> allBooks = bookRepository.getAllBooks();
        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book entity: allBooks) {
            bookDtoList.add(
                    new BookDto(
                            entity.getId(),
                            entity.getTittle(),
                            entity.getGenre(),
                            entity.getAuthor()
                    )
            );
        }
        return bookDtoList;
    }

    @Override
    public boolean addBook(BookDto dto) throws SQLException {
        Book entity = new Book(
                dto.getId(),
                dto.getTittle(),
                dto.getGenre(),
                dto.getAuthor()
        );
        return bookRepository.add(entity);
    }

    @Override
    public boolean deleteBook(String id) throws SQLException {
        return bookRepository.delete(id);
    }

    @Override
    public boolean updateBook(BookDto dto) throws SQLException {
        Book entity = new Book(
                dto.getId(),
                dto.getTittle(),
                dto.getGenre(),
                dto.getAuthor()
        );
        return bookRepository.update(entity);
    }

    @Override
    public BookDto searchBook(String id) throws SQLException {
        Book entity = bookRepository.search(id);
        return new BookDto(
                entity.getId(),
                entity.getTittle(),
                entity.getGenre(),
                entity.getAuthor()
        );
    }
}
