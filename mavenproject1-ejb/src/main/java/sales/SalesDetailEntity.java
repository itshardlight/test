/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sales;

import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import product.ProductEntity;

/**
 *
 * @author unish
 */
@Entity
@Table(name="salesDetail_table")
public class SalesDetailEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sales_id")
    private SalesEntity sales;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productId;

    @PositiveOrZero(message = "Selling Price cannot be negative")
    @Column(name = "selling_price", nullable = false)
    private BigDecimal sellingPrice;
    
    @PositiveOrZero(message = "Cost Price cannot be negative")
    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice;

    @PositiveOrZero(message = "Stock cannot be negative")
    @Column(name = "sold_quantity", nullable = false)
    private Integer soldQuantity;

    
    
    
    //getter and setter 

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesEntity getSales() {
        return sales;
    }

    public void setSales(SalesEntity sales) {
        this.sales = sales;
    }

    public ProductEntity getProductId() {
        return productId;
    }

    public void setProductId(ProductEntity productId) {
        this.productId = productId;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Integer soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

   
    
}
