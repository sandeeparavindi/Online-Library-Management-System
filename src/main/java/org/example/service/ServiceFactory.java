package org.example.service;

import org.example.service.Custom.Impl.SignInFormServiceImpl;
import org.example.service.Custom.Impl.SignUpFromServiceImpl;
import org.example.service.Custom.SignInFormService;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return (serviceFactory == null) ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

     public enum ServiceTypes{
        SIGN_UP_FROM, SIGN_IN_FROM
     }

     public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case SIGN_UP_FROM:
                return new SignUpFromServiceImpl();
            case SIGN_IN_FROM:
                return new SignInFormServiceImpl();
            default:
                return null;
        }
     }
}
