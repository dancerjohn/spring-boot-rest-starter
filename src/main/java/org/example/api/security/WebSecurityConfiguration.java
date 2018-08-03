package org.example.api.security;

import org.example.model.user.Permission;
import org.example.model.user.Role;
import org.example.model.user.User;
import org.example.service.MyUserDetailsService;
import org.libex.additions.rest.role.spring.RolePermissionFilter;
import org.libex.additions.rest.role.spring.SpringRoleUtils;
import org.libex.additions.rest.role.spring.SpringRoleUtils.PermissionConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import static org.example.api.security.MethodSecurityConfiguration.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.addFilterBefore(rolePermissionFilter(), FilterSecurityInterceptor.class)
				.authorizeRequests()
				.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
						"/configuration/**",
						"/swagger-ui.html", "/webjars/**")
				.permitAll()
				.antMatchers("/")
				.permitAll()
				.antMatchers("/something")
				.hasAuthority(Permission.ReadRecord.name())
				.antMatchers("/someAdmin")
				.hasAuthority(Permission.CreateRecord.name())
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.permitAll()
				.and()
				.logout()
				.permitAll();
	}

	@Bean
	public RolePermissionFilter rolePermissionFilter() {
		return new RolePermissionFilter<>(
				(k, v) -> SpringRoleUtils.getUserDetailsOf(User.class),
				user -> SpringRoleUtils.getRolesAndPermissionsAsGrantedAuthorities(user,
						PermissionConverter.<Permission, Role>builder()
								.permissionToStringFunction(Permission::name)
								.prefix("")
								.prefix(PERMISSION_PREFIX)
								.build()));
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());
		// should have password encryptor
	}
}
