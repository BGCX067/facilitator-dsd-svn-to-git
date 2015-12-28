package th.ac.kmutt.dsd.train.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sun.org.apache.xpath.internal.axes.HasPositionalPredChecker;

import th.ac.kmutt.dsd.train.dao.UserProfileDAO;
import th.ac.kmutt.dsd.train.dao.SearchCondition;
import th.ac.kmutt.dsd.train.pojo.db.UserProfile;
import th.ac.kmutt.dsd.train.service.impl.UserProfileServiceImpl;
import th.ac.kmutt.dsd.train.sqlmap.SqlMapConfig;

public class UserProfileDAOImpl implements UserProfileDAO{

	private static Logger log = LogManager.getLogger(UserProfileDAOImpl.class);
	
	@Override
	public UserProfile checkAuthenticationDB(String username, String password)
			throws Exception {
		
		SqlSession session = SqlMapConfig.getSqlSession(false);
		
		try {

			// Run command ----------------------------------------------------
			Map<String,Object> map = new HashMap<String, Object>();

			map.put("username", username);
			map.put("password", password);
			
			UserProfile userProfile =  session.selectOne("UserProfile.getUserProfile", map);

			session.commit();

			return userProfile;

		} catch (Exception e) {

			session.rollback();

			throw e;

		} finally {

			session.clearCache();
			session.close();
			session = null;

		}
	}
	
	

	@Override
	public long create(Object o) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Object o) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Object id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserProfile> list(List<SearchCondition> conditions, int pageNo,
			int pageSize, String orderByClause) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(List<SearchCondition> conditions) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserProfile getByPK(Object id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> listForWeb(
			List<SearchCondition> conditions, int pageNo, int pageSize,
			String orderByClause) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countListForWeb(List<SearchCondition> conditions)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
