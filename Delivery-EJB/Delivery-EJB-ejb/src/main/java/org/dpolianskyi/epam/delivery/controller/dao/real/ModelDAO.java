package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.IModelDAO;
import org.dpolianskyi.epam.delivery.model.Model;

/**
 *
 * @author Likurg
 */
@Stateless
public class ModelDAO extends CRUD<Model, Long> implements IModelDAO {

    private final static String querySequenceFindAll = "select M from Model M";
    private final static String querySequenceFindByName = "SELECT M FROM Model M WHERE M.name = :modelName";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYNAME = "Try to find by name from: ";

    public ModelDAO() {
        super(Model.class);
    }

    @Override
    public List<Model> findAll() {
        Query query = getEntityManager().createQuery(querySequenceFindAll);
        try {
            LogBean.getLogger().debug(FINDALL + getEntityManager().getClass());
            return (List<Model>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public List<Model> findByName(final String modelName) {
        Query query = getEntityManager().createQuery(querySequenceFindByName);
        query.setParameter("modelName", modelName);
        try {
            LogBean.getLogger().debug(FINDBYNAME + getEntityManager().getClass());
            return (List<Model>) query.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }
}
