package th.ac.kmutt.dsd.train.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;

import com.google.gson.Gson;

import th.ac.kmutt.dsd.helper.CommunicationHelper;
import th.ac.kmutt.dsd.train.model.Detection;
import th.ac.kmutt.dsd.train.model.ServerDataCompair;
import th.ac.kmutt.dsd.train.pojo.db.Data;
import th.ac.kmutt.dsd.train.pojo.db.Reconition;
import th.ac.kmutt.dsd.train.service.FaceCompareService;

public class FaceCompareServiceImpl implements FaceCompareService{

	
	public List<ServerDataCompair> filterServerMessageOK(List<ServerDataCompair> serverList)throws Exception{
		
		List<ServerDataCompair> filterServer = new ArrayList<ServerDataCompair>();
		
		try {
			
			for(ServerDataCompair server : serverList){
				
				Reconition regFilter = new Reconition();
				Gson gson = new Gson();  
				regFilter = gson.fromJson(server.getJsonResponse().toString(), Reconition.class);  
				
					if(!regFilter.getMessage().equals("2000")&&!regFilter.getMessage().equals("3000")
						&&!regFilter.getMessage().equals("3001")&&!regFilter.getMessage().equals("3002")){
						
//						if(regFilter.getData()!=null&&regFilter.getData().getDetections().length>0){
//							filterServer.add(server);
//						}
						
						if(regFilter.getData()!=null){
							if(regFilter.getData().getDetections()!=null){
								filterServer.add(server);
							}
						}
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filterServer;
	}
	
	@Override
	public String faceCompare(List<ServerDataCompair> listSrv) throws Exception {

		List<ServerDataCompair> listFilterSrv = new ArrayList<ServerDataCompair>();
		listFilterSrv = filterServerMessageOK(listSrv);
		List<Detection> listDetecionTmpNotCompair = new ArrayList<Detection>();
		List<Detection> listDetecionTmp = new ArrayList<Detection>();
		
		if(!listFilterSrv.isEmpty()&&listFilterSrv.size()>1){
			Gson gson = new Gson();  
			Reconition ServerBase = new Reconition();
			Detection[] listDetecionBase;
			
			//Server One For Compair Other Server
			ServerBase = gson.fromJson(listFilterSrv.get(0).getJsonResponse().toString(), Reconition.class);  
			listDetecionBase = ServerBase.getData().getDetections();
			
			for(int i=0;i<listDetecionBase.length;i++){
					boolean detectCheck = true;
					Detection detectBase = listDetecionBase[i];
					
				for(int j=1;j<listFilterSrv.size();j++){
					
					ServerDataCompair server = new ServerDataCompair();
					server = listFilterSrv.get(j);
					Reconition serverOther = gson.fromJson(server.getJsonResponse().toString(), Reconition.class); 
					Detection[] listDetecionOthers = serverOther.getData().getDetections();
						
					for(int index=0;index<listDetecionOthers.length-1;index++){
									
						Detection detectOthers = listDetecionOthers[index];
							
						int ratio = getAbsoluteNumber(
								 detectBase.getBoundingBox().getPosition().getX()
								,detectBase.getBoundingBox().getPosition().getY()
								,detectOthers.getBoundingBox().getPosition().getX()
								,detectOthers.getBoundingBox().getPosition().getY()); 
						
								System.out.println("ID "+detectBase.getMatch().getId()+"  Detection Base = X: "+detectBase.getBoundingBox().getPosition().getX()+" Y:"+detectBase.getBoundingBox().getPosition().getY() 
										+ "ID "+detectOthers.getMatch().getId()+" Detection Others = X:"+detectOthers.getBoundingBox().getPosition().getX()+" Y:"+detectOthers.getBoundingBox().getPosition().getY()
										+" RATIO = "+ratio);
							    
								if(ratio<10){
									detectCheck = false;
								    //Same Human
								   if(listDetecionTmp.size()>0){
									
											boolean isCheck = false;
											int indexRemove = 0;
											for(int inxTmp =0 ;inxTmp<listDetecionTmp.size();inxTmp++){
												
												Detection detectTmp = listDetecionTmp.get(inxTmp);
												
												int ratioCheck = getAbsoluteNumber(
														 detectBase.getBoundingBox().getPosition().getX()
														,detectBase.getBoundingBox().getPosition().getY()
														,detectTmp.getBoundingBox().getPosition().getX()
														,detectTmp.getBoundingBox().getPosition().getY()); 
												
												if(ratioCheck<10){
													isCheck = true;
													indexRemove = inxTmp;
												}
											}
										
											if(isCheck){
												//Same Human
												if(detectBase.getMatch().getSimilarity()<detectOthers.getMatch().getSimilarity()){
													listDetecionTmp.remove(indexRemove);
													listDetecionTmp.add(detectOthers);
													
												}else{
													listDetecionTmp.remove(indexRemove);
													listDetecionTmp.add(detectBase);
												}
											}
									
									}else{
											if(detectBase.getMatch().getSimilarity()<detectOthers.getMatch().getSimilarity()){
												listDetecionTmp.add(detectOthers);
											}else{
												listDetecionTmp.add(detectBase);
											}
									}
								
								}else if(ratio>10){
									//Not Same
										  detectCheck = false;
									      if(listDetecionTmp.size()>0){
												
												boolean isCheck = false;
												boolean valid = true;
												int indexRemove = 0;
													for(int inxTmp =0 ;inxTmp<listDetecionTmp.size();inxTmp++){

														Detection detectTmp = listDetecionTmp.get(inxTmp);
														
														int ratioCheck = getAbsoluteNumber(
																 detectOthers.getBoundingBox().getPosition().getX()
																,detectOthers.getBoundingBox().getPosition().getY()
																,detectTmp.getBoundingBox().getPosition().getX()
																,detectTmp.getBoundingBox().getPosition().getY()); 
														
														if(ratioCheck<10){
															isCheck = true;
															valid = false;
															indexRemove = inxTmp;
															break;
														}else if(ratioCheck>10){
															
															if(detectBase.getMatch().getSimilarity()<detectOthers.getMatch().getSimilarity()){
																isCheck = true;
																valid = false;
																break;
															}
														}
													}
												
														if(isCheck){
															if(detectBase.getMatch().getSimilarity()<detectOthers.getMatch().getSimilarity()){
																listDetecionTmp.remove(indexRemove);
																listDetecionTmp.add(detectOthers);
															}
														}
														
														if(valid){
															listDetecionTmp.add(detectOthers);
														}
											}else{
												
													if(detectBase.getMatch().getSimilarity()<detectOthers.getMatch().getSimilarity()){
														listDetecionTmp.add(detectOthers);
													}else{
														listDetecionTmp.add(detectBase);
													}
													
											}
									      
								}
								
								if(detectCheck){
									listDetecionTmpNotCompair.add(detectBase);
								}
							}
					
					}
				}
				
				

			for(int indexNotCompair=0;indexNotCompair<listDetecionTmpNotCompair.size();indexNotCompair++){
				
				boolean valid = true;
				Detection detect_one = listDetecionTmpNotCompair.get(indexNotCompair);
				
				//System.out.println("detect_one "+detect_one.getMatch().getId());
				for(Detection detect_two : listDetecionTmp){
					
					int ratio = getAbsoluteNumber(
						  	 detect_one.getBoundingBox().getPosition().getX()
							,detect_one.getBoundingBox().getPosition().getY()
							,detect_two.getBoundingBox().getPosition().getX()
							,detect_two.getBoundingBox().getPosition().getY()); 
					
//					/||(detect_one.getMatch().getId().equals(detect_two.getMatch().getId()))
					if(ratio<10||detect_one.getMatch().getId().equals(detect_two.getMatch().getId())){
						valid = false;
					}
					
				}
				
				if(valid){
					listDetecionTmp.add(detect_one);
				}
				
			}
			
			Reconition responsed = new Reconition();
			Data data = new Data(); 
			Detection[] detecton = new Detection[listDetecionTmp.size()];
			
			if(listDetecionTmp.size()>0){
				responsed.setCode("1000");
				responsed.setMessage("success");
				
				for(int indexTmp =0;indexTmp<listDetecionTmp.size();indexTmp++){
					detecton[indexTmp] = listDetecionTmp.get(indexTmp);
					
				}
				
				data.setDetections(detecton);
				responsed.setData(data);
				
			}else if(listDetecionTmp.size()<1&&listDetecionTmp.size()>0){
				
				responsed.setCode("1000");
				responsed.setMessage("success");
				
				for(int indexTmp =0;indexTmp<listDetecionTmp.size();indexTmp++){
					detecton[indexTmp] = listDetecionTmp.get(indexTmp);
					
				}
				
				data.setDetections(detecton);
				responsed.setData(data);
				
			}else if(listDetecionTmp.size()<1&&listDetecionTmp.size()<1){
				
				responsed.setCode("1000");
				responsed.setMessage("success");
				data.setDetections(detecton);
				responsed.setData(data);
				
			}
			
			return CommunicationHelper.getPraseDataToJSONForMat(responsed);
			
		}else if(listFilterSrv.isEmpty()&&listFilterSrv.size()>0){
			
			Reconition responsed = new Reconition();
			Data data = new Data(); 
			Detection[] detecton = new Detection[listDetecionTmp.size()];
			
			responsed.setCode("1000");
			responsed.setMessage("success");
			data.setDetections(detecton);
			responsed.setData(data);

		    return CommunicationHelper.getPraseDataToJSONForMat(responsed);
		}
		
		
		return "{'code':2000,'message':'failed to recognize'}";
	}


	public static int getAbsoluteNumber(int x1,int y1,int x2,int y2){
		
		int ratio_1 = x1-x2;
		int ratio_2 = y1-y2;
		int totalRatio = 0;
		
		if (ratio_1 < 0) {
			ratio_1 *= -1;
		}
		
		if (ratio_2 < 0) {
			ratio_2 *= -1;
		}
		
//		System.out.println("ratio_1 "+ratio_1);
//		System.out.println("ratio_2 "+ratio_2);
		
		totalRatio = ratio_1+ratio_2;
		
		return totalRatio;
	}
	
	public static void main(String[] args) {
	
		
	}
}
