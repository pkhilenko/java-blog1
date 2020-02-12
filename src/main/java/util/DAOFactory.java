package util;

import dao.user.HibernateUserDaoImpl;
import dao.user.JDBCUserDaoImpl;
import dao.user.UserDao;

import java.io.*;
import java.util.Properties;

public class DAOFactory {
     FileInputStream fis;
     Properties property = new Properties();


    public UserDao getDAOFactory() {
        String dao = "";


        try {
            InputStream inputStream = getClass()
                    .getClassLoader().getResourceAsStream(".property");

            property.load(inputStream);
            dao = property.getProperty("dao");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (dao) {
            case "jdbcUserDao": return new JDBCUserDaoImpl();
            case "hibernateUserDao": return new HibernateUserDaoImpl();
            default: return  null;
        }
    }
}
