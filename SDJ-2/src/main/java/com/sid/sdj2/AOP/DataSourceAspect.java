package com.sid.sdj2.AOP;

import com.mysql.cj.jdbc.ConnectionImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.sql.Connection;

@Component
@Aspect
public class DataSourceAspect {

    @Around("target(javax.sql.DataSource)")
    public Object logDataSourceConnectionInfo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("DataSourceTracker:" + proceedingJoinPoint.getSignature());
        Object returnVal = proceedingJoinPoint.proceed();
        if (returnVal instanceof Connection) {
            Connection con = (Connection) Proxy.newProxyInstance(ConnectionImpl.class.getClassLoader(),
                    new Class[]{Connection.class}, new ConnectionInvocationHandler((Connection) returnVal)
            );
            return con;
        }
        return returnVal;
    }
}
