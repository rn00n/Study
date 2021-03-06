package me.rn00n.server.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AppAccessDecisionManager appAccessDecisionManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 접속 권한 설정
        http.antMatcher("/api/**")
                .authorizeRequests()
                .accessDecisionManager(appAccessDecisionManager)
                .anyRequest().authenticated();
    }

}
