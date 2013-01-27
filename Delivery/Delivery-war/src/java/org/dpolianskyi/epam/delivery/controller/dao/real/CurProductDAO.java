package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.ICurProductDAO;
import org.dpolianskyi.epam.delivery.model.CurProduct;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.model.StatusEnum;

/**
 *
 * @author Likurg
 */
public class CurProductDAO extends CRUD<CurProduct, Long> implements ICurProductDAO {

    private final static String querySequenceFindAll = "select CP from CurProduct CP";
    private final static String querySequenceFindByStatus = "SELECT CP FROM CurProduct CP WHERE CP.status = :curProductStatus";
    private final static String querySequenceFindByName = "SELECT CP FROM CurProduct CP WHERE CP.name = :curProductName";
    private final static String querySequenceFindByCategoryName = "SELECT CP FROM CurProduct CP WHERE CP.category.name = :categoryName";
    private final static String querySequenceFindByModelName = "SELECT CP FROM CurProduct CP WHERE CP.model.name = :modelName";
    private final static String querySequenceFindByProducerName = "SELECT CP FROM CurProduct CP WHERE CP.producer.name = :producerName";
    private final static String querySequenceFindByYear = "SELECT CP FROM CurProduct CP WHERE CP.year = :curProductYear";
    private final static String querySequenceFindEntitiesByCurrentRequest = "SELECT CP FROM CurProduct CP INNER JOIN Curpro_Request CR ON CP.id = CR.curproduct INNER JOIN Request R ON R.id = CR.request WHERE CR.request = :curRequest";
    private final static String nativeQuerySequenceFindEntitiesByCurrentRequest = "SELECT * FROM CURPRODUCT INNER JOIN CURPRODUCT_REQUEST ON CURPRODUCT.CURPRODUCT_ID=CURPRODUCT_REQUEST.CURPRODUCT INNER JOIN REQUEST ON REQUEST.REQUEST_ID = CURPRODUCT_REQUEST.REQUEST WHERE CURPRODUCT_REQUEST.REQUEST=17";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYSTATUS = "Try to find by status from: ";
    private final static String FINDBYNAME = "Try to find by name from: ";
    private final static String FINDBYCATEGORY = "Try to find by category name from: ";
    private final static String FINDBYMODEL = "Try to find by model name from: ";
    private final static String FINDBYPRODUCER = "Try to find by producer name from: ";
    private final static String FINDBYYEAR = "Try to find by year from: ";
    private final static String FINDMSG = "Try to find from range: ";

    public CurProductDAO() {
        super(CurProduct.class);
        emf = Persistence.createEntityManagerFactory("Delivery-warPU");

    }

    @Override
    public List<CurProduct> findAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindAll);
        try {
            LogBean.getLogger().debug(FINDALL + entityManager.getClass());
            return (List<CurProduct>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<CurProduct> findByStatus(final StatusEnum curProductStatus) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByStatus);
        query.setParameter("curProductStatus", curProductStatus);
        try {
            LogBean.getLogger().debug(FINDBYSTATUS + entityManager.getClass());
            return (List<CurProduct>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<CurProduct> findByName(final String curProductName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByName);
        query.setParameter("curProductName", curProductName);
        try {
            LogBean.getLogger().debug(FINDBYNAME + entityManager.getClass());
            return (List<CurProduct>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<CurProduct> findByYear(final Integer curProductYear) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByYear);
        query.setParameter("curProductYear", curProductYear);
        try {
            LogBean.getLogger().debug(FINDBYYEAR + entityManager.getClass());
            return (List<CurProduct>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<CurProduct> findByModelName(final String modelName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByModelName);
        query.setParameter("modelName", modelName);
        try {
            LogBean.getLogger().debug(FINDBYMODEL + entityManager.getClass());
            return (List<CurProduct>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<CurProduct> findByCategoryName(final String categoryName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByCategoryName);
        query.setParameter("categoryName", categoryName);
        try {
            LogBean.getLogger().debug(FINDBYCATEGORY + entityManager.getClass());
            return (List<CurProduct>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<CurProduct> findByProducerName(final String producerName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByProducerName);
        query.setParameter("producerName", producerName);
        try {
            LogBean.getLogger().debug(FINDBYPRODUCER + entityManager.getClass());
            return (List<CurProduct>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<CurProduct> findEntities(boolean all, int maxResults, int firstResult, Request request) throws Exception {
        EntityManager entityManager = getEntityManager();
        Query nquery = entityManager.createNativeQuery(nativeQuerySequenceFindEntitiesByCurrentRequest);
        try {
            System.out.println("TRY in FE");
            if (!all) {
                System.out.println("IF in FE");
                nquery.setMaxResults(maxResults);
                nquery.setFirstResult(firstResult);
            }
            List<CurProduct> resultList = (List<CurProduct>) nquery.getResultList();
            System.out.println("BEFORE FOR" + resultList.isEmpty() + " " + resultList.size());
            return resultList;
        } catch (NullPointerException e) {
            System.out.println("CATCH in FE");
            LogBean.getLogger().debug(FINDMSG + " " + java.util.Calendar.getInstance().getTime());
            return null;
        }
    }
}
