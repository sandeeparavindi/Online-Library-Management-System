package org.example.service.Custom;

import org.example.dto.BookDto;
import org.example.dto.BranchDto;
import org.example.entity.Branch;
import org.example.service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface BranchService extends SuperService {
    List<BranchDto> loadAllBranches() throws SQLException;
    boolean addBranch(final BranchDto dto) throws SQLException ;
    boolean deleteBranch(String code) throws SQLException;
    boolean updateBranch(final BranchDto dto) throws SQLException;
    BranchDto searchBranch(String code) throws SQLException;
    BranchDto searchBranchByName(String name) throws SQLException;
}
