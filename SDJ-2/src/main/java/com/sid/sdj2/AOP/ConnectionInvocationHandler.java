package com.sid.sdj2.AOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class ConnectionInvocationHandler implements InvocationHandler {
    public ConnectionInvocationHandler(Connection connection) {
        this.connection = connection;
    }

    private final Connection connection;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().contains("commit") || method.getName().contains("rollback") || method.getName().contains("close")) {
            System.out.println("ConnectionTrace: " + method.toGenericString());
        }

        Object returnVal = method.invoke(connection, args);
        return returnVal;
    }
}
