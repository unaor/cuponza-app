package com.datasol.cuponza.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.model.CuponzaSocialUserDetail;
import com.datasol.cuponza.model.SocialMediaService;
import com.datasol.cuponza.model.User;
import com.datasol.cuponza.service.UserService;

public class CuponzaSocialUser implements SocialUserDetailsService {
	
	@Autowired
	UserService userService;
	
	private static final Logger log = Logger.getLogger(CuponzaSocialUser.class);

	@Override
	public SocialUserDetails loadUserByUserId(String username)
			throws UsernameNotFoundException, DataAccessException {
		User user;
		try {
			 user = userService.getUserByEmail(username);
			
		} catch (ServiceException e) {
			log.error("error getting social user "+e);
			//TODO: think of better error handling
			return null;
		}
		if (user==null){
			log.debug("no social user was found with this username");
			throw new UsernameNotFoundException("no social user found");
		}
		CuponzaSocialUserDetail principal = CuponzaSocialUserDetail.getBuilder().firstName(user.getFirstName())
                .id(user.getUserId())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getAuthority())
                .socialSignInProvider(SocialMediaService.FACEBOOK)
                .username(user.getEmail())
                .build();
		return principal;
	}

}
