package th.ac.kmutt.dsd.train.dao;

import java.io.File;
import java.util.List;
import java.util.Map;

import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;

public interface TrainHistoryDAO extends BaseDAO<TrainHistory>{

	  public void uploadSingleFile(File file) throws Exception;
	  public void uploadMultipleFile(File file[]) throws Exception;
	  public int addTranData(TrainHistory trn) throws Exception;
	  public List<TrainHistory> getListTrainHistory(String studenId)throws Exception;
	  public  List<String> getListStudentId()throws Exception;
	  public  List<String> getListImage(String studenId)throws Exception;
	  public  boolean deleteStudent(String studenId)throws Exception;
	  public  boolean deleteStudentImage(String imageUrl)throws Exception;
	  public  boolean updateStudentImage(String imageUrl)throws Exception;
	  public  int chkStudentId(String studenId)throws Exception;
}
