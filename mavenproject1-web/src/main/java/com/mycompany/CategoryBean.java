
package com.mycompany;

import category.CategoryDao;
import category.CategoryEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="categoryBean")
@ViewScoped
public class CategoryBean {
    
    @EJB
    private CategoryDao dao;
    
    public CategoryEntity entity = new CategoryEntity();
    
    public void save(){
         try {
            dao.save(entity);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Success",
                            "Category saved successfully."
                    ));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", e.getMessage()));
        }
    }
    
    public List<CategoryEntity> show(){
        return dao.show();
    }
    
    public void remove(CategoryEntity entity){
        dao.remove(entity);
    }
        
    
    //getter and setter

    public CategoryDao getDao() {
        return dao;
    }

    public void setDao(CategoryDao dao) {
        this.dao = dao;
    }

    public CategoryEntity getEntity() {
        return entity;
    }

    public void setEntity(CategoryEntity entity) {
        this.entity = entity;
    }

 
    
    
}