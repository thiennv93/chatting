package org.synapse.chatting.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
@RequiredArgsConstructor
public class OAuth2JwtResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
//            "/v2/api-docs",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/swagger-resources/configuration/ui",
//            "/swagger-resources/configuration/security",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/webjars/**"
            // other public endpoints of your API may be appended to this array
    };
    @Value("${security.oauth2.resource.whitelist}")
    private String[] whiteList;
    private final ResourceServerProperties resource;
    private final JwtAccessTokenConverter jwtTokenEnhancer;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(whiteList).permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().csrf().disable();
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.resourceId(resource.getResourceId())
                .tokenServices(jwtTokenServices());
    }

    @Bean
    public DefaultTokenServices jwtTokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(jwtTokenStore());
        return services;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer);
    }
}
