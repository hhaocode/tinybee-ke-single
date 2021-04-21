package cn.tinybee.ke.portal.core.security.spring;

import cn.tinybee.ke.portal.core.security.spring.filter.JwtAuthenticationTokenFilter;
import cn.tinybee.ke.portal.core.security.spring.handler.ArcusAccessDeniedHandler;
import cn.tinybee.ke.portal.core.security.spring.handler.ArcusAuthenticationFailureHandler;
import cn.tinybee.ke.portal.core.security.spring.handler.ArcusAuthenticationSuccessHandler;
import cn.tinybee.ke.portal.core.security.spring.handler.ArcusLogoutSuccessHandler;
import cn.tinybee.ke.portal.core.security.spring.provider.ArcusAuthenticationProvider;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusAuthenticationDetailsSource;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusAuthenticationEntryPoint;
import cn.tinybee.ke.portal.core.security.spring.support.ArcusUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * spring security 配置
 * @description 
 * @author hao.huang
 * @date 2019年11月19日
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private ArcusAuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private ArcusAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private ArcusLogoutSuccessHandler logoutSuccessHandler;
	
	@Autowired
	private ArcusAccessDeniedHandler accessDeniedHandler;
	@Autowired
	private ArcusUserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	@Autowired
	private ArcusAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private ArcusAuthenticationProvider authenticationProvider;
	@Autowired
	private ArcusAuthenticationDetailsSource authenticationDetailsSource;

	@Value("${spring.security.permits}")
	private String[] permitAll;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("正在配置HttpSecuriry=== > ");
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.cors()
		.and()
			.httpBasic().authenticationEntryPoint(authenticationEntryPoint)
		.and()
		//配置登录吗
//		.addFilterBefore(filter, beforeFilter)
		.authorizeRequests()
				//设置不需要认证的路径
		.antMatchers(permitAll).permitAll()//.anonymous()
		.and()
			.authorizeRequests().anyRequest()//.access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证
			.authenticated()
			.and()
			.formLogin()
			.loginProcessingUrl("/api/auth/login")

			.authenticationDetailsSource(authenticationDetailsSource)
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)

			.permitAll()
		.and()
			.logout()
			.logoutUrl("/api/auth/logout")
			.logoutSuccessHandler(logoutSuccessHandler)
			.permitAll()
		.and()
			.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint)
			;
		//自定义过滤器
		http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
	}


	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);

	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addExposedHeader("Authorization");
		return corsConfiguration;
	}
}
