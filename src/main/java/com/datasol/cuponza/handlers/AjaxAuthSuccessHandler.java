package com.datasol.cuponza.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component("ajaxAuthSuccessHandler")
public class AjaxAuthSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	public AjaxAuthSuccessHandler() {
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.write(gson.toJson("GREAT"));
		out.close();
	}

}
