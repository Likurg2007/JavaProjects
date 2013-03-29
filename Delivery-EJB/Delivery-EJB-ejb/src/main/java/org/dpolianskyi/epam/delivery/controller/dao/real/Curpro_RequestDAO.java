package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.ICurpro_RequestDAO;
import org.dpolianskyi.epam.delivery.model.Curpro_Request;

/**
 *
 * @author Likurg
 */
@Stateless
public class Curpro_RequestDAO extends CRUD<Curpro_Request, Long> implements ICurpro_RequestDAO {

    private final static String querySequenceFindAll = "select CR from Curpro_Request CR";
    private final static String querySequenceFindByQuantity = "SELECT CR FROM Curpro_Request CR WHERE CR.cpquant = :curpro_RequestQuantity";
    private final static String querySequenceFindByProductId = "SELECT CR FROM Curpro_Request CR WHERE CR.curproduct.id=:curpro_RequestProductID";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYQUANT = "Try to find by quantity from: ";
    private final static String FINDBYPRODUCTID = "Try to find by product id from: ";

    public Curpro_RequestDAO() {
        super(Curpro_Request.class);
    }

    @Override
    public List<Curpro_Request> findAll() {
        Query query = getEntityManager().createQuery(querySequenceFindAll);
        try {
            LogBean.getLogger().debug(FINDALL + getEntityManager().getClass());
            return (List<Curpro_Request>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Curpro_Request> findByQuantity(final Integer curpro_RequestQuantity) {
        Query query = getEntityManager().createQuery(querySequenceFindByQuantity);
        query.setParameter("curpro_RequestQuantity", curpro_RequestQuantity);
        try {
            LogBean.getLogger().debug(FINDBYQUANT + getEntityManager().getClass());
            return (List<Curpro_Request>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Curpro_Request findByProductId(final Long curpro_RequestProductID) {
        Query query = getEntityManager().createQuery(querySequenceFindByProductId);
        query.setParameter("curpro_RequestProductID", curpro_RequestProductID);
        try {
            LogBean.getLogger().debug(FINDBYPRODUCTID + getEntityManager().getClass());
            return (Curpro_Request) query.getSingleResult();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
