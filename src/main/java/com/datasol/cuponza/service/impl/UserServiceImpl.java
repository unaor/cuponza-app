package com.datasol.cuponza.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.datasol.cuponza.dao.UserDao;
import com.datasol.cuponza.exception.DaoException;
import com.datasol.cuponza.exception.ServiceException;
import com.datasol.cuponza.exception.UserAlreadyExistsException;
import com.datasol.cuponza.model.Authority;
import com.datasol.cuponza.model.User;
import com.datasol.cuponza.service.UserService;
import com.datasol.cuponza.util.AuthProvider;

@Component("cuponZaUserService")
@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final String CUPONZA_FROM_EMAIL = "registrar@cuponza.com.co";
	private static final String CUPONZA_SUBJECT_EMAIL = "verifique tu email y sea parte de Cuponza";

	@Autowired
	UserDao userDao;
	@Autowired
	private JavaMailSender mailSender;

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	@Override
	public User getUserByEmail(String email) throws ServiceException {
		try {
			return userDao.getUserByEmail(email);
		} catch (DaoException e) {
			log.error("error getting user with email " + email + " stack: " + e);
			throw new ServiceException("error getting user with email " + email);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void insertUser(User user) throws ServiceException, UserAlreadyExistsException {
		log.debug("Starting user registration");
		try {
			if(userDao.getUserByEmail(user.getEmail())!=null){
				log.debug("user already registered email: "+user.getEmail());
				throw new UserAlreadyExistsException("this email is already registered");
			}
		} catch (DaoException e1) {
			log.error("Database error while retreiving user with email "+user.getEmail());
			throw new ServiceException("error saving user");
		}
		user.setEnabled(false);
		user.setUuid(UUID.randomUUID().toString());
		user.setRegistrationDate(new Date());
		user.setAuthProvider(AuthProvider.CUPONZA.name());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			Authority authority = userDao
					.getAuthorityByName(Authority.NORMAL_USER_ROLE);
			user.setAuthority(authority);
			userDao.insertUser(user);
			log.debug("completed user registration");
			sendConfirmationEmail(user);
		} catch (DaoException e) {
			log.error("error registering user with email " + user.getEmail());
			throw new ServiceException("error saving user with email "
					+ user.getEmail());
		}

	}

	private void sendConfirmationEmail(final User user) {
		mailSender.send(new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws ServiceException {
				MimeMessageHelper message;
				try {
					message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom(CUPONZA_FROM_EMAIL);
					message.setTo(user.getEmail());
					message.setSubject(CUPONZA_SUBJECT_EMAIL);
					// message.setText("my text <img src='cid:myLogo'>", true);
					// message.addInline("myLogo", new
					// ClassPathResource("img/mylogo.gif"));
					// message.addAttachment("myDocument.pdf", new
					// ClassPathResource("doc/myDocument.pdf"));
					message.setText("","<a href=\"http://localhost:8082/cuponza/user/activate?uuid="+ user.getUuid()+"&email="+user.getEmail()+"\">Haz clic aqui para activar tu cuenta</a>");
				} catch (MessagingException e) {
					log.error("error sending confirmation email "+e);
					throw new ServiceException("Error sending confirmation email");
				}
				log.debug("sent confirmation email to user " + user.getEmail());
			}
		});
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void activateUser(String email, String uuid) throws ServiceException {
		User user = getUserByEmail(email);
		if(user==null){
			log.debug("no user found when activating user "+email);
			throw new ServiceException("no user found when activating user");
		}
		if(user.getUuid().equals(uuid)){
			user.setEnabled(true);
			user.setLastLoginDate(new Date());
			try {
				userDao.insertUser(user);
				//now the user is persisted lets create him a session
				authenticateUser(user);
			} catch (DaoException e) {
				log.error("DB error when updating user to active state "+email);
				log.error(e);
				throw new ServiceException("error activating user");
			}
			log.debug("activated user "+email);
		}else{
			log.debug("the uuid of the users does not match "+email);
			throw new ServiceException("uuid do not match");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		try {
			return (UserDetails)getUserByEmail(username);
		} catch (ServiceException e) {
			log.debug("no user was found with this username "+username);
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false, rollbackFor = Exception.class)
	public void authenticateSocialUser(User user) throws ServiceException {
		log.debug("Starting user registration via social provider: "+user.getAuthProvider());
		User dbUser = getUserByEmail(user.getEmail());
		if (dbUser!=null){
			//returning user
			dbUser.setLastLoginDate(new Date());
			try {
				userDao.insertUser(dbUser);
				authenticateUser(user);
				log.debug("returning social user:"+user.getEmail());
			} catch (DaoException e) {
				log.error("error social registering user with email " + user.getEmail());
				throw new ServiceException("error social registering user");
			}
		}else{
			user.setEnabled(true);
			user.setRegistrationDate(new Date());
			Authority authority;
			try {
				authority = userDao
						.getAuthorityByName(Authority.NORMAL_USER_ROLE);
				user.setAuthority(authority);
				userDao.insertUser(user);
				log.debug("completed user registration");
			} catch (DaoException e) {
				log.error("error social registering user with email " + user.getEmail());
				throw new ServiceException("error social registering user");
			}
			//TODO: implement welcome email method
			sendConfirmationEmail(user);
			authenticateUser(user);
		}
		
	}
	
	private void authenticateUser(User user){
		Authentication auth = new UsernamePasswordAuthenticationToken (user.getUsername (),user.getPassword (),user.getAuthorities ());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
