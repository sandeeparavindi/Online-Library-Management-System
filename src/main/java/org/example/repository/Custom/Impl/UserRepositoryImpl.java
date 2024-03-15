package org.example.repository.Custom.Impl;

import org.example.entity.Branch;
import org.example.entity.User;
import org.example.repository.Custom.UserRepository;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean add(User entity) throws SQLException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return false;
    }

    @Override
    public User search(String id) throws SQLException {
            try (Session session = SessionFactoryConfig.getInstance().getSession()) {
                Transaction transaction = session.beginTransaction();
                try {
                    User user = session.get(User.class, id);
                    transaction.commit();
                    return user;
                } catch (Exception e) {
                    transaction.rollback();
                    throw new SQLException("Failed to search for user by ID", e);
                }
            }

    }

    @Override
    public List<User> loadAll() throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query<User> query = session.createQuery("FROM User", User.class);
                List<User> allUsers = query.getResultList();
                transaction.commit();
                return allUsers;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to get all users", e);
            }
        }
    }

    @Override
    public String getEmail(String email) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean isExistUser(String userName, String pw) throws SQLException, ClassNotFoundException {
        Session session = SessionFactoryConfig.getInstance().getSession();
        try {
            session.beginTransaction();
            Query<User> query = session.createQuery("FROM User WHERE name = :userName AND password = :password", User.class);
            query.setParameter("userName", userName);
            query.setParameter("password", pw);
            List<User> resultList = query.getResultList();
            session.getTransaction().commit();
            return !resultList.isEmpty();
        } finally {
            session.close();
        }
    }

    @Override
    public User searchUser(String email) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
                query.setParameter("email", email);
                User user = query.uniqueResult();
                transaction.commit();
                return user;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to search for user by email", e);
            }
        }
    }
}
