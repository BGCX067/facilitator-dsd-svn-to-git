package th.ac.kmutt.dsd.train.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import th.ac.kmutt.dsd.train.dao.SearchCondition;
import th.ac.kmutt.dsd.train.dao.TrainHistoryDAO;
import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;
import th.ac.kmutt.dsd.train.pojo.db.UserProfile;
import th.ac.kmutt.dsd.train.sqlmap.SqlMapConfig;

public class TrainHistoryDAOImpl implements TrainHistoryDAO {

	private static Logger log = LogManager.getLogger(TrainHistoryDAOImpl.class);

	@Override
	public void uploadSingleFile(File file) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadMultipleFile(File[] file) throws Exception {
		// TODO Auto-generated method stub

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
	public List<TrainHistory> list(List<SearchCondition> conditions,
			int pageNo, int pageSize, String orderByClause) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count(List<SearchCondition> conditions) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TrainHistory getByPK(Object id) throws Exception {
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

	@Override
	public int addTranData(TrainHistory trn) throws Exception {

		SqlSession session = SqlMapConfig.getSqlSession(false);
		int init = 0;
		try {

			// Run command ----------------------------------------------------

			init = session.insert("TrainHistory.create", trn);

			session.commit();

		} catch (Exception e) {

			session.rollback();

			throw e;

		} finally {

			session.clearCache();
			session.close();
			session = null;

		}

		return init;
	}

	@Override
	public List<TrainHistory> getListTrainHistory(String studenId)
			throws Exception {

		SqlSession session = SqlMapConfig.getSqlSession(false);

		// Set parameters ------------------------------------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("studenId", studenId);

		try {

			// Run command -----------------------------------------------------
			return session.selectList("TrainHistory.listTrainHistory", map);

		} catch (Exception e) {

			throw e;

		} finally {

			session.rollback();
			session.clearCache();
			session.close();
			session = null;

		}
	}

	@Override
	public List<String> getListStudentId() throws Exception {

		SqlSession session = SqlMapConfig.getSqlSession(false);

		try {

			// Run command -----------------------------------------------------
			List result = null;
			result = session.selectList("TrainHistory.listStudentId");
			return result;

		} catch (Exception e) {

			throw e;

		} finally {

			session.rollback();
			session.clearCache();
			session.close();
			session = null;

		}
	}

	@Override
	public List<String> getListImage(String studentId) throws Exception {
		SqlSession session = SqlMapConfig.getSqlSession(false);

		try {

			// Run command -----------------------------------------------------
			List<String> result = null;
			result = session.selectList("TrainHistory.listImage", studentId);
			System.out.println(result);
			return result;

		} catch (Exception e) {

			throw e;

		} finally {

			session.rollback();
			session.clearCache();
			session.close();
			session = null;

		}
	}

	@Override
	public boolean deleteStudent(String studenId) throws Exception {
		SqlSession session = SqlMapConfig.getSqlSession(false);
		int result = 0;
		try {
			// Run command -----------------------------------------------------
			result = session.update("TrainHistory.delectStudent", studenId);
			return result <= 0;
		} catch (Exception e) {

			throw e;
		} finally {
			session.rollback();
			session.clearCache();
			session.close();
			session = null;
		}

	}

	@Override
	public int chkStudentId(String studentId) throws Exception {
		SqlSession session = SqlMapConfig.getSqlSession(false);
		int result = 0;
		try {
			// Run command -----------------------------------------------------
			result = session.selectOne("TrainHistory.chkStudentId", studentId);
//			System.out.print("result >>>" + result);
			return result;

		} catch (Exception e) {
			System.out.print("error >>>" + e.getMessage());
			throw e;
		} finally {
			session.rollback();
			session.clearCache();
			session.close();
			session = null;
		}
	}

	public static void main(String[] args) throws Exception {
		TrainHistoryDAOImpl trainhis = new TrainHistoryDAOImpl();
		System.out.println(">>>>>> " + trainhis.chkStudentId("KMUTT-55441346"));
		// trainhis.chkStudentId("KMUTT-599999943");
	}

	@Override
	public boolean deleteStudentImage(String fileImage) throws Exception {
		SqlSession session = SqlMapConfig.getSqlSession(false);
		int result = 0;
		try {
			// Run command -----------------------------------------------------
			result = session.update("TrainHistory.delectStudentImages", fileImage);
			return result <= 0;
		} catch (Exception e) {

			throw e;
		} finally {
			session.rollback();
			session.clearCache();
			session.close();
			session = null;
		}
	}
	
	@Override
	public boolean updateStudentImage(String fileImage) throws Exception {
		SqlSession session = SqlMapConfig.getSqlSession(false);
		int result = 0;
		try {
			// Run command -----------------------------------------------------
			result = session.update("TrainHistory.updateStudentImages", fileImage);
			return result <= 0;
		} catch (Exception e) {

			throw e;
		} finally {
			session.rollback();
			session.clearCache();
			session.close();
			session = null;
		}
	}
}
