package com.mycompany;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import purchase.PurchaseDao;
import purchase.PurchaseEntity;

@ManagedBean(name = "purchaseBean")
@ViewScoped
public class PurchaseBean {

    @EJB
    private PurchaseDao dao;

    private PurchaseEntity entity = new PurchaseEntity();

    public void save() {
        dao.save(entity);
    }

    public void del(PurchaseEntity entity) {
        dao.remove(entity);
    }

    public List<PurchaseEntity> show() {
        return dao.show();
    }

    //getter and setter
    public PurchaseDao getDao() {
        return dao;
    }

    public void setDao(PurchaseDao dao) {
        this.dao = dao;
    }

    public PurchaseEntity getEntity() {
        return entity;
    }

    public void setEntity(PurchaseEntity entity) {
        this.entity = entity;
    }

}
