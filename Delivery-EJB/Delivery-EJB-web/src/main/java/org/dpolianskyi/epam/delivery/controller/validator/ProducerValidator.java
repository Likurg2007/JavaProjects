package org.dpolianskyi.epam.delivery.controller.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("producerValidator")
public class ProducerValidator extends AbstractValidator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (!validateProducer(value)) {
            FacesMessage message =
                    new FacesMessage("Information about Producer is incorrect",
                    "Invalid producer input information");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
