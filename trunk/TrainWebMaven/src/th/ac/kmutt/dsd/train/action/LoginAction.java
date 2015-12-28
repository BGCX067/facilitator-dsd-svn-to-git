package th.ac.kmutt.dsd.train.action;

import java.net.MalformedURLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import th.ac.kmutt.dsd.train.service.JsonService;
import th.ac.kmutt.dsd.train.service.TrainHistoryService;
import th.ac.kmutt.dsd.train.service.UserProfileService;
import th.ac.kmutt.dsd.train.service.impl.TrainHistoryServiceImpl;

@ManagedBean
@RequestScoped
public class LoginAction extends BaseController{
	
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

	//CONTROLLER
    public String checkAuthentication() throws MalformedURLException{
    	
    	String page = "welcome";
    
    	String username = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginForm:username");
	    String password = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("loginForm:password");
	    
	  
	  
    	try {
			userProfileservice.checkUserProfile(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return page;
    }
}
