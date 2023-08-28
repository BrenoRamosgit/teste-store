package br.com.stoom.store.TestSecurityConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

import br.com.stoom.store.TestSecurityConfig.TestSecurityConfig.WithMockCustomUserSecurityContextFactory;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    String username() default "user";

    String password() default "password";

    String[] roles() default {"USER"};
}