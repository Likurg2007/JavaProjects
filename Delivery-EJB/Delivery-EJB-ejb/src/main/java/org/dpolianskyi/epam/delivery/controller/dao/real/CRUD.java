package org.dpolianskyi.epam.delivery.controller.dao.real;

//import controllers.exceptions.JpaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;

public abstract class CRUD<E, K> implements ICRUD<E, K> {

    private final static String PERSISTMSG = "Try to persist: ";
    private final static String REMOVEMSG = "Try to remove: ";
    private final static String FINDMSG = "Try to find from range: ";
    private final static String MERGEMSG = "Try to merge: ";
    private final static String FINDIDMSG = "Try to find by id: ";
    private Class<E> entityClass;
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("Delivery-warPU");

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CRUD(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void persist(E entity) throws Exception {
        EntityManager entityManager = getEntityManager();
        LogBean.getLogger().debug(PERSISTMSG + entity.getClass().getSimpleName() + " " + java.util.Calendar.getInstance().getTime());
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void remove(E entity) throws Exception {
        EntityManager entityManager = getEntityManager();
        LogBean.getLogger().debug(REMOVEMSG + entity.getClass().getSimpleName() + " " + java.util.Calendar.getInstance().getTime());
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.merge(entity));
        entityManager.getTransaction().commit();
    }

    @Override
    public List<E> findEntities(boolean all, int maxResults, int firstResult) throws Exception {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("SELECT A FROM " + entityClass.getSimpleName() + " A");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            List<E> resultList = (List<E>) q.getResultList();
            return resultList;
        } catch (Exception e) {
            LogBean.getLogger().debug(FINDMSG + " " + java.util.Calendar.getInstance().getTime());
            throw e;
        }
    }

    @Override
    public void merge(E entity) throws Exception {
        EntityManager entityManager = getEntityManager();
        LogBean.getLogger().debug(MERGEMSG + entity.getClass().getSimpleName() + " " + java.util.Calendar.getInstance().getTime());
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public E findById(K id) throws Exception {
        EntityManager entityManager = getEntityManager();
        LogBean.getLogger().debug(FINDIDMSG + id + " " + java.util.Calendar.getInstance().getTime());
        entityManager.getTransaction().begin();
        E result = entityManager.find(entityClass, id);
        entityManager.getTransaction().commit();
        return result;
    }
}
