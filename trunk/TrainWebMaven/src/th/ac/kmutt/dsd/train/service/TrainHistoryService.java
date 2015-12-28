package th.ac.kmutt.dsd.train.service;

import java.util.List;
import java.util.Map;

import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;
import th.ac.kmutt.dsd.train.pojo.db.TrainObject;
  

public interface TrainHistoryService {

	  public int addTrainData(String sutdentId, String path_url, String base_url, String group) throws Exception;
	  public void parseAndSend(TrainObject obj) throws Exception;
	  public List<TrainHistory> getListTrainHistory(String studenId)throws Exception;
	  public List<String> getListStudentId()throws Exception;
	  public List<String> getListImage(String studenId)throws Exception;
	  public boolean deleteStudent(String studenId)throws Exception;
	  public int chkStudentId(String studenId)throws Exception;
	  public boolean deleteStudentImage(String fileImage)throws Exception;
	  public boolean updateStudentImage(String fileImage)throws Exception;
}
