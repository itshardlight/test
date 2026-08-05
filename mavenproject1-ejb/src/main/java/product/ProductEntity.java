/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author unish
 */
@Entity
@Table(name = "Ptable")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "supplier_id")
    private Long supplierId;

    @PositiveOrZero(message = "Cost Price cannot be negative")
    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice;

    @PositiveOrZero(message = "Selling Price cannot be negative")
    @Column(name = "selling_price", nullable = false)
    private BigDecimal sellingPrice;

    @PositiveOrZero(message = "Stock cannot be negative")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "vat_applicable")
    private Boolean vatApplicable;

    @Column(name = "category")
    private String category;

    //getter and setter 
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Boolean getVatApplicable() {
        return vatApplicable;
    }

    public void setVatApplicable(Boolean vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
