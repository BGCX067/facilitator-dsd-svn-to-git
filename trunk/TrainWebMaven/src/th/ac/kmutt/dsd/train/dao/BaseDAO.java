package th.ac.kmutt.dsd.train.dao;

import java.util.List;
import java.util.Map;

public interface  BaseDAO<T> {

	public long create(Object o) throws Exception;

	public int update(Object o) throws Exception;

	public boolean delete(Object id) throws Exception;

	public List<T> list(List<SearchCondition> conditions, int pageNo, int pageSize, String orderByClause) throws Exception;

	public long count(List<SearchCondition> conditions) throws Exception;

	public T getByPK(Object id) throws Exception;

	public List<Map<String, Object>> listForWeb(List<SearchCondition> conditions, int pageNo, int pageSize, String orderByClause) throws Exception;

	public long countListForWeb(List<SearchCondition> conditions) throws Exception;

}

