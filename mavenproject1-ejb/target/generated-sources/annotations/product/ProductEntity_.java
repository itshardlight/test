package product;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ProductEntity.class)
public abstract class ProductEntity_ {

	public static volatile SingularAttribute<ProductEntity, Long> id;
	public static volatile SingularAttribute<ProductEntity, String> productName;
	public static volatile SingularAttribute<ProductEntity, Long> categoryId;

}

