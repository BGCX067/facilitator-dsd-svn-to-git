package th.ac.kmutt.dsd.train.service.impl;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import th.ac.kmutt.dsd.helper.CommunicationHelper;
import th.ac.kmutt.dsd.train.service.RecognizeService;
import th.ac.kmutt.dsd.train.service.impl.RecognizeServiceImpl;

public class Simple extends ServerResource {

	@Override
	protected Representation post(Representation entity)
			throws ResourceException {
		// TODO Auto-generated method stub
		Representation rep;
		try{
			JSONObject obj = new JSONObject(entity.getText()); 
			String result = CommunicationHelper.getResultPost("http://localhost:8080/TrainWebFS/service/recognize", obj.toString());
			rep = new JsonRepresentation(result);
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			rep = new JsonRepresentation("{'code':2000,'message':'failed to recognize'}");
		}
		return rep;
	}
	
	
	
}
