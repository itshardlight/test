/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import supplier.SupplierDao;
import supplier.SupplierEntity;

@ManagedBean(name="supplierBean")
@ViewScoped
public class SupplierBean {
    
    @EJB
    private SupplierDao dao;
    
    SupplierEntity entity =new SupplierEntity();
    
    public void add(){
     dao.add(entity);
    }
    
    public void remove(SupplierEntity rEntity){
        dao.remove(rEntity);
    }
    
    public void update(SupplierEntity entity){
        dao.update(entity);
    }
    
    public List<SupplierEntity> show(){
        return dao.show();
    }
    
    
    //getter and setter

    public SupplierDao getDao() {
        return dao;
    }

    public void setDao(SupplierDao dao) {
        this.dao = dao;
    }

    public SupplierEntity getEntity() {
        return entity;
    }

    public void setEntity(SupplierEntity entity) {
        this.entity = entity;
    }
    
    
    
}
