package org.example.service;

import org.eclipse.collections.impl.factory.Lists;
import org.example.model.user.Role;
import org.example.model.user.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		if (s.equals("admin")) {
			return new User("admin", "adminpass", Lists.immutable.of(Role.Admin));
		}
		else if (s.equals("user")) {
			return new User("user", "userpass", Lists.immutable.of(Role.User));
		}
		return null;
	}
}
