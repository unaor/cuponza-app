package com.datasol.cuponza.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.datasol.cuponza.model.User;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "form.customer.lastName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "form.customer.firstName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.customer.password.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "form.customer.email.empty");
		if(user.getPassword()!=null && user.getPasswordConfirmation()!=null &&!user.getPassword().equals(user.getPasswordConfirmation())){
			errors.rejectValue("passwordConfirmation", "form.customer.password.not.equals");
		}
		
	}

}
