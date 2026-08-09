package com.mycompany;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import purchase.PurchaseDao;
import purchase.PurchaseDetailEntity;
import purchase.PurchaseEntity;

@ManagedBean(name = "purchaseBean")
@ViewScoped
public class PurchaseBean {

    @EJB
    private PurchaseDao dao;

    PurchaseEntity entity1 = new PurchaseEntity();
    PurchaseDetailEntity entity2 = new PurchaseDetailEntity();

    List<PurchaseDetailEntity> items2 = new ArrayList<>();

    @PostConstruct
    public void init() {
        items2.add(new PurchaseDetailEntity()); // first row
    }

    public void addRow() {
        items2.add(new PurchaseDetailEntity());
    }

    public void removeRow(PurchaseDetailEntity item) {
        items2.remove(item);
    }

    public List<PurchaseDetailEntity> getItems() {
        return items2;
    }

    //calculate Total price
    public void calculateTotal() {

        BigDecimal total = BigDecimal.ZERO;

        for (PurchaseDetailEntity item : items2) {

            if (item.getCostPrice() != null && item.getStockQuantity() != null) {

                BigDecimal rowTotal = item.getCostPrice()
                        .multiply(BigDecimal.valueOf(item.getStockQuantity()));

                total = total.add(rowTotal);
            }
        }

        entity1.setTotalPrice(total);
    }

    //saving 
    public void save() {
        try {

            dao.save(entity1, items2);

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

    //getter and setter
    public PurchaseDao getDao() {
        return dao;
    }

    public void setDao(PurchaseDao dao) {
        this.dao = dao;
    }

    public PurchaseEntity getEntity1() {
        return entity1;
    }

    public void setEntity1(PurchaseEntity entity1) {
        this.entity1 = entity1;
    }

    public PurchaseDetailEntity getEntity2() {
        return entity2;
    }

    public void setEntity2(PurchaseDetailEntity entity2) {
        this.entity2 = entity2;
    }

    public List<PurchaseDetailEntity> getItems2() {
        return items2;
    }

    public void setItems2(List<PurchaseDetailEntity> items2) {
        this.items2 = items2;
    }

}
