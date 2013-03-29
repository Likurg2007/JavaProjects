package org.dpolianskyi.epam.delivery.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.dpolianskyi.epam.delivery.controller.dao.real.CurProductDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.Curpro_RequestDAO;
import org.dpolianskyi.epam.delivery.controller.dao.real.RequestDAO;
import org.dpolianskyi.epam.delivery.model.*;
import org.dpolianskyi.epam.delivery.paging.*;

@ManagedBean(name = "requestBean")
@SessionScoped
public class RequestBean implements Serializable {

    @EJB
    private Manager manager;

    public void setDAO(RequestDAO dao) {
        manager.setDAO(dao);
    }

    public void setDAO(CurProductDAO dao) {
        manager.setDAO(dao);
    }

    public void setDAO(Curpro_RequestDAO dao) {
        manager.setDAO(dao);
    }

    public Request getRequest() {
        return manager.getRequest();
    }

    public void setRequest(Request request) {
        manager.setRequest(request);
    }

    public Long getRequestQuantity() {
        return manager.getRequestQuantity();
    }

    public Long getProductQuantity() throws Exception {
        return manager.getProductQuantity();
    }

    public Long getDeliveryQuantity() {
        return manager.getDeliveryQuantity();
    }

    public String getProductListStatus() {
        return manager.getProductListStatus();
    }

    public Request getCurRequest() {
        return manager.getCurRequest();
    }

    public void setCurRequest(Request curRequest) {
        manager.setCurRequest(curRequest);
    }

    public CurProduct getCurProduct() {
        return manager.getCurProduct();
    }

    public void setCurProduct(CurProduct curProd) {
        manager.setCurProduct(curProd);
    }

    public String getCurModelName() {
        return manager.getCurModelName();
    }

    public void setCurModelName(String curModelName) {
        manager.setCurModelName(curModelName);
    }

    public String getCurCategoryName() {
        return manager.getCurCategoryName();
    }

    public void setCurCategoryName(String curCategoryName) {
        manager.setCurCategoryName(curCategoryName);
    }

    public String getCurProducerName() {
        return manager.getCurProducerName();
    }

    public void setCurProducerName(String curProducerName) {
        manager.setCurProducerName(curProducerName);
    }

    public RequestBean() {
    }

    public List<CurProduct> selectCurRequest() throws Exception {
        return manager.selectCurRequest();
    }

    public List<Request> selectCurProduct() throws Exception {
        return manager.selectCurProduct();
    }

    public List<Request> getProductListByProduct() {
        return manager.getProductListByProduct();
    }

    public List<Curpro_Request> getCurPro_Request() {
        return manager.getCurPro_Request();
    }

    public void setCurPro_Request(List<Curpro_Request> curPro_Request) {
        manager.setCurPro_Request(curPro_Request);
    }

    public DataModel<Request> getModelRequest() {
        return manager.getModelRequest();
    }

    public DataModel<Request> getModelDelivery() {
        return manager.getModelDelivery();
    }

    public DataModel<Request> getEditedModelDelivery() {
        return manager.getEditedModelDelivery();
    }

    public DataModel<CurProduct> getModelCurProduct() throws Exception {
        return manager.getModelCurProduct();
    }

    public String setupAddRequest() {
        return manager.setupAddRequest();
    }

    public String setupEditRequest() {
        return manager.setupAddRequest();
    }

    public String createRequest() {
        return manager.createRequest();
    }

    public String createProduct() {
        return manager.createProduct();
    }

    public String editRequest() {
        return manager.editRequest();
    }

    public String editProduct() {
        return manager.editProduct();
    }

    public String editDelivery() {
        return manager.editDelivery();
    }

    public String removeRequest() {
        return manager.removeRequest();
    }

    public String removeDelivery() {
        return manager.removeDelivery();
    }

    public String confirmDelivery() {
        return manager.confirmDelivery();
    }

    public String removeProduct() throws Exception {
        return manager.removeProduct();
    }

    public Pagination getPagination() {
        return manager.getPagination();
    }

    public void setPagination(Pagination pagination) {
        manager.setPagination(pagination);
    }

    public String moveNextPageRequest() {
        return manager.moveNextPageRequest();
    }

    public String movePrevPageRequest() {
        return manager.movePrevPageRequest();
    }

    public String moveProductListPage() throws IOException, Exception {
        return manager.moveProductListPage();
    }

    public String setupAddProduct() {
        return manager.setupAddProduct();
    }

    public String setupEditProduct() throws Exception {
        return manager.setupEditProduct();
    }

    public String moveNextPageProduct() {
        return manager.moveNextPageProduct();
    }

    public String movePrevPageProduct() {
        return manager.movePrevPageProduct();
    }

    public String moveDeliveryPageConfirm() {
        return manager.moveDeliveryPageConfirm();
    }

    public String moveDeliveryPage() {
        return manager.moveDeliveryPage();
    }

    public String moveRequestPage() {
        return manager.moveRequestPage();
    }
}
