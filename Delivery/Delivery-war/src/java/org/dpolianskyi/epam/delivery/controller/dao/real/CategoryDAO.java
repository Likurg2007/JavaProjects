package org.dpolianskyi.epam.delivery.controller.dao.real;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.dpolianskyi.epam.delivery.beans.LogBean;
import org.dpolianskyi.epam.delivery.controller.dao.entity.interf.ICategoryDAO;
import org.dpolianskyi.epam.delivery.model.Category;

/**
 *
 * @author Likurg
 */
public class CategoryDAO extends CRUD<Category, Long> implements ICategoryDAO {

    private final static String querySequenceFindAll = "select C from Category C";
    private final static String querySequenceFindByName = "SELECT C FROM Category C WHERE C.name = :categoryName";
    private final static String FINDALL = "Try to find all from: ";
    private final static String FINDBYNAME = "Try to find by name from: ";

    public CategoryDAO() {
        super(Category.class);
        emf = Persistence.createEntityManagerFactory("Delivery-warPU");

    }

    @Override
    public List<Category> findAll() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindAll);
        try {
            LogBean.getLogger().debug(FINDALL + entityManager.getClass());
            return (List<Category>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Category> findByName(final String categoryName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(querySequenceFindByName);
        query.setParameter("categoryName", categoryName);
        try {
            LogBean.getLogger().debug(FINDBYNAME + entityManager.getClass());
            return (List<Category>) query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
