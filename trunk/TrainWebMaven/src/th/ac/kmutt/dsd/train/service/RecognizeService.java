package th.ac.kmutt.dsd.train.service;

import org.json.JSONObject;

public interface RecognizeService {

	public String RecognizeRequest(String json) throws Exception;
	public String ChooseBestResult(JSONObject uniq_res)throws Exception;
	
}
