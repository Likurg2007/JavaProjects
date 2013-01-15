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

@FacesValidator("requestCodeValidator")
public class RequestCodeValidator extends AbstractValidator {
    
    public RequestCodeValidator() {
        pattern = Pattern.compile(REQUESTCODE_PATTERN);
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!validateRequestCode(value)) {
            FacesMessage message =
                    new FacesMessage("Request № should be like XXYYYYYYYY, where X - letter, Y - digit",
                    "Invalid request code format.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
