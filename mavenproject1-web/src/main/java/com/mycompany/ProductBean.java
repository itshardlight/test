package com.mycompany;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import product.ProductEntity;
import product.ProductService;

@ManagedBean(name = "productBean")
@ViewScoped
public class ProductBean {

    private ProductEntity entity = new ProductEntity();

    @EJB
    private ProductService service;

    public String save() {
        try {
            service.save(entity);
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
        return "productForm.xhtml?faces-redirect=true";
    }

    public List<ProductEntity> show() {
        return service.show();
    }

    public void delProduct(ProductEntity product) {

        service.deletebyId(product.getProductCode());
    }

    //getter and setter
    public ProductEntity getEntity() {
        return entity;
    }

    public void setEntity(ProductEntity entity) {
        this.entity = entity;
    }

}
