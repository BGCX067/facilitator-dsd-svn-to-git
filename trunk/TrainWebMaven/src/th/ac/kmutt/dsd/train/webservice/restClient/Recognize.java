package th.ac.kmutt.dsd.train.webservice.restClient;

import org.json.JSONObject;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import th.ac.kmutt.dsd.train.service.RecognizeService;
import th.ac.kmutt.dsd.train.service.impl.RecognizeServiceImpl;

public class Recognize extends ServerResource {

	protected Representation post(Representation entity) throws ResourceException {
	
		Representation rep;
		
		try{
			JSONObject obj = new JSONObject(entity.getText()); 
			
			RecognizeService recognizeService = new RecognizeServiceImpl();
			rep = new JsonRepresentation(recognizeService.RecognizeRequest(obj.toString()));
			
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			rep = new JsonRepresentation("{'code':2000,'message':'failed to recognize'}");
		}
		return rep;
	}

	protected Representation get() throws ResourceException {
		
		JSONObject jsObj = new JSONObject(new SamplePOJO());
		Representation rep = new JsonRepresentation(jsObj.toString());
		rep.setMediaType(MediaType.APPLICATION_JSON);
	
		return rep;
		
	}
}
