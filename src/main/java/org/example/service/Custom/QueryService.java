package org.example.service.Custom;

import org.example.dto.BorrowingBookDto;
import org.example.dto.HistoryDto;
import org.example.service.SuperService;

import java.util.List;

public interface QueryService extends SuperService {
    List<HistoryDto> getUserHistory(String userEmail);
}
