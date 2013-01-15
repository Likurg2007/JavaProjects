package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.IProducerDAO;
import org.dpolianskyi.epam.delivery.model.Producer;

/**
 *
 * @author Likurg
 */
public class ProducerDAO extends CRUD<Producer, Long> implements IProducerDAO {

    private final static String querySequenceFindAll = "select P from Producer P";
    private final static String querySequenceFindByName = "SELECT P FROM Producer P WHERE P.name = :producerName";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYNAME = "Try to find by name from: ";

    public ProducerDAO() {
        super(Producer.class);
        emf = Persistence.createEntityManagerFactory("Delivery-warPU");

    }

    @Override
    public List<Producer> findAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindAll);
        try {
            LogBean.getLogger().debug(FINDALL + entityManager.getClass());
            return (List<Producer>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Producer> findByName(final String producerName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByName);
        query.setParameter("producerName", producerName);
        try {
            LogBean.getLogger().debug(FINDBYNAME + entityManager.getClass());
            return (List<Producer>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
