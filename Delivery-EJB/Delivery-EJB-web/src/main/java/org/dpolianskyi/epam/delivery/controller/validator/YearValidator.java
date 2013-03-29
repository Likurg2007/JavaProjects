/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dpolianskyi.epam.delivery.controller.validator;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("yearValidator")
public class YearValidator extends AbstractValidator {

    public YearValidator() {
        pattern = Pattern.compile(YEAR_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!validateYear(value)) {
            FacesMessage message =
                    new FacesMessage("Year should be in XXXX-format",
                    "Invalid year input.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
