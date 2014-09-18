package com.datasol.cuponza.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CuponzaController {

	@Autowired
	MessageSource messageSource;

	private static final Logger logger = Logger.getLogger(CuponzaController.class);

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception ex, Locale locale,HttpServletRequest request) {
		logger.error("internal server error " + ex);
		request.setAttribute("msg", ex.getMessage());
		return "error-pages/500";
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public String handleUnauthorizedException(Exception ex, Locale locale,HttpServletRequest request) {
		logger.debug("unauthorized access attempt");
		request.setAttribute("msg", messageSource.getMessage("controller.response.403", null, locale));
		return "error-pages/403";
	}
}