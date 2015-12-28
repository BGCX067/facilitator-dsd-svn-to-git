package th.ac.kmutt.dsd.train.pojo.db;

import th.ac.kmutt.dsd.train.api.model.Match;

public class Boundingbox {
	
	private Position position;
	private Size size;
	private Match match;
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	
}
