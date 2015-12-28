package th.ac.kmutt.dsd.train.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import th.ac.kmutt.dsd.train.dao.impl.UserProfileDAOImpl;
import th.ac.kmutt.dsd.train.pojo.db.UserProfile;
import th.ac.kmutt.dsd.train.service.UserProfileService;


public class UserProfileServiceImpl implements UserProfileService{
	
	private static Logger log = LogManager.getLogger(UserProfileServiceImpl.class);
	
	@Override
	public UserProfile checkUserProfile(String username, String password)
			throws Exception {
	
		UserProfileDAOImpl dao = new UserProfileDAOImpl();
		
		
		UserProfile userProfile = dao.checkAuthenticationDB(username, password);
		
		return userProfile;
		
	}

}
