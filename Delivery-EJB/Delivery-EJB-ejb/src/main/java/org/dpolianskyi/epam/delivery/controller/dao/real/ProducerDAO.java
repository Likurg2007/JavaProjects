package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.IProducerDAO;
import org.dpolianskyi.epam.delivery.model.Producer;

/**
 *
 * @author Likurg
 */
@Stateless
public class ProducerDAO extends CRUD<Producer, Long> implements IProducerDAO {

    private final static String querySequenceFindAll = "select P from Producer P";
    private final static String querySequenceFindByName = "SELECT P FROM Producer P WHERE P.name = :producerName";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYNAME = "Try to find by name from: ";

    public ProducerDAO() {
        super(Producer.class);
    }

    @Override
    public List<Producer> findAll() {
        Query query = getEntityManager().createQuery(querySequenceFindAll);
        try {
            LogBean.getLogger().debug(FINDALL + getEntityManager().getClass());
            return (List<Producer>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Producer> findByName(final String producerName) {
        Query query = getEntityManager().createQuery(querySequenceFindByName);
        query.setParameter("producerName", producerName);
        try {
            LogBean.getLogger().debug(FINDBYNAME + getEntityManager().getClass());
            return (List<Producer>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
