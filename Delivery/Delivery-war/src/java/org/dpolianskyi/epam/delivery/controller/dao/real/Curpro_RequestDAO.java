package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.ICurpro_RequestDAO;
import org.dpolianskyi.epam.delivery.model.Curpro_Request;

/**
 *
 * @author Likurg
 */
public class Curpro_RequestDAO extends CRUD<Curpro_Request, Long> implements ICurpro_RequestDAO {

    private final static String querySequenceFindAll = "select CR from Curpro_Request CR";
    private final static String querySequenceFindByQuantity = "SELECT CR FROM Curpro_Request CR WHERE CR.cpquant = :curpro_RequestQuantity";
    private final static String querySequenceFindByProductId = "SELECT CR FROM Curpro_Request CR WHERE CR.curproduct.id=:curpro_RequestProductID";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYQUANT = "Try to find by quantity from: ";
    private final static String FINDBYPRODUCTID = "Try to find by product id from: ";

    public Curpro_RequestDAO() {
        super(Curpro_Request.class);
        emf = Persistence.createEntityManagerFactory("Delivery-warPU");

    }

    @Override
    public List<Curpro_Request> findAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindAll);
        try {
            LogBean.getLogger().debug(FINDALL + entityManager.getClass());
            return (List<Curpro_Request>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Curpro_Request> findByQuantity(final Integer curpro_RequestQuantity) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByQuantity);
        query.setParameter("curpro_RequestQuantity", curpro_RequestQuantity);
        try {
            LogBean.getLogger().debug(FINDBYQUANT + entityManager.getClass());
            return (List<Curpro_Request>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Curpro_Request findByProductId(final Long curpro_RequestProductID) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByProductId);
        query.setParameter("curpro_RequestProductID", curpro_RequestProductID);
        try {
            LogBean.getLogger().debug(FINDBYPRODUCTID + entityManager.getClass());
            return (Curpro_Request) query.getSingleResult();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
