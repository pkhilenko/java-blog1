package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import javax.persistence.Query;
import java.util.List;

public class HibernateUserDaoImpl implements UserDao {
    private SessionFactory sessionFactory;

    public HibernateUserDaoImpl() {
        this.sessionFactory = DBHelper.getInstance().getSessionFactory();
    }


    @Override
    public List<User> selectAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("SELECT u FROM User u", User.class).list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public User selectUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void createUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        if (isExistsUser(user.getEmail())) {
            transaction.rollback();
        } else {
            session.save(user);
            transaction.commit();
        }
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User userBefore = selectUser(user.getId());
        String newEmail = user.getEmail();
        if (isExistsUser(newEmail) && !newEmail.equals(userBefore.getEmail())) {
            transaction.rollback();
        } else {
            session.update(user);
            transaction.commit();
        }
        session.close();
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User u WHERE u.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public boolean isExistsUser(String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> user = session.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email).list();
        transaction.commit();
        session.close();
        return !user.isEmpty();
    }

    @Override
    public User login(String email, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> user = session.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                .setParameter("email", email).setParameter("password", password).list();
        if (user.isEmpty()) {
            transaction.rollback();
            session.close();
            return null;
        }
        transaction.commit();
        session.close();
        return user.get(0);
    }

}
