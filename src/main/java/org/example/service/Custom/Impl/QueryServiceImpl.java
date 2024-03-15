package org.example.service.Custom.Impl;

import org.example.dto.BorrowingBookDto;
import org.example.dto.HistoryDto;
import org.example.repository.Custom.QueryRepository;
import org.example.repository.RepositoryFactory;
import org.example.service.Custom.QueryService;

import java.util.List;

public class QueryServiceImpl implements QueryService {

    QueryRepository queryRepository = (QueryRepository) RepositoryFactory.
            getRepositoryFactory().getRepo(RepositoryFactory.RepositoryTypes.QUERY);
    @Override
    public List<HistoryDto> getUserHistory(String userEmail) {
        return queryRepository.getUserHistory(userEmail);
    }
}
