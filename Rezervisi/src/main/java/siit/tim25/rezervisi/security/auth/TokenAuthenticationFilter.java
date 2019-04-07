package siit.tim25.rezervisi.security.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import siit.tim25.rezervisi.security.TokenUtils;


public class TokenAuthenticationFilter extends OncePerRequestFilter {
	
	private TokenUtils tokenUtils;

	private UserDetailsService userDetailsService;

	public TokenAuthenticationFilter(TokenUtils tokenUtils, UserDetailsService userDetailsService) {
		this.tokenUtils = tokenUtils;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String user;
		String token = tokenUtils.getToken(request);
		if(token != null) {
			user = tokenUtils.getUsernameFromToken(token);
			if(user != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(user);
				if(userDetails != null) {
					TokenBasedAuthentication autentication = new TokenBasedAuthentication(userDetails);
					autentication.setToken(token);
					SecurityContextHolder.getContext().setAuthentication(autentication);
				}
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
