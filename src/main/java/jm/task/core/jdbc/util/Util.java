package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DB_NAME = "test";
    private static final String LOGIN = "root";
    private static final String PASS = "root";
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getConnection() {

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME)
                    .setProperty("hibernate.connection.username", LOGIN)
                    .setProperty("hibernate.connection.password", PASS)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    //.setProperty("hibernate.hbm2ddl.auto", "none")
                    .addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static void closeConnection() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}