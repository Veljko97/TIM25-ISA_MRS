package siit.tim25.rezervisi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			//.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.authorizeRequests()
			.antMatchers("/index", "reservations/seats.html", "/guest/**", "/auth/**").permitAll()
			.antMatchers("/admin-hotels/index","/admin-hotels/edit-room", "/admin-hotels/rooms"
					,"/admin-hotels/report","/admin-hotels/fastReservation").hasRole("HOTEL_ADMIN")
			.antMatchers("/admin-airlines/index","/admin-airlines/edit-destination", "/admin-airlines/destinations", 
					"/admin-airlines/edit-flight", "/admin-airlines/flights","/admin-airlines/add-stop"
					,"/admin-airlines/fastReservation","/admin-airlines/report").hasRole("AIRLINE_ADMIN")
			.antMatchers("/admin/airlines", "/admin/edit-airline", "/admin/edit-hotel", 
					"/admin/hotels","/admin/rentacars", "/admin/edit-rentacar", 
					"/admin/add-admin-airline","/admin/add-admin-hotel","/admin/add-admin-rentACar","/admin/discount").hasRole("SYS_ADMIN")
			.antMatchers("/admin-rentacars/index","/admin-rentacars/branches", "/admin-rentacars/edit-branch",
					"/admin-rentacars/vehicles","/admin-rentacars/edit-vehicle","/admin-rentacars/fastReservation"
					,"/admin-rentacars/report").hasRole("RENTACAR_ADMIN")

			.antMatchers("/user/friends", "/reserve/fast/flight", "/reserve/fast/room"
					,"/reserve/fast/vehicle","/reserve/flight","/reserve/continue","/reserve/flight"
					,"/reservations","/grading").hasRole("USER")
			.and()
			.addFilterBefore(new TokenAuthenticationFilter(tokenUtils, jwtUserDetailsService), BasicAuthenticationFilter.class);

		http.csrf().disable();
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Putanje za koje nije potrebna autentikacija
		web.ignoring().antMatchers("/css/**", "/js/**","/model/**","/service/**","/UnregisteredView/**");
	}
}
