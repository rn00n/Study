package me.rn00n.server.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppAccessDecisionManager appAccessDecisionManager;

    @Autowired
    private AppUserDetailsService appUserDetailService;


    /**
     * refresh_token 사용 하기 위해서는 이걸 해야함
     * 설정 파일에서 authorizationServer 설정 시 이놈을 추가 해줘야 함
     * 아니면 재구현 할려면 자동 설정을 사용 할 수 없음
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( appUserDetailService );
    }

    @Override
    public void init(WebSecurity web) throws Exception {
        super.init(web);
        // 권한 체크에서 제외
        web.ignoring().antMatchers("/favicon.ico", "/error/**"
                , "/css/**", "/img/**", "/js/**", "/libs/**", "/upload/**"
                , "/api/getLanData/*", "/api/app/registered", "/api/v2/app/registered"
                , "/api/app/socialLogin", "/api/v2/app/socialLogin", "/api/app/forget/*", "/api/v2/app/forget/*"
                , "/api/getStore/*", "/api/getTerms/*/*", "/api/order", "/api/innopay", "/pay/receive", "/api/pay/approval"
                , "/api/app/mpay", "/api/app/rpay", "/api/app/cpay"
                , "/api/getOrderStatus/*/*/*/*/*", "/api/cancelOrder/*/*/*/*/*"
                , "/biz/send", "/api/app/chkEmail");
    }

    /**
     * HttpSecurity 설정
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 로그인 설정
        http.formLogin()
                .loginPage("/")						// 로그인 주소
                .usernameParameter("accountId")		// 로그인 ID
                .passwordParameter("accountPw")		// 로그인 PW
                .loginProcessingUrl("/login")		// 로그인 접속 주소
                .defaultSuccessUrl("/main")			// 로그인 성공 주소
                .failureUrl("/?failure=true");		// 로그인 실패 주소

        // 로그아웃 설정
        http.logout()
                .logoutUrl("/logout")				// 로그아웃 주소
                .logoutSuccessUrl("/")				// 로그아웃 성공 시 로그인 페이지로 이동
                .deleteCookies("SESSION")			// 로그아웃 시 Cookies 데이타 삭제
                .invalidateHttpSession(true);		// 로그아웃 시 Session 데이타 삭제

        // Session 설정
        http.sessionManagement()
                .sessionFixation().newSession()			// 로그인 성공 시 새로운 Session 생성
                .invalidSessionUrl("/error/timeout")	// timeout 시 이동 주소
                .maximumSessions(5)						// 동시 로그인 수 5로 설정
                .maxSessionsPreventsLogin(false)		// 접속 수 초과 시 새로 로그인 인증 할 수 있게 함
                .expiredUrl("/error/timeout");

        // 접속 권한 설정
        http.antMatcher("/**")
                .authorizeRequests()
                .accessDecisionManager(appAccessDecisionManager)	// 권한 인증 처리 객체
                .anyRequest().authenticated()						// 모든 접속 권한 체크
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint()); // 권한 인증 실패 시 처리 부분

        http.cors();

        // Iframe 허용
        http.headers().frameOptions().sameOrigin();
    }
}