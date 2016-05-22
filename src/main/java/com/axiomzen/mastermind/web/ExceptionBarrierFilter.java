package com.axiomzen.mastermind.web;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ExceptionBarrierFilter implements Filter {

	private static Logger logger = Logger
			.getLogger(ExceptionBarrierFilter.class.getName());

	private static final String ERROR_ID_PLACEHOLDER = "$ERROR_ID";

	private static final String MSG = "Unexpected error. Please contact the technical support and inform the error code: "
			+ ERROR_ID_PLACEHOLDER;

	private void sendError(HttpServletResponse httpServletResponse,
			String errorId) throws IOException {
		httpServletResponse
				.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		httpServletResponse.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
				MSG.replace(ERROR_ID_PLACEHOLDER, errorId));
		httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {

			chain.doFilter(request, response);

		} catch (Exception e) {
			String errorId = UUID.randomUUID().toString();

			logger.log(Level.SEVERE, errorId, e);

			sendError((HttpServletResponse) response, errorId);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
