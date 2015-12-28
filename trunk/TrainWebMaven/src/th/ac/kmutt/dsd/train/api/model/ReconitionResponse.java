package th.ac.kmutt.dsd.train.api.model;

import java.io.Serializable;
import java.util.List;


public class ReconitionResponse implements Serializable{

	private static final long serialVersionUID = 4790413612256567325L;
	
	private String message;
	private List<FaceDetection> detections;

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<FaceDetection> getDetections() {
		return detections;
	}
	public void setDetections(List<FaceDetection> detections) {
		this.detections = detections;
	}
	
}
