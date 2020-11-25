package com.pablogiraldo.grupoenlaces.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pablogiraldo.grupoenlaces.config.CustomAccessDeniedHandler;
import com.pablogiraldo.grupoenlaces.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	String[] resources = new String[] { "/img/**" };

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(resources).permitAll()
				.antMatchers("/", "/home", "/error", "/fragments", "/forbidden", "/login", "/user/registry",
						"/link/search", "/link/searcher")
				.permitAll().antMatchers("/user/register").permitAll().anyRequest().authenticated().and().formLogin()
				.loginProcessingUrl("/signin").loginPage("/login").permitAll().defaultSuccessUrl("/home")
				.usernameParameter("username").passwordParameter("password").and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler()).and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll().deleteCookies("JSESSIONID").and().rememberMe().tokenValiditySeconds(3600000).key("secret")
				.rememberMeParameter("checkRememberMe");
	}
}
