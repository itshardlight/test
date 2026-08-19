package stock;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class StockDao {

    @PersistenceContext
    private EntityManager em;

    public void save(StockEntity entity) {
        try {
            em.persist(entity);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Success",
                            "Product saved successfully in stock database."
                    ));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", e.getMessage()));
        }

    }

    public List<StockEntity> displayStock(Long code) {
        String query = "SELECT e FROM StockEntity e WHERE e.productId.id = :productId";
        return em.createQuery(query, StockEntity.class).setParameter("productId", code).getResultList();
    }

    public List<Object[]> displayAllStock() {

        String query
                = "SELECT e.productId.id, SUM(e.Quantity) "
                + "FROM StockEntity e "
                + "GROUP BY e.productId";

        return em.createQuery(query, Object[].class)
                .getResultList();
    }

}
