package util;

import dao.HibernateUserDaoImpl;
import dao.JDBCUserDaoImpl;
import dao.UserDao;

import java.io.*;
import java.util.Properties;

public class DAOFactory {
    Properties property = new Properties();

    public UserDao getDAOFactory() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(".property");

        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dao = property.getProperty("dao");

        switch (dao) {
            case "jdbcUserDao":
                return new JDBCUserDaoImpl();
            case "hibernateUserDao":
                return new HibernateUserDaoImpl();
            default:
                return new HibernateUserDaoImpl();
        }

    }
}
