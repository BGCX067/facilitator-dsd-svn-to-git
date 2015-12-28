package th.ac.kmutt.dsd.train.model;

import th.ac.kmutt.dsd.train.api.model.BoundingBox;
import th.ac.kmutt.dsd.train.api.model.Match;


public class Detection {
	
	private BoundingBox boundingBox;
	private Match match;
	
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
}
