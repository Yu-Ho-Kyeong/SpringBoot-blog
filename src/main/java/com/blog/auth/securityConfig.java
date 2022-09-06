package com.blog.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration            // 이 클래스를 스프링 설정 빈으로 등록
@EnableWebSecurity         // 이 클래스에 스프링 시큐리티 기능 활성화
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class securityConfig extends WebSecurityConfigurerAdapter {
	
	// 인가, 로그인 설정, 로그아웃 설정, 접근거부 처리, 자동로그인

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 인가 
		// authorizeRequests()          : URL 요청에 대한  접근권한 설정
		// permitAll()               : 모두에게 접근 허용
		// hasRole(), hasAnyRoles()      : 특정권한을 가진 사용자만 허용
		http.authorizeRequests()
		    .antMatchers("/").permitAll()
		    .antMatchers("/css/**", "/js/**", "/img/**", "/bootstrap/**").permitAll()
		    .antMatchers("/admin/**").hasRole("ADMIN");
		
		// 로그인 설정
	    // - default 로그인 화면 URL       : /login
	    // - custom  로그인 화면 URL        : /auth/loginForm
	    // - custom  로그인 처리 URL       : /auth/login
	    // - custom  로그인 에러 화면 URL   : /auth/loginError
	    http.formLogin()
	        .loginPage("/login")
	        .loginProcessingUrl("/login")
	        .failureUrl("/login?error")
	        .usernameParameter("username")
	        .passwordParameter("password")
//	              .successHandler( createAuthenticationSuccessHandler() )
	        .permitAll();
	    
	    // 로그아웃 설정
	    // - default 로그아웃 화면 URL        : /logout
	    // - custom  로그아웃 요청 URL        : /auth/logout
	    // - default 로그아웃 성공 후 이동 URL  : /[로그인경로]
	    // - custom  로그아웃 성공 후 이동 RUL  : /
	    // - invalidateHttpSession(true) : 로그아웃 시, 세션을 유효하지 않도록 지정 ( 세션에 등록된 로그인 정보를 삭제하시 위해서 )
	    http.logout()
	        .logoutUrl("/auth/logout")
	        .logoutSuccessUrl("/")
	        .invalidateHttpSession(true)
//	        .addLogoutHandler(createLogoutSuccessHandler())
	        .permitAll()
	        ;
	    // 자동로그인 설정
	    http.rememberMe()
	        .key("blog")
//	        .tokenRepository( createJDBCRepository() )
	        .tokenValiditySeconds( 60*60*24*30 );        // 30일
	      
	    http.headers().frameOptions().sameOrigin();
	      
	    http.csrf();//.disable();
	    
	}
	
	
}
