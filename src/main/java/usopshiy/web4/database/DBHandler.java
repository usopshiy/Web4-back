package usopshiy.web4.database;

import jakarta.ejb.Stateful;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import usopshiy.web4.entity.User;
import usopshiy.web4.utils.Hash;

import javax.persistence.NoResultException;

@Stateful
public class UserDB {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private final Session session = sessionFactory.openSession();

    public User createUser(String login, String email, String password) {
            final User user = new User();
            final String hashPass = Hash.SHA(password);

            user.setEmail(email);
            user.setLogin(login);
            user.setPassword(hashPass);

            return user;
    }

    public boolean saveUser(User user){
        try{
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    public boolean checkPass(String pass, String email){
        User user = getUser(email);

        return user.getPassword().equals(Hash.SHA(pass));
    }

    public String doUserExist(String email){
        try{
            User user = (User) session.createQuery("FROM User u where u.email = :email").
                    setParameter("email", email).
                    getSingleResult();
            if (user == null){
                return "false1";
            }
            else return "true";
        } catch (NoResultException e) {
            return "false2";
        }
    }
    public User getUser(String email){
        User user = (User) session.createQuery("FROM User u where u.email = :email").
                setParameter("email", email).
                getSingleResult();
        return user;
    }

}
