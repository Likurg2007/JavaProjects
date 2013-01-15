package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.IRequestDAO;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.model.StatusEnum;

/**
 *
 * @author Likurg
 */
public class RequestDAO extends CRUD<Request, Long> implements IRequestDAO {

    private final static String querySequenceFindAll = "select R from Request R";
    private final static String querySequenceFindByCode = "SELECT R FROM Request R WHERE R.code = :requestCode";
    private final static String querySequenceFindByStatus = "SELECT R FROM Request R WHERE R.status = :requestStatus";
    private final static String querySequenceFindByCredentials = "SELECT R FROM Request R WHERE R.credent = :requestCredentials";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYCODE = "Try to find by code from: ";
    private final static String FINDBYSTATUS = "Try to find by status from: ";
    private final static String FINDBYCREDENTIALS = "Try to find by credentials from: ";

    public RequestDAO() {
        super(Request.class);
        emf = Persistence.createEntityManagerFactory("Delivery-warPU");

    }

    @Override
    public List<Request> findAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindAll);
        try {
            LogBean.getLogger().debug(FINDALL + entityManager.getClass());
            return (List<Request>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Request> findByCode(final String requestCode) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByCode);
        query.setParameter("requestCode", requestCode);
        try {
            LogBean.getLogger().debug(FINDBYCODE + entityManager.getClass());
            return (List<Request>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Request> findByStatus(final StatusEnum requestStatus) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByStatus);
        query.setParameter("requestStatus", requestStatus);
        try {
            LogBean.getLogger().debug(FINDBYSTATUS + entityManager.getClass());
            return (List<Request>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Request> findByCredentials(final String requestCredentials) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByCredentials);
        query.setParameter("requestCredentials", requestCredentials);
        try {
            LogBean.getLogger().debug(FINDBYCREDENTIALS + entityManager.getClass());
            return (List<Request>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
