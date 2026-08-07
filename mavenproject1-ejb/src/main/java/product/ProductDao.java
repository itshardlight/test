package product;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProductDao {

    @PersistenceContext(unitName = "um_test")
    private EntityManager em;

    // saving in data base
    public void save(ProductEntity entity){
        em.persist(entity);
    }

    // get product by id
    public ProductEntity findById(String code) {
        String query = "SELECT a FROM ProductEntity a WHERE a.productCode = :code";
        return em.createQuery(query, ProductEntity.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    // delete product by id
    public void deletebyId(ProductEntity entity){
        em.remove(entity);
    }
    
    // show all product
    public List<ProductEntity> show(){
        String query = "SELECT e FROM ProductEntity e";
                return em.createQuery(query,ProductEntity.class).getResultList();
    }
  
    
}
