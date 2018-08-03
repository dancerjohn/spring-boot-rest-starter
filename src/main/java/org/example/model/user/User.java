package org.example.model.user;

import java.util.Collection;
import java.util.Collections;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.eclipse.collections.api.list.ImmutableList;
import org.libex.additions.rest.role.HasRoles;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@RequiredArgsConstructor
public class User implements HasRoles<Role>, UserDetails {

	private final String username, password;
	private final ImmutableList<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.EMPTY_LIST;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
