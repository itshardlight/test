package category;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CategoryDao {

    @PersistenceContext
    private EntityManager em;

    public CategoryEntity entity;

    public void save(CategoryEntity entity) {
        em.persist(entity);

    }

    public String getCategoryName(Long categoryId) {
        String query = "SELECT e.cname FROM CategoryEntity e WHERE e.id = :id";

        return em.createQuery(query, String.class)
                .setParameter("id", categoryId)
                .getSingleResult();
    }

    public List<CategoryEntity> show() {
        String query = "SELECT e FROM CategoryEntity e";
        return em.createQuery(query, CategoryEntity.class).getResultList();
    }

    public void remove(CategoryEntity entity) {
        em.remove(em.merge(entity));
    }

}
