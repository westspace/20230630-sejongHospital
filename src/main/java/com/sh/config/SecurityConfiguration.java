package com.sh.config;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private CustomAuthFailureHandler customAuthFailureHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
	    .exceptionHandling()
	    .accessDeniedHandler(new CustomAccessDeniedHandler());
		
		http
		.cors()
//		.and()
//		.csrf()
//		.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and()
		.csrf().disable() // rest api만 사용하고 싶을때
         /*
          */
         .authorizeRequests().antMatchers("/api/*","/api/**",
    		 "/login", "/join", "/test", "/test/**", "/admin", "/admin/**", "/articleImage/**", "/articleImage/*", "/articleImage").permitAll()
	        .antMatchers("/admin/**", "/admin").hasAnyAuthority("관리자", "병원담당자")
	        .anyRequest().authenticated()
        	.and()
        .formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/doLogin")
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/", true)
			.permitAll()
			//.successHandler(null)
			.failureHandler(customAuthFailureHandler)
			.and()
		.logout()
			.logoutUrl("/comn/logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/login")
			.permitAll();
		
		http
			.rememberMe()
			.key(UUID.randomUUID().toString())
			.rememberMeParameter("remember-me")
			.tokenValiditySeconds(3600*24*365);
		
		//http.headers().frameOptions().sameOrigin();
		return http.build();
	}
	
	@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/resources/**","/css/**","fonts/**","/js/**","/static/**","/module/**","/webjars/**","/less/**","/images/**");
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
