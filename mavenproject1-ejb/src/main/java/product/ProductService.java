package product;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProductService {

    @EJB
    private ProductDao dao;

    // saving in data base
    public void save(ProductEntity test) throws Exception{
     
        dao.save(test);
    }

    // find by product code
    public ProductEntity findbyId(String code) {
        return dao.findById(code);
    }

    // delete by id
    public void deletebyId(String code) {
        ProductEntity entity = findbyId(code);
        dao.deletebyId(entity);
    }

    // show all table 
    public List<ProductEntity> show() {
        return dao.show();
    }

    // increase Stock 
    public void increaseStock(String code, Integer stock) {
        ProductEntity entity = dao.findById(code);
        entity.setStockQuantity(entity.getStockQuantity() + stock);
    }

    // decrease Stock
    public void decreaseStock(String code, Integer stock) {
        ProductEntity entity = dao.findById(code);
        entity.setStockQuantity(entity.getStockQuantity() - stock);
    }
    
    // show low stock product 
    public List<ProductEntity> getlowStock(Integer stock){
        return dao.getlowStock(stock);
    }
    
}
