package usopshiy.web4.database;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import usopshiy.web4.entity.User;

@Stateless
public class Auth {
    @EJB
    private DBHandler dbHandler;

    public String register(String login, String email, String pass){
        String user = dbHandler.doUserExist(email);
        if(!user.equals("true")){
            User user1 = dbHandler.createUser(login, email, pass);
            if(user1 == null){
                return "false";
            }
            else
                return String.valueOf(dbHandler.saveUser(user1));
        }
        return "false";
    }

    public String login(String email, String pass){
        String user = dbHandler.doUserExist(email);

        if(user.equals("true") && dbHandler.checkPass(pass, email)){
            return "true";
        }

        return "false";
    }
}
