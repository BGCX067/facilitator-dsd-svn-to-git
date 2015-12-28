package th.ac.kmutt.dsd.train.service;

import th.ac.kmutt.dsd.train.pojo.db.UserProfile;

public interface UserProfileService {
	
	public UserProfile checkUserProfile(String username, String password) throws Exception;
	
}