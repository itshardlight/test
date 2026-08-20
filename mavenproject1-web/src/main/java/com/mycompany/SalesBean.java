/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import product.ProductDao;
import sales.SalesDao;
import sales.SalesDetailEntity;
import sales.SalesEntity;
import stock.StockDao;
import stock.StockDto;

@ManagedBean(name = "salesBean")
@ViewScoped
public class SalesBean {

    @EJB
    private SalesDao dao;

    @EJB
    private StockDao stodao;

    @EJB
    private ProductDao prodao;

    private Long selectedProduct;

    private SalesEntity entity1 = new SalesEntity();
    private SalesDetailEntity entity2 = new SalesDetailEntity();
    private List<SalesDetailEntity> cart = new ArrayList<>();

    public void removeFromCart(SalesDetailEntity item) {
        if (item != null) {
            cart.remove(item);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_FATAL,
                            "Removed",
                            item.getProductId().getProductName() + " removed from cart."
                    ));
        }
    }
  

    public void onRowSelect(SelectEvent<StockDto> event) {

        // Get EXACTLY the row that was clicked
        StockDto selectedStock = event.getObject();

        if (selectedStock == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_WARN,
                            "No Selection",
                            "No stock selected!"
                    ));
            return;
        }

        // Check available stock
        if (selectedStock.getQuantity() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_WARN,
                            "Out of Stock",
                            "Product is out of stock!"
                    ));
            return;
        }

        SalesDetailEntity existingItem = null;

        // Check if SAME product + SAME selling price + SAME cost price
        // already exists in cart
        for (SalesDetailEntity item : cart) {

            if (item.getProductId() != null
                    && item.getProductId().getId().equals(selectedStock.getProductId())
                    && item.getSellingPrice() != null
                    && selectedStock.getSellingPrice() != null
                    && item.getSellingPrice().compareTo(selectedStock.getSellingPrice()) == 0
                    && item.getCostPrice() != null
                    && selectedStock.getCostPrice() != null
                    && item.getCostPrice().compareTo(selectedStock.getCostPrice()) == 0) {

                existingItem = item;
                break;
            }
        }

        if (existingItem != null) {

            // Don't allow quantity to exceed stock
            if (existingItem.getSoldQuantity() < selectedStock.getQuantity()) {

                existingItem.setSoldQuantity(
                        existingItem.getSoldQuantity() + 1
                );

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_INFO,
                                "Cart Updated",
                                "Quantity increased to "
                                + existingItem.getSoldQuantity()
                        ));

            } else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(
                                FacesMessage.SEVERITY_WARN,
                                "Stock Limit",
                                "Cannot add more. Stock quantity is only "
                                + selectedStock.getQuantity()
                        ));
            }

        } else {

            // Create a NEW cart item using the CLICKED ROW
            SalesDetailEntity newItem = new SalesDetailEntity();

            newItem.setProductId(
                    prodao.entitybyId(selectedStock.getProductId())
            );

            // Take prices from selectedStock
            newItem.setSellingPrice(
                    selectedStock.getSellingPrice()
            );

            newItem.setCostPrice(
                    selectedStock.getCostPrice()
            );

            newItem.setSoldQuantity(1);

            cart.add(newItem);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Added to Cart",
                            newItem.getProductId().getProductName() + " added successfully."
                    ));
        }

        // Clear selection AFTER processing the clicked row
        selectedProduct = null;
    }

    public void selectedItems() {

    }

    //getter and setter 
    
    
    
    public StockDao getStodao() {
        return stodao;
    }

    public void setStodao(StockDao stodao) {
        this.stodao = stodao;
    }

    public ProductDao getProdao() {
        return prodao;
    }

    public void setProdao(ProductDao prodao) {
        this.prodao = prodao;
    }

    public Long getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Long selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

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

    public List<SalesDetailEntity> getCart() {
        return cart;
    }

    public void setCart(List<SalesDetailEntity> cart) {
        this.cart = cart;
    }

}
