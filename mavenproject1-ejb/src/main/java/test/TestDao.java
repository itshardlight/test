
package test;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TestDao {
    
    @PersistenceContext
    private EntityManager em;
    TestEntity entity = new TestEntity();
    
    public List<TestEntity> show(){
        String query = "SELECT e FROM TestEntity e";
        return em.createQuery(query,TestEntity.class).getResultList();
    }
    
    public void save(TestEntity entity){
        em.persist(entity);
    }
    
    
}
