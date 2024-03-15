package org.example.repository.Custom;

import org.example.entity.User;
import org.example.repository.CrudRepository;

import java.sql.SQLException;

public interface UserRepository extends CrudRepository<User> {

    String getEmail(String email) throws SQLException, ClassNotFoundException;
    boolean isExistUser(String userName, String pw) throws SQLException, ClassNotFoundException;
    User searchUser(String email) throws SQLException;
}
