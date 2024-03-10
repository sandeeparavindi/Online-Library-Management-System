package org.example.service;

import org.example.service.Custom.Impl.BookServiceImpl;
import org.example.service.Custom.Impl.BranchServiceImpl;
import org.example.service.Custom.Impl.SignInFormServiceImpl;
import org.example.service.Custom.Impl.SignUpFromServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return (serviceFactory == null) ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

     public enum ServiceTypes{
        SIGN_UP_FROM, SIGN_IN_FROM, BOOK, BRANCH
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
            default:
                return null;
        }
     }
}
