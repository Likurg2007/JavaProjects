package org.dpolianskyi.epam.delivery.controller.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("modelValidator")
public class ModelValidator extends AbstractValidator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!validateModel(value)) {
            FacesMessage message =
                    new FacesMessage("Information about Model is incorrect",
                    "Invalid model input information");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
