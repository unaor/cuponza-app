package com.datasol.cuponza.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component("ajaxAuthFailureHandler")
public class AjaxAuthFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	public AjaxAuthFailureHandler() {
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.write(gson.toJson("BAD"));
		out.close();
	}

}
