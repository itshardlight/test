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

    public String findById(Long code) {
    String query = "SELECT a.productName FROM ProductEntity a WHERE a.id = :id";

    return em.createQuery(query, String.class)
            .setParameter("id", code)
            .getSingleResult();
}

    // delete product by id
    public void deletebyId(ProductEntity entity){
        em.remove(em.merge(entity));
    }
    
    // show all product
    public List<ProductEntity> show(){
        String query = "SELECT e FROM ProductEntity e";
                return em.createQuery(query,ProductEntity.class).getResultList();
    }
  
    
}
