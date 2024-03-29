package com.grp3.bid.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

	/**
	 * TODO A enlever avec BDD SQL SERVER
	 * @return
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
	}
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// autorisations
		http.authorizeHttpRequests(auth -> {
			// peuvent s'exécuter sans login
			auth.requestMatchers(HttpMethod.GET, "/user/register").anonymous();
			auth.requestMatchers(HttpMethod.POST, "/user/register").anonymous();
			auth.requestMatchers(HttpMethod.GET, "/user/forgottenPassword").anonymous();
			auth.requestMatchers(HttpMethod.POST, "/user/forgottenPassword").anonymous();
			auth.requestMatchers(HttpMethod.POST, "/user/forgottenPassword/reset").anonymous();
			auth.requestMatchers(HttpMethod.GET, "/user/editAccount").authenticated();
			auth.requestMatchers(HttpMethod.POST, "/user/editAccount").authenticated();
			auth.requestMatchers(HttpMethod.GET, "/product/get").authenticated();
			auth.requestMatchers(HttpMethod.GET, "/**").permitAll();
			auth.requestMatchers(HttpMethod.POST, "/**").permitAll();
			/*TODO
			*  UPDATE ROUTE AUTH AFTER DEV*/

			auth.requestMatchers("/static/upload/css/*").permitAll()
				.requestMatchers("/images/**").permitAll()
				.requestMatchers("/javascript/*").permitAll()
				.requestMatchers("/error").permitAll()
				.anyRequest().authenticated();
		});

		// Customiser le formulaire de login
		http.formLogin(form -> {
			form.loginPage("/login").permitAll();
			form.defaultSuccessUrl("/").permitAll();
			form.failureUrl("/login-error");
			
			// permet de définir ce qu'il se passe lorsque le login est validé
			form.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
				   @Override
				    public void onAuthenticationSuccess(HttpServletRequest request, 
				      HttpServletResponse response, Authentication authentication)
				      throws IOException, ServletException {
				 
				    	  // MET L'UTILISATEUR CONNECTE DANS UNE VARIABLE DE SESSION currentUser
				    	  if(authentication!=null) {
				    		  MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
				    		  request.getSession().setAttribute("currentUser", userDetails.getUser());
				    	  }
				    	  super.onAuthenticationSuccess(request, response, authentication);
				    }

			});
		});

		// /logout --> vider la session et le contexte de securite
		http.logout(logout -> logout.invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/product/list").permitAll());

		return http.build();

	}

}
