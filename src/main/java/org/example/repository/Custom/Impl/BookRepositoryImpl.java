package org.example.repository.Custom.Impl;

import org.example.entity.Book;
import org.example.repository.Custom.BookRepository;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public boolean add(Book entity) throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.delete(book);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Book entity) throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Book search(String id) throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class, id);
        transaction.commit();
        session.close();
        return book;
    }

    @Override
    public List<Book> loadAll() throws SQLException {
        return getAllBooks();
    }

    @Override
    public String totalBookCount() throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Book", Long.class);
        Long count = query.uniqueResult();
        transaction.commit();
        session.close();
        return count.toString();
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List<Book> books = session.createQuery("FROM Book", Book.class).list();
        transaction.commit();
        session.close();
        return books;
    }
}
