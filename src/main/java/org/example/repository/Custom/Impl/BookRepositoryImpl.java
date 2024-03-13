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
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to add borrowing book", e);
            }
        }
    }

    @Override
    public Book searchByTitle(String title) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query<Book> query = session.createQuery("FROM Book WHERE tittle = :title", Book.class);
                query.setParameter("title", title);
                Book book = query.uniqueResult();
                transaction.commit();
                return book;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to search for book by title", e);
            }
        }
    }

    @Override
    public boolean updateStatus(String bookId, String status) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Book book = session.get(Book.class, bookId);
                if (book != null) {
                    book.setStatus(status);
                    session.update(book);
                }
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to update book status", e);
            }
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Book book = session.get(Book.class, id);
                session.delete(book);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to delete book", e);
            }
        }
    }

    @Override
    public boolean update(Book entity) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(entity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to update book", e);
            }
        }
    }

    @Override
    public Book search(String tittle) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.get(Book.class, tittle);
        } catch (Exception e) {
            throw new SQLException("Failed to search for book", e);
        }
    }

    @Override
    public List<Book> loadAll() throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                List<Book> allBooks;
                Query<Book> query = session.createQuery("FROM Book", Book.class);
                allBooks = query.getResultList();
                transaction.commit();
                return allBooks;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to load all books", e);
            }
        }
    }

    @Override
    public String totalBookCount() throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Book", Long.class);
                Long count = query.uniqueResult();
                transaction.commit();
                return count.toString();
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to get total book count", e);
            }
        }
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query<Book> query = session.createQuery("FROM Book", Book.class);
                List<Book> allBooks = query.getResultList();
                transaction.commit();
                return allBooks;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to get all books", e);
            }
        }
    }


    }

    //    @Override
//    public boolean add(Book entity) throws SQLException {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(entity);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//    @Override
//    public boolean delete(String id) throws SQLException {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Book book = session.get(Book.class, id);
//        session.delete(book);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//    @Override
//    public boolean update(Book entity) throws SQLException {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        session.update(entity);
//        transaction.commit();
//        session.close();
//        return true;
//    }
//
//    @Override
//    public Book search(String id) throws SQLException {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Book book = session.get(Book.class, id);
//        transaction.commit();
//        session.close();
//        return book;
//    }
//
//    @Override
//    public List<Book> loadAll() throws SQLException {
//        return getAllBooks();
//    }
//
//    @Override
//    public String totalBookCount() throws SQLException {
//        Session session = SessionFactoryConfig.getInstance().getSession();
//        Transaction transaction = session.beginTransaction();
//        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Book", Long.class);
//        Long count = query.uniqueResult();
//        transaction.commit();
//        session.close();
//        return count.toString();
//    }
//
//    @Override
//    public List<Book> getAllBooks() throws SQLException {
//        List<Book> allBooks;
//        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
//            Transaction transaction = session.beginTransaction();
//            Query<Book> query = session.createQuery("FROM Book", Book.class);
//            allBooks = query.getResultList();
//            transaction.commit();
//        }
//        return allBooks;
//    }


