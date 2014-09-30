package com.datasol.cuponza.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.datasol.cuponza.model.User;
import com.datasol.cuponza.util.AuthProvider;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
				"form.customer.lastName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"form.customer.firstName.empty");
		if (user.getAuthProvider() == null
				|| user.getAuthProvider().equals(AuthProvider.CUPONZA.name())) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
					"form.customer.password.empty");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"form.customer.email.empty");
		if (user.getAuthProvider() == null
				|| user.getAuthProvider().equals(AuthProvider.CUPONZA.name())) {
			if (user.getPassword() != null
					&& user.getPasswordConfirmation() != null
					&& !user.getPassword().equals(
							user.getPasswordConfirmation())) {
				errors.rejectValue("passwordConfirmation",
						"form.customer.password.not.equals");
			}
		}

	}

}
