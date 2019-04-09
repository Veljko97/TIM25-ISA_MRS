package siit.tim25.rezervisi.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import siit.tim25.rezervisi.security.TokenUtils;
import siit.tim25.rezervisi.security.auth.RestAuthenticationEntryPoint;
import siit.tim25.rezervisi.security.auth.TokenAuthenticationFilter;
import siit.tim25.rezervisi.security.servicce.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private CustomUserDetailsService jwtUserDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private TokenUtils tokenUtils;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			// Ako uskljucim da je bezbednost stateless nisam pronasao nacin za prosledjivanje
			// tokena kroz cromove zahteve kad se prebacuje na drugu html stranicu
			//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			//.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.authorizeRequests()
			.antMatchers("/index", "/auth/**").permitAll()
			.antMatchers("/admin-hotels/edit-room.html", "/admin-hotels/rooms.html").hasRole("HOTEL_ADMIN")
			.antMatchers("/admin-airlines/edit-destination.html", "/admin-airlines/destinations.html", 
					"/admin-airlines/edit-flight.html", "/admin-airlines/flights.html").hasRole("AIRLINE_ADMIN")
			.antMatchers("/admin/airlines.html", "/admin/edit-airline.html", "/admin/edit-hotel.html", 
					"/admin/hotels.html","/admin/rentacars.html", "/admin/edit-rentacar.html").hasRole("SYS_ADMIN")
			.and()
			.formLogin().and()
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

		http.csrf().disable();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Putanje za koje nije potrebna autentikacija
		web.ignoring().antMatchers("/css/**", "/js/**","/model/**","/service/**");
	}
}
