package org.example.service;

import org.example.service.Custom.Impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return (serviceFactory == null) ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

     public enum ServiceTypes{
        SIGN_UP_FROM, SIGN_IN_FROM, BOOK, BRANCH, BORROWING_BOOK
     }

     public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case SIGN_UP_FROM:
                return new SignUpFromServiceImpl();
            case SIGN_IN_FROM:
                return new SignInFormServiceImpl();
            case BOOK:
                return new BookServiceImpl();
            case BRANCH:
                return new BranchServiceImpl();
           case BORROWING_BOOK:
                return new BorrowingBookServiceImpl();
            default:
                return null;
        }
     }
}
