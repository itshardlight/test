/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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

    public void onRowSelect() {
        StockDto selectedCart = stodao.getStock(selectedProduct);

        SalesDetailEntity newItem = new SalesDetailEntity();
        newItem.setSellingPrice(selectedCart.getSellingPrice());
        newItem.setProductId(prodao.entitybyId(selectedCart.getProductId()));
        newItem.setSoldQuantity(1);
        cart.add(newItem);
        selectedProduct = null;
    }

    public void selectedItems() {

    }

    //getter and setter 
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
