package lolguide.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lolguide.constants.ConfigConstants;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public WebSecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder bcyBCryptPasswordEncoder) {
		
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bcyBCryptPasswordEncoder;
		
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(this.userDetailsService).passwordEncoder(this.bCryptPasswordEncoder);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage(ConfigConstants.FormLoginPage)
			.failureUrl(ConfigConstants.AuthEntryPoint)
			.and()
			.rememberMe()
			.rememberMeParameter(ConfigConstants.RememberMeParameter)
			.rememberMeCookieName(ConfigConstants.CookieName)
			.key(ConfigConstants.RememberMeKey)
			.tokenValiditySeconds(ConfigConstants.TokenValidation)
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher(ConfigConstants.FormLogoutPage)).logoutSuccessUrl(ConfigConstants.LogoutUrl)
            .and()
            .exceptionHandling().accessDeniedPage(ConfigConstants.AccessDeniedPage)
            .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(ConfigConstants.AuthEntryPoint))
            .and()
            .csrf().disable();
			
		
	}
	
}
