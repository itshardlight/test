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
import sales.SalesDao;
import sales.SalesDetailEntity;
import sales.SalesEntity;

@ManagedBean(name = "salesBean")
@ViewScoped
public class SalesBean {

    @EJB
    private SalesDao dao;

    private Long selectedProduct;
    
    private SalesEntity entity1 = new SalesEntity();
    private SalesDetailEntity entity2 = new SalesDetailEntity();

    public void onRowSelect(){
        selectedProduct = null;
    }
    
    public void selectedItems(){
    }
    
    
    //getter and setter 
    
    public Long getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Long selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

}
