package org.dpolianskyi.epam.delivery.controller.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("formValidator")
public class Request_DeliveryValidator extends AbstractValidator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!validateRequestCode(value) && !validateName(value)) {
            FacesMessage message =
                    new FacesMessage("Your login validation failed",
                    "Invalid login format");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
