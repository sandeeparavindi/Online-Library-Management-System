package org.example.repository.Custom.Impl;

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
        return null;
    }

    @Override
    public List<User> loadAll() throws SQLException {
        return null;
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
}
