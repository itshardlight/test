package com.mycompany;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import stock.StockDao;

@ManagedBean(name = "stockBean")
@ViewScoped
public class StockBean {



    @EJB
    private StockDao dao;

    private Object[] selectedStock;

    public List<Object[]> displayStock() {
        return dao.displayStock();
    }

    public List<Object[]> displayDetailStock(Long productId) {
        if (productId == null) {
            return Collections.emptyList();
        }

        return dao.displayDetailStock(productId);
    }

    public void onRowSelect(SelectEvent event) {

        Object[] stock = (Object[]) event.getObject();

        if (stock == null || stock.length == 0 || stock[0] == null) {
            return;
        }

        selectedStock = stock;

        Long productId = ((Number) stock[0]).longValue();

        try {
            FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .redirect(
                            FacesContext.getCurrentInstance()
                                    .getExternalContext()
                                    .getRequestContextPath()
                            + "/Stock/stockDetailDisplay.xhtml?productId="
                            + productId
                    );

        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_ERROR,
                            "Error",
                            e.getMessage()
                    )
            );
        }
    }

      //get productId url
    public Long getUrlId() {
        Long urlId = Long.valueOf(
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getRequestParameterMap()
                        .get("productId")
        );
        return urlId;
    }

    
    

    public Object[] getSelectedStock() {
        return selectedStock;
    }

    public void setSelectedStock(Object[] selectedStock) {
        this.selectedStock = selectedStock;
    }

    public StockDao getDao() {
        return dao;
    }

    public void setDao(StockDao dao) {
        this.dao = dao;
    }
}
