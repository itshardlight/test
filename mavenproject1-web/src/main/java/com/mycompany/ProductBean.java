package com.mycompany;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import product.ProductDao;
import product.ProductEntity;

@ManagedBean(name = "productBean")
@ViewScoped
public class ProductBean {

    ProductEntity entity = new ProductEntity();

    @EJB
    private ProductDao dao;

    public void save() {
        try {
            dao.save(entity);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Success",
                            "Product saved successfully."
                    ));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", e.getMessage()));
        }
    }

    public List<ProductEntity> show() {
        return dao.show();
    }

    public void delProduct(ProductEntity entity) {

        dao.deletebyId(entity);
    }

    
    //getter and setter 

    public ProductEntity getEntity() {
        return entity;
    }

    public void setEntity(ProductEntity entity) {
        this.entity = entity;
    }

    public ProductDao getDao() {
        return dao;
    }

    public void setDao(ProductDao dao) {
        this.dao = dao;
    }
     
}
