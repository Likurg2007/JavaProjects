package org.dpolianskyi.epam.delivery.controller.dao.abstr;

import org.dpolianskyi.epam.delivery.model.Curpro_Request;
import org.dpolianskyi.epam.delivery.model.Model;
import org.dpolianskyi.epam.delivery.model.CurProduct;
import org.dpolianskyi.epam.delivery.model.Producer;
import org.dpolianskyi.epam.delivery.model.Category;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.controller.dao.real.*;
import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;

public class JpaDAOFactory {

    public static ICRUD getDAO(Class<?> classtype) {
        if (classtype.equals(Category.class)) {
            return new CategoryDAO();
        } else if (classtype.equals(CurProduct.class)) {
            return new CurProductDAO();
        } else if (classtype.equals(Curpro_Request.class)) {
            return new Curpro_RequestDAO();
        } else if (classtype.equals(Model.class)) {
            return new ModelDAO();
        } else if (classtype.equals(Producer.class)) {
            return new ProducerDAO();
        } else if (classtype.equals(Request.class)) {
            return new RequestDAO();
        } else {
            throw new UnsupportedOperationException("Undefined class type");
        }
    }
}
