package com.demo.products.config;

import feign.RequestInterceptor;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class ClientConfiguration {

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			KeycloakSecurityContext context = getKeycloakSecurityContext();
			requestTemplate.header("Authorization", "Bearer " + context.getTokenString());
		};
	}

	protected KeycloakSecurityContext getKeycloakSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KeycloakAuthenticationToken token;
        KeycloakSecurityContext context;



        if (authentication == null) {

            throw new IllegalStateException("Não é possível definir o cabeçalho de autorização porque não há principal autenticado");
        }

        if (!KeycloakAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {

            throw new IllegalStateException(
                    String.format(
                            "\n" +
                                    "Não é possível definir o cabeçalho de autorização porque a autenticação é do tipo %s mas %s é necessario",
                            authentication.getClass(), KeycloakAuthenticationToken.class)
            );
        }

        token = (KeycloakAuthenticationToken) authentication;
        context = token.getAccount().getKeycloakSecurityContext();

        return context;
    }

}
