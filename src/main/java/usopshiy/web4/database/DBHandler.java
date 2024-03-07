package usopshiy.web4.database;

import jakarta.ejb.Singleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import usopshiy.web4.entity.Point;
import usopshiy.web4.entity.User;
import usopshiy.web4.utils.Hash;

import java.util.List;

@Singleton
public class DBHandler {
    private final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private final Session session = sessionFactory.openSession();

    //User part
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
        } catch (Exception e){
            return false;
        }
    }

    public boolean checkPass(String pass, String email){
        User user = getUser(email);

        return user.getPassword().equals(Hash.SHA(pass));
    }

    public String doUserExist(String email){
        try{
            User user = (User) session.createQuery("FROM User  u where u.email = :email").
                    setParameter("email", email).
                    getSingleResult();
            if (user == null){
                return "false1";
            }
            else return "true";
        } catch (Exception e) {
            return "false2";
        }
    }
    public User getUser(String email){
        User user = (User) session.createQuery("FROM User u where u.email = :email").
                setParameter("email", email).
                getSingleResult();
        return user;
    }


    //Point part

    public Point createPoint(Double x, Double y, Integer r, User user){
        final Point point = new Point();
        point.setUser(user);
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.check();

        return point;
    }

    public boolean clearPoints(User owner){
        try{
            session.getTransaction().begin();
            session.createQuery("delete from Point p where p.user = :owner").
                setParameter("owner", owner).executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;}
    }

    public boolean addPoint(Point point){
        try {
            session.getTransaction().begin();
            session.persist(point);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public List getPoints(User owner){
        return session.createQuery("From Point p where p.user = :owner").
                setParameter("owner", owner).
                getResultList();
    }
}
