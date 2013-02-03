package com.planner.user.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "emailAddressValidator")
public class EmailAddressValidator implements Validator
{
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public void validate( FacesContext arg0, UIComponent arg1, Object value ) throws ValidatorException
	{
		String email = String.valueOf( value );
		
		Pattern pattern = Pattern.compile( EMAIL_PATTERN );
		Matcher matcher = pattern.matcher( email );
		
		if (!matcher.matches())
		{
            FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, "Invalid email address", "The email address you entered is not valid.");
            throw new ValidatorException( message );
        }
	}
}
