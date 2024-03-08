package org.example.service.Custom;

import org.example.service.SuperService;

import java.sql.SQLException;

public interface SignInFormService extends SuperService {
    boolean isExistUser(String userName, String password) throws SQLException, ClassNotFoundException;

}
