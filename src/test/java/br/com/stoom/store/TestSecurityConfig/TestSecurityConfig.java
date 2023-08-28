package br.com.stoom.store.TestSecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

@Configuration
public class TestSecurityConfig {

    @Bean
    public WithSecurityContextFactory<WithMockCustomUser> withMockCustomUserFactory() {
        return new WithMockCustomUserSecurityContextFactory();
    }

    static class WithMockCustomUserSecurityContextFactory
            implements WithSecurityContextFactory<WithMockCustomUser> {

        @Override
        public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
            UserDetails principal = User.builder()
                    .username(customUser.username())
                    .password(customUser.password())
                    .roles(customUser.roles())
                    .build();
            
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities()));
            
            return context;
        }
    }
}
