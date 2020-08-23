package com.example.authserver.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oauth_client_details database table.
 * 
 */
@Entity
@Table(name="oauth_client_details")
public class OauthClientDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="ACCESS_TOKEN_VALIDITY")
	private String accessTokenValidity;

	private String authorities;

	@Column(name="AUTHORIZED_GRANT_TYPES")
	private String authorizedGrantTypes;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CLIENT_ID")
	private String clientId;

	@Column(name="CLIENT_SECRET")
	private String clientSecret;

	@Column(name="REFRESH_TOKEN_VALIDITY")
	private String refreshTokenValidity;

	@Column(name="RESOURCE_IDS")
	private String resourceIds;

	private String scope;

	public OauthClientDetail() {
	}

	public String getAccessTokenValidity() {
		return this.accessTokenValidity;
	}

	public void setAccessTokenValidity(String accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public String getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getAuthorizedGrantTypes() {
		return this.authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return this.clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getRefreshTokenValidity() {
		return this.refreshTokenValidity;
	}

	public void setRefreshTokenValidity(String refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getResourceIds() {
		return this.resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}