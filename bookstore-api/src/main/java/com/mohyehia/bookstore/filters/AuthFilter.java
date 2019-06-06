package com.mohyehia.bookstore.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mohyehia.bookstore.services.UserService;
import com.mohyehia.bookstore.utils.TokenUtil;

public class AuthFilter extends OncePerRequestFilter{

	@Value("${auth.header}")
	private String TOKEN_HEADER;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TokenUtil tokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/**
		 * 1 => get token from header
		 * 2 => make sure token is valid
		 */
		final String header = request.getHeader(TOKEN_HEADER);
		final SecurityContext securityContext = SecurityContextHolder.getContext();
		
		if(header != null && securityContext.getAuthentication() == null) {
			String token = header.substring("bearer ".length());
			String username = tokenUtil.getUsernameFromToken(token);
			if(username != null) {
				UserDetails userDetails = userService.loadUserByUsername(username);
				if(tokenUtil.isTokenValid(token, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					securityContext.setAuthentication(authenticationToken);
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}
	
}
