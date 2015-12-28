package th.ac.kmutt.dsd.train.dao;

import th.ac.kmutt.dsd.train.pojo.db.UserProfile;

public interface UserProfileDAO extends BaseDAO<UserProfile>{

	public UserProfile checkAuthenticationDB(String username,String password)throws Exception;

}
