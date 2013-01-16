/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dpolianskyi.epam.delivery.controller.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.validator.Validator;

public abstract class AbstractValidator implements Validator {

    protected static final String REQUESTCODE_PATTERN = "[A-ZА-ЯІЇЄҐ]{2}+\\d{8}";
    protected static final String NAME_PATTERN = "/([^\\s]+)\\s+([^\\s.])[^\\s.]*(?:\\s|\\.)([^\\s.])[^\\s.]*/";
    protected static final String YEAR_PATTERN = "(20)\\d\\d";
    protected static final String MODEL_PATTERN = "";
    protected static final String CATEGORY_PATTERN = "";
    protected static final String PRODUCER_PATTERN = "";
    protected Pattern pattern;
    protected Matcher matcher;

    protected boolean validateRequestCode(Object value) {
        pattern = Pattern.compile(REQUESTCODE_PATTERN);
        matcher = pattern.matcher(value.toString());
        return matcher.matches();
    }

    protected boolean validateName(Object value) {
        pattern = Pattern.compile(NAME_PATTERN);
        matcher = pattern.matcher(value.toString());
        return matcher.matches();
    }

    protected boolean validateYear(Object value) {
        pattern = Pattern.compile(YEAR_PATTERN);
        matcher = pattern.matcher(value.toString());
        return matcher.matches();
    }

    protected boolean validateModel(Object value) {
        pattern = Pattern.compile(MODEL_PATTERN);
        matcher = pattern.matcher(value.toString());
        return matcher.matches();
    }

    protected boolean validateCategory(Object value) {
        pattern = Pattern.compile(CATEGORY_PATTERN);
        matcher = pattern.matcher(value.toString());
        return matcher.matches();
    }

    protected boolean validateProducer(Object value) {
        pattern = Pattern.compile(PRODUCER_PATTERN);
        matcher = pattern.matcher(value.toString());
        return matcher.matches();
    }
}
