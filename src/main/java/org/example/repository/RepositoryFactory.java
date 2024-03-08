package org.example.repository;

import org.example.repository.Custom.Impl.BookRepositoryImpl;
import org.example.repository.Custom.Impl.UserRepositoryImpl;

public class RepositoryFactory {
    private static volatile RepositoryFactory repositoryFactory;

    private RepositoryFactory() {}

    public static RepositoryFactory getRepositoryFactory() {
        if (repositoryFactory == null) {
            synchronized (RepositoryFactory.class) {
                if (repositoryFactory == null) {
                    repositoryFactory = new RepositoryFactory();
                }
            }
        }
        return repositoryFactory;
    }

    public enum RepositoryTypes {
        USER, BOOK
    }

    public SuperRepository getRepo(RepositoryTypes type) {
        switch (type) {
            case USER:
                return new UserRepositoryImpl();
            case BOOK:
                return new BookRepositoryImpl();
            default:
                throw new IllegalArgumentException("Unknown repository type: " + type);
        }
    }
}
