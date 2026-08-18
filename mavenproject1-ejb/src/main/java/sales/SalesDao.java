
package sales;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SalesDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(SalesEntity entity1,List<SalesDetailEntity> entity2){
        em.persist(entity1);
        for(SalesDetailEntity entity: entity2){
            em.persist(entity);
        }
    }
    
    public List<SalesEntity> showSalesBill(){
        String query = "SELECT e FROM SalesEntity e";
        return em.createQuery(query, SalesEntity.class).getResultList();
    }
    
    public List<SalesEntity> showBillDetail(Long salesId){
          String query = "SELECT e FROM SalesDetailEntity e where e.sales = :salesId";
        return em.createQuery(query, SalesEntity.class).setParameter("salesId", salesId).getResultList();
    }
    
}
