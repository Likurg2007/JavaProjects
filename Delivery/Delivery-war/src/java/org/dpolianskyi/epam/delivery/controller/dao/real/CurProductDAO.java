package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    //   private final static String querySequenceFindEntitiesByCurrentRequest = "SELECT CP.curproduct FROM Request R IN (R.curproreq) CP";
    private final static String nativeQuerySequenceFindEntitiesByCurrentRequest = "SELECT CP.CURPRODUCT_ID FROM CURPRODUCT AS CP "
            + "INNER JOIN CURPRODUCT_REQUEST ON CP.CURPRODUCT_ID=CURPRODUCT_REQUEST.CURPRODUCT "
            + "INNER JOIN REQUEST ON REQUEST.REQUEST_ID = CURPRODUCT_REQUEST.REQUEST	"
            + "WHERE CURPRODUCT_REQUEST.REQUEST= ?";
    private final static String nativeQuerySequenceFindStatusEntitiesByCurrentRequest = "SELECT CP.CURPRODUCT_STATUS FROM CURPRODUCT AS CP "
            + "INNER JOIN CURPRODUCT_REQUEST ON CP.CURPRODUCT_ID=CURPRODUCT_REQUEST.CURPRODUCT "
            + "INNER JOIN REQUEST ON REQUEST.REQUEST_ID = CURPRODUCT_REQUEST.REQUEST "
            + "WHERE CURPRODUCT_REQUEST.REQUEST= ?";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYSTATUS = "Try to find by status from: ";
    private final static String FINDBYNAME = "Try to find by name from: ";
    private final static String FINDBYCATEGORY = "Try to find by category name from: ";
    private final static String FINDBYMODEL = "Try to find by model name from: ";
    private final static String FINDBYPRODUCER = "Try to find by producer name from: ";
    private final static String FINDBYYEAR = "Try to find by year from: ";
    private final static String FINDMSG = "Try to find from range: ";
    private final static String SELECTPRODUCTCOUNT = "Try to select product count from: ";
    private final static String GETSTATUS = "Try to get complex status of current product list from: ";
    private final static String sA = "STATUS_AVAILABLE";
    private Integer pageCounter;

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
    public Long selectProductQuantityOfCurrentRequest(Request request) throws Exception {
        EntityManager entityManager = getEntityManager();
        Long resultListIdSize;
        Long requestId = request.getId();
        Query nquery = entityManager.createNativeQuery(nativeQuerySequenceFindEntitiesByCurrentRequest);
        nquery.setParameter(1, requestId);
        try {
            List resultListId = nquery.getResultList();
            resultListIdSize = new Long(resultListId.size() - 1);
            System.out.println("resultListIdSize:   " + resultListIdSize);
            return resultListIdSize;
        } catch (NullPointerException e) {
            LogBean.getLogger().debug(FINDMSG + " " + java.util.Calendar.getInstance().getTime());
            return null;
        }
    }

    @Override
    public Boolean defStatusOfCurrentProducts(Request request) {
        Boolean concStatusFlag = true;
        EntityManager entityManager = getEntityManager();
        Long requestId = request.getId();
        Query nquery = entityManager.createNativeQuery(nativeQuerySequenceFindStatusEntitiesByCurrentRequest);
        nquery.setParameter(1, requestId);
        try {
            List resultListStatus = nquery.getResultList();
            for (Object st : resultListStatus) {
                if (!st.toString().equals(sA)) {
                    concStatusFlag = false;
                }
            }
            return concStatusFlag;
        } catch (NullPointerException e) {
            LogBean.getLogger().debug(GETSTATUS + " " + java.util.Calendar.getInstance().getTime());
            return null;
        }

    }

    @Override
    public List<CurProduct> findEntities(boolean all, int maxResults, int firstResult, Request request) throws Exception {
        EntityManager entityManager = getEntityManager();
        CurProductDAO cpdao = new CurProductDAO();
        CurProduct cp;
        Long resultIdLong;
        Long requestId = request.getId();
        Query nquery = entityManager.createNativeQuery(nativeQuerySequenceFindEntitiesByCurrentRequest);
        nquery.setParameter(1, requestId);
        try {
            if (!all) {
                nquery.setMaxResults(maxResults);
                nquery.setFirstResult(firstResult);
            }
            List resultListId = nquery.getResultList();
            List<CurProduct> resultList = new ArrayList<CurProduct>();
            for (Object elem : resultListId) {
                resultIdLong = Long.parseLong(elem.toString());
                cp = cpdao.findById(resultIdLong);
                resultList.add(cp);
            }
            return resultList;
        } catch (NullPointerException e) {
            LogBean.getLogger().debug(FINDMSG + " " + java.util.Calendar.getInstance().getTime());
            return null;
        }
    }
}
