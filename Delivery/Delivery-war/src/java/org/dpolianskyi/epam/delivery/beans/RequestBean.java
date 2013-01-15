package org.dpolianskyi.epam.delivery.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.dpolianskyi.epam.delivery.controller.dao.abstr.JpaDAOFactory;
import org.dpolianskyi.epam.delivery.controller.dao.real.CurProductDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.RequestDAO;
import org.dpolianskyi.epam.delivery.model.*;
import org.dpolianskyi.epam.delivery.paging.*;

@ManagedBean(name = "requestBean")
@SessionScoped
public class RequestBean implements Serializable {

    private Logger log = Logger.getLogger(getClass().getName());
    private Pagination pagination = new Pagination(5, 0);
    private static final long serialVersionUID = 1L;
    private DataModel<Request> modelRequest;
    private DataModel<CurProduct> modelCurProduct;
    private List<Curpro_Request> curPro_Request;
    private Request request;
    private Request curRequest;
    private RequestDAO requestJPAController = null;
    private CurProductDAO curProductJPAController = null;
    private String selectedRow;
    @ManagedProperty(value = "#{actionResultBean}")
    private ActionResultBean actionResultBean;
    private CurProduct curProd;
    private String curModelName;
    private String curProducerName;
    private String curCategoryName;

    public void setDAO(RequestDAO dao) {
        this.requestJPAController = dao;
    }

    public void setDAO(CurProductDAO dao) {
        this.curProductJPAController = dao;
    }

    public void setActionResultBean(ActionResultBean actionResultBean) {
        this.actionResultBean = actionResultBean;
    }

    public ActionResultBean getActionResultBean() {
        return actionResultBean;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public CurProduct getCurProduct() {
        return curProd;
    }

    public void setCurProduct(CurProduct curProd) {
        this.curProd = curProd;
    }

    public String getCurModelName() {
        return curModelName;
    }

    public void setCurModelName(String curModelName) {
        this.curModelName = curModelName;
    }

    public String getCurCategoryName() {
        return curCategoryName;
    }

    public void setCurCategoryName(String curCategoryName) {
        this.curCategoryName = curCategoryName;
    }

    public String getCurProducerName() {
        return curProducerName;
    }

    public void setCurProducerName(String curProducerName) {
        this.curProducerName = curProducerName;
    }

    public RequestBean() {
        requestJPAController = (RequestDAO) JpaDAOFactory.getDAO(Request.class);
        curProductJPAController = (CurProductDAO) JpaDAOFactory.getDAO(CurProduct.class);
        modelRequest = new ListDataModel();
        modelCurProduct = new ListDataModel();
    }

    public List<CurProduct> selectCurRequest() throws Exception {
        System.out.println("getID");
        long curRequestID = curRequest.getId(); //1
        System.out.println("findRequest");
        curRequest = requestJPAController.findById(curRequestID);
        System.out.println("getProductList_begin");
        curPro_Request = new LinkedList<Curpro_Request>(curRequest.getCurrentProduct_Request());
        return getProductListByRequest();
        // return curPro_Request;
    }

    public List<CurProduct> getProductListByRequest() {
        System.out.println("getProductList_started");
        List<CurProduct> curProdList = new LinkedList<CurProduct>();
        System.out.println("adding");
        for (Curpro_Request elem : getCurPro_Request()) {
            System.out.println("curElem_add" + elem.hashCode());
            curProdList.add(elem.getCurrentProduct());
        }
        System.out.println("final_return");
        return curProdList;
    }

    public List<Request> selectCurProduct() throws Exception {
        System.out.println("getID");
        long curProductID = curProd.getId(); //1
        System.out.println("findRequest");
        curProd = curProductJPAController.findById(curProductID);
        System.out.println("getProductList_begin");
        curPro_Request = new LinkedList<Curpro_Request>(curProd.getCurrentProduct_Request());
        return getProductListByProduct();
        // return curPro_Request;
    }

    public List<Request> getProductListByProduct() {
        System.out.println("getProductList_started");
        List<Request> curRequestList = new LinkedList<Request>();
        System.out.println("adding");
        for (Curpro_Request elem : getCurPro_Request()) {
            System.out.println("curElem_add" + elem.hashCode());
            curRequestList.add(elem.getRequest());
        }
        System.out.println("final_return");
        return curRequestList;
    }

    public List<Curpro_Request> getCurPro_Request() {
        return curPro_Request;
    }

    public void setCurPro_Request(List<Curpro_Request> curPro_Request) {
        this.curPro_Request = curPro_Request;
    }

    public DataModel<Request> getModelRequest() {
        try {
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            if ((modelRequest.getRowCount() < 1) && (pagination.isPossiblePrev())) {
                movePrevPageRequest();
            }
        } catch (Exception e) {
            actionResultBean.setResult("Error: " + e.getMessage());
        }
        return modelRequest;
    }

    public DataModel<CurProduct> getModelCurProduct() {
        try {
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
            if ((modelCurProduct.getRowCount() < 1) && (pagination.isPossiblePrev())) {
                movePrevPageProduct();
            }
        } catch (Exception e) {
            actionResultBean.setResult("Error: " + e.getMessage());
        }
        return modelCurProduct;
    }

    public String setupAddRequest() {
        request = new Request();
        return PagesNS.PAGE_ADD_REQUEST;
    }

    public String setupEditRequest() {
        request = modelRequest.getRowData();
        return PagesNS.PAGE_EDIT_REQUEST;
    }

    public String createRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
            try {
                requestJPAController.persist(request);
                actionResultBean.setResult("Request '" + request.getCode() + "' with id=" + request.getId() + " has been created.");
                PageController.updateModel(modelRequest, pagination, requestJPAController);
                return PagesNS.PAGE_LIST_REQUESTS;
            } catch (Exception e) {
                actionResultBean.setResult("Error: " + e.getMessage());
                return "";
            }
        }

////////////////////////////////////////////////////////
    public String createProduct() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
            try {
                Category curCategory = new Category(curCategoryName, null);
                Producer curProducer = new Producer(curProducerName, null);
                Model curModel = new Model(curModelName, null);
                curProd.setCategory(curCategory);
                curProd.setProducer(curProducer);
                curProd.setModel(curModel);
                curProductJPAController.persist(curProd);
                Curpro_Request curpro_Request = new Curpro_Request();
                curpro_Request.setCurrentProduct(curProd);
                Set<Curpro_Request> setCPR = curRequest.getCurrentProduct_Request();
                setCPR.add(curpro_Request);
                curRequest.setCurrentProduct_Request(setCPR);
                requestJPAController.persist(curRequest);
                actionResultBean.setResult("Product '" + curProd.getName() + "' with id=" + curProd.getId() + " has been created.");
                PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
                return PagesNS.PAGE_LIST_PRODUCTS;
            } catch (Exception e) {
                actionResultBean.setResult("Error: " + e.getMessage());
                return "";
            }
        }

    public String editRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
            try {
                requestJPAController.merge(request);
                actionResultBean.setResult("Request '" + request.getCode() + "' with id=" + request.getId() + " has been updated.");
                PageController.updateModel(modelRequest, pagination, requestJPAController);
                return PagesNS.PAGE_LIST_REQUESTS;
            } catch (Exception e) {
                actionResultBean.setResult("Error: " + e.getMessage());
                return "";
            }
        }

    public String editProduct() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
            try {
                Category curCategory = curProd.getCategory();
                Model curModel = curProd.getModel();
                Producer curProducer = curProd.getProducer();
                curModel.setName(curModelName);
                curCategory.setName(curCategoryName);
                curProducer.setName(curProducerName);
                curProd.setModel(curModel);
                curProd.setCategory(curCategory);
                curProd.setProducer(curProducer);
                curProductJPAController.merge(curProd);
                curProd.getCurrentProduct_Request();
                actionResultBean.setResult("Product '" + curProd.getName() + "' with id=" + curProd.getId() + " has been updated.");
                PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
                return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
            } catch (Exception e) {
                actionResultBean.setResult("Error: " + e.getMessage());
                return "";
            }
        }

    public String removeRequest() {
        Request requestToRemove = modelRequest.getRowData();
        Long deletedId = requestToRemove.getId();
        String deletedRequestCode = requestToRemove.getCode();
        try {
            requestJPAController.remove(requestToRemove);
            actionResultBean.setResult("Request '" + deletedRequestCode + "' with id=" + deletedId + " has been deleted.");
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_REQUESTS;
        } catch (Exception e) {
            actionResultBean.setResult("Error: " + e.getMessage());
            return "";
        }
    }

    public String removeProduct() {
        System.out.println("removeProduct");
        CurProduct productToRemove = modelCurProduct.getRowData();
        Long deletedId = productToRemove.getId();
        String deletedProductName = productToRemove.getName();
        try {
            curProductJPAController.remove(productToRemove);
            actionResultBean.setResult("Product '" + deletedProductName + "' with id=" + deletedId + " has been deleted.");
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
            return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
        } catch (Exception e) {
            actionResultBean.setResult("Error: " + e.getMessage());
            return "";
        }
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String moveNextPageRequest() {
        if (pagination.isPossibleNext()) {
            int currentPage = pagination.getCurrentPage();
            pagination.setCurrentPage(++currentPage);
            try {
                PageController.updateModel(modelRequest, pagination, requestJPAController);
                return PagesNS.PAGE_LIST_REQUESTS;//"list_requests";
            } catch (Exception e) {
                //log.error(e);
                actionResultBean.setResult("Error: " + e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public String movePrevPageRequest() {
        if (pagination.isPossiblePrev()) {
            int currentPage = pagination.getCurrentPage();
            if (currentPage > 0) {
                pagination.setCurrentPage(--currentPage);
            }
            try {
                PageController.updateModel(modelRequest, pagination, requestJPAController);
                return PagesNS.PAGE_LIST_REQUESTS;//"list_requests";
            } catch (Exception e) {
                //log.error(e);
                actionResultBean.setResult("Error: " + e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public void moveProductListPage() throws IOException, Exception {
        curRequest = modelRequest.getRowData();
        FacesContext.getCurrentInstance().getExternalContext().redirect("list_products.xhtml");
    }

    public void setSelectedRow(String selectedRow) {
        this.selectedRow = selectedRow;
    }

    public String setupAddProduct() {
        System.out.println("setupAddProduct");
        curProd = new CurProduct();
        return PagesNS.PAGE_ADD_PRODUCT;//" add_product";
    }

    public String setupEditProduct() {
        System.out.println("setupEditProduct");
        curProd = modelCurProduct.getRowData();
        return PagesNS.PAGE_EDIT_PRODUCT;//"edit_request";
    }

    public String moveNextPageProduct() {
        System.out.println("moveNextPageProduct");
        if (pagination.isPossibleNext()) {
            int currentPage = pagination.getCurrentPage();
            pagination.setCurrentPage(++currentPage);
            try {
                PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
                return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
            } catch (Exception e) {
                //log.error(e);
                actionResultBean.setResult("Error: " + e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public String movePrevPageProduct() {
        System.out.println("movePrevPageProduct");
        if (pagination.isPossiblePrev()) {
            int currentPage = pagination.getCurrentPage();
            if (currentPage > 0) {
                pagination.setCurrentPage(--currentPage);
            }
            try {
                PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
                return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
            } catch (Exception e) {
                // log.error(e);
                actionResultBean.setResult("Error: " + e.getMessage());
                return "";
            }
        } else {
            return "";
        }
    }

    public void moveDeliveryPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("list_delivery.xhtml");
    }

    public void moveRequestPage() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("list_requests.xhtml");
    }
}
