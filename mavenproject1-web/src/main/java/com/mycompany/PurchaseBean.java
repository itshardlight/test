package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import purchase.PurchaseDao;
import purchase.PurchaseEntity;

@ManagedBean(name = "purchaseBean")
@ViewScoped
public class PurchaseBean {

    @EJB
    private PurchaseDao dao;

    private PurchaseEntity entity = new PurchaseEntity();
    List<PurchaseEntity> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        items.add(new PurchaseEntity()); // first row
    }

    public void addRow() {
        items.add(new PurchaseEntity());
    }

    public void removeRow(PurchaseEntity item) {
        items.remove(item);
    }

    public List<PurchaseEntity> getItems() {
        return items;
    }

    public void save() {
        try {
            for (PurchaseEntity item : items) {
                item.setSupplierId(entity.getSupplierId());
                item.setDate(entity.getDate());
                dao.save(item);
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Success",
                            "Product purchased successfully."
                    ));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", e.getMessage()));
        }
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
