package org.example.api.security;

import org.libex.additions.rest.role.spring.PermissionMethodSecurityConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
@Import(PermissionMethodSecurityConfiguration.class)
public class MethodSecurityConfiguration {
}
