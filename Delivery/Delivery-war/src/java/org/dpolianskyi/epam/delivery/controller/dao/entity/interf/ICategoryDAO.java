package org.dpolianskyi.epam.delivery.controller.dao.entity.interf;

import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;
import java.util.List;
import org.dpolianskyi.epam.delivery.model.Category;

/**
 *
 * @author Likurg
 */
public interface ICategoryDAO extends ICRUD<Category, Long> {

    public List<Category> findAll();

    public List<Category> findByName(String str);
    
}
