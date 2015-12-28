package th.ac.kmutt.dsd.train.service.impl;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.json.JSONObject;

import th.ac.kmutt.dsd.helper.CommunicationHelper;
import th.ac.kmutt.dsd.train.dao.impl.TrainHistoryDAOImpl;
import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;
import th.ac.kmutt.dsd.train.pojo.db.TrainObject;
import th.ac.kmutt.dsd.train.service.TrainHistoryService;
import th.ac.kmutt.dsd.train.utility.DateUtil;

public class TrainHistoryServiceImpl implements TrainHistoryService{

	private static Logger log = LogManager.getLogger(TrainHistoryServiceImpl.class);

	@Override
	public int addTrainData(String studentId, String path_url, String base_url, String group) throws Exception {
		
		TrainHistory trn = new TrainHistory();
		
		trn.setStudenId(studentId);
		trn.setFileImageURL(path_url);
		trn.setLocationDisk(base_url);
		trn.setGroupId(group);
		trn.setStatus("N");
		trn.setCraeteDate(DateUtil.getCurrentTimestamp());
		trn.setUpdateDate(DateUtil.getCurrentTimestamp());
		
		TrainHistoryDAOImpl trnDAO = new TrainHistoryDAOImpl();
		
		return trnDAO.addTranData(trn);
	}

	
	@Override
	public void parseAndSend(TrainObject obj) throws Exception {
		
		ResourceBundle bundle = ResourceBundle.getBundle("applicationConfig");
		String train_url[] = ((String)bundle.getObject("SERV.PATH.TRAIN")).split(",");
		
		for(String url:train_url){
			if(!CommunicationHelper.checkAvailable(url)){
				JSONObject json = new JSONObject(obj);

				//String jsonResult = CommunicationHelper.get(url,json.toString(),"POST");
				//json = new JSONObject(jsonResult);
			}
		}
	}




	@Override
	public List<TrainHistory> getListTrainHistory(String studenId)
			throws Exception {
	
		TrainHistoryDAOImpl dao = new TrainHistoryDAOImpl();
		return dao.getListTrainHistory(studenId);
		
	}


	@Override
	public List<String> getListStudentId() throws Exception {
		TrainHistoryDAOImpl dao = new TrainHistoryDAOImpl();
		List<String> students =  dao.getListStudentId();
		return students;
	}


	@Override
	public List<String> getListImage(String studenId) throws Exception {
		TrainHistoryDAOImpl dao = new TrainHistoryDAOImpl();
		List<String> images =  dao.getListImage(studenId);
		return images;
	}
	
	


	@Override
	public boolean deleteStudent(String studenId) throws Exception {
		TrainHistoryDAOImpl dao = new TrainHistoryDAOImpl();
		return dao.deleteStudent(studenId);
		//return false;
		
	}
//	public static void main(String[] args) throws Exception {
//		TrainHistoryServiceImpl a = new TrainHistoryServiceImpl();
//		System.out.println(a.chkStudentId("KMUTT-8765"));
//	}


	@Override
	public int chkStudentId(String studenId) throws Exception {
		TrainHistoryDAOImpl dao = new TrainHistoryDAOImpl();
//		System.out.println(dao.chkStudentId(studenId));
		return dao.chkStudentId(studenId);
	}


	@Override
	public boolean deleteStudentImage(String fileImage) throws Exception {
		TrainHistoryDAOImpl dao = new TrainHistoryDAOImpl();
		return dao.deleteStudentImage(fileImage);
	}


	@Override
	public boolean updateStudentImage(String fileImage) throws Exception {
		TrainHistoryDAOImpl dao = new TrainHistoryDAOImpl();
		return dao.updateStudentImage(fileImage);
	}
}
