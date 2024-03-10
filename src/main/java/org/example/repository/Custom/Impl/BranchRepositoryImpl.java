package org.example.repository.Custom.Impl;

import org.example.entity.Branch;
import org.example.repository.Custom.BranchRepository;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class BranchRepositoryImpl implements BranchRepository {
    @Override
    public boolean add(Branch entity) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to add branch", e);
            }
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Branch branch = session.get(Branch.class, id);
                session.delete(branch);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to delete book", e);
            }
        }
    }

    @Override
    public boolean update(Branch entity) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(entity);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to update branch", e);
            }
        }
    }

    @Override
    public Branch search(String id) throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            return session.get(Branch.class, id);
        } catch (Exception e) {
            throw new SQLException("Failed to search for branch", e);
        }
    }

    @Override
    public List<Branch> loadAll() throws SQLException {
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query<Branch> query = session.createQuery("FROM Branch ", Branch.class);
                List<Branch> allBranches = query.getResultList();
                transaction.commit();
                return allBranches;
            } catch (Exception e) {
                transaction.rollback();
                throw new SQLException("Failed to get all branches", e);
            }
        }
    }
}
