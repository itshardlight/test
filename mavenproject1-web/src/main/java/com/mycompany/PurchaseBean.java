package com.mycompany;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import product.ProductDao;
import purchase.PurchaseDao;
import purchase.PurchaseDetailEntity;
import purchase.PurchaseEntity;
import stock.StockDao;
import stock.StockEntity;
import supplier.SupplierDao;

@ManagedBean(name = "purchaseBean")
@ViewScoped
public class PurchaseBean {

    @EJB
    private PurchaseDao dao;

    @EJB
    private StockDao stockDao;

    @EJB
    private SupplierDao supDao;

    @EJB
    private ProductDao proDao;

    PurchaseEntity entity1 = new PurchaseEntity();
    PurchaseDetailEntity entity2 = new PurchaseDetailEntity();
    private PurchaseEntity SelectedPurchase;
    List<PurchaseDetailEntity> items2 = new ArrayList<>();

    @PostConstruct
    public void init() {
        items2.add(new PurchaseDetailEntity());
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

    public void saveStock(List<PurchaseDetailEntity> entity) {
        for (PurchaseDetailEntity item : entity) {
            StockEntity stockEntity = new StockEntity();
            stockEntity.setProductId(proDao.entitybyId(item.getProductId()));
            stockEntity.setQuantity(item.getStockQuantity());
            stockEntity.setCostPrice(item.getCostPrice());
            stockEntity.setSellingPrice(item.getSellingPrice());
            stockDao.save(stockEntity);

        }

    }

    //saving 
    public void save() {
        try {
            saveStock(items2);
            dao.save(entity1, items2);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Success",
                            "Product purchased successfully."
                    ));
            entity1 = new PurchaseEntity();
            items2 = new ArrayList<>();
            addRow();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", e.getMessage()));
        }
    }

    //redirecting to purchased product 
    public void onRowSelect(SelectEvent<PurchaseEntity> event) {
        PurchaseEntity selected = event.getObject();

        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("purchasedProductDisplay.xhtml?purchaseId=" + selected.getId());
        } catch (IOException e) {
        }
    }

    //get purhcaseid url
    public Long getUrlId() {
        Long urlId = Long.valueOf(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequestParameterMap()
                        .get("purchaseId")
        );
        return urlId;
    }

    //get purchase details
    public List<PurchaseDetailEntity> getPurchaseDetails() {
        Long purchaseId = getUrlId();
        return dao.getPurchaseDetails(purchaseId);
    }

    //get supplier name
    public String getSupplierName() {
        Long purhcaseId = getUrlId();
        PurchaseEntity purhcaseBill = dao.getPurchaseBill(purhcaseId);
        Long Supplierid = purhcaseBill.getSupplierId();
        String supplierName = supDao.getSupplierName(Supplierid);
        return supplierName;
    }

    //get total amt
    public BigDecimal getTotalAmt() {
        Long purhcaseId = getUrlId();
        PurchaseEntity purhcaseBill = dao.getPurchaseBill(purhcaseId);
        return purhcaseBill.getTotalPrice();
    }

    //getter and setter
    public PurchaseEntity getSelectedPurchase() {
        return SelectedPurchase;
    }

    public void setSelectedPurchase(PurchaseEntity SelectedPurchase) {
        this.SelectedPurchase = SelectedPurchase;
    }

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
