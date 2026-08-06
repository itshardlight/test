
package com.mycompany;

import category.CategoryDao;
import category.CategoryEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="categoryBean")
@ViewScoped
public class CategoryBean {
    
    @EJB
    private CategoryDao dao;
    
    public CategoryEntity entity = new CategoryEntity();
    
    public String save(){
        dao.save(entity);
        return "categoryForm.xhtml?faces-redirect=true";
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