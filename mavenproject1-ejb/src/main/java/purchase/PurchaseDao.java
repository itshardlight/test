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

    public void save(PurchaseEntity entity1, List<PurchaseDetailEntity> entity2) {
        em.persist(entity1);
        for (PurchaseDetailEntity item : entity2) {
                    em.persist(item);
            }
    }

    public void remove(PurchaseEntity entity1, PurchaseDetailEntity entity2) {
        em.remove(entity1);
        em.remove(entity2);

    }

 

}
