package org.dpolianskyi.epam.delivery.controller.dao.entity.interf;

import java.util.List;
import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;
import org.dpolianskyi.epam.delivery.model.CurProduct;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.model.StatusEnum;

/**
 *
 * @author Likurg
 */
public interface ICurProductDAO extends ICRUD<CurProduct, Long> {

    public List<CurProduct> findAll();

    public List<CurProduct> findByStatus(StatusEnum st);

    public List<CurProduct> findByName(String str);

    public List<CurProduct> findByModelName(final String modelName);

    public List<CurProduct> findByCategoryName(final String categoryName);

    public List<CurProduct> findByProducerName(final String producerName);

    public List<CurProduct> findByYear(final Integer productListYear);
    
    public List<CurProduct> findEntities(boolean all, int maxResults, int firstResult, Request request) throws Exception;
}
