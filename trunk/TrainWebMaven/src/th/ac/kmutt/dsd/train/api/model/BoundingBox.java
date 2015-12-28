package th.ac.kmutt.dsd.train.api.model;

public class BoundingBox {
	
	private Position position;
	private Size size;
	
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
	
	@Override
	public String toString() {
		return "BoundingBox [position=" + position + ", size=" + size + "]";
	}
	
}
