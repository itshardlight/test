/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import stock.StockDao;
import stock.StockDto;
import stock.StockEntity;

@ManagedBean(name = "stockBean")
@ViewScoped
public class StockBean {

    @EJB
    private StockDao dao;

    private Object[] SelectedProduct;

    public List<StockDto> show(Long code) {
        return dao.displayStock(code);
    }

    public List<Object[]> showAll() {
        return dao.displayAllStock();
    }

    public String onRowSelect() {
        String role = SelectedProduct[0].toString();
        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect("stockDetailDisplay.xhtml?productId=" + role);
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", e.getMessage()));
        }
        return null;
    }

    public Long getUrlId() {
        Long urlId = Long.valueOf(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequestParameterMap()
                        .get("productId")
        );
        return urlId;
    }

//getter and setter
    public StockDao getDao() {
        return dao;
    }

    public void setDao(StockDao dao) {
        this.dao = dao;
    }

    public Object[] getSelectedProduct() {
        return SelectedProduct;
    }

    public void setSelectedProduct(Object[] SelectedProduct) {
        this.SelectedProduct = SelectedProduct;
    }

}
