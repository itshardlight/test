/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package purchase;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unish
 */
@Stateless
public class PurchaseDao {
    
    @PersistenceContext
    private EntityManager em;
    
    public void save(PurchaseEntity entity){
        em.persist(entity);
    }
    public void remove(PurchaseEntity entity){
        em.remove(entity);
    }
    public List<PurchaseEntity> show(){
        String query = "SELECT e FROM PurchaseEntity e";
        return em.createQuery(query,PurchaseEntity.class).getResultList();
    }
    
}
