package org.example.repository.Custom;

import org.example.entity.Branch;
import org.example.repository.CrudRepository;

import java.sql.SQLException;

public interface BranchRepository extends CrudRepository<Branch> {
    String totalBranchCount() throws SQLException;
}
