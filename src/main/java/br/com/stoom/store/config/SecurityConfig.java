package br.com.stoom.store.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/**")
            .hasAnyRole("ROLE_ADMIN", "ROLE_USER", "ADMIN_TEST")
            .and()
            .csrf().disable()
            .httpBasic(); 
    }
}
