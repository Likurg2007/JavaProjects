package model;

import org.dpolianskyi.epam.delivery.model.Model;
import org.dpolianskyi.epam.delivery.model.CurProduct;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.model.Curpro_Request;
import org.dpolianskyi.epam.delivery.model.Producer;
import org.dpolianskyi.epam.delivery.model.Category;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class TestEquals {

    @Test
    public void testCategoryEquals() {
        EqualsVerifier.forClass(Category.class).verify();
    }

    @Test
    public void testCurProductEquals() {
        EqualsVerifier.forClass(CurProduct.class).verify();
    }

    @Test
    public void testCurpro_RequestEquals() {
        EqualsVerifier.forClass(Curpro_Request.class).verify();
    }

    @Test
    public void testModelEquals() {
        EqualsVerifier.forClass(Model.class).verify();
    }

    @Test
    public void testProducerEquals() {
        EqualsVerifier.forClass(Producer.class).verify();
    }

    @Test
    public void testRequestEquals() {
        EqualsVerifier.forClass(Request.class).verify();
    }
}