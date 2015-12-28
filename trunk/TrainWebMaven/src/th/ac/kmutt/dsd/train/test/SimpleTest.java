package th.ac.kmutt.dsd.train.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import th.ac.kmutt.dsd.helper.CommunicationHelper;
import th.ac.kmutt.dsd.train.dao.impl.TrainHistoryDAOImpl;
import th.ac.kmutt.dsd.train.dao.impl.UserProfileDAOImpl;
import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;
import th.ac.kmutt.dsd.train.utility.DateUtil;

public class SimpleTest {

	private static Logger log = LogManager.getLogger(SimpleTest.class);
	UserProfileDAOImpl user = new UserProfileDAOImpl();
	

	public void testCreateTrainHistory() {
		try {
			  TrainHistory trainhistory = new TrainHistory();
			  trainhistory.setStudenId("KMUTT-55441323");
			  trainhistory.setLocationDisk("D:\\PROJECT\\Project DSD FS\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TrainWebFS\\temp\\train\\5.jpg");
			  trainhistory.setStatus("N");
			  trainhistory.setCraeteDate(DateUtil.getCurrentTimestamp());
			  trainhistory.setUpdateDate(DateUtil.getCurrentTimestamp());
				
			  TrainHistoryDAOImpl trnDAO = new TrainHistoryDAOImpl();
			
			  trnDAO.addTranData(trainhistory);
			 
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	

	public void testDeleteTrainHistory() {
		try {
//			  TrainHistory trainhistory = new TrainHistory();
//			  trainhistory.setStudenId("KMUTT-55441323");
//			  trainhistory.setFileImageURL("http:\\localhost:8080\\TrainWebFS\\trainImage\\1.jpg");
//			  trainhistory.setLocationDisk("D:\\PROJECT\\Project DSD FS\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\TrainWebFS\\temp\\train\\5.jpg");
//			  trainhistory.setStatus("N");
//			  trainhistory.setCraeteDate(DateUtil.getCurrentTimestamp());
//			  trainhistory.setUpdateDate(DateUtil.getCurrentTimestamp());
//				
//			  TrainHistoryDAOImpl trnDAO = new TrainHistoryDAOImpl();
//			
//			  trnDAO.addTranData(trainhistory);
//			 
		} catch (Throwable e) {
			e.printStackTrace();
			log.error(e);
		}
	}
	
	
	public void testConnectionToServer() {
		
		try {
			
			List<String> serverList = new ArrayList<String>();
			serverList = CommunicationHelper.getALLServer();
			
			for(int i=0;i<serverList.size();i++){
				System.out.println(CommunicationHelper.checkAvailable(serverList.get(i)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
