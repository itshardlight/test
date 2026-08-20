package stock;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockDto implements Serializable {

    private Long id;
    private Long productId;
    private BigDecimal sellingPrice;
    private BigDecimal costPrice;
    private Integer quantity;

    // No-args constructor — needed for JSF/PrimeFaces to instantiate the bean, and generally good practice
    public StockDto() {
    }

    // Constructor for JPQL "SELECT new stock.StockDto(...)" — matches the aggregated query
    public StockDto(Long id,Long productId, BigDecimal costPrice,
            BigDecimal sellingPrice, Long Quantity) {
        this.id = id;
        this.productId = productId;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantity = Quantity != null ? Quantity.intValue() : 0;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
