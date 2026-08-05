package auth;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public void save(UserEntity entity) {
        em.persist(entity);
    }

    public List<UserEntity> getallUsers() {
        String query = "SELECT u FROM UserEntity u";
        return em.createQuery(query, UserEntity.class).getResultList();
    }

    public UserEntity findByUser(String uName) {
        String query = "SELECT u FROM UserEntity u WHERE u.UName = :uName";

        try {
            UserEntity entity = em.createQuery(query, UserEntity.class)
                    .setParameter("uName", uName)
                    .getSingleResult();

            return entity;
        } catch (NoResultException e) {
            return null; 
        }
    }

    public boolean userExists(String uName) {
        String query = "SELECT u FROM UserEntity u WHERE u.UName = :uName";
        return !em.createQuery(query, UserEntity.class)
                .setParameter("uName", uName)
                .getResultList()
                .isEmpty();
    }
}
