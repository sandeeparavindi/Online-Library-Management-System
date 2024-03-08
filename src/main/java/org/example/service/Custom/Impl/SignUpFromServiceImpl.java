package org.example.service.Custom.Impl;

import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.repository.Custom.UserRepository;
import org.example.repository.RepositoryFactory;
import org.example.service.Custom.SignUpFromService;

import java.sql.SQLException;

public class SignUpFromServiceImpl implements SignUpFromService {

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory().getRepo(RepositoryFactory.RepositoryTypes.USER);
    @Override
    public boolean addUser(UserDto dto) throws SQLException {
        User entity = new User(
                dto.getEmail(),
                dto.getName(),
                dto.getPassword()
        );
        return userRepository.add(entity);
    }

    @Override
    public String getEmail(String email) throws SQLException, ClassNotFoundException {
        return userRepository.getEmail(email);
    }
}
