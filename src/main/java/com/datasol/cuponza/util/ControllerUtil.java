package com.datasol.cuponza.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class ControllerUtil {
	@Deprecated
	public static String decodeErrorMessages(List<ObjectError> errors,Locale locale,MessageSource messageSource){
		Multimap<String,Object> myMultimap = ArrayListMultimap.create();
		for(ObjectError error : errors){
			FieldError fieldError = (FieldError)error;		
			if(fieldError.getDefaultMessage()==null){
				String message = messageSource.getMessage(fieldError.getCode(),null, locale);
				myMultimap.put(fieldError.getField(), message);
			}else{
				myMultimap.put(fieldError.getField(), fieldError.getDefaultMessage());
			}							
		}
		return myMultimap.toString();
	}
	
	public static String decodeErrorMessage(FieldError error,Locale locale,MessageSource messageSource){
		Multimap<String,Object> myMultimap = ArrayListMultimap.create();
		String message = messageSource.getMessage(error.getDefaultMessage(),null, locale);
		myMultimap.put(error.getField(), message);
		return myMultimap.toString();
	}
	
	public static List<ErrorMessage> decodeErrorMessage(List<ObjectError> errors,Locale locale,MessageSource messageSource){
		List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
		for(ObjectError error : errors){
			ErrorMessage errorMessage = new ErrorMessage();
			FieldError fieldError = (FieldError)error;
			errorMessage.setErrorHeader(fieldError.getField());
			if(fieldError.getDefaultMessage()==null){
				String message = messageSource.getMessage(fieldError.getCode(),null, locale);
				errorMessage.setErrorDescription(message);				
			}else{
				errorMessage.setErrorDescription(fieldError.getDefaultMessage());
			}
			errorMessages.add(errorMessage);
		}
		Collections.sort(errorMessages);
		return errorMessages;
	}
}
