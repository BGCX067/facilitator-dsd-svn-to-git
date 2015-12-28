package th.ac.kmutt.dsd.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import th.ac.kmutt.dsd.train.pojo.db.TrainHistory;

public class CommunicationHelper {
	
	private static Logger log = LogManager.getLogger(CommunicationHelper.class);
	public static final ResourceBundle resource = ResourceBundle.getBundle("applicationConfig");
	public static final String TRAIN = "SERV.PATH.TRAIN";
	public static final String RECOGNIZE = "SERV.PATH.RECOGNI"; 

	public static void main(String[] args) throws JSONException {
		/*
		TrainHistory t = new TrainHistory();
		
		t.setLocationDisk("YYY");
		t.setStudenId("Arucha");
		System.out.println(getPraseDataToJSONForMat(t));
		
		
		String OutPutJson = "{\"code\":\"1000\",\"message\":\"Delete faceset successfully\"}";
		
		System.out.println(getPraseJsonToData(OutPutJson).get("message"));
		*/
		try {
		
		//	for(int i=0;i<20;i++){
				JSONObject o = new JSONObject();
				o.put("group", "KMUTT");
				o.put("url", "https://fbcdn-sphotos-d-a.akamaihd.net/hphotos-ak-ash3/579331_10151779793648093_1436446483_n.jpg");
				System.out.println(getResultPost("http://54.254.162.241/TrainWebFS/service/recognize", o.toString()));
		//	}
				
				
			
			//	System.out.println(getResultGet("http://54.254.162.241/TrainWebFS/service/recognize"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static String getPraseDataToJSONForMat(Object object){
		
		JSONObject json = new JSONObject(object);
		
		return json.toString();
		
	}

	public static JSONObject getPraseJsonToData(String json) throws JSONException{
		
		JSONObject obj = new JSONObject(json);
		return obj;
		
	}
	
	public List<String> getOpenConnectServer() throws Exception{
		
		List<String> serverConnectList = new ArrayList<String>();
		List<String> serverCheckList = getALLServer();
		
		for(int i=0;i<serverCheckList.size();i++){
			
			if(checkAvailable(serverCheckList.get(i))){
				serverConnectList.add(serverCheckList.get(i));
			}
			
		}
		
		return serverConnectList;
	}
	
	
	public static List<String> getALLServer()throws Exception{
		
		String server[] = resource.getString("SERV.PATH.TRAIN").split(",");
		
		List<String> serverList = new ArrayList<String>();
		
		for(int i=0;i<server.length;i++){
			serverList.add(server[i]);
		}
		
		return serverList;
	}
	
	
	public static List<String> getALLServer(String module)throws Exception{
		
		String server[] = resource.getString(module).split(",");
		
		List<String> serverList = new ArrayList<String>();
		
		for(int i=0;i<server.length;i++){
			serverList.add(server[i]);
		}
		
		return serverList;
	}
	
	
	public static boolean checkAvailable(String str_url){
		
		boolean available = false;
		 try {
			 
				String url = str_url;
			 
				URL obj = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
				conn.setReadTimeout(15000);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				System.out.println("Request URL ... " + url);
			 
				boolean redirect = false;
			 
				// normally, 3xx is redirect
				int status = conn.getResponseCode();
				
				if(status == HttpURLConnection.HTTP_OK) {
						redirect = true;
					}	
			 
				log.info("Response Code ... " + conn.getResponseCode());
				System.out.println("Response Code ... " + status);
			 
					if (redirect) available = true;
				
			 
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer html = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						html.append(inputLine);
					}
					
				in.close();
				
				log.info("URL Content... \n" + html.toString());
				log.info("Done");
			 
			    } catch (Exception e) {
			    	log.error("java.net.SocketTimeoutException: Read timed out");
			    }

		 	return available;
	}
	
public static boolean checkAvailableTrain(String str_url){
		
		boolean available = false;
		 try {
			 
				String url = str_url;
			 
				URL obj = new URL(url);
				HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setReadTimeout(15000);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				System.out.println("Request URL ... " + url);
			 
				boolean redirect = false;
			 
				// normally, 3xx is redirect
				int status = conn.getResponseCode();
				
				if(status == HttpURLConnection.HTTP_OK||status == 500) {
						redirect = true;
					}	
			 
				log.info("Response Code ... " + conn.getResponseCode());
				System.out.println("Response Code ... " + status);
			 
					if (redirect) available = true;
				
			 
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer html = new StringBuffer();
			 
					while ((inputLine = in.readLine()) != null) {
						html.append(inputLine);
					}
					
				in.close();
				
				log.info("URL Content... \n" + html.toString());
				log.info("Done");
			 
			    } catch (Exception e) {
			    	log.error("java.net.SocketTimeoutException: Read timed out");
			    }

		 	return available;
	}
	

	public static String getResultPut(String serverURL, String json)throws IOException, JSONException {

		ClientResource resource = new ClientResource(serverURL);
		String result = "";
		Representation rep = new JsonRepresentation(json);

		rep.setMediaType(MediaType.APPLICATION_JSON);
		Representation reply = null;
		
		reply = resource.put(rep);

		if (reply != null) {
			String replyText = reply.getText();
			reply.write(System.out);
			if (reply.getMediaType().equals(new MediaType("application/json"))) {
				JSONObject jsObj = new JSONObject(replyText);
				result = jsObj.toString();
			}
			reply.release();
			System.out.println(resource.getStatus());
		}

		return result;
	}

	public static String getResultPost(String serverURL, String json)
			throws IOException, JSONException {

		Client client = new Client(new Context(), Protocol.HTTP);
		ClientResource resource = new ClientResource(serverURL);
		String result = "";
		Representation rep = new JsonRepresentation(json);

		rep.setMediaType(MediaType.APPLICATION_JSON);
		Representation reply = null;
		resource.setNext(client);
		
		reply = resource.post(rep);

		if (reply != null) {
			String replyText = reply.getText();

			if(replyText!=null){
				replyText  = replyText.substring(0, replyText.length());
			}else{
				replyText = "{\"code\":2000,\"message\":\"Success\",\"data\":{\"detections\":[]}}";
			}
			
			System.out.println("replyText "+replyText);
			reply.write(System.out);
			if (reply.getMediaType().equals(new MediaType("application/json"))) {
				JSONObject jsObj = new JSONObject(replyText);
				result = jsObj.toString();
			}
			reply.release();
			System.out.println(resource.getStatus());
		}

		return result;
		
	}
	

	public static String getResultDelete(String serverURL)
			throws IOException, JSONException {
		
		ClientResource resource = new ClientResource(serverURL);
		String result = "";

		Representation reply = null;
		
		reply = resource.delete();

		if (reply != null) {

			String replyText = reply.getText();
			reply.write(System.out);
			if (reply.getMediaType().equals(new MediaType("application/json"))) {
				JSONObject jsObj = new JSONObject(replyText);
				result = jsObj.toString();
			}
			
			reply.release();
		}

		return result;
		
	}

	public static String getResultGet(String serverURL)
			throws IOException, JSONException {
		Client client = new Client(new Context(), Protocol.HTTP);
		ClientResource resource = new ClientResource(serverURL);
		String result = "";
		Representation reply = null;
		resource.setNext(client);
		reply = resource.get();

		if (reply != null) {
			String replyText = reply.getText();
			reply.write(System.out);
			if (reply.getMediaType().equals(new MediaType("application/json"))) {
				JSONObject jsObj = new JSONObject(replyText);
				result = jsObj.toString();
			}
			reply.release();
			System.out.println(resource.getStatus());
		}

		return result;
	}
	
}
