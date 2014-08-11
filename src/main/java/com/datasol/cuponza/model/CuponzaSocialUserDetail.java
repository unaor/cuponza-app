package com.datasol.cuponza.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

public class CuponzaSocialUserDetail extends SocialUser {


	private static final long serialVersionUID = 4276075264519188419L;
	
	private Long id;
	private String firstName;
	private String lastName;
	private Authority role;
	private SocialMediaService socialSignInProvider;

	public CuponzaSocialUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

	
	public static class Builder {
		 
        private Long id;
 
        private String username;
 
        private String firstName;
 
        private String lastName;
 
        private String password;
 
        private Authority role;
 
        private SocialMediaService socialSignInProvider;
 
        private Set<GrantedAuthority> authorities;
 
        public Builder() {
            this.authorities = new HashSet<>();
        }
 
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
 
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
 
        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
 
        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }
 
            this.password = password;
            return this;
        }
 
        public Builder role(Authority role) {
            this.role = role;
 
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);
 
            return this;
        }
 
        public Builder socialSignInProvider(SocialMediaService socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }
 
        public Builder username(String username) {
            this.username = username;
            return this;
        }
 
        public CuponzaSocialUserDetail build() {
        	CuponzaSocialUserDetail user = new CuponzaSocialUserDetail(username, password, authorities);
 
            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.socialSignInProvider = socialSignInProvider;
 
            return user;
        }
    }
	
	public static Builder getBuilder() {
        return new Builder();
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Authority getRole() {
		return role;
	}


	public void setRole(Authority role) {
		this.role = role;
	}


	public SocialMediaService getSocialSignInProvider() {
		return socialSignInProvider;
	}


	public void setSocialSignInProvider(SocialMediaService socialSignInProvider) {
		this.socialSignInProvider = socialSignInProvider;
	}
}
