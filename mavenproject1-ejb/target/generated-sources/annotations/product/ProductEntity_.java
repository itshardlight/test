package product;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductEntity.class)
public abstract class ProductEntity_ {

	public static volatile SingularAttribute<ProductEntity, BigDecimal> sellingPrice;
	public static volatile SingularAttribute<ProductEntity, String> productCode;
	public static volatile SingularAttribute<ProductEntity, Long> supplierId;
	public static volatile SingularAttribute<ProductEntity, Boolean> vatApplicable;
	public static volatile SingularAttribute<ProductEntity, BigDecimal> costPrice;
	public static volatile SingularAttribute<ProductEntity, Integer> stockQuantity;
	public static volatile SingularAttribute<ProductEntity, Long> id;
	public static volatile SingularAttribute<ProductEntity, String> category;
	public static volatile SingularAttribute<ProductEntity, String> productName;

}

