
package sales;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SalesDao {
    
    @PersistenceContext
    private EntityManager em;
    
    private Long selectedProduct;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Long getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Long selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
   
    
}
