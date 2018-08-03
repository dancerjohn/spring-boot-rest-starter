package org.example.api.security;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true,
		securedEnabled = true,
		jsr250Enabled = true)
public class MethodSecurityConfiguration
		extends GlobalMethodSecurityConfiguration {
	public static final String PERMISSION_PREFIX = "PERMISSION_";

	@Override
	protected AccessDecisionManager accessDecisionManager() {
		AffirmativeBased result = (AffirmativeBased) super.accessDecisionManager();
		List<AccessDecisionVoter<? extends Object>> decisionVoters = result.getDecisionVoters();

		RoleVoter permissionVoter = new RoleVoter();
		permissionVoter.setRolePrefix(PERMISSION_PREFIX);

		decisionVoters.add(permissionVoter);

		return result;
	}
}
