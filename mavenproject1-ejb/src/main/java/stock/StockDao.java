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

    public StockDto getStock(Long code){
          String query = "SELECT new stock.StockDto("
                + "e.productId.id, e.costPrice, e.sellingPrice, SUM(e.Quantity))  "
                + "FROM StockEntity e "
                + "WHERE e.productId.id = :productId "
                + "GROUP BY e.costPrice, e.sellingPrice";

        return em.createQuery(query, StockDto.class)
                .setParameter("productId", code)
                .getSingleResult();
        
    }
    
    
    public List<StockDto> displayStock(Long code) {
        String query = "SELECT new stock.StockDto("
                + "e.id, e.productId.id, e.costPrice, e.sellingPrice, SUM(e.Quantity))  "
                + "FROM StockEntity e "
                + "WHERE e.productId.id = :productId "
                + "GROUP BY e.costPrice, e.sellingPrice";

        return em.createQuery(query, StockDto.class)
                .setParameter("productId", code)
                .getResultList();
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
