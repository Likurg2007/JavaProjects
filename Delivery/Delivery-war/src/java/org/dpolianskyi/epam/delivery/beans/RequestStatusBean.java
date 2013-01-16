package org.dpolianskyi.epam.delivery.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.dpolianskyi.epam.delivery.controller.dao.abstr.JpaDAOFactory;
import org.dpolianskyi.epam.delivery.controller.dao.real.Curpro_RequestDAO;
import org.dpolianskyi.epam.delivery.model.Curpro_Request;
import org.dpolianskyi.epam.delivery.paging.*;

@ManagedBean(name = "requestStatusBean")
@SessionScoped
public class RequestStatusBean implements Serializable {

    private Logger log = Logger.getLogger(getClass().getName());
    private Pagination pagination = new Pagination(5, 0);
    private static final long serialVersionUID = 1L;
    private DataModel<Curpro_Request> model;
    private Curpro_Request delivery;
    private Curpro_RequestDAO deliveryJPAController = null;
    private String selectedRow;
    private final static String PAGINATIONERROR = "Something caused the error of pagging";
    private final static String REMOVEERROR = "Error with removing of ";

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

    public RequestStatusBean() {
        deliveryJPAController = (Curpro_RequestDAO) JpaDAOFactory.getDAO(Curpro_Request.class);
        model = new ListDataModel();
    }

    public DataModel getModel() {
        try {
            PageController.updateModel(model, pagination, deliveryJPAController);
            if ((model.getRowCount() < 1) && (pagination.isPossiblePrev())) {
                movePrevPage();
            }
        } catch (Exception e) {
            LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
        }
        return model;
    }

    public String removeDelivery() {
        Curpro_Request deliveryToRemove = model.getRowData();
        Long deletedId = deliveryToRemove.getId();
        try {
            deliveryJPAController.remove(deliveryToRemove);
            PageController.updateModel(model, pagination, deliveryJPAController);
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
                PageController.updateModel(model, pagination, deliveryJPAController);
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
                PageController.updateModel(model, pagination, deliveryJPAController);
                return PagesNS.PAGE_LIST_DELIVERY;//"list_delivery";
            } catch (Exception e) {
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
                return "";
            }
        } else {
            return "";
        }
    }

    public void moveProductListPage() throws IOException {
        selectedRow = model.getRowData().getId().toString();
        FacesContext.getCurrentInstance().getExternalContext().redirect("list_products.xhtml");
    }
}