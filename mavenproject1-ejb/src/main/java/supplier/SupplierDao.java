/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supplier;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author unish
 */
@Stateless
public class SupplierDao {

    @PersistenceContext
    private EntityManager em;

    //saving the Supplier name
    public void add(SupplierEntity entity) {
        em.persist(entity);
    }

    //removing the Supplier name
    public void remove(SupplierEntity entity) {
        em.remove(em.merge(entity));
//        em.remove(entity);
    }

    //showing the Supplier list
    public List<SupplierEntity> show() {
        String query = "SELECT e FROM SupplierEntity e";
        return em.createQuery(query, SupplierEntity.class).getResultList();
    }

    //updating the Supplier list
    public void update(SupplierEntity newEntity) {
        em.getTransaction().begin();
        em.merge(newEntity);
        em.getTransaction().commit();
    }

}
