package th.ac.kmutt.dsd.train.pojo.db;

import th.ac.kmutt.dsd.train.model.Detection;

public class Data {

	//String group;
	//String url;
	Detection detections[];

	public Detection[] getDetections() {
		return detections;
	}
	public void setDetections(Detection[] detections) {
		this.detections = detections;
	}
//	public String getGroup() {
//		return group;
//	}
//	public void setGroup(String group) {
//		this.group = group;
//	}
	
//	public String getUrl() {
//		return url;
//	}
//	public void setUrl(String url) {
//		this.url = url;
//	}
}
