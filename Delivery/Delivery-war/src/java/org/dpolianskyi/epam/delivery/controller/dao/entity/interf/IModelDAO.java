package org.dpolianskyi.epam.delivery.controller.dao.entity.interf;

import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;
import java.util.List;
import org.dpolianskyi.epam.delivery.model.Model;

/**
 *
 * @author Likurg
 */
public interface IModelDAO extends ICRUD<Model, Long> {

    public List<Model> findAll();

    public List<Model> findByName(String str);
}
