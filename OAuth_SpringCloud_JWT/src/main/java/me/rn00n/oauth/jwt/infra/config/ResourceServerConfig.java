//package me.rn00n.oauth.jwt.infra.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .anonymous()
//                    .and()
//                .authorizeRequests()
//                    .antMatchers(HttpMethod.GET, "/api/**").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .exceptionHandling()
//                    .accessDeniedHandler(new OAuth2AccessDeniedHandler())
//        ;
//    }
//}
