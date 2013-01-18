package org.dpolianskyi.epam.delivery.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    private static final long serialVersionUID = 1L;
    private static final String REQUESTPAGE = "requestpage";

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language, String languageB) throws IOException {
        locale = new Locale(language, languageB);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }

    public String setLanguageRedirect(String language, String languageB) throws IOException {
        locale = new Locale(language, languageB);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        return REQUESTPAGE;
    }
}