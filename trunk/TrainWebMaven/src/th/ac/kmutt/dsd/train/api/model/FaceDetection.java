package th.ac.kmutt.dsd.train.api.model;

import java.util.List;

public class FaceDetection {
	
	private BoundingBox boundingBox;
	private List<Match> matches;
	
	public FaceDetection() {
		super();
	}
	
	public FaceDetection(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	public FaceDetection(BoundingBox boundingBox, List<Match> matches) {
		this.boundingBox = boundingBox;
		this.matches = matches;
	}
	
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	
	public List<Match> getMatches() {
		return matches;
	}
	
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	@Override
	public String toString() {
		return "Detection [boundingBox=" + boundingBox + ", matches=" + matches
				+ "]";
	}
	
}
