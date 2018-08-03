package org.example.model.user;

import javax.annotation.Nonnull;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role implements org.libex.additions.rest.role.Role<Permission> {
	Admin(Lists.immutable.of(Permission.values())),
	User(Lists.immutable.of(Permission.ReadRecord));

	private final ImmutableList<Permission> permissions;

	@Nonnull
	@Override
	public Iterable<Permission> getPermissions() {
		return permissions;
	}
}
