package com.example.authserver.models;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


/**
 * The persistent class for the user_ database table.
 * 
 */
@Entity
@Table(name="user_")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="ACCOUNT_EXPIRED")
	private boolean accountExpired;

	@Column(name="ACCOUNT_LOCKED")
	private boolean accountLocked;

	@Column(name="CREDENTIALS_EXPIRED")
	private boolean credentialsExpired;

	private boolean enabled;

	private String password;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-many association to Authority
	@ManyToMany
	@JoinTable(
		name="users_authorities"
		, joinColumns={
			@JoinColumn(name="USER_ID", referencedColumnName="ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="AUTHORITY_ID", referencedColumnName="ID")
			}
		)
	private List<Authority> authorities;

	public UserEntity() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getAccountExpired() {
		return this.accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean getAccountLocked() {
		return this.accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean getCredentialsExpired() {
		return this.credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}