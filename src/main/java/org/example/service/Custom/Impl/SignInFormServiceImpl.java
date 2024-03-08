package org.example.service.Custom.Impl;

import org.example.repository.Custom.UserRepository;
import org.example.repository.RepositoryFactory;
import org.example.service.Custom.SignInFormService;

import java.sql.SQLException;

public class SignInFormServiceImpl implements SignInFormService {

    UserRepository userRepository = (UserRepository) RepositoryFactory.getRepositoryFactory()
            .getRepo(RepositoryFactory.RepositoryTypes.USER);

    @Override
    public boolean isExistUser(String userName, String pw) throws SQLException, ClassNotFoundException {
        return userRepository.isExistUser(userName,pw);
    }
}
