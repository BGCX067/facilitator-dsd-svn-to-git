package th.ac.kmutt.dsd.train.sqlmap;


import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private static final SqlSessionFactory factory;

	static {

		try {

			String resource = "th/ac/kmutt/dsd/sqlmap/Configuration.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			factory = builder.build(reader);

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException(
					"Fatal Error initializing SqlMapConfig class. Cause: " + e);
		}

	}

	public static SqlSession getSqlSession(boolean autoCommit) {
		return factory.openSession(autoCommit);
	}

}
