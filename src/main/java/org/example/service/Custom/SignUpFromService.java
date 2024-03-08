package org.example.service.Custom;

import org.example.dto.UserDto;
import org.example.service.SuperService;

import java.sql.SQLException;

public interface SignUpFromService extends SuperService {
    boolean addUser(final UserDto dto) throws SQLException;
    String getEmail(String email) throws SQLException, ClassNotFoundException;
}
