package org.example.service.Custom.Impl;

import org.example.dto.BookDto;
import org.example.dto.BranchDto;
import org.example.entity.Book;
import org.example.entity.Branch;
import org.example.repository.Custom.BookRepository;
import org.example.repository.Custom.BranchRepository;
import org.example.repository.RepositoryFactory;
import org.example.service.Custom.BookService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory()
            .getRepo(RepositoryFactory.RepositoryTypes.BOOK);

    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory()
            .getRepo(RepositoryFactory.RepositoryTypes.BRANCH);

    @Override
    public List<BookDto> getAllBooks() throws SQLException {
        List<Book> allBooks = bookRepository.getAllBooks();
        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book entity : allBooks) {
            bookDtoList.add(
                    new BookDto(
                            entity.getId(),
                            entity.getTittle(),
                            entity.getGenre(),
                            entity.getAuthor(),
                            entity.getBranch(),
                            entity.getStatus()
                            )
            );
        }
        return bookDtoList;
    }

    @Override
    public boolean addBook(BookDto dto) throws SQLException {
        if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
            dto.setStatus("Available");
        }
        Book entity = new Book(
                dto.getId(),
                dto.getTittle(),
                dto.getGenre(),
                dto.getAuthor(),
                dto.getBranch(),
                dto.getStatus()

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
                dto.getAuthor(),
                dto.getBranch(),
                dto.getStatus()
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
                entity.getAuthor(),
                entity.getBranch(),
                entity.getStatus()
        );
    }

    @Override
    public List<BranchDto> loadAllBranches() throws SQLException {
        List<Branch> branches = branchRepository.loadAll();
        List<BranchDto> branchDtoList = new ArrayList<>();

        for (Branch entity : branches) {
            branchDtoList.add(new BranchDto(
                    entity.getCode(),
                    entity.getName(),
                    entity.getManager(),
                    entity.getLocation()
            ));
        }
        return branchDtoList;
    }

}

