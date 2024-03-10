package org.example.service.Custom.Impl;

import org.example.dto.BookDto;
import org.example.dto.BranchDto;
import org.example.entity.Branch;
import org.example.repository.Custom.BranchRepository;
import org.example.repository.RepositoryFactory;
import org.example.service.Custom.BranchService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchServiceImpl implements BranchService {

    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory()
            .getRepo(RepositoryFactory.RepositoryTypes.BRANCH);

    @Override
    public List<BranchDto> loadAllBranches() throws SQLException {
        List<Branch> allBranches = branchRepository.loadAll();
        List<BranchDto> branchDtoList = new ArrayList<>();

        for (Branch entity: allBranches) {
            branchDtoList.add(
                    new BranchDto(
                            entity.getCode(),
                            entity.getName(),
                            entity.getManager(),
                            entity.getLocation()
                    )
            );
        }
        return branchDtoList;
    }

    @Override
    public boolean addBranch(BranchDto dto) throws SQLException {
        Branch entity = new Branch(
                dto.getCode(),
                dto.getName(),
                dto.getManager(),
                dto.getLocation()
        );
        return branchRepository.add(entity);
    }

    @Override
    public boolean deleteBranch(String code) throws SQLException {
        return branchRepository.delete(code);
    }

    @Override
    public boolean updateBranch(BranchDto dto) throws SQLException {
        Branch entity = new Branch(
                dto.getCode(),
                dto.getName(),
                dto.getManager(),
                dto.getLocation()
        );
        return branchRepository.update(entity);
    }

    @Override
    public BranchDto searchBranch(String code) throws SQLException {
        Branch entity = branchRepository.search(code);
        return new BranchDto(
                entity.getCode(),
                entity.getName(),
                entity.getManager(),
                entity.getLocation()
        );
    }
}
