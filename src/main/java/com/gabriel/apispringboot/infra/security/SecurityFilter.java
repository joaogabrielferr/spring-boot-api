package com.gabriel.apispringboot.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.gabriel.apispringboot.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
		
		String token = recoverToken(request);

		if(token != null)
		{
			String email = tokenService.validateToken(token);
			UserDetails user = userService.findByEmail(email);
			
			if(user != null)
			{

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);		
			}
		}
		
		filterChain.doFilter(request, response);
		
	}	
	
	private String recoverToken(HttpServletRequest request)
	{
		String authHeader = request.getHeader("Authorization");
		
		if(authHeader == null)return null;
		return authHeader.replace("Bearer ", "");
		
	}
	
}
