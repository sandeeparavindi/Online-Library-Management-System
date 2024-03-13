package org.example.service.Custom.Impl;

import org.example.dto.BookDto;
import org.example.dto.BorrowingBookDto;
import org.example.dto.BranchDto;
import org.example.entity.Book;
import org.example.entity.BorrowingBook;
import org.example.entity.Branch;
import org.example.repository.Custom.BookRepository;
import org.example.repository.Custom.BorrowingBookRepository;
import org.example.repository.Custom.UserRepository;
import org.example.repository.RepositoryFactory;
import org.example.service.Custom.BookService;
import org.example.service.Custom.BorrowingBookService;
import org.example.tm.BorrowingBookTm;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowingBookServiceImpl implements BorrowingBookService {

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory()
            .getRepo(RepositoryFactory.RepositoryTypes.USER);

    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory()
            .getRepo(RepositoryFactory.RepositoryTypes.BOOK);

    BorrowingBookRepository borrowingBookRepository = (BorrowingBookRepository) RepositoryFactory
            .getRepositoryFactory().getRepo(RepositoryFactory.RepositoryTypes.BORROWING_BOOK);


    @Override
    public String generateNextBorrowingBookId() throws SQLException {
        return borrowingBookRepository.generateNextBorrowingId();
    }


    @Override
    public boolean addBorrowBook(BorrowingBookDto dto) throws SQLException {
        BorrowingBook borrowingBook = new BorrowingBook(dto.getBorrowing_id(), dto.getTittle(), dto.getDueDate());
        return borrowingBookRepository.add(borrowingBook);
    }

    @Override
    public List<BorrowingBookDto> loadAllBorrowBook() throws SQLException {
        List<BorrowingBook> allBook = borrowingBookRepository.loadAll();
        List<BorrowingBookDto> borrowingBookDtos = new ArrayList<>();

        for (BorrowingBook entity: allBook) {
            borrowingBookDtos.add(
                    new BorrowingBookDto(
                            entity.getBorrowing_id(),
                            entity.getTittle(),
                            entity.getDueDate()
                    )
            );
        }
        return borrowingBookDtos;
    }

    @Override
    public List<BookDto> loadAllBook() throws SQLException {
        List<Book> bookList = bookRepository.loadAll();
        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book entity : bookList) {
            bookDtoList.add(new BookDto(
                    entity.getId(),
                    entity.getTittle(),
                    entity.getGenre(),
                    entity.getAuthor(),
                    entity.getBranch(),
                    entity.getStatus()
            ));
        }
        return bookDtoList;
    }

    @Override
    public BookDto searchBookID(String tittle) throws SQLException {
        Book entity = bookRepository.searchByTitle(tittle);
        return new BookDto(
                entity.getId(),
                entity.getTittle(),
                entity.getGenre(),
                entity.getAuthor(),
                entity.getBranch(),
                entity.getStatus()
        );
    }

}
