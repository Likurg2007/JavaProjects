package org.dpolianskyi.epam.delivery.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.dpolianskyi.epam.delivery.controller.dao.abstr.JpaDAOFactory;
import org.dpolianskyi.epam.delivery.controller.dao.real.CurProductDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.Curpro_RequestDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.RequestDAO;
import org.dpolianskyi.epam.delivery.model.*;
import org.dpolianskyi.epam.delivery.paging.*;

@ManagedBean(name = "requestBean")
@SessionScoped
public class RequestBean implements Serializable {

    private Pagination pagination = new Pagination(5, 0);
    private static final long serialVersionUID = 1L;
    private DataModel<Request> modelRequest;
    private DataModel<CurProduct> modelCurProduct;
    private List<Curpro_Request> curPro_Request;
    private Request request;
    private Request curRequest;
    private RequestDAO requestJPAController = null;
    private CurProductDAO curProductJPAController = null;
    private Curpro_RequestDAO curPro_RequestJPAController = null;
    private CurProduct curProd;
    private String curModelName;
    private String curProducerName;
    private String curCategoryName;
    private final static String PAGINATIONERROR = "Something caused the error of pagging";
    private final static String CREATEERROR = "Error with creating of ";
    private final static String EDITERROR = "Error with editing of ";
    private final static String REMOVEERROR = "Error with removing of ";

    ;

    public void setDAO(RequestDAO dao) {
        this.requestJPAController = dao;
    }

    public void setDAO(CurProductDAO dao) {
        this.curProductJPAController = dao;
    }

    public void setDAO(Curpro_RequestDAO dao) {
        this.curPro_RequestJPAController = dao;
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
        curPro_RequestJPAController = (Curpro_RequestDAO) JpaDAOFactory.getDAO(Curpro_Request.class);
        modelRequest = new ListDataModel();
        modelCurProduct = new ListDataModel();
    }

    public List<CurProduct> selectCurRequest() throws Exception {
        List<CurProduct> curProdList = new LinkedList<CurProduct>();
        long curRequestID = curRequest.getId(); //1
        curRequest = requestJPAController.findById(curRequestID);
        curPro_Request = new LinkedList<Curpro_Request>(curRequest.getCurrentProduct_Request());
        for (Curpro_Request elem : getCurPro_Request()) {
            curProdList.add(elem.getCurrentProduct());
            elem.setRequest(curRequest);
        }
        requestJPAController.merge(curRequest);
        return curProdList;
    }

    public List<Request> selectCurProduct() throws Exception {
        long curProductID = curProd.getId(); //1
        curProd = curProductJPAController.findById(curProductID);
        curPro_Request = new LinkedList<Curpro_Request>(curProd.getCurrentProduct_Request());
        return getProductListByProduct();
    }

    public List<Request> getProductListByProduct() {
        List<Request> curRequestList = new LinkedList<Request>();
        for (Curpro_Request elem : getCurPro_Request()) {
            curRequestList.add(elem.getRequest());
        }
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
            LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
        }
        return modelRequest;
    }

    public DataModel<CurProduct> getModelCurProduct() throws Exception {
       // modelCurProduct=(DataModel<CurProduct>) selectCurRequest();
        System.out.println("modelCurProduct:   " + modelCurProduct);
        try {
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
            if ((modelCurProduct.getRowCount() < 1) && (pagination.isPossiblePrev())) {
                movePrevPageProduct();
            }
        } catch (Exception e) {
            LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
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
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_REQUESTS;
        } catch (Exception e) {
            LogBean.getLogger().error(CREATEERROR + " " + request.getId() + "-" + request.getCode() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

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
            for (Curpro_Request elem : getCurPro_Request()) {
                curPro_RequestJPAController.persist(elem);
            }
            curRequest.setCurrentProduct_Request(setCPR);
            requestJPAController.merge(curRequest);
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
            return PagesNS.PAGE_LIST_PRODUCTS;
        } catch (Exception e) {
            LogBean.getLogger().error(CREATEERROR + " " + curProd.getId() + "-" + curProd.getName() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String editRequest() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            requestJPAController.merge(request);
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_REQUESTS;
        } catch (Exception e) {
            LogBean.getLogger().error(EDITERROR + " " + request.getId() + "-" + request.getCode() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String editProduct() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            System.out.println("curProduct   " + curProd);

            Category curCategory = curProd.getCategory();

            Model curModel = curProd.getModel();

            Producer curProducer = curProd.getProducer();

            curModel.setName(curModelName);
            System.out.println("curCategory   " + curCategory);
            curCategory.setName(curCategoryName);
            System.out.println("curCategory   " + curModel);
            curProducer.setName(curProducerName);
            System.out.println("curCategory   " + curProducer);
            curProd.setModel(curModel);
            curProd.setCategory(curCategory);
            curProd.setProducer(curProducer);
            System.out.println("curProduct    " + curProd);
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController);
            return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
        } catch (Exception e) {
            LogBean.getLogger().error(EDITERROR + " " + curProd.getId() + "-" + curProd.getName() + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String removeRequest() {
        Request requestToRemove = modelRequest.getRowData();
        Long deletedId = requestToRemove.getId();
        String deletedRequestCode = requestToRemove.getCode();
        try {
            requestJPAController.remove(requestToRemove);
            PageController.updateModel(modelRequest, pagination, requestJPAController);
            return PagesNS.PAGE_LIST_REQUESTS;
        } catch (Exception e) {
            LogBean.getLogger().error(REMOVEERROR + " " + deletedId + "-" + deletedRequestCode + " " + java.util.Calendar.getInstance().getTime(), e);
            return "";
        }
    }

    public String removeProduct() {
       // modelCurProduct = getModelCurProduct();
        CurProduct productToRemove = modelCurProduct.getRowData();
        Long deletedId = productToRemove.getId();
        String deletedProductName = productToRemove.getName();
        try {
            curProductJPAController.remove(productToRemove);
            PageController.updateModel(modelCurProduct, pagination, curProductJPAController);

            return PagesNS.PAGE_LIST_PRODUCTS;//"list_products";
        } catch (Exception e) {
            LogBean.getLogger().error(REMOVEERROR + " " + deletedId + "-" + deletedProductName + " " + java.util.Calendar.getInstance().getTime(), e);
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
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
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
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
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

    public String setupAddProduct() {
        curProd = new CurProduct();
        return PagesNS.PAGE_ADD_PRODUCT;//" add_product";
    }

    public String setupEditProduct() throws Exception {
        System.out.println("setupEditProduct");
        curProd = getModelCurProduct().getRowData();
        System.out.println("curProduct^  " + curProd);
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
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
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
                LogBean.getLogger().error(PAGINATIONERROR + " " + java.util.Calendar.getInstance().getTime(), e);
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
