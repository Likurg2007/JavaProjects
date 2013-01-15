package org.dpolianskyi.epam.delivery.controller.dao.entity.interf;

import java.util.List;
import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.model.StatusEnum;

/**
 *
 * @author Likurg
 */
public interface IRequestDAO extends ICRUD<Request, Long> {

    public List<Request> findAll();

    public List<Request> findByCode(String c);

    public List<Request> findByStatus(StatusEnum st);

    public List<Request> findByCredentials(String str);
}
