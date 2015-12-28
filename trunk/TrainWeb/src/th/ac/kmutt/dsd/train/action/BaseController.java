package th.ac.kmutt.dsd.train.action;


import java.io.File;
import java.util.Iterator;
import java.util.Map;

import javax.el.ELContext;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.context.ApplicationContext;

import th.ac.kmutt.dsd.train.utility.Context;


public class BaseController {
	
    private static final String PATH = File.separator; 
    public static final String DRIVE = "C:";
    public static final String URL_DISK_TEMP = "D:"+PATH+"DSD"+PATH;
    

    public static String errorMessage="";
    
    public static ApplicationContext applicationContext = Context.getInstance().applicationContext;
    
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	protected Application getApplication() {
		return FacesContext.getCurrentInstance().getApplication();
	}

	protected ELContext getELContext() {
		return FacesContext.getCurrentInstance().getELContext();
	}

	protected Object getRequestMapBean(String requestMapBean) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get(requestMapBean);
	}

	protected String getRequestParameterMap(String requestParameterMap) {
		return (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(requestParameterMap);
	}
	
    public static String getAuthenUserLogonId(){
        String userAuthenticat = (String)getHttpSession().getAttribute("userLogonId");
        return userAuthenticat;
    }
    
    public static String getAuthenUserLogonName(){
        String userAuthenticat = (String)getHttpSession().getAttribute("userLogonName");
        return userAuthenticat;
    }

    public static String getAuthenUserLogonRank(){
        String userAuthenticat = (String)getHttpSession().getAttribute("userLogonRank");
        return userAuthenticat;
    }
    
    public static HttpSession getHttpSession() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facescontext.getExternalContext().getSession(true);
        return session;
    }
    
    public static Map<String, Object> getSessionMap(){
        FacesContext facescontext = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = facescontext.getExternalContext().getSessionMap();
        return sessionMap;
    }

    public static HttpServletResponse getHttpServletResponse() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        HttpServletResponse response=(HttpServletResponse)facescontext.getExternalContext().getResponse();
        return response;
    }

    public static HttpServletRequest getHttpServletRequest() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        HttpServletRequest request=(HttpServletRequest)facescontext.getExternalContext().getRequest();
        return request;
    }
    
    public static ServletContext getServletContext() {
        ServletContext context = getHttpSession().getServletContext();
        return context;
    }
    
    public void addErrorMessage(String errorMsg, String errorMsgDetail){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg , errorMsgDetail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void addInfoMessage(String infoMessage) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, infoMessage, null);        
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
  
    public static void addWarningMessage(String message) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, message, message);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, facesMsg);
    }

    @SuppressWarnings("unchecked") //$NON-NLS-1$
    public static void resetMessages() {
        FacesContext context = FacesContext.getCurrentInstance();
        Iterator iter = context.getMessages();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
        }
    }
}
