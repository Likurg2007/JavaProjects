package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.IRequestDAO;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.model.StatusEnum;

/**
 *
 * @author Likurg
 */
@Stateless
public class RequestDAO extends CRUD<Request, Long> implements IRequestDAO {

    private final static String querySequenceFindAll = "select R from Request R";
    private final static String querySequenceFindByCode = "SELECT R FROM Request R WHERE R.code = :requestCode";
    private final static String querySequenceFindByStatus = "SELECT R FROM Request R WHERE R.status = :requestStatus";
    private final static String querySequenceFindByCredentials = "SELECT R FROM Request R WHERE R.credent = :requestCredentials";
    private final static String querySequenceSelectRequestCount = "SELECT COUNT(R) FROM Request R";
    private final static String nativeQuerySequenceFindRequestWithAvailableProducts = "SELECT R.REQUEST_ID FROM REQUEST AS R "
            + "INNER JOIN CURPRODUCT_REQUEST ON R.REQUEST_ID=CURPRODUCT_REQUEST.REQUEST "
            + "INNER JOIN CURPRODUCT AS CP ON CP.CURPRODUCT_ID = CURPRODUCT_REQUEST.CURPRODUCT "
            + "EXCEPT "
            + "SELECT R.REQUEST_ID FROM REQUEST AS R "
            + "INNER JOIN CURPRODUCT_REQUEST ON R.REQUEST_ID=CURPRODUCT_REQUEST.REQUEST "
            + "INNER JOIN CURPRODUCT AS CP ON CP.CURPRODUCT_ID = CURPRODUCT_REQUEST.CURPRODUCT "
            + "WHERE CP.CURPRODUCT_STATUS = 'STATUS_UNAVAILABLE'";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYCODE = "Try to find by code from: ";
    private final static String FINDBYSTATUS = "Try to find by status from: ";
    private final static String FINDBYCREDENTIALS = "Try to find by credentials from: ";
    private final static String FINDMSG = "Try to find from range: ";
    private final static String SELECTREQUESTCOUNT = "Try to select request count from: ";
    private final static String SELECTDELIVERYCOUNT = "Try to select delivery count from: ";

    public RequestDAO() {
        super(Request.class);
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

    @Override
    public Long selectRequestCount() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceSelectRequestCount);
        try {
            LogBean.getLogger().debug(SELECTREQUESTCOUNT + entityManager.getClass());
            return (Long) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Long selectDeliveryCount() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(nativeQuerySequenceFindRequestWithAvailableProducts);
        try {
            LogBean.getLogger().debug(SELECTDELIVERYCOUNT + entityManager.getClass());
            return (Long) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Request> findEntitiesWithAvailableStatus(boolean all, int maxResults, int firstResult) throws Exception {
        EntityManager entityManager = getEntityManager();
        RequestDAO rdao = new RequestDAO();
        Long resultIdLong;
        Request cr;
        Query nquery = entityManager.createNativeQuery(nativeQuerySequenceFindRequestWithAvailableProducts);
        try {
            if (!all) {
                nquery.setMaxResults(maxResults);
                nquery.setFirstResult(firstResult);
            }
            List resultListId = nquery.getResultList();
            List<Request> resultList = new ArrayList<Request>();
            for (Object elem : resultListId) {
                resultIdLong = Long.parseLong(elem.toString());
                cr = rdao.findById(resultIdLong);
                resultList.add(cr);
            }
            for (Object elem : resultList) {
            }
            return resultList;
        } catch (NullPointerException e) {
            LogBean.getLogger().debug(FINDMSG + " " + java.util.Calendar.getInstance().getTime());
            return null;
        }
    }
}
