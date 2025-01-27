/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.oauth2.server.authorization.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.Version;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * An {@link Authentication} implementation used for the OAuth 2.0 Resource Owner Password Credentials Grant.
 * It is no longer recommended
 *
 * @author luamas
 * @since 0.0.1
 * @see AbstractAuthenticationToken
 * @see OAuth2ClientCredentialsAuthenticationProvider
 * @see OAuth2ClientAuthenticationToken
 */
@Deprecated
public class OAuth2OwnerPasswordCredentialsAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = Version.SERIAL_VERSION_UID;
	private final Authentication clientPrincipal;
	private final Set<String> scopes;
	private final Authentication userPrincipal;

	/**
	 * Constructs an {@code OAuth2ClientCredentialsAuthenticationToken} using the provided parameters.
	 *  @param clientPrincipal the authenticated client principal
	 * @param userPrincipal
	 */
	public OAuth2OwnerPasswordCredentialsAuthenticationToken(Authentication clientPrincipal, Authentication userPrincipal) {
		this(clientPrincipal, Collections.emptySet(), userPrincipal);
	}

	/**
	 * Constructs an {@code OAuth2ClientCredentialsAuthenticationToken} using the provided parameters.
	 *  @param clientPrincipal the authenticated client principal
	 * @param scopes the requested scope(s)
	 * @param userPrincipal
	 */
	public OAuth2OwnerPasswordCredentialsAuthenticationToken(Authentication clientPrincipal, Set<String> scopes, Authentication userPrincipal) {
		super(Collections.emptyList());
		this.userPrincipal = userPrincipal;
		Assert.notNull(clientPrincipal, "clientPrincipal cannot be null");
		Assert.notNull(scopes, "scopes cannot be null");
		this.clientPrincipal = clientPrincipal;
		this.scopes = Collections.unmodifiableSet(new LinkedHashSet<>(scopes));
	}

	@Override
	public Object getPrincipal() {
		return this.userPrincipal;
	}

	@Override
	public Object getCredentials() {
		return "";
	}

	/**
	 * Returns the requested ClientPrincipal.
	 *
	 * @return
	 */
	public Authentication getClientPrincipal() {
		return this.clientPrincipal;
	}

	/**
	 * Returns the requested scope(s).
	 *
	 * @return the requested scope(s), or an empty {@code Set} if not available
	 */
	public Set<String> getScopes() {
		return this.scopes;
	}
}
