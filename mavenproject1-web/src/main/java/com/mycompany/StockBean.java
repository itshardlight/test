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
import stock.StockDao;
import stock.StockEntity;

@ManagedBean(name="stockBean")
@ViewScoped
public class StockBean {
    
    @EJB
    private StockDao dao;
    
    private StockEntity entity;  
    
    public List<StockEntity> show(Long code){
        return dao.displayStock(code);
    }
    
    public List<Object[]> showAll(){
        return dao.displayAllStock();
    }
    
    
}
