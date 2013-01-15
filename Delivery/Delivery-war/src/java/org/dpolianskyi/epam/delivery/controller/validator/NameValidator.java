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

@FacesValidator("nameValidator")
public class NameValidator extends AbstractValidator {
    
    public NameValidator() {
        pattern = Pattern.compile(NAME_PATTERN);
    }
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!validateRequestCode(value)) {
            FacesMessage message =
                    new FacesMessage("Name should be as XXXXXXXXXX Y.Z. or XXXXXXXXXX YYYYY ZZZZZZZZZZZZ, where X - symbols of surname, Y - symbols of firstname, Z - symbols of middlename",
                    "Invalid full name request.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
