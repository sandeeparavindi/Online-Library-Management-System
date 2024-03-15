package org.example.repository.Custom;

import org.example.dto.HistoryDto;
import org.example.repository.SuperRepository;

import java.util.List;

public interface QueryRepository extends SuperRepository {
    List<HistoryDto> getUserHistory(String userEmail);
}
