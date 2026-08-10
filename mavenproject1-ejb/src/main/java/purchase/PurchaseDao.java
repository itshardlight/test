package purchase;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PurchaseDao {

    @PersistenceContext
    private EntityManager em;

    public void save(PurchaseEntity entity1, List<PurchaseDetailEntity> entity2) {

        em.persist(entity1);

        for (PurchaseDetailEntity item : entity2) {
            item.setPurchase(entity1);
            em.persist(item);
        }
    }

    public void remove(PurchaseEntity entity1, PurchaseDetailEntity entity2) {

        em.remove(entity2);
        em.remove(entity1);
    }

    public List<PurchaseEntity> showPurchase() {

        String query = "SELECT e FROM PurchaseEntity e";

        return em.createQuery(query, PurchaseEntity.class)
                .getResultList();
    }

    //get individual purhcaseEntity
    public PurchaseEntity getPurchaseBill(Long purchaseId) {

        String query = "SELECT e FROM PurchaseEntity e WHERE e.id = :id";

        return em.createQuery(query, PurchaseEntity.class)
                .setParameter("id", purchaseId)
                .getSingleResult();
    }

    public List<PurchaseDetailEntity> getPurchaseDetails(Long id) {

        String query = "SELECT e FROM PurchaseDetailEntity e WHERE e.purchase.id = :id";

        return em.createQuery(query, PurchaseDetailEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

}
