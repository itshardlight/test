package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sales.SalesDao;
import sales.SalesDetailEntity;
import sales.SalesEntity;

@ManagedBean(name = "salesBean")
@ViewScoped
public class SalesBean {

    @EJB
    private SalesDao dao;

    SalesEntity entity1 = new SalesEntity();
    SalesDetailEntity entity2 = new SalesDetailEntity();
    List<SalesDetailEntity> item = new ArrayList<>();
    Long ProductId;
    
    public void productChanged() {
        item.add(entity2);
        entity2 = new SalesDetailEntity();
    }

    //getter and setter
    public SalesDao getDao() {
        return dao;
    }

    public void setDao(SalesDao dao) {
        this.dao = dao;
    }

    public SalesEntity getEntity1() {
        return entity1;
    }

    public void setEntity1(SalesEntity entity1) {
        this.entity1 = entity1;
    }

    public SalesDetailEntity getEntity2() {
        return entity2;
    }

    public void setEntity2(SalesDetailEntity entity2) {
        this.entity2 = entity2;
    }

    public List<SalesDetailEntity> getItem() {
        return item;
    }

    public void setItem(List<SalesDetailEntity> item) {
        this.item = item;
    }

}
