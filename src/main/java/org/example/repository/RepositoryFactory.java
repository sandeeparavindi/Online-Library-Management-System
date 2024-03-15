package org.example.repository;

import org.example.repository.Custom.Impl.*;

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
        USER, BOOK, BRANCH, BORROWING_BOOK, QUERY
    }

    public SuperRepository getRepo(RepositoryTypes type) {
        switch (type) {
            case USER:
                return new UserRepositoryImpl();
            case BOOK:
                return new BookRepositoryImpl();
            case BRANCH:
                return new BranchRepositoryImpl();
            case BORROWING_BOOK:
                return new BorrowingBookRepositoryImpl();
            case QUERY:
                return new QueryRepositoryImpl();
            default:
                throw new IllegalArgumentException("Unknown repository type: " + type);
        }
    }
}
