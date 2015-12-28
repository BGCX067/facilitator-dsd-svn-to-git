package th.ac.kmutt.dsd.train.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;


import com.google.gson.Gson;
import th.ac.kmutt.dsd.helper.CommunicationHelper;
import th.ac.kmutt.dsd.train.model.Match;
import th.ac.kmutt.dsd.train.model.ServerDataCompair;
import th.ac.kmutt.dsd.train.pojo.db.Reconition;
import th.ac.kmutt.dsd.train.service.RecognizeService;

public class RecognizeServiceImpl implements RecognizeService {

	public static final ResourceBundle bundle = ResourceBundle
			.getBundle("applicationConfig");

	@Override
	public String RecognizeRequest(String json) throws Exception {
		List<String> servers = CommunicationHelper.getALLServer(CommunicationHelper.RECOGNIZE);
		String reply = ""; 
		List<ServerDataCompair> listServer = new ArrayList<ServerDataCompair>();
		JSONObject o = new JSONObject(json);
		String response, group = "", url = "";

		if ((o.has("group") || o.has("university")) && o.has("url")) {
			if (o.has("group")) {
				group = o.getString("group");
			} else if (o.has("university")) {
				group = o.getString("university");
				o.put("group", o.getString("university"));
				o.remove("university");
			}
			
			for (int i = 0; i < servers.size(); i++) {
				if (CommunicationHelper.checkAvailable(servers.get(i))) {
					ServerDataCompair server = new ServerDataCompair();
					if(servers.get(i).equals(bundle.getObject("FAKE"))){
						
						server.setServerName("ZOMBIE_SERVER");
						server.setBaseURL(bundle.getObject("FAKE").toString());
						server.setJsonResponse(CommunicationHelper.getResultPost(servers.get(i), o.toString()));
						listServer.add(server);
					
					}else if(servers.get(i).equals(bundle.getObject("ISU"))){
						
						server.setServerName("TURING_SERVER");
						server.setBaseURL(bundle.getObject("ISU").toString());
						server.setJsonResponse(CommunicationHelper.getResultPost(servers.get(i), o.toString()));
						listServer.add(server);
				
					}else if(servers.get(i).equals(bundle.getObject("UNAL"))){
					
						server.setServerName("UNAL_COLUMBIA_SERVER");
						server.setBaseURL(bundle.getObject("UNAL").toString());
						server.setJsonResponse(CommunicationHelper.getResultPost(servers.get(i), o.toString()));
						listServer.add(server);
					}
					
					
				}
			}

			// Sent resplist to P'Oat Method
			try {
				// Sent resplist to Arucha Method	
				FaceCompareServiceImpl impl = new FaceCompareServiceImpl();
				reply = impl.faceCompare(listServer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			response = "{'code':2000,'message':'failed to recognize'}";
		}
		System.out.println("Response String To Client : " + reply);
		return reply;
	}

	@Override
	public String ChooseBestResult(JSONObject uniq_res) throws Exception {
		JSONArray datalist = uniq_res.getJSONObject("data").getJSONArray(
				"detections");
		Match[] match = new Match[datalist.length()];
		int selectServ[] = new int[datalist.length()];

		for (int i = 0; i < datalist.length(); i++) {
			JSONArray faceset = datalist.getJSONArray(i);
			for (int j = 0; j < faceset.length(); j++) {
				if (faceset.getJSONObject(j) != null) {
					JSONObject o = faceset.getJSONObject(j).getJSONObject(
							"match");
					if (j == 0) {
						match[i] = new Match();
						match[i].setId(o.getString("id"));
						match[i].setSimilarity(o.getInt("similarity"));
					} else {
						if (match[i].getId().equals(o.getString("id"))) {
							if (match[i].getSimilarity() > o
									.getInt("similarity")) {
								match[i].setId(o.getString("id"));
								match[i].setSimilarity(o.getInt("similarity"));
							}
						}
					}
				}
			}
		}

		return null;
	}

	public static void main(String[] args) throws Exception {

		RecognizeServiceImpl reg = new RecognizeServiceImpl();

		// Test Connect FS To Server
//		ServerDataCompair server = new ServerDataCompair();
//		String json_send_to_server = "{\"group\":KMUTT,\"url\":\"http://54.254.162.241/TrainWebFS/TrainImage/1384799546068_oat-2.jpg\"}";
//		String serverName = "ZOMBIE_SERVER";
//		String serverBaseURL = "http://54.254.179.127:80";
//		String jsonResponsed = reg.RecognizeRequest(json_send_to_server);
		
//
////		Gson gson = new Gson();
////		Reconition s = gson
////				.fromJson(jsonResponsed.toString(), Reconition.class);
//
//		server.setServerName(serverName);
//		server.setBaseURL(serverBaseURL);
//		server.setJsonResponse(jsonResponsed);
//
//		List<ServerDataCompair> listServer = new ArrayList<ServerDataCompair>();
//
//		listServer.add(server);
//
//		// listJsonFromServer.add(jsonResponsed);
//
//		FaceCompareServiceImpl impl = new FaceCompareServiceImpl();
//		String json = impl.faceCompare(listServer);


	
		//TEST Connect to FS From Client
		
//		String data = "{\"group\":KMUTT,\"url\":\"http://54.254.162.241/TrainWebFS/TrainImage/1384799546068_oat-2.jpg\"}";
//		JSONObject obj = new JSONObject(data); 
//		
//		
//		String result = CommunicationHelper.getResultPost("http://localhost:8080/TrainWebFS/service/recognize", obj.toString());

//		ServerDataCompair server = new ServerDataCompair();
		String json_send_to_server = "";
		String serverName = "";
		String serverBaseURL = "";
		String jsonResponsed_1 = "";
		String jsonResponsed_2 = "";
		
//		json_send_to_server = "{\"group\":KMUTT,\"url\":\"https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-prn2/1460262_10151779792228093_1628192529_n.jpg\"}";
//		serverName = "ZOMBIE_SERVER";
//		serverBaseURL = "http://54.254.179.127:80";
//		jsonResponsed_1 = reg.RecognizeRequest(json_send_to_server);
//		
//		
		json_send_to_server = "{\"group\":KMUTT,\"url\":\"https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-prn2/1460262_10151779792228093_1628192529_n.jpg\"}";
		serverName = "UNAL_COLUMBIA_SERVER";
		serverBaseURL = "http://xue.unalmed.edu.co:5000";
		jsonResponsed_2 = reg.RecognizeRequest(json_send_to_server);
		 
		//http://xue.unalmed.edu.co:5000/OpenCVServer/recognize
		
	    //System.out.println(CommunicationHelper.getResultPost("http://localhost:8080/TrainWebFS/service/recognize",json_send_to_server));
		System.out.println(CommunicationHelper.getResultPost("http://54.254.162.241/TrainWebFS/service/recognize",json_send_to_server));
		
//		JSONObject o = new JSONObject();
//		o.put("group", "KMUTT");
//		o.put("url", "https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-prn2/1460262_10151779792228093_1628192529_n.jpg");
//		System.out.println(CommunicationHelper.getResultPost("http://localhost:8080/TrainWebFS/service/recognize", o.toString()));
	}

}
