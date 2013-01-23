package org.dpolianskyi.epam.delivery.beans;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.dpolianskyi.epam.delivery.controller.dao.abstr.JpaDAOFactory;
import org.dpolianskyi.epam.delivery.controller.dao.real.Curpro_RequestDAO;
import org.dpolianskyi.epam.delivery.model.Curpro_Request;
import org.dpolianskyi.epam.delivery.model.Request;
import org.dpolianskyi.epam.delivery.paging.*;

@ManagedBean(name = "requestStatusBean")
@SessionScoped
public class RequestStatusBean implements Serializable {

    private Pagination pagination = new Pagination(5, 0);
    private static final long serialVersionUID = 1L;
    private DataModel<Curpro_Request> modelDelivery;
    private Request request;
    private Curpro_Request delivery;
    private Curpro_RequestDAO deliveryJPAController = null;
    private String selectedRow;
    private final static String PAGINATIONERROR = "Something caused the error of pagging";
    private final static String REMOVEERROR = "Error with removing of ";
    private final static String REQUESTPAGE = "requestpage";

    public void setDAO(Curpro_RequestDAO dao) {
        this.deliveryJPAController = dao;
    }

    public void setSelectedRow(String selectedRow) {
        this.selectedRow = selectedRow;
    }

    public String getSelectedRow() {
        return selectedRow;
    }

    public Curpro_Request getDelivery() {
        return delivery;
    }

    public void setDelivery(Curpro_Request delivery) {
        this.delivery = delivery;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public RequestStatusBean() {
        deliveryJPAController = (Curpro_RequestDAO) JpaDAOFactory.getDAO(Curpro_Request.class);
        modelDelivery = new ListDataModel();
    }

    public DataModel getModelDelivery() {
        try {
            PageController.updateModel(modelDelivery, pagination, deliveryJPAController);
            if ((modelDelivery.getRowCount() < 1) && (pagination.isPossiblePrev())) {
                movePrevPage();
            }
        } catch (Exception e) {
            LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
        }
        return modelDelivery;
    }

    public String removeDelivery() {
        Curpro_Request deliveryToRemove = modelDelivery.getRowData();
        Long deletedId = deliveryToRemove.getId();
        try {
            deliveryJPAController.remove(deliveryToRemove);
            PageController.updateModel(modelDelivery, pagination, deliveryJPAController);
            return PagesNS.PAGE_LIST_DELIVERY;//"list_delivery";
        } catch (Exception e) {
            LogBean.getLogger().error(REMOVEERROR + " " + deletedId + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String moveNextPage() {
        if (pagination.isPossibleNext()) {
            int currentPage = pagination.getCurrentPage();
            pagination.setCurrentPage(++currentPage);
            try {
                PageController.updateModel(modelDelivery, pagination, deliveryJPAController);
                return PagesNS.PAGE_LIST_DELIVERY;//"list_delivery";
            } catch (Exception e) {
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
                return "";
            }
        } else {
            return "";
        }
    }

    public String movePrevPage() {
        if (pagination.isPossiblePrev()) {
            int currentPage = pagination.getCurrentPage();
            if (currentPage > 0) {
                pagination.setCurrentPage(--currentPage);
            }
            try {
                PageController.updateModel(modelDelivery, pagination, deliveryJPAController);
                return PagesNS.PAGE_LIST_DELIVERY;//"list_delivery";
            } catch (Exception e) {
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
                return "";
            }
        } else {
            return "";
        }
    }

    public String moveRequestPage() throws IOException {
        return REQUESTPAGE;
    }
}