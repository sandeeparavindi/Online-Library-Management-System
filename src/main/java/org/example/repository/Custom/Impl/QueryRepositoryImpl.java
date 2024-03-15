package org.example.repository.Custom.Impl;

import org.example.dto.HistoryDto;
import org.example.repository.Custom.QueryRepository;
import org.example.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QueryRepositoryImpl implements QueryRepository {
    @Override
    public List<HistoryDto> getUserHistory(String userEmail) {
        List<HistoryDto> historyDtos = new ArrayList<>();
        try (Session session = SessionFactoryConfig.getInstance().getSession()) {
            session.beginTransaction();

            String hql = "SELECT new org.example.dto.HistoryDto(u.name, b.borrowing_id, b.book.id, b.book.tittle, b.book.status) " +
                    "FROM BorrowingBook b " +
                    "JOIN b.user u " +
                    "JOIN b.book book " +
                    "WHERE u.email = :userEmail";

            Query<HistoryDto> query = session.createQuery(hql, HistoryDto.class);
            query.setParameter("userEmail", userEmail);
            List<HistoryDto> result = query.getResultList();

            historyDtos.addAll(result);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return historyDtos;
    }
}
