package th.ac.kmutt.dsd.train.api.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ReKognitionClientResult {
	
	private String rawData;
	
	private Usage usage;
	private String url;
	private Size originalImageSize;
	private List<FaceDetection> detections;

	public ReKognitionClientResult() {
		super();
	}
	
	public ReKognitionClientResult(String rawData) throws JSONException {
		this.setRawData(rawData);
	}
	
	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) throws JSONException {
		
		this.rawData = rawData;
		
		JSONParser parser = new JSONParser();
		JSONObject result;
		
		try {
			Object object = parser.parse(this.rawData);
			result = (JSONObject) object;
		} catch(ParseException e) {
			result = new JSONObject();
		}
		
		this.setUsage(result.get("usage"));
		this.setUrl(result.get("url"));
		this.setOriginalImageSize(result.get("ori_img_size"));
		this.setDetection(result.get("face_detection"));
		
	}
	
	public Usage getUsage() {
		return usage;
	}
	
	private void setUsage(Object object) throws JSONException {
		
		if (object == null)
			return;
		
		JSONObject jsonObject = (JSONObject) object;
		
		Usage usage = new Usage();
		usage.setQuota(jsonObject.get("quota"));
		usage.setStatus(jsonObject.get("status"));
		usage.setApiKey(jsonObject.get("api_id"));
		
		this.usage = usage;
	}
	
	public String getUrl() {
		return url;
	}
	
	private void setUrl(Object object) {
		if (object == null)
			return;
		this.url = (String) object;
	}
	
	public Size getOriginalImageSize() {
		return originalImageSize;
	}
	
	private void setOriginalImageSize(Object object) {
		
		if (object == null)
			return;
		
		Size originalImageSize = new Size();
		
		JSONObject jsonSize = (JSONObject) object;
		
		try {
			Long width	= (Long) jsonSize.get("width");
			Long height	= (Long) jsonSize.get("height");
			originalImageSize.setWidth(width.intValue());
			originalImageSize.setHeight(height.intValue());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.originalImageSize = originalImageSize;
	}
	
	public List<FaceDetection> getDetection() {
		return detections;
	}
	
	@SuppressWarnings("unchecked")
	public void setDetection(Object object) {
		
		if (object == null)
			return;
		
		JSONArray detections = (JSONArray) object;
		
		//Iterator<JSONObject> iterator = detections.iterator();
		
//		while (iterator.hasNext()) {
//			
//			JSONObject jsonDetection = iterator.next();
//			
//			FaceDetection detection = new FaceDetection();
//			
//			/* Bounding Box section */
//			BoundingBox boundingBox = this.getBoundingBox(jsonDetection.get("boundingbox"));
//			detection.setBoundingBox(boundingBox);
//			
//			/* Match section */
//			Object matches = jsonDetection.get("matches");
//			if (matches != null) {
//				detection.setMatches(this.getMatches(matches));
//			}
//
//			// add list of bounding box
//			if (this.detections == null) {
//				this.detections = new ArrayList<FaceDetection>();
//			}
//			this.detections.add(detection);
//		}
		
	}
	
	private BoundingBox getBoundingBox(Object object) {
		
		BoundingBox boundingBox = new BoundingBox();
		
		Position position = new Position();
		Size size = new Size();
		
		if (object == null)
			return boundingBox;

		JSONObject jsonBoundingBox	= (JSONObject) object;
		JSONObject jsonPosition		= (JSONObject) jsonBoundingBox.get("tl");
		JSONObject jsonSize			= (JSONObject) jsonBoundingBox.get("size");
		
		try {
			
			// get position of bounding box
			Double x	= (Double) jsonPosition.get("x");
			Double y	= (Double) jsonPosition.get("y");
			position.setX(x.intValue());
			position.setY(y.intValue());
			
			// get size of bounding box
			Double width	= (Double) jsonSize.get("width");
			Double height	= (Double) jsonSize.get("height");
			size.setWidth(width.intValue());
			size.setHeight(height.intValue());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		boundingBox.setPosition(position);
		boundingBox.setSize(size);
		
		return boundingBox;
	}
	
	@SuppressWarnings("unchecked")
	private List<Match> getMatches(Object object) {
		
		List<Match> matches = new ArrayList<Match>();
		
		JSONArray array = (JSONArray) object;

//		Iterator<JSONObject> i = array.iterator();
//		
//		while (i.hasNext()) {
//			
//			JSONObject jsonMatch = i.next();
//			
//			// get matching
//			String tag		= (String) jsonMatch.get("tag");
//			String score	= (String) jsonMatch.get("score");
//			Double similarity;
//			try {
//				similarity = Double.parseDouble(score) * 100;
//			} catch(Exception e) {
//				similarity = new Double(0);
//			}
//			
//			matches.add(new Match(tag, similarity.intValue()));
//
//		}
		
		return matches;
		
	}
	
	public boolean isSuccess() {
		return !this.isError();
	}
	
	public boolean isError() {
		if (this.usage != null) {
			return this.usage.getStatus().contains("ERROR!");
		}
		return false;
	}

	@Override
	public String toString() {
		return "ReKognitionResult [rawData=" + rawData + ", usage=" + usage
				+ ", url=" + url + ", originalImageSize=" + originalImageSize
				+ ", detections=" + detections + "]";
	}
	
}
