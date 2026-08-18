/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import purchase.PurchaseDetailEntity;
import stock.StockDao;

@ManagedBean(name="stockBean")
public class StockBean {
    
    @EJB
    private StockDao dao;

     public List<Object[]> displayStock() {
         return dao.displayStock();
    }
     
     //getter and setter 

    public StockDao getDao() {
        return dao;
    }

    public void setDao(StockDao dao) {
        this.dao = dao;
    }
     
     
}
