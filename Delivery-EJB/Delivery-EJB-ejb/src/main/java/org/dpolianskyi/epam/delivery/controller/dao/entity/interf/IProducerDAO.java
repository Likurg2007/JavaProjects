package org.dpolianskyi.epam.delivery.controller.dao.entity.interf;

import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;
import java.util.List;
import javax.ejb.Local;
import org.dpolianskyi.epam.delivery.model.Producer;

/**
 *
 * @author Likurg
 */
@Local
public interface IProducerDAO extends ICRUD<Producer, Long> {

    public List<Producer> findAll();

    public List<Producer> findByName(String str);
}
