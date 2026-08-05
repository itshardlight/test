package auth;

import auth.UserDao;
import auth.UserEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import util.PasswordUtil;

@Stateless
public class UserService {

    @EJB
    private UserDao dao;

//    Registering the User 
    public String save(String UName, String UPass, String URole) {
        if (!dao.userExists(UName)) {
            UserEntity entity = new UserEntity();
            entity.setUName(UName);
            String hashPass = PasswordUtil.hashPassword(UPass);
            entity.setUPass(hashPass);
            entity.setURole(URole);
            dao.save(entity);
            return "User Registration Sucessfull";
        } else {
            return "User Already Exist";
        }
    }

//    Verifying the User 
    public String auth(String UName, String UPass, String URole) {
        UserEntity entity = dao.findByUser(UName);

        if (entity == null) {
            return "User not Found";
        } else {
            Boolean passMatch = PasswordUtil.verifyPassword(UPass, entity.getUPass());

            if (passMatch && entity.getURole().equals(URole)) {
                return "User Found";
            } else {
                return "Password or Role Did not match";
            }
        }
    }
}
