package com.aim.foodtaxi.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.aim.foodtaxi.repositories.DriverRepository;
import com.aim.foodtaxi.repositories.ShopUserRepository;

import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@AllArgsConstructor
public class JWTAuthenticationFilter extends GenericFilterBean {

	private DriverRepository driverRepository;
	private ShopUserRepository shopUserRepository;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		Authentication authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request,
				driverRepository, shopUserRepository);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}