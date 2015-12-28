package th.ac.kmutt.dsd.train.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import th.ac.kmutt.dsd.train.service.JsonService;
import th.ac.kmutt.dsd.train.service.TrainHistoryService;
import th.ac.kmutt.dsd.train.service.UserProfileService;

@ManagedBean
@RequestScoped
public class MenuAction extends BaseController{
	
	private static Logger log = LogManager.getLogger(LoginAction.class);

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
	
	
	//CONTROLLER PAGE
	public String pageAdd(){
		return "create";
	}
	
	public String pageUpdate(){
		return "update";
	}
	
	public String pageDelete(){
		return "delete";
	}
	
}
