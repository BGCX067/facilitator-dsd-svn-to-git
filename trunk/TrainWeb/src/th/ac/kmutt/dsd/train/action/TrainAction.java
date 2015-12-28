package th.ac.kmutt.dsd.train.action;

import javax.faces.bean.ManagedProperty;

import th.ac.kmutt.dsd.train.service.JsonService;
import th.ac.kmutt.dsd.train.service.TrainHistoryService;
import th.ac.kmutt.dsd.train.service.UserProfileService;

public class TrainAction extends BaseController{

	
	@ManagedProperty(value="#{userProfileservice}")
	private UserProfileService userProfileservice;
	
	@ManagedProperty(value="#{trainHistoryService}")
	private TrainHistoryService trainHistoryService;
	
	@ManagedProperty(value="#{jsonService}")
	private JsonService jsonService;
	
	
	public void setUserProfileservice(UserProfileService userProfileservice) {
		this.userProfileservice = (UserProfileService) applicationContext.getBean("userProfileservice");
	}

	public void setTrainHistoryService(TrainHistoryService trainHistoryService) {
		this.trainHistoryService = (TrainHistoryService)applicationContext.getBean("trainHistoryService");
	}
	
	public void setJsonService(JsonService jsonService) {
		this.jsonService = (JsonService) applicationContext.getBean("jsonService");
	}
	
	
	//CONTROLLER
	public String updatefileSingle(){
		
		return "";
	}
}
