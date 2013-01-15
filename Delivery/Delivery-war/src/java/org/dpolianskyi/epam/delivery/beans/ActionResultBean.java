package org.dpolianskyi.epam.delivery.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "actionResultBean")
@SessionScoped
public class ActionResultBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String result = new String();

    public void clear() {
        result = "";
    }

    public String getResult() {
        String returned = result; 
        result = "";
        return returned;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
