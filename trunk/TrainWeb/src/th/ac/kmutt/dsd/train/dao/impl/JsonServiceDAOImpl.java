package th.ac.kmutt.dsd.train.dao.impl;

import java.util.List;
import java.util.Map;

import th.ac.kmutt.dsd.train.dao.JsonServiceDAO;
import th.ac.kmutt.dsd.train.dao.SearchCondition;
import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;

public class JsonServiceDAOImpl implements JsonServiceDAO{

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

}
