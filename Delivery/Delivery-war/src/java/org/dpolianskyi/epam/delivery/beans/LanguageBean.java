package org.dpolianskyi.epam.delivery.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 *
 * Based on solution:
 * http://www.mkyong.com/jsf2/jsf-2-internationalization-example/
 *
 */
@ManagedBean(name = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {

    private Logger log = Logger.getLogger(getClass().getName());
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    private static final long serialVersionUID = 1L;
    private String localeCode; //default values
    private static Map<String, Object> countries;

    static {
        countries = new LinkedHashMap<String, Object>();
        countries.put("Russian", new Locale("ru", "RU"));  //label, value
        countries.put("Ukranian", new Locale("ua", "UA"));
        countries.put("English", new Locale("eng", "ENG"));
    }

    public Map<String, Object> getCountriesInMap() {
        log.info("Locale countries: " + countries);
        return countries;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

//value change event listener
    public void countryLocaleCodeChanged(ValueChangeEvent e) {

        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {

                FacesContext.getCurrentInstance()
                        .getViewRoot().setLocale((Locale) entry.getValue());

            }
        }
    }

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
}