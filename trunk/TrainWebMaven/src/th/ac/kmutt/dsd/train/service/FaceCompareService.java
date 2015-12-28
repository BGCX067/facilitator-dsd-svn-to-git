package th.ac.kmutt.dsd.train.service;

import java.util.List;
import th.ac.kmutt.dsd.train.model.ServerDataCompair;

public interface FaceCompareService {

	public String faceCompare(List<ServerDataCompair> jsonObj)throws Exception;
	
}
