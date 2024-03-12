package org.example.repository.Custom.Impl;

import org.example.entity.Book;
import org.example.entity.BorrowingBook;
import org.example.repository.Custom.BorrowingBookRepository;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class BorrowingBookImpl implements BorrowingBookRepository {
    @Override
    public boolean add(BorrowingBook entity) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed", e);
            }
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BorrowingBook entity) throws SQLException {
        return false;
    }

    @Override
    public BorrowingBook search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<BorrowingBook> loadAll() throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                List<BorrowingBook> allBooks;
                Query<BorrowingBook> query = session.createQuery("FROM BorrowingBook ", BorrowingBook.class);
                allBooks = query.getResultList();
                transaction.commit();
                return allBooks;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to load all borrowing", e);
            }
        }
    }

    @Override
    public String generateNextBorrowingId() throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createQuery("SELECT MAX(id) FROM BorrowingBook");
                Integer maxId = (Integer) query.uniqueResult();
                transaction.commit();
                return splitBorrowingId(maxId != null ? maxId.toString() : null);
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to generate next borrowing ID", e);
            }
        }
    }

    @Override
    public String splitBorrowingId(String currentOrderId) {
        if (currentOrderId != null) {
            int id = Integer.parseInt(currentOrderId) + 1;
            return "B" + String.format("%03d", id);
        }
        return "B001";
    }
}
