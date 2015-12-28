package th.ac.kmutt.dsd.train.dao;

import java.io.File;

import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;

public interface TrainHistoryDAO extends BaseDAO<TrainHistory>{

	  public void uploadSingleFile(File file) throws Exception;
	  public void uploadMultipleFile(File file[]) throws Exception;
	  
	  
}
