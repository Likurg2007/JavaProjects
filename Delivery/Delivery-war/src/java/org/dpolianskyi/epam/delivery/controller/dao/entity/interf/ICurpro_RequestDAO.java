package org.dpolianskyi.epam.delivery.controller.dao.entity.interf;

import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;
import java.util.List;
import org.dpolianskyi.epam.delivery.model.Curpro_Request;

/**
 *
 * @author Likurg
 */
public interface ICurpro_RequestDAO extends ICRUD<Curpro_Request, Long> {

    public List<Curpro_Request> findAll();

    public List<Curpro_Request> findByQuantity(Integer q);

    public Curpro_Request findByProductId(final Long curpro_RequestProductID);
}
