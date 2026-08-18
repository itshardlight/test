package stock;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import purchase.PurchaseDetailEntity;

@Stateless
public class StockDao {

    @PersistenceContext
    private EntityManager em;

    public List<Object[]> displayStock() {

        String query
                = "SELECT p.productId, "
                + "       SUM(p.stockQuantity) - "
                + "       COALESCE("
                + "           (SELECT SUM(s.soldQuantity) "
                + "            FROM SalesDetailEntity s "
                + "            WHERE s.productId.id = p.productId.id), "
                + "           0"
                + "       ) "
                + "FROM PurchaseDetailEntity p "
                + "GROUP BY p.productId";

        return em.createQuery(query).getResultList();
    }
}
