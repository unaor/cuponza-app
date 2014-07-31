package com.datasol.cuponza.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.datasol.cuponza.model.Customer;

public class CustomerValidator implements Validator {
	
	@Override
	public boolean supports(Class clazz) {
		return Customer.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "form.customer.lastName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "form.customer.firstName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "form.customer.password.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contactEmail", "form.customer.password.empty");
		if(customer.getPassword()!=null && customer.getPasswordConfirmation()!=null &&!customer.getPassword().equals(customer.getPasswordConfirmation())){
			errors.rejectValue("passwordConfirmation", "form.customer.password.not.equals");
		}
	}

}
