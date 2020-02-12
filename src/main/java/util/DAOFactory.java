package util;

import dao.user.HibernateUserDaoImpl;
import dao.user.JDBCUserDaoImpl;
import dao.user.UserDao;

import java.io.*;
import java.util.Properties;

public class DAOFactory {
    Properties property = new Properties();

    public UserDao getDAOFactory() {
        String dao = "";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(".property");

        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dao = property.getProperty("dao");

        if (dao == "jdbcUserDao") {
            return new JDBCUserDaoImpl();
        }
            return  new HibernateUserDaoImpl();
    }
}
