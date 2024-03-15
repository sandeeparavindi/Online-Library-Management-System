package org.example.repository.Custom.Impl;

import org.example.entity.BorrowingBook;
import org.example.repository.Custom.BorrowingBookRepository;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.sql.SQLException;
import java.util.List;

public class BorrowingBookRepositoryImpl implements BorrowingBookRepository {
    @Override
    public boolean add(BorrowingBook entity) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                entity.setBorrowing_id(generateNextBorrowingId());
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
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BorrowingBook entity) throws SQLException {
        return false;
    }

    @Override
    public BorrowingBook search(String id) throws SQLException {
            try (Session session = SessionFactoryConfig.getInstance().getSession()) {
                Transaction transaction = session.beginTransaction();
                try {
                    BorrowingBook borrowingBook = session.get(BorrowingBook.class, id);
                    transaction.commit();
                    return borrowingBook;
                } catch (Exception e) {
                    transaction.rollback();
                    throw new SQLException("Failed to search for borrowing book with ID: " + id, e);
                }
            }
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
        public String splitBorrowingId(String currentOrderId) {
            if (currentOrderId != null) {
         int id = Integer.parseInt(currentOrderId.substring(1)) + 1;
            return "B" + String.format("%03d", id);
      }
        return "B001";
    }

    @Override
    public boolean returnBook(String borrowingId) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                BorrowingBook borrowingBook = session.get(BorrowingBook.class, borrowingId);
                if (borrowingBook != null) {
                    session.delete(borrowingBook);
                }
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to return the book", e);
            }
        }
    }

    @Override
    public String generateNextBorrowingId() throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createQuery("SELECT MAX(borrowing_id) FROM BorrowingBook");
                String maxId = (String) query.uniqueResult();
                transaction.commit();

                if (maxId == null || maxId.isEmpty()) {
                    return "B001";
                } else {
                    return splitBorrowingId(maxId);
                }
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw new SQLException("Failed to generate next borrowing ID", e);
            }
        }
    }
}
