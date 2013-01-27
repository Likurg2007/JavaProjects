package org.dpolianskyi.epam.delivery.paging;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.DataModel;
import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;
import org.dpolianskyi.epam.delivery.controller.dao.real.CurProductDAO;
import org.dpolianskyi.epam.delivery.model.CurProduct;
import org.dpolianskyi.epam.delivery.model.Request;

public final class PageController {
    
    private PageController() {
    }
    
    public static void updateModel(DataModel dataModel, Pagination pagin, ICRUD dao) throws Exception {
        int maxReturnValues = (pagin.getCurrentPage() + 1) * pagin.getRecordsOnPage();
        int firstOfReturned = (pagin.getCurrentPage()) * pagin.getRecordsOnPage();
        dataModel.setWrappedData(dao.findEntities(false, pagin.getRecordsOnPage(), firstOfReturned));
        updatePagination(dataModel, pagin, dao);
    }
    
    private static void updatePagination(DataModel dataModel, Pagination pagin, ICRUD dao) throws Exception {
        if (((List) dataModel.getWrappedData()).size() < pagin.getRecordsOnPage()) {
            pagin.setPossibleNext(false);
        } else {
            int firstOfReturned = (pagin.getCurrentPage() + 1) * pagin.getRecordsOnPage();
            int maxReturnValues = firstOfReturned + 1;
            List probeNextPage = dao.findEntities(false, maxReturnValues, firstOfReturned);
            if ((probeNextPage != null) && (probeNextPage.size() > 0)) {
                pagin.setPossibleNext(true);
            } else {
                pagin.setPossibleNext(false);
            }
        }
        if (pagin.getCurrentPage() > 0) {
            pagin.setPossiblePrev(true);
        } else {
            pagin.setPossiblePrev(false);
        }
    }
    
    public static void updateModel(DataModel dataModel, Pagination pagin, CurProductDAO cpdao, Request request) throws Exception {
        int maxReturnValues = (pagin.getCurrentPage() + 1) * pagin.getRecordsOnPage();
        System.out.println("maxReturnValues   " + maxReturnValues);
        int firstOfReturned = (pagin.getCurrentPage()) * pagin.getRecordsOnPage();
        System.out.println("firstOfReturned   " + firstOfReturned);
        List<CurProduct> CPL = new ArrayList<CurProduct>(cpdao.findEntities(false, pagin.getRecordsOnPage(), firstOfReturned, request));
        dataModel.setWrappedData(CPL);
        System.out.println("dataModel:   " + dataModel.getWrappedData());
        System.out.println("UPDATEPAGINATIONCONDITION");
        updatePagination(dataModel, pagin, cpdao, request);
    }
    
    private static void updatePagination(DataModel dataModel, Pagination pagin, CurProductDAO cpdao, Request request) throws Exception {
        if (((List) dataModel.getWrappedData()).size() < pagin.getRecordsOnPage()) {
            System.out.println("IF in UPC:   1");
            pagin.setPossibleNext(false);
        } else {
            System.out.println("ELSE in UPC:   1");
            int firstOfReturned = (pagin.getCurrentPage() + 1) * pagin.getRecordsOnPage();
            int maxReturnValues = firstOfReturned + 1;
            List probeNextPage = cpdao.findEntities(false, maxReturnValues, firstOfReturned, request);
            if ((probeNextPage != null) && (probeNextPage.size() > 0)) {
                System.out.println("IF in UPC:   2");
                pagin.setPossibleNext(true);
            } else {
                System.out.println("ELSE in UPC:   2");
                pagin.setPossibleNext(false);
            }
        }
        if (pagin.getCurrentPage() > 0) {
            System.out.println("IF in UPC:   3");
            pagin.setPossiblePrev(true);
        } else {
            System.out.println("ELSE in UPC:   3");
            pagin.setPossiblePrev(false);
        }
    }
}
