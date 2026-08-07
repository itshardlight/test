package com.mycompany;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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

    public void save() {
            try {
            dao.save(entity);
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
