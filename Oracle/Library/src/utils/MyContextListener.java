package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
 
public class MyContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
 
    }
 
    // 自己写代码注销驱动
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
 
        // 再关闭AbandonedConnectionCleanupThread线程
        try {
            AbandonedConnectionCleanupThread.uncheckedShutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        System.out.println("手动注销驱动、关闭AbandonedConnectionCleanupThread线程");
    }
}