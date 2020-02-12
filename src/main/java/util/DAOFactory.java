package util;

import dao.user.HibernateUserDaoImpl;
import dao.user.JDBCUserDaoImpl;
import dao.user.UserDao;

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

        if (dao.equals("jdbcUserDao")) {
            return new JDBCUserDaoImpl();
        }
        if (dao.equals("hibernateUserDao")) {
            return  new HibernateUserDaoImpl();
        }
            return  new HibernateUserDaoImpl();
    }
}
