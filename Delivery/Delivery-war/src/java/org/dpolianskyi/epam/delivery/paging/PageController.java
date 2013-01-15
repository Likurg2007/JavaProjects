package org.dpolianskyi.epam.delivery.paging;

import java.util.List;

import javax.faces.model.DataModel;

import org.dpolianskyi.epam.delivery.controller.dao.interf.ICRUD;

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
}
