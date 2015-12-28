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
	
	//CONTROLLER PAGE
	public String pageAdd()throws Exception{
			
		return "create";
	}
	
	public String pageDelete(){
		return "delete";
	}
	
}
