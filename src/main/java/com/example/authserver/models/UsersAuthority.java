package com.example.authserver.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the users_authorities database table.
 * 
 */
@Entity
@Table(name="users_authorities")
public class UsersAuthority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="AUTHORITY_ID")
	private String authorityId;

	@Id
	@Column(name="USER_ID")
	private String userId;

	public UsersAuthority() {
	}

	public String getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}